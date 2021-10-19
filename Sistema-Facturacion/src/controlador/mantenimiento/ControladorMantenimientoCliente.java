/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.mantenimiento;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.JOptionPane;
import modelo.dao.ClienteDAO;
import modelo.entidades.Cliente;
import vista.crud.cliente.PrincipalMantenimientoCliente;
import vista.crud.cliente.menu.listado.contenido.ContenidoTablaCliente;
import vista.crud.cliente.menu.listado.encabezado.HeaderListadoCliente;
import vista.crud.cliente.menu.listado.pie_pagina.PiePaginaListadoCliente;
import vista.crud.cliente.menu.mantenimiento.ComponentesClienteUpdateDelete;
import vista.crud.cliente.menu.mantenimiento.clientec.ClienteCreate;
import vista.crud.cliente.menu.mantenimiento.clientec.ComponentesClienteCreate;
import vista.crud.cliente.menu.mantenimiento.clienteu.ClienteUpdate;
import vista.inicio.Inicio;

/**
 *
 * @author javier
 */
public class ControladorMantenimientoCliente implements ActionListener, ItemListener {

    public final PrincipalMantenimientoCliente ventanaMantenimientoCliente;
        
    private final ClienteDAO clienteDAO;

    private final HeaderListadoCliente headerListadoCliente;
    private final ContenidoTablaCliente contenidoTablaCliente;
    private final PiePaginaListadoCliente piePaginaListadoCliente;

    private final ClienteCreate clienteCreate;
    private final ClienteUpdate clienteUpdate;
    private final ComponentesClienteCreate componentesClienteCreate;
    private final ComponentesClienteUpdateDelete componentesClienteUpdate;
    private final ComponentesClienteUpdateDelete componentesClienteDelete;

    int id_actualizar = 0;
    int id_eliminar = 0;

    private final Color colorErrrorFormularios = new Color(250, 177, 160);

    public ControladorMantenimientoCliente(PrincipalMantenimientoCliente ventanaMantenimientoCliente) {
        this.ventanaMantenimientoCliente = ventanaMantenimientoCliente;
        this.clienteDAO = new ClienteDAO();

        /* VARIABLES A USAR */
        headerListadoCliente = ventanaMantenimientoCliente.getMenuMantenimientoCliente().ventanaListadoCliente.getHeaderListadoCliente();
        contenidoTablaCliente = ventanaMantenimientoCliente.getMenuMantenimientoCliente().ventanaListadoCliente.getContenidioTablaCliente();
        piePaginaListadoCliente = ventanaMantenimientoCliente.getMenuMantenimientoCliente().ventanaListadoCliente.getPiePaginaListadoCliente();

        clienteCreate = ventanaMantenimientoCliente.getMenuMantenimientoCliente().panelMantenimientoCliente.getMenuMantenimientoClienteCRUD().getClienteCreate();
        clienteUpdate = ventanaMantenimientoCliente.getMenuMantenimientoCliente().panelMantenimientoCliente.getMenuMantenimientoClienteCRUD().getClienteUpdate();
        
        componentesClienteCreate = ventanaMantenimientoCliente.getMenuMantenimientoCliente().panelMantenimientoCliente.getMenuMantenimientoClienteCRUD().getClienteCreate().getComponentesClienteCreate();
        componentesClienteUpdate = ventanaMantenimientoCliente.getMenuMantenimientoCliente().panelMantenimientoCliente.getMenuMantenimientoClienteCRUD().getClienteUpdate().getComponentesClienteUpdate();
        componentesClienteDelete = ventanaMantenimientoCliente.getMenuMantenimientoCliente().panelMantenimientoCliente.getMenuMantenimientoClienteCRUD().getClienteDelete().getComponentesClienteDelete();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == headerListadoCliente.getBuscar()) {

            if (headerListadoCliente.getCampo().getText().isEmpty()) {
                llenarTabla();
                JOptionPane.showMessageDialog(null, "Cliente no encontrado", "ERROR", 0);
            } else {
                List<Cliente> lista = null;
                if (headerListadoCliente.getTipoBusqueda().getSelectedItem().equals("id_cliente") || headerListadoCliente.getTipoBusqueda().getSelectedItem().equals("edad")) {
                    try {
                        int number = Integer.parseInt(headerListadoCliente.getCampo().getText());
                        
                        contenidoTablaCliente.limpiarTabla();
                        lista = clienteDAO.filtar(headerListadoCliente.getTipoBusqueda().getSelectedItem().toString(), headerListadoCliente.getCampo().getText());

                    } catch (NumberFormatException e) {
                    }

                } else {
                    contenidoTablaCliente.limpiarTabla();
                    lista = clienteDAO.filtar(headerListadoCliente.getTipoBusqueda().getSelectedItem().toString(), headerListadoCliente.getCampo().getText());
                }

                if (lista != null) {
                    if (!lista.isEmpty()) {
                        Cliente[] miarray = new Cliente[lista.size()];
                        miarray = lista.toArray(miarray);

                        Object cliente[] = new Object[9];

                        for (Cliente i : miarray) {
                            cliente[0] = i.getId_cliente();
                            cliente[1] = i.getNombre();
                            cliente[2] = i.getApellidoP();
                            cliente[3] = i.getApellidoM();
                            cliente[4] = i.getEdad();
                            cliente[5] = i.getEmail();
                            cliente[6] = i.getDireccion();
                            cliente[7] = i.getTelefono();
                            cliente[8] = i.getCreate_at();
                            contenidoTablaCliente.getDtm().addRow(cliente);

                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado", "ERROR", 0);
                        llenarTabla();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado", "ERROR", 0);
                }

            }

        } else if (ae.getSource() == componentesClienteCreate.getGuardarJB()) {
            String nombre = componentesClienteCreate.getNombreJTF().getText();
            String apellidop = componentesClienteCreate.getApellidoPJTF().getText();
            String apellidom = componentesClienteCreate.getApellidoMJTF().getText();
            String edad = componentesClienteCreate.getEdadJTF().getText();
            String email = componentesClienteCreate.getEmailJTF().getText();
            String direccion = componentesClienteCreate.getDireccionJTF().getText();
            String telefono = componentesClienteCreate.getTelefonoJTF().getText();

            String err[] = new String[6];

            int verificar[] = verificarClienteNuevo(email, telefono);

            /* VALIDAR NOMBRE */
            if (nombre.isEmpty()) {
                err[0] = "   Nombre no puede estar vacio";
                clienteCreate.getComponentesClienteErrores().getErrorNombreJL().setText(err[0]);
                componentesClienteCreate.getNombreJTF().setBackground(colorErrrorFormularios);
            } else {
                clienteCreate.getComponentesClienteErrores().getErrorNombreJL().setText("");
                componentesClienteCreate.getNombreJTF().setBackground(Color.WHITE);
            }

            /* VALIDAR APELLIDO PATERNO */
            if (apellidop.isEmpty()) {
                err[1] = "   Apellido Paterno no puede estar vacio";
                clienteCreate.getComponentesClienteErrores().getErrorApellidoPJL().setText(err[1]);
                componentesClienteCreate.getApellidoPJTF().setBackground(colorErrrorFormularios);
            } else {
                clienteCreate.getComponentesClienteErrores().getErrorApellidoPJL().setText("");
                componentesClienteCreate.getApellidoPJTF().setBackground(Color.WHITE);
            }

            /* VALIDAR APELLIDO MATERNO */
            if (apellidom.isEmpty()) {
                err[2] = "   Apellido Materno no puede estar vacio";
                clienteCreate.getComponentesClienteErrores().getErrorApellidoMJL().setText(err[2]);
                componentesClienteCreate.getApellidoMJTF().setBackground(colorErrrorFormularios);
            } else {
                clienteCreate.getComponentesClienteErrores().getErrorApellidoMJL().setText("");
                componentesClienteCreate.getApellidoMJTF().setBackground(Color.WHITE);
            }

            /* VALIDAR EDAD */
            try {
                if (!edad.isEmpty()) {
                    int _edad = Integer.valueOf(edad);
                    if (_edad > 100 || _edad < 18) {
                        err[3] = "   Edad no valida";
                        clienteCreate.getComponentesClienteErrores().getErrorEdadJL().setText(err[3]);
                        componentesClienteCreate.getEdadJTF().setBackground(colorErrrorFormularios);
                    } else {
                        clienteCreate.getComponentesClienteErrores().getErrorEdadJL().setText("");
                        componentesClienteCreate.getEdadJTF().setBackground(Color.WHITE);
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("exception");
                err[3] = "   Edad no valida";
                clienteCreate.getComponentesClienteErrores().getErrorEdadJL().setText(err[3]);
                componentesClienteCreate.getEdadJTF().setBackground(colorErrrorFormularios);
            }

            /* VALIDAR EMAIL */
            if (email.isEmpty()) {
                err[4] = "   Correo no puede estar vacio";
                clienteCreate.getComponentesClienteErrores().getErrorEmailJL().setText(err[4]);
                componentesClienteCreate.getEmailJTF().setBackground(colorErrrorFormularios);
            } else if (!email.matches("^[a-z0-9!#$%&*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$")) {
                err[4] = "   Correo no valido";
                clienteCreate.getComponentesClienteErrores().getErrorEmailJL().setText(err[4]);
                componentesClienteCreate.getEmailJTF().setBackground(colorErrrorFormularios);
            } else if (verificar[0] == 1) {
                err[4] = "   Correo ya existe";
                clienteCreate.getComponentesClienteErrores().getErrorEmailJL().setText(err[4]);
                componentesClienteCreate.getEmailJTF().setBackground(colorErrrorFormularios);
            } else {
                clienteCreate.getComponentesClienteErrores().getErrorEmailJL().setText("");
                componentesClienteCreate.getEmailJTF().setBackground(Color.WHITE);
            }

            /* VALIDAR TELEFONO */
            if (telefono.isEmpty()) {
                err[5] = "   Telefono no puede estar vacio";
                clienteCreate.getComponentesClienteErrores().getErrorTelefonoJL().setText(err[5]);
                componentesClienteCreate.getTelefonoJTF().setBackground(colorErrrorFormularios);
            } else if (!telefono.matches("[\\(]?[\\+]?(\\d{2}|\\d{3})[\\)]?[\\s]?((\\d{6}|\\d{8})|(\\d{3}[\\*\\.\\-\\s]){3}|(\\d{2}[\\*\\.\\-\\s]){4}|(\\d{4}[\\*\\.\\-\\s]){2})|\\d{8}|\\d{10}|\\d{12}")) {
                err[5] = "   Telefono no valido";
                clienteCreate.getComponentesClienteErrores().getErrorTelefonoJL().setText(err[5]);
                componentesClienteCreate.getTelefonoJTF().setBackground(colorErrrorFormularios);
            } else if (verificar[1] == 1) {
                err[5] = "   Telefono ya existe";
                clienteCreate.getComponentesClienteErrores().getErrorTelefonoJL().setText(err[5]);
                componentesClienteCreate.getTelefonoJTF().setBackground(colorErrrorFormularios);
            } else {
                clienteCreate.getComponentesClienteErrores().getErrorTelefonoJL().setText("");
                componentesClienteCreate.getTelefonoJTF().setBackground(Color.WHITE);
            }

            Long nErrores = numerosErrores(err);

            if (nErrores <= 0) {
                clienteDAO.ingresar(nombre, apellidop, apellidom, edad, email, direccion, telefono);
            }

        } else if (ae.getSource() == componentesClienteUpdate.getBuscarJB()) {
            Cliente cliente = null;
            if (!componentesClienteUpdate.getIdJTF().getText().isEmpty()) {
                try {
                    cliente = clienteDAO.filtar(Integer.valueOf(componentesClienteUpdate.getIdJTF().getText()));
                } catch (NumberFormatException e) {
                }

            }

            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado", "ERROR", 0);
            } else {
                componentesClienteUpdate.getNombreJTF().setText(cliente.getNombre());
                componentesClienteUpdate.getApellidoPJTF().setText(cliente.getApellidoP());
                componentesClienteUpdate.getApellidoMJTF().setText(cliente.getApellidoM());
                componentesClienteUpdate.getEdadJTF().setText(String.valueOf(cliente.getEdad()));
                componentesClienteUpdate.getEmailJTF().setText(cliente.getEmail());
                componentesClienteUpdate.getDireccionJTF().setText(cliente.getDireccion());
                componentesClienteUpdate.getTelefonoJTF().setText(cliente.getTelefono());

                clienteUpdate.getComponentesClienteErrores().getErrorNombreJL().setText("");
                componentesClienteUpdate.getNombreJTF().setBackground(Color.WHITE);
                clienteUpdate.getComponentesClienteErrores().getErrorApellidoPJL().setText("");
                componentesClienteUpdate.getApellidoPJTF().setBackground(Color.WHITE);
                clienteUpdate.getComponentesClienteErrores().getErrorApellidoMJL().setText("");
                componentesClienteUpdate.getApellidoMJTF().setBackground(Color.WHITE);
                clienteUpdate.getComponentesClienteErrores().getErrorEmailJL().setText("");
                componentesClienteUpdate.getEmailJTF().setBackground(Color.WHITE);
                clienteUpdate.getComponentesClienteErrores().getErrorTelefonoJL().setText("");
                componentesClienteUpdate.getTelefonoJTF().setBackground(Color.WHITE);

                id_actualizar = Integer.valueOf(cliente.getId_cliente());
            }

        } else if (ae.getSource() == componentesClienteUpdate.getBotonJB()) {
            if (id_actualizar == 0) {
                JOptionPane.showMessageDialog(null, "Busque un Cliente", "ERROR", 0);
            } else {
                String nombre = componentesClienteUpdate.getNombreJTF().getText();
                String apellidop = componentesClienteUpdate.getApellidoPJTF().getText();
                String apellidom = componentesClienteUpdate.getApellidoMJTF().getText();
                String edad = componentesClienteUpdate.getEdadJTF().getText();
                String email = componentesClienteUpdate.getEmailJTF().getText();
                String direccion = componentesClienteUpdate.getDireccionJTF().getText();
                String telefono = componentesClienteUpdate.getTelefonoJTF().getText();
                String err[] = new String[6];

                int verificar[] = verificarClienteNuevo(email, telefono);

                /* VALIDAR NOMBRE */
                if (nombre.isEmpty()) {
                    err[0] = "   Nombre no puede estar vacio";
                    clienteUpdate.getComponentesClienteErrores().getErrorNombreJL().setText(err[0]);
                    componentesClienteUpdate.getNombreJTF().setBackground(colorErrrorFormularios);
                } else {
                    clienteUpdate.getComponentesClienteErrores().getErrorNombreJL().setText("");
                    componentesClienteUpdate.getNombreJTF().setBackground(Color.WHITE);
                }

                /* VALIDAR APELLIDO PATERNO */
                if (apellidop.isEmpty()) {
                    err[1] = "   Apellido Paterno no puede estar vacio";
                    clienteUpdate.getComponentesClienteErrores().getErrorApellidoPJL().setText(err[1]);
                    componentesClienteUpdate.getApellidoPJTF().setBackground(colorErrrorFormularios);
                } else {
                    clienteUpdate.getComponentesClienteErrores().getErrorApellidoPJL().setText("");
                    componentesClienteUpdate.getApellidoPJTF().setBackground(Color.WHITE);
                }

                /* VALIDAR APELLIDO MATERNO */
                if (apellidom.isEmpty()) {
                    err[2] = "   Apellido Materno no puede estar vacio";
                    clienteUpdate.getComponentesClienteErrores().getErrorApellidoMJL().setText(err[2]);
                    componentesClienteUpdate.getApellidoMJTF().setBackground(colorErrrorFormularios);
                } else {
                    clienteUpdate.getComponentesClienteErrores().getErrorApellidoMJL().setText("");
                    componentesClienteUpdate.getApellidoMJTF().setBackground(Color.WHITE);
                }

                /* VALIDAR EDAD */
                try {
                    if (!edad.isEmpty()) {
                        int _edad = Integer.valueOf(edad);
                        if (_edad > 100 || _edad < 18) {
                            err[3] = "   Edad no valida";
                            clienteUpdate.getComponentesClienteErrores().getErrorEdadJL().setText(err[3]);
                            componentesClienteUpdate.getEdadJTF().setBackground(colorErrrorFormularios);
                        } else {
                            clienteUpdate.getComponentesClienteErrores().getErrorEdadJL().setText("");
                            componentesClienteUpdate.getEdadJTF().setBackground(Color.WHITE);
                        }
                    }
                } catch (NumberFormatException e) {
                    err[3] = "   Edad no valida";
                    clienteUpdate.getComponentesClienteErrores().getErrorEdadJL().setText(err[3]);
                    componentesClienteUpdate.getEdadJTF().setBackground(colorErrrorFormularios);
                }

                /* VALIDAR EMAIL */
                if (email.isEmpty()) {
                    err[4] = "   Correo no puede estar vacio";
                    clienteUpdate.getComponentesClienteErrores().getErrorEmailJL().setText(err[4]);
                    componentesClienteUpdate.getEmailJTF().setBackground(colorErrrorFormularios);
                } else if (!email.matches("^[a-z0-9!#$%&*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$")) {
                    err[4] = "   Correo no valido";
                    clienteUpdate.getComponentesClienteErrores().getErrorEmailJL().setText(err[4]);
                    componentesClienteUpdate.getEmailJTF().setBackground(colorErrrorFormularios);
                } else if (verificar[0] == 1) {
                    Cliente cliente = clienteDAO.filtar(id_actualizar);
                    if (!cliente.getEmail().equals(email)) {
                        System.out.println("entro");
                        err[4] = "   Correo ya existe";
                        clienteUpdate.getComponentesClienteErrores().getErrorEmailJL().setText(err[4]);
                        componentesClienteUpdate.getEmailJTF().setBackground(colorErrrorFormularios);
                    } else {
                        clienteUpdate.getComponentesClienteErrores().getErrorEmailJL().setText("");
                        componentesClienteUpdate.getEmailJTF().setBackground(Color.WHITE);
                    }
                } else {
                    clienteUpdate.getComponentesClienteErrores().getErrorEmailJL().setText("");
                    componentesClienteUpdate.getEmailJTF().setBackground(Color.WHITE);
                }

                /* VALIDAR TELEFONO */
                if (telefono.isEmpty()) {
                    err[5] = "   Telefono no puede estar vacio";
                    clienteUpdate.getComponentesClienteErrores().getErrorTelefonoJL().setText(err[5]);
                    componentesClienteUpdate.getTelefonoJTF().setBackground(colorErrrorFormularios);
                } else if (!telefono.matches("[\\(]?[\\+]?(\\d{2}|\\d{3})[\\)]?[\\s]?((\\d{6}|\\d{8})|(\\d{3}[\\*\\.\\-\\s]){3}|(\\d{2}[\\*\\.\\-\\s]){4}|(\\d{4}[\\*\\.\\-\\s]){2})|\\d{8}|\\d{10}|\\d{12}")) {
                    err[5] = "   Telefono no valido";
                    clienteUpdate.getComponentesClienteErrores().getErrorTelefonoJL().setText(err[5]);
                    componentesClienteUpdate.getTelefonoJTF().setBackground(colorErrrorFormularios);
                } else if (verificar[1] == 1) {
                    Cliente cliente = clienteDAO.filtar(id_actualizar);
                    if (!cliente.getTelefono().equals(telefono)) {
                        err[5] = "   Telefono ya existe";
                        clienteUpdate.getComponentesClienteErrores().getErrorTelefonoJL().setText(err[5]);
                        componentesClienteUpdate.getTelefonoJTF().setBackground(colorErrrorFormularios);
                    } else {
                        clienteUpdate.getComponentesClienteErrores().getErrorTelefonoJL().setText("");
                        componentesClienteUpdate.getTelefonoJTF().setBackground(Color.WHITE);
                    }
                } else {
                    clienteUpdate.getComponentesClienteErrores().getErrorTelefonoJL().setText("");
                    componentesClienteUpdate.getTelefonoJTF().setBackground(Color.WHITE);
                }

                Long nErrores = numerosErrores(err);

                if (nErrores <= 0) {
                    clienteDAO.actualizar(String.valueOf(id_actualizar), nombre, apellidop, apellidom, edad, email, direccion, telefono);
                } 

            }
        } else if (ae.getSource() == componentesClienteDelete.getBuscarJB()) {
            Cliente cliente = null;
            try {
                cliente = clienteDAO.filtar(Integer.valueOf(componentesClienteDelete.getIdJTF().getText()));
            } catch (NumberFormatException e) {
            }

            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado", "ERROR", 0);
            } else {
                componentesClienteDelete.getNombreJTF().setText(cliente.getNombre());
                componentesClienteDelete.getApellidoPJTF().setText(cliente.getApellidoP());
                componentesClienteDelete.getApellidoMJTF().setText(cliente.getApellidoM());
                componentesClienteDelete.getEdadJTF().setText(String.valueOf(cliente.getEdad()));
                componentesClienteDelete.getEmailJTF().setText(cliente.getEmail());
                componentesClienteDelete.getDireccionJTF().setText(cliente.getDireccion());
                componentesClienteDelete.getTelefonoJTF().setText(cliente.getTelefono());

                id_eliminar = Integer.valueOf(cliente.getId_cliente());
            }
            /* EVENTO DE ACTUALIZAR CLIENTE, BOTON CANCELAR */
        } else if (ae.getSource() == componentesClienteUpdate.getCancelarJB()) {
            componentesClienteUpdate.getIdJTF().setText("");
            componentesClienteUpdate.getNombreJTF().setText("");
            componentesClienteUpdate.getApellidoPJTF().setText("");
            componentesClienteUpdate.getApellidoMJTF().setText("");
            componentesClienteUpdate.getEdadJTF().setText("");
            componentesClienteUpdate.getEmailJTF().setText("");
            componentesClienteUpdate.getDireccionJTF().setText("");
            componentesClienteUpdate.getTelefonoJTF().setText("");
        } else if (ae.getSource() == componentesClienteDelete.getBotonJB()) {

            if (id_eliminar == 0) {
                JOptionPane.showMessageDialog(null, "Busque un Cliente", "ERROR", 0);
            } else {
                clienteDAO.eliminar(id_eliminar);
            }

        } else if (ae.getSource() == componentesClienteDelete.getCancelarJB()) {
            componentesClienteDelete.getIdJTF().setText("");
            componentesClienteDelete.getNombreJTF().setText("");
            componentesClienteDelete.getApellidoPJTF().setText("");
            componentesClienteDelete.getApellidoMJTF().setText("");
            componentesClienteDelete.getEdadJTF().setText("");
            componentesClienteDelete.getEmailJTF().setText("");
            componentesClienteDelete.getDireccionJTF().setText("");
            componentesClienteDelete.getTelefonoJTF().setText("");
        }

    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        llenarTabla();
        if (ie.getSource() == piePaginaListadoCliente.getComboNRegistros()) {
            piePaginaListadoCliente.paginar();
        }
    }

    private void llenarTabla() {
        contenidoTablaCliente.limpiarTabla();
        List<Cliente> lista = clienteDAO.paginar(String.valueOf(piePaginaListadoCliente.getComboNRegistros().getSelectedItem()), String.valueOf(ventanaMantenimientoCliente.getMenuMantenimientoCliente().ventanaListadoCliente.getPiePaginaListadoCliente().getComboPaginas().getSelectedItem()));

        Cliente[] miarray = new Cliente[lista.size()];
        miarray = lista.toArray(miarray);

        Object cliente[] = new Object[9];

        Stream.of(miarray).forEach(i -> {
            cliente[0] = i.getId_cliente();
            cliente[1] = i.getNombre();
            cliente[2] = i.getApellidoP();
            cliente[3] = i.getApellidoM();
            cliente[4] = i.getEdad();
            cliente[5] = i.getEmail();
            cliente[6] = i.getDireccion();
            cliente[7] = i.getTelefono();
            cliente[8] = i.getCreate_at();

            contenidoTablaCliente.getDtm().addRow(cliente);
        });

        piePaginaListadoCliente.getEtiquetaContador().setText("Mostrando " + lista.size() + " de un total de " + clienteDAO.getLeer().numeroRegistrosTabla("clientes") + " registros  ");
    }

    private int[] verificarClienteNuevo(String correo, String telefono) {
        List<Cliente> lista = clienteDAO.listar();

        int err[] = new int[2];

        err[0] = (int) lista.stream()
                .filter(c -> c.getEmail().contains(correo))
                .count();

        err[1] = (int) lista.stream()
                .filter(c -> c.getTelefono().equalsIgnoreCase(telefono))
                .count();

        return err;
    }

    private Long numerosErrores(String err[]) {
        return Stream.of(err).filter(e -> e != null).count();
    }
}

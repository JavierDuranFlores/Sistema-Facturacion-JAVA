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
import java.sql.Date;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.JOptionPane;
import modelo.dao.ProductoDAO;
import modelo.entidades.Producto;
import vista.crud.producto.PrincipalMantenimientoProducto;
import vista.crud.producto.menu.listado.contenido.TablaProducto;
import vista.crud.producto.menu.listado.encabezado.HeaderListadoProducto;
import vista.crud.producto.menu.listado.pie_pagina.PiePaginaListadoProducto;
import vista.crud.producto.menu.mantenimiento.ComponentesProductoUpdateDelete;
import vista.crud.producto.menu.mantenimiento.productoc.ComponentesProductoCreate;
import vista.crud.producto.menu.mantenimiento.productoc.ProductoCreate;
import vista.crud.producto.menu.mantenimiento.productou.ProductoUpdate;

/**
 *
 * @author javier
 */
public class ControladorMantenimientoProducto implements ActionListener, ItemListener {

    public final PrincipalMantenimientoProducto principalMantenimientoProducto;

    private final ProductoDAO productoDAO;

    private final HeaderListadoProducto headerListadoProducto;
    private final TablaProducto tablaProducto;
    private final PiePaginaListadoProducto piePaginaListadoProducto;

    private final ProductoCreate productoCreate;
    private final ProductoUpdate productoUpdate;
    private final ComponentesProductoCreate componentesProductoCreate;
    private final ComponentesProductoUpdateDelete componentesProductoUpdate;
    private final ComponentesProductoUpdateDelete componentesProductoDelete;

    int id_actualizar = 0;
    int id_eliminar = 0;

    private final Color colorErrrorFormularios = new Color(250, 177, 160);

    public ControladorMantenimientoProducto(PrincipalMantenimientoProducto principalMantenimientoProducto) {
        this.principalMantenimientoProducto = principalMantenimientoProducto;

        this.productoDAO = new ProductoDAO();

        this.headerListadoProducto = principalMantenimientoProducto.getMenuMantenimientoProducto().getListadoProducto().getHeaderListadoProducto();
        this.tablaProducto = principalMantenimientoProducto.getMenuMantenimientoProducto().getListadoProducto().getTablaProducto();
        this.piePaginaListadoProducto = principalMantenimientoProducto.getMenuMantenimientoProducto().getListadoProducto().getPiePaginaListadoProducto();

        this.productoCreate = principalMantenimientoProducto.getMenuMantenimientoProducto().getMantenimientoProducto().getMenuMantenimientoProductoCRUD().getProductoCreate();
        this.productoUpdate = principalMantenimientoProducto.getMenuMantenimientoProducto().getMantenimientoProducto().getMenuMantenimientoProductoCRUD().getProductoUpdate();

        this.componentesProductoCreate = principalMantenimientoProducto.getMenuMantenimientoProducto().getMantenimientoProducto().getMenuMantenimientoProductoCRUD().getProductoCreate().getComponentesProductoCreate();
        this.componentesProductoDelete = principalMantenimientoProducto.getMenuMantenimientoProducto().getMantenimientoProducto().getMenuMantenimientoProductoCRUD().getProductoDelete().getComponentesProductoDelete();
        this.componentesProductoUpdate = principalMantenimientoProducto.getMenuMantenimientoProducto().getMantenimientoProducto().getMenuMantenimientoProductoCRUD().getProductoUpdate().getComponentesProductoUpdate();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == headerListadoProducto.getBuscar()) {
            if (headerListadoProducto.getCampo().getText().isEmpty()) {
                llenarTabla();
                JOptionPane.showMessageDialog(null, "Producto no encontrado", "ERROR", 0);
            } else {
                List<Producto> lista = null;

                if (headerListadoProducto.getTipoBusqueda().getSelectedItem().equals("id_producto")
                        || headerListadoProducto.getTipoBusqueda().getSelectedItem().equals("stock")) {
                    try {
                        Integer number = Integer.parseInt(headerListadoProducto.getCampo().getText());
                        tablaProducto.limpiarTabla();
                        lista = productoDAO.filtar(headerListadoProducto.getTipoBusqueda().getSelectedItem().toString(), String.valueOf(number));
                    } catch (NumberFormatException e) {

                    }
                } else if (headerListadoProducto.getTipoBusqueda().getSelectedItem().equals("precio")) {
                    try {
                        double number = Double.parseDouble(headerListadoProducto.getCampo().getText());

                        tablaProducto.limpiarTabla();
                        lista = productoDAO.filtar(headerListadoProducto.getTipoBusqueda().getSelectedItem().toString(), headerListadoProducto.getCampo().getText());

                    } catch (NumberFormatException e) {
                    }
                } else if (headerListadoProducto.getTipoBusqueda().getSelectedItem().equals("fecha")) {
                    try {
                        
                        Date _fecha = Date.valueOf(headerListadoProducto.getCampo().getText());
                        lista = productoDAO.filtar(headerListadoProducto.getTipoBusqueda().getSelectedItem().toString(), String.valueOf(_fecha));
                    } catch (IllegalArgumentException e) {}

                } else {
                    tablaProducto.limpiarTabla();
                    lista = productoDAO.filtar(headerListadoProducto.getTipoBusqueda().getSelectedItem().toString(), headerListadoProducto.getCampo().getText());
                }

                if (lista != null) {
                    if (!lista.isEmpty()) {
                        tablaProducto.limpiarTabla();
                        Producto[] miarray = new Producto[lista.size()];
                        miarray = lista.toArray(miarray);

                        Object producto[] = new Object[9];

                        for (Producto i : miarray) {
                            producto[0] = i.getId();
                            producto[1] = i.getNombre();
                            producto[2] = i.getPrecio();
                            producto[3] = i.getStock();
                            producto[4] = i.getCreate_at();
                            tablaProducto.getDtm().addRow(producto);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado", "ERROR", 0);
                        llenarTabla();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Producto no encontrado", "ERROR", 0);
                }

            }
        } else if (ae.getSource() == componentesProductoCreate.getGuardarJB()) {

            String nombre = componentesProductoCreate.getNombreJTF().getText();
            String precio = componentesProductoCreate.getPrecioJTF().getText();
            String stock = componentesProductoCreate.getStockJTF().getText();

            String err[] = new String[3];

            int verificar = verificarProductoNuevo(nombre);
            /* VALIDAR NOMBRE */
            if (!nombre.isEmpty()) {
                if (nombre.matches("^([a-zA-Z0-9]{0,3})||([\\d][a-zA-Z]{0,})$")) {
                    err[0] = "   Nombre no valido";
                    productoCreate.getComponentesProductoErrores().getErrorNombreJL().setText(err[0]);
                    componentesProductoCreate.getNombreJTF().setBackground(colorErrrorFormularios);
                } else if (nombre.matches("[0-9]{0,}")) {
                    err[0] = "   Nombre no valido";
                    productoCreate.getComponentesProductoErrores().getErrorNombreJL().setText(err[0]);
                    componentesProductoCreate.getNombreJTF().setBackground(colorErrrorFormularios);
                } else {
                    productoCreate.getComponentesProductoErrores().getErrorNombreJL().setText("");
                    componentesProductoCreate.getNombreJTF().setBackground(Color.WHITE);
                }
            } else {
                err[0] = "   Nombre no puede estar vacio";
                productoCreate.getComponentesProductoErrores().getErrorNombreJL().setText(err[0]);
                componentesProductoCreate.getNombreJTF().setBackground(colorErrrorFormularios);
            }

            /* VALIDAR APELLIDO PATERNO */
 /* VALIDAR EDAD */
            try {
                if (!precio.isEmpty()) {
                    float _precio = Float.valueOf(precio);
                    if (_precio < 0) {
                        err[1] = "   Precio no valido";
                        productoCreate.getComponentesProductoErrores().getErrorPrecioJL().setText(err[1]);
                        componentesProductoCreate.getPrecioJTF().setBackground(colorErrrorFormularios);
                    } else {
                        productoCreate.getComponentesProductoErrores().getErrorPrecioJL().setText("");
                        componentesProductoCreate.getPrecioJTF().setBackground(Color.WHITE);
                    }
                } else {
                    err[1] = "   Precio no puede estar vacio";
                    productoCreate.getComponentesProductoErrores().getErrorPrecioJL().setText(err[1]);
                    componentesProductoCreate.getPrecioJTF().setBackground(colorErrrorFormularios);
                }

            } catch (NumberFormatException e) {
                err[1] = "   Precio no valido";
                productoCreate.getComponentesProductoErrores().getErrorPrecioJL().setText(err[1]);
                componentesProductoCreate.getPrecioJTF().setBackground(colorErrrorFormularios);
            }

            /* VALIDAR APELLIDO MATERNO */
            try {
                if (!stock.isEmpty()) {
                    int _stock = Integer.valueOf(stock);
                    if (_stock < 0) {
                        err[2] = "   Stock no valido";
                        productoCreate.getComponentesProductoErrores().getErrorStockJL().setText(err[2]);
                        componentesProductoCreate.getStockJTF().setBackground(colorErrrorFormularios);
                    } else {
                        productoCreate.getComponentesProductoErrores().getErrorStockJL().setText("");
                        componentesProductoCreate.getStockJTF().setBackground(Color.WHITE);
                    }
                } else {
                    err[2] = "   Stock no puede estar vacio";
                    productoCreate.getComponentesProductoErrores().getErrorStockJL().setText(err[2]);
                    componentesProductoCreate.getStockJTF().setBackground(colorErrrorFormularios);
                }

            } catch (NumberFormatException e) {
                err[2] = "   Stock no valido";
                productoCreate.getComponentesProductoErrores().getErrorStockJL().setText(err[2]);
                componentesProductoCreate.getStockJTF().setBackground(colorErrrorFormularios);
            }

            int nErrores = (int) Stream.of(err).filter(p -> p != null).count();

            if (nErrores <= 0) {
                productoDAO.ingresar(nombre, precio, stock);
            }

        } else if (ae.getSource() == componentesProductoUpdate.getBuscarJB()) {
            Producto producto = null;
            if (!componentesProductoUpdate.getIdJTF().getText().isEmpty()) {
                try {
                    producto = productoDAO.filtar(Integer.valueOf(componentesProductoUpdate.getIdJTF().getText()));
                } catch (NumberFormatException e) {
                }

            }

            if (producto == null) {
                JOptionPane.showMessageDialog(null, "Producto no encontrado", "ERROR", 0);
            } else {
                componentesProductoUpdate.getNombreJTF().setText(producto.getNombre());
                componentesProductoUpdate.getPrecioJTF().setText(String.valueOf(producto.getPrecio()));
                componentesProductoUpdate.getStockJTF().setText(String.valueOf(producto.getStock()));

                productoUpdate.getComponentesProductoErrores().getErrorNombreJL().setText("");
                componentesProductoUpdate.getNombreJTF().setBackground(Color.WHITE);
                productoUpdate.getComponentesProductoErrores().getErrorPrecioJL().setText("");
                componentesProductoUpdate.getPrecioJTF().setBackground(Color.WHITE);
                productoUpdate.getComponentesProductoErrores().getErrorStockJL().setText("");
                componentesProductoUpdate.getStockJTF().setBackground(Color.WHITE);

                id_actualizar = Integer.valueOf(producto.getId());
            }

        } else if (ae.getSource() == componentesProductoUpdate.getBotonJB()) {

            if (id_actualizar == 0) {
                JOptionPane.showMessageDialog(null, "Busque un Producto", "ERROR", 0);
            } else {
                String nombre = componentesProductoUpdate.getNombreJTF().getText();
                String precio = componentesProductoUpdate.getPrecioJTF().getText();
                String stock = componentesProductoUpdate.getStockJTF().getText();

                String err[] = new String[3];

                int verificar = verificarProductoNuevo(nombre);

                if (nombre.isEmpty()) {
                    err[0] = "   Nombre no puede estar vacio";
                    productoUpdate.getComponentesProductoErrores().getErrorNombreJL().setText(err[0]);
                    componentesProductoUpdate.getNombreJTF().setBackground(colorErrrorFormularios);
                } else {
                    productoUpdate.getComponentesProductoErrores().getErrorNombreJL().setText("");
                    componentesProductoUpdate.getNombreJTF().setBackground(Color.WHITE);
                }

                try {

                    if (!precio.isEmpty()) {
                        float _precio = Float.valueOf(precio);

                        productoUpdate.getComponentesProductoErrores().getErrorPrecioJL().setText("");
                        componentesProductoUpdate.getPrecioJTF().setBackground(Color.WHITE);
                    } else {
                        err[1] = "   Precio no puede estar vacio";
                        productoUpdate.getComponentesProductoErrores().getErrorPrecioJL().setText(err[1]);
                        componentesProductoUpdate.getPrecioJTF().setBackground(colorErrrorFormularios);
                    }
                } catch (NumberFormatException e) {
                    err[1] = "   Precio no valido";
                    productoUpdate.getComponentesProductoErrores().getErrorPrecioJL().setText(err[1]);
                    componentesProductoUpdate.getPrecioJTF().setBackground(colorErrrorFormularios);
                }

                try {

                    if (!stock.isEmpty()) {
                        int _stock = Integer.valueOf(stock);

                        productoUpdate.getComponentesProductoErrores().getErrorStockJL().setText("");
                        componentesProductoUpdate.getStockJTF().setBackground(Color.WHITE);
                    } else {
                        err[2] = "   Stock no puede estar vacio";
                        productoUpdate.getComponentesProductoErrores().getErrorStockJL().setText(err[2]);
                        componentesProductoUpdate.getStockJTF().setBackground(colorErrrorFormularios);
                    }
                } catch (NumberFormatException e) {
                    err[2] = "   Stock no valido";
                    productoUpdate.getComponentesProductoErrores().getErrorStockJL().setText(err[2]);
                    componentesProductoUpdate.getStockJTF().setBackground(colorErrrorFormularios);
                }

                Long nErrores = numerosErrores(err);

                if (nErrores <= 0) {
                    productoDAO.actualizar(String.valueOf(id_actualizar), nombre, precio, stock);
                }

            }

        } else if (ae.getSource() == componentesProductoDelete.getBuscarJB()) {
            Producto producto = null;
            try {
                producto = productoDAO.filtar(Integer.valueOf(componentesProductoDelete.getIdJTF().getText()));
            } catch (NumberFormatException e) {
            }

            if (producto == null) {
                JOptionPane.showMessageDialog(null, "Producto no encontrado", "ERROR", 0);
            } else {
                componentesProductoDelete.getNombreJTF().setText(producto.getNombre());
                componentesProductoDelete.getPrecioJTF().setText(String.valueOf(producto.getPrecio()));
                componentesProductoDelete.getStockJTF().setText(String.valueOf(producto.getStock()));

                id_eliminar = Integer.valueOf(producto.getId());
            }
            /* EVENTO DE ACTUALIZAR CLIENTE, BOTON CANCELAR */
        } else if (ae.getSource() == componentesProductoDelete.getCancelarJB()) {
            componentesProductoUpdate.getIdJTF().setText("");
            componentesProductoUpdate.getNombreJTF().setText("");
            componentesProductoUpdate.getPrecioJTF().setText("");
            componentesProductoUpdate.getStockJTF().setText("");
        } else if (ae.getSource() == componentesProductoDelete.getBotonJB()) {

            if (id_eliminar == 0) {
                JOptionPane.showMessageDialog(null, "Busque un Producto", "ERROR", 0);
            } else {
                productoDAO.eliminar(id_eliminar);
            }

        }

    }

    @Override
    public void itemStateChanged(ItemEvent ie
    ) {
        llenarTabla();
        if (ie.getSource() == piePaginaListadoProducto.getComboNRegistros()) {
            piePaginaListadoProducto.paginar();
        }
    }

    private void llenarTabla() {
        tablaProducto.limpiarTabla();
        List<Producto> lista = productoDAO.paginar(String.valueOf(piePaginaListadoProducto.getComboNRegistros().getSelectedItem()), String.valueOf(principalMantenimientoProducto.getMenuMantenimientoProducto().getListadoProducto().getPiePaginaListadoProducto().getComboPaginas().getSelectedItem()));

        Producto[] miarray = new Producto[lista.size()];
        miarray = lista.toArray(miarray);

        Object producto[] = new Object[9];

        Stream.of(miarray).forEach(i -> {
            producto[0] = i.getId();
            producto[1] = i.getNombre();
            producto[2] = i.getPrecio();
            producto[3] = i.getStock();
            producto[4] = i.getCreate_at();

            tablaProducto.getDtm().addRow(producto);
        });
        piePaginaListadoProducto.getEtiquetaContador().setText("Mostrando " + lista.size() + " de un total de " + productoDAO.getLeer().numeroRegistrosTabla("productos") + " registros  ");
    }

    private int verificarProductoNuevo(String nombre) {

        return (int) productoDAO.listar().stream()
                .filter(p -> p.getNombre()
                .contains(nombre))
                .count();
    }

    private Long numerosErrores(String err[]) {
        return Stream.of(err).filter(e -> e != null).count();
    }

}

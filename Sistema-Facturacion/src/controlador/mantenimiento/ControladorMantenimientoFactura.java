/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.mantenimiento;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.dao.ClienteDAO;
import modelo.dao.FacturaDAO;
import modelo.dao.FacturaItemDAO;
import modelo.dao.ProductoDAO;
import modelo.entidades.Cliente;
import modelo.entidades.Factura;
import modelo.entidades.FacturaItem;
import modelo.entidades.Producto;
import vista.crud.factura.PrincipalMantenimientoFactura;
import vista.crud.factura.menu.listado.contenido.ContenidoTablaFactura;
import vista.crud.factura.menu.listado.encabezado.HeaderListadoFactura;
import vista.crud.factura.menu.listado.pie_pagina.PiePaginaListadoFactura;
import vista.crud.factura.menu.mantenimiento.ComponentesFacturaUpdateDelete;
import vista.crud.factura.menu.mantenimiento.facturac.ComponentesFacturaCreate;
import vista.crud.factura.menu.mantenimiento.facturac.FacturaCreate;
import vista.crud.factura.menu.mantenimiento.facturac.TablaProductoGuardado;
import vista.crud.factura.menu.mantenimiento.facturau.FacturaUpdate;
import vista.crud.factura.menu.mantenimiento.facturad.FacturaDelete;

/**
 *
 * @author javier
 */
public class ControladorMantenimientoFactura extends MouseAdapter implements ActionListener, ItemListener {

    public final PrincipalMantenimientoFactura principalMantenimientoFactura;

    private final FacturaDAO facturaDAO;
    private final ProductoDAO productoDAO;
    private final ClienteDAO clienteDAO;
    private final FacturaItemDAO facturaItemDAO;

    private final HeaderListadoFactura headerListadoFactura;
    private final ContenidoTablaFactura contenidoTablaFactura;
    private final PiePaginaListadoFactura piePaginaListadoFactura;

    private final FacturaCreate facturaCreate;
    private final FacturaUpdate facturaUpdate;
    private final FacturaDelete facturaDelete;
    private final ComponentesFacturaCreate componentesFacturaCreate;
    private final ComponentesFacturaUpdateDelete componentesFacturaUpdate;
    private final ComponentesFacturaUpdateDelete componentesFacturaDelete;

    private final TablaProductoGuardado tablaProductoGuardado;

    private ComponentesFacturaCreate cfc;
    private JFrame ventana;

    private final Color colorErrrorFormularios = new Color(250, 177, 160);

    public ControladorMantenimientoFactura(PrincipalMantenimientoFactura principalMantenimientoFactura) {

        this.principalMantenimientoFactura = principalMantenimientoFactura;

        this.facturaDAO = new FacturaDAO();
        this.productoDAO = new ProductoDAO();
        this.clienteDAO = new ClienteDAO();
        this.facturaItemDAO = new FacturaItemDAO();

        this.headerListadoFactura = principalMantenimientoFactura.getMenuMantenimientoFactura().getListadoFactura().getHeaderListadoFactura();
        this.contenidoTablaFactura = principalMantenimientoFactura.getMenuMantenimientoFactura().getListadoFactura().getContenidioTablaFactura();
        this.piePaginaListadoFactura = principalMantenimientoFactura.getMenuMantenimientoFactura().getListadoFactura().getPiePaginaListadoFactura();

        this.facturaCreate = principalMantenimientoFactura.getMenuMantenimientoFactura().getMantenimientoFactura().getMenuMantenimientoFacturaCRUD().getFacturaCreate();
        this.facturaUpdate = principalMantenimientoFactura.getMenuMantenimientoFactura().getMantenimientoFactura().getMenuMantenimientoFacturaCRUD().getFacturaUpdate();
        this.facturaDelete = principalMantenimientoFactura.getMenuMantenimientoFactura().getMantenimientoFactura().getMenuMantenimientoFacturaCRUD().getFacturaDelete();

        this.componentesFacturaCreate = principalMantenimientoFactura.getMenuMantenimientoFactura().getMantenimientoFactura().getMenuMantenimientoFacturaCRUD().getFacturaCreate().getComponentesFacturaCreate();
        this.componentesFacturaDelete = principalMantenimientoFactura.getMenuMantenimientoFactura().getMantenimientoFactura().getMenuMantenimientoFacturaCRUD().getFacturaDelete().getComponentesFacturaDelete();
        this.componentesFacturaUpdate = principalMantenimientoFactura.getMenuMantenimientoFactura().getMantenimientoFactura().getMenuMantenimientoFacturaCRUD().getFacturaUpdate().getComponentesFacturaUpdate();

        this.tablaProductoGuardado = TablaProductoGuardado.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == headerListadoFactura.getBuscar()) {
            if (headerListadoFactura.getCampo().getText().isEmpty()) {
                llenarTablaListado();
                JOptionPane.showMessageDialog(null, "Producto no encontrado", "ERROR", 0);
            } else {
                List<Factura> lista = null;

                if (headerListadoFactura.getTipoBusqueda().getSelectedItem().equals("id_factura")
                        || headerListadoFactura.getTipoBusqueda().getSelectedItem().equals("n_productos")
                        || headerListadoFactura.getTipoBusqueda().getSelectedItem().equals("id_cliente")) {
                    try {
                        Integer number = Integer.parseInt(headerListadoFactura.getCampo().getText());
                        contenidoTablaFactura.limpiarTabla();
                        lista = facturaDAO.filtar(headerListadoFactura.getTipoBusqueda().getSelectedItem().toString(), String.valueOf(number));
                    } catch (NumberFormatException e) {
                    }
                } else if (headerListadoFactura.getTipoBusqueda().getSelectedItem().equals("total")) {
                    try {
                        double number = Double.parseDouble(headerListadoFactura.getCampo().getText());

                        contenidoTablaFactura.limpiarTabla();
                        lista = facturaDAO.filtar(headerListadoFactura.getTipoBusqueda().getSelectedItem().toString(), headerListadoFactura.getCampo().getText());

                    } catch (NumberFormatException e) {
                    }
                } else if (headerListadoFactura.getTipoBusqueda().getSelectedItem().equals("fecha")) {
                    try {

                        Date _fecha = Date.valueOf(headerListadoFactura.getCampo().getText());
                        lista = facturaDAO.filtar(headerListadoFactura.getTipoBusqueda().getSelectedItem().toString(), String.valueOf(_fecha));
                    } catch (IllegalArgumentException e) {
                    }

                } else {
                    contenidoTablaFactura.limpiarTabla();
                    lista = facturaDAO.filtar(headerListadoFactura.getTipoBusqueda().getSelectedItem().toString(), headerListadoFactura.getCampo().getText());
                }
                if (lista != null) {
                    if (!lista.isEmpty()) {
                        contenidoTablaFactura.limpiarTabla();
                        Factura[] miarray = new Factura[lista.size()];
                        miarray = lista.toArray(miarray);

                        Object producto[] = new Object[5];

                        for (Factura i : miarray) {
                            producto[0] = i.getId();
                            producto[1] = i.getNProductos();
                            producto[2] = i.getTotal();
                            producto[3] = i.getIdCliente();
                            producto[4] = i.getCreateAt();
                            contenidoTablaFactura.getDtm().addRow(producto);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Factura no encontrada", "ERROR", 0);
                        llenarTablaListado();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Factura no encontrada", "ERROR", 0);
                }
            }
        } else if (ae.getSource() == componentesFacturaCreate.getGuardarJB()) {
            String err[] = new String[3];

            List<List<Object>> listaProductos = tablaProductoGuardado.getListaProductos();

            List<Integer> nProductosProductos = new ArrayList<>();

            List<Integer> idProductos = new ArrayList<>();

            String total = "";

            Integer idFactura = 0;

            String idCliente = "0";

            String nProductosTabla = String.valueOf(tablaProductoGuardado.nProductos());

            if (componentesFacturaCreate.getTotalJTF().getText().isEmpty()) {
                err[0] = "No puede estar vacio";
                facturaCreate.getComponentesFacturaErrores().getErrorNProductosJL().setText(err[0]);
                componentesFacturaCreate.getnProductosJTF().setBackground(colorErrrorFormularios);
            } else {
                total = String.valueOf(tablaProductoGuardado.total());
            }

            if (componentesFacturaCreate.getnProductosJTF().getText().isEmpty()) {
                err[1] = "No puede estar vacio";
                facturaCreate.getComponentesFacturaErrores().getErrorTotalJL().setText(err[1]);
                componentesFacturaCreate.getTotalJTF().setBackground(colorErrrorFormularios);
            } else {

                for (int i = 0; i < listaProductos.size(); i++) {
                    nProductosProductos.add(Integer.valueOf(listaProductos.get(i).get(2).toString()));
                    idProductos.add(Integer.valueOf(listaProductos.get(i).get(0).toString()));
                }
            }

            if (componentesFacturaCreate.getIdClienteJTF().getText().isEmpty()) {
                err[2] = "No puede estar vacio";
                facturaCreate.getComponentesFacturaErrores().getErrorIdClienteJL().setText(err[2]);
                componentesFacturaCreate.getIdClienteJTF().setBackground(colorErrrorFormularios);
            } else {
                idCliente = String.valueOf(componentesFacturaCreate.getIdClienteJTF().getText());
                idFactura = Integer.valueOf(componentesFacturaCreate.getIdJTF().getText());
            }

            if (Stream.of(err).filter(e -> e != null).count() <= 0) {

                facturaDAO.ingresar(nProductosTabla, total, idCliente);

                for (int i = 0; i < listaProductos.size(); i++) {
                    facturaItemDAO.ingresar(String.valueOf(nProductosProductos.get(i)), String.valueOf(idFactura), String.valueOf(idProductos.get(i)));
                }
                componentesFacturaCreate.getIdJTF().setText(String.valueOf(facturaDAO.getLeer().ultimoId("facturas") + 1));
                componentesFacturaCreate.getnProductosJTF().setText("");
                componentesFacturaCreate.getTotalJTF().setText("");
                componentesFacturaCreate.getIdClienteJTF().setText("");
                tablaProductoGuardado.limpiarTabla();
            }

        } else if (ae.getSource() == componentesFacturaCreate.getCancelarJB()) {
            componentesFacturaCreate.getnProductosJTF().setText("");
            componentesFacturaCreate.getTotalJTF().setText("");
            componentesFacturaCreate.getIdClienteJTF().setText("");
            tablaProductoGuardado.limpiarTabla();
        } else if (ae.getSource() == componentesFacturaUpdate.getBuscarJB()) {
            if (!componentesFacturaUpdate.getIdJTF().getText().isEmpty()) {
                List<FacturaItem> lista = facturaItemDAO.filtar(componentesFacturaUpdate.getIdJTF().getText());
                List<Producto> productos = new ArrayList<>();
                List<Integer> nProductos = new ArrayList<>();
                if (!lista.isEmpty()) {
                    for (FacturaItem fi : lista) {

                        for (Producto p : fi.getProductos()) {

                            productos.add(p);
                        }

                        nProductos.add(fi.getCantidad());

                    }
                    Factura factura = lista.get(0).getFacturas().get(0);
                    componentesFacturaUpdate.getnProductosJTF().setText(String.valueOf(factura.getNProductos()));
                    componentesFacturaUpdate.getTotalJTF().setText(String.valueOf(factura.getTotal()));
                    componentesFacturaUpdate.getIdClienteJTF().setText(String.valueOf(factura.getIdCliente()));
                    componentesFacturaUpdate.getCreatAtJTF().setText(String.valueOf(factura.getCreateAt()));
                    componentesFacturaUpdate.getTablaProductoUD().poblarTabla(productos, nProductos);

                    facturaUpdate.getComponentesMantenimientoFacturaErrores().getErrorNProductosJL().setText("");
                    componentesFacturaUpdate.getnProductosJTF().setBackground(Color.WHITE);
                    facturaUpdate.getComponentesMantenimientoFacturaErrores().getErrorTotalJL().setText("");
                    componentesFacturaUpdate.getTotalJTF().setBackground(Color.WHITE);
                    facturaUpdate.getComponentesMantenimientoFacturaErrores().getErrorIdClienteJL().setText("");
                    componentesFacturaUpdate.getIdClienteJTF().setBackground(Color.WHITE);
                    facturaUpdate.getComponentesMantenimientoFacturaErrores().getErrorCreateAtJL().setText("");
                    componentesFacturaUpdate.getCreatAtJTF().setBackground(Color.WHITE);

                } else {
                    componentesFacturaUpdate.getIdJTF().setText("");
                    componentesFacturaUpdate.getnProductosJTF().setText("");
                    componentesFacturaUpdate.getTotalJTF().setText("");
                    componentesFacturaUpdate.getIdClienteJTF().setText("");
                    componentesFacturaUpdate.getCreatAtJTF().setText("");
                    componentesFacturaUpdate.getTablaProductoUD().limpiarTabla();
                    JOptionPane.showMessageDialog(null, "Factura no encontrada", "ERROR", 0);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Factura no encontrada", "ERROR", 0);
            }

        } else if (ae.getSource() == componentesFacturaUpdate.getBotonJB()) {
            if (!componentesFacturaUpdate.getIdJTF().getText().isEmpty()) {
                List<FacturaItem> listaAnteriores = facturaItemDAO.filtar(componentesFacturaUpdate.getIdJTF().getText());

                List<List<Object>> listaNuevos = new ArrayList<>();
                int row = componentesFacturaUpdate.getTablaProductoUD().getDtm().getRowCount();
                JTable tabla = componentesFacturaUpdate.getTablaProductoUD();

                String idF = componentesFacturaUpdate.getIdJTF().getText();
                String nProductos = componentesFacturaUpdate.getnProductosJTF().getText();
                String total = componentesFacturaUpdate.getTotalJTF().getText();
                String idCliente = componentesFacturaUpdate.getIdClienteJTF().getText();
                String fecha = componentesFacturaUpdate.getCreatAtJTF().getText();

                for (int i = 0; i < row; i++) {
                    List<Object> arr = new ArrayList<>();

                    arr.add(tabla.getValueAt(i, 0));
                    arr.add(tabla.getValueAt(i, 1));
                    arr.add(tabla.getValueAt(i, 2));
                    arr.add(tabla.getValueAt(i, 3));
                    arr.add(tabla.getValueAt(i, 4));
                    arr.add(tabla.getValueAt(i, 5));

                    listaNuevos.add(arr);
                }

                String err[] = new String[4];

                if (componentesFacturaUpdate.getTotalJTF().getText().isEmpty()) {
                    err[0] = "No puede estar vacio";
                    facturaUpdate.getComponentesMantenimientoFacturaErrores().getErrorNProductosJL().setText(err[0]);
                    componentesFacturaUpdate.getnProductosJTF().setBackground(colorErrrorFormularios);
                } else {
                    facturaUpdate.getComponentesMantenimientoFacturaErrores().getErrorNProductosJL().setText("");
                    componentesFacturaUpdate.getnProductosJTF().setBackground(Color.WHITE);
                }

                if (componentesFacturaUpdate.getnProductosJTF().getText().isEmpty()) {
                    err[1] = "No puede estar vacio";
                    facturaUpdate.getComponentesMantenimientoFacturaErrores().getErrorTotalJL().setText(err[1]);
                    componentesFacturaUpdate.getTotalJTF().setBackground(colorErrrorFormularios);
                } else {
                    facturaUpdate.getComponentesMantenimientoFacturaErrores().getErrorTotalJL().setText("");
                    componentesFacturaUpdate.getTotalJTF().setBackground(Color.WHITE);
                }

                if (componentesFacturaUpdate.getIdClienteJTF().getText().isEmpty()) {
                    err[2] = "No puede estar vacio";
                    facturaUpdate.getComponentesMantenimientoFacturaErrores().getErrorIdClienteJL().setText(err[2]);
                    componentesFacturaUpdate.getIdClienteJTF().setBackground(colorErrrorFormularios);
                } else {
                    facturaUpdate.getComponentesMantenimientoFacturaErrores().getErrorIdClienteJL().setText("");
                    componentesFacturaUpdate.getIdClienteJTF().setBackground(Color.WHITE);
                }

                if (componentesFacturaUpdate.getCreatAtJTF().getText().isEmpty()) {
                    err[3] = "No puede estar vacio";
                    facturaUpdate.getComponentesMantenimientoFacturaErrores().getErrorCreateAtJL().setText(err[3]);
                    componentesFacturaUpdate.getCreatAtJTF().setBackground(colorErrrorFormularios);
                } else {
                    facturaUpdate.getComponentesMantenimientoFacturaErrores().getErrorCreateAtJL().setText("");
                    componentesFacturaUpdate.getCreatAtJTF().setBackground(Color.WHITE);
                }

                if (Stream.of(err).filter(e -> e != null).count() <= 0) {
                    facturaDAO.actualizar(nProductos, total, idCliente, idF);
                }

                for (int i = 0; i < listaNuevos.size(); i++) {
                    if (i < listaAnteriores.size()) {
                        facturaItemDAO.actualizar(String.valueOf(listaNuevos.get(i).get(1)), idF, String.valueOf(listaNuevos.get(i).get(0)), String.valueOf(listaAnteriores.get(i).getIdFacturaItem()));
                    } else {
                        facturaItemDAO.ingresar(String.valueOf(listaNuevos.get(i).get(1)), idF, String.valueOf(listaNuevos.get(i).get(0)));
                    }
                }
                int i = 0;

                int idsfin[] = new int[listaNuevos.size()];
                int idsfia[] = new int[listaAnteriores.size()];
                
                for (; i < listaNuevos.size(); i++) {
                    idsfin[i] = Integer.valueOf(listaNuevos.get(i).get(0).toString());
                }

                i = 0;
                for (; i < listaAnteriores.size(); i++) {
                    idsfia[i] = Integer.valueOf(listaAnteriores.get(i).getProductos().get(0).getId());
                }
                i = 0;
                boolean diferentes = false;
                for (; i < idsfia.length; i++) {
                    for (int j = 0; j < idsfin.length; j++) {
                        if (idsfin[j] == idsfia[i]) {
                            diferentes=true;
                        } 
                    }
                    
                    if (!diferentes) {
                        facturaItemDAO.eliminar(String.valueOf(listaAnteriores.get(i).getIdFacturaItem()));
                    } else {
                        diferentes = false;
                    }
                }
                listaAnteriores = facturaItemDAO.filtar(componentesFacturaUpdate.getIdJTF().getText());
                for (i = 0; i < listaNuevos.size(); i++) {
                    if (i < listaAnteriores.size()) {
                        facturaItemDAO.actualizar(String.valueOf(listaNuevos.get(i).get(1)), idF, String.valueOf(listaNuevos.get(i).get(0)), String.valueOf(listaAnteriores.get(i).getIdFacturaItem()));
                    } else {
                        facturaItemDAO.ingresar(String.valueOf(listaNuevos.get(i).get(1)), idF, String.valueOf(listaNuevos.get(i).get(0)));
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Elija una Factura a actualizar", "ERROR", 0);
            }
        } else if (ae.getSource() == componentesFacturaUpdate.getCancelarJB()) {
            componentesFacturaUpdate.getIdJTF().setText("");
            componentesFacturaUpdate.getnProductosJTF().setText("");
            componentesFacturaUpdate.getTotalJTF().setText("");
            componentesFacturaUpdate.getIdClienteJTF().setText("");
            componentesFacturaUpdate.getCreatAtJTF().setText("");
            componentesFacturaUpdate.getTablaProductoUD().limpiarTabla();
        } else if (ae.getSource() == componentesFacturaDelete.getBuscarJB()) {

            if (!componentesFacturaDelete.getIdJTF().getText().isEmpty()) {
                List<FacturaItem> lista = facturaItemDAO.filtar(componentesFacturaDelete.getIdJTF().getText());
                List<Producto> productos = new ArrayList<>();
                List<Integer> nProductos = new ArrayList<>();
                if (!lista.isEmpty()) {
                    for (FacturaItem fi : lista) {

                        for (Producto p : fi.getProductos()) {
                            productos.add(p);
                        }

                        nProductos.add(fi.getCantidad());

                    }
                    Factura factura = lista.get(0).getFacturas().get(0);
                    componentesFacturaDelete.getnProductosJTF().setText(String.valueOf(factura.getNProductos()));
                    componentesFacturaDelete.getTotalJTF().setText(String.valueOf(factura.getTotal()));
                    componentesFacturaDelete.getIdClienteJTF().setText(String.valueOf(factura.getIdCliente()));
                    componentesFacturaDelete.getCreatAtJTF().setText(String.valueOf(factura.getCreateAt()));
                    componentesFacturaDelete.getTablaProductoUD().poblarTabla(productos, nProductos);

                    facturaDelete.getComponentesMantenimientoFacturaErrores().getErrorNProductosJL().setText("");
                    componentesFacturaDelete.getnProductosJTF().setBackground(Color.WHITE);
                    facturaDelete.getComponentesMantenimientoFacturaErrores().getErrorTotalJL().setText("");
                    componentesFacturaDelete.getTotalJTF().setBackground(Color.WHITE);
                    facturaDelete.getComponentesMantenimientoFacturaErrores().getErrorIdClienteJL().setText("");
                    componentesFacturaDelete.getIdClienteJTF().setBackground(Color.WHITE);
                    facturaDelete.getComponentesMantenimientoFacturaErrores().getErrorCreateAtJL().setText("");
                    componentesFacturaDelete.getCreatAtJTF().setBackground(Color.WHITE);

                } else {
                    componentesFacturaDelete.getIdJTF().setText("");
                    componentesFacturaDelete.getnProductosJTF().setText("");
                    componentesFacturaDelete.getTotalJTF().setText("");
                    componentesFacturaDelete.getIdClienteJTF().setText("");
                    componentesFacturaDelete.getCreatAtJTF().setText("");
                    componentesFacturaDelete.getTablaProductoUD().limpiarTabla();
                    JOptionPane.showMessageDialog(null, "Factura no encontrada", "ERROR", 0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Factura no encontrada", "ERROR", 0);
            }

        } else if (ae.getSource() == componentesFacturaDelete.getBotonJB()) {

            if (!componentesFacturaDelete.getIdJTF().getText().isEmpty()) {
                List<FacturaItem> listaAnteriores = facturaItemDAO.filtar(componentesFacturaDelete.getIdJTF().getText());

                List<List<Object>> listaNuevos = new ArrayList<>();
                int row = componentesFacturaDelete.getTablaProductoUD().getDtm().getRowCount();
                JTable tabla = componentesFacturaDelete.getTablaProductoUD();

                String idF = componentesFacturaDelete.getIdJTF().getText();
                String nProductos = componentesFacturaDelete.getnProductosJTF().getText();
                String total = componentesFacturaDelete.getTotalJTF().getText();
                String idCliente = componentesFacturaDelete.getIdClienteJTF().getText();
                String fecha = componentesFacturaDelete.getCreatAtJTF().getText();

                for (int i = 0; i < row; i++) {
                    List<Object> arr = new ArrayList<>();

                    arr.add(tabla.getValueAt(i, 0));
                    arr.add(tabla.getValueAt(i, 1));
                    arr.add(tabla.getValueAt(i, 2));
                    arr.add(tabla.getValueAt(i, 3));
                    arr.add(tabla.getValueAt(i, 4));
                    arr.add(tabla.getValueAt(i, 5));

                    listaNuevos.add(arr);
                }

                String err[] = new String[4];

                if (componentesFacturaDelete.getTotalJTF().getText().isEmpty()) {
                    err[0] = "No puede estar vacio";
                    facturaDelete.getComponentesMantenimientoFacturaErrores().getErrorNProductosJL().setText(err[0]);
                    componentesFacturaDelete.getnProductosJTF().setBackground(colorErrrorFormularios);
                } else {
                    facturaDelete.getComponentesMantenimientoFacturaErrores().getErrorNProductosJL().setText("");
                    componentesFacturaDelete.getnProductosJTF().setBackground(Color.WHITE);
                }

                if (componentesFacturaDelete.getnProductosJTF().getText().isEmpty()) {
                    err[1] = "No puede estar vacio";
                    facturaDelete.getComponentesMantenimientoFacturaErrores().getErrorTotalJL().setText(err[1]);
                    componentesFacturaDelete.getTotalJTF().setBackground(colorErrrorFormularios);
                } else {
                    facturaDelete.getComponentesMantenimientoFacturaErrores().getErrorTotalJL().setText("");
                    componentesFacturaDelete.getTotalJTF().setBackground(Color.WHITE);
                }

                if (componentesFacturaDelete.getIdClienteJTF().getText().isEmpty()) {
                    err[2] = "No puede estar vacio";
                    facturaDelete.getComponentesMantenimientoFacturaErrores().getErrorIdClienteJL().setText(err[2]);
                    componentesFacturaDelete.getIdClienteJTF().setBackground(colorErrrorFormularios);
                } else {
                    facturaDelete.getComponentesMantenimientoFacturaErrores().getErrorIdClienteJL().setText("");
                    componentesFacturaDelete.getIdClienteJTF().setBackground(Color.WHITE);
                }

                if (componentesFacturaDelete.getCreatAtJTF().getText().isEmpty()) {
                    err[3] = "No puede estar vacio";
                    facturaDelete.getComponentesMantenimientoFacturaErrores().getErrorCreateAtJL().setText(err[3]);
                    componentesFacturaDelete.getCreatAtJTF().setBackground(colorErrrorFormularios);
                } else {
                    facturaDelete.getComponentesMantenimientoFacturaErrores().getErrorCreateAtJL().setText("");
                    componentesFacturaDelete.getCreatAtJTF().setBackground(Color.WHITE);
                }

                if (Stream.of(err).filter(e -> e != null).count() <= 0) {
                    facturaDAO.eliminar(Integer.valueOf(componentesFacturaDelete.getIdJTF().getText()));
                    componentesFacturaDelete.getIdJTF().setText("");
                    componentesFacturaDelete.getnProductosJTF().setText("");
                    componentesFacturaDelete.getTotalJTF().setText("");
                    componentesFacturaDelete.getIdClienteJTF().setText("");
                    componentesFacturaDelete.getCreatAtJTF().setText("");
                    componentesFacturaDelete.getTablaProductoUD().limpiarTabla();
                    componentesFacturaCreate.getIdJTF().setText(String.valueOf(facturaDAO.getLeer().ultimoId("facturas") + 1));
                }

            } else {
                JOptionPane.showMessageDialog(null, "Elija una Factura a eliminar", "ERROR", 0);
            }
        } else if (ae.getSource() == componentesFacturaDelete.getCancelarJB()) {
            componentesFacturaDelete.getIdJTF().setText("");
            componentesFacturaDelete.getnProductosJTF().setText("");
            componentesFacturaDelete.getTotalJTF().setText("");
            componentesFacturaDelete.getIdClienteJTF().setText("");
            componentesFacturaDelete.getCreatAtJTF().setText("");
            componentesFacturaDelete.getTablaProductoUD().limpiarTabla();
        }

    }

    @Override
    public void itemStateChanged(ItemEvent ie) {

        if (ie.getSource() == piePaginaListadoFactura.getComboNRegistros()) {
            llenarTablaListado();
            piePaginaListadoFactura.paginar();
        } else if (ie.getSource() == piePaginaListadoFactura.getComboPaginas()) {
            llenarTablaListado();
        }

        if (ie.getSource() == componentesFacturaCreate.getRegistrosPJCB()) {
            llenarTablaMantenimientoProductos();
            componentesFacturaCreate.paginarProducto();
        } else if (ie.getSource() == componentesFacturaCreate.getPaginaPJCB()) {
            llenarTablaMantenimientoProductos();
        }

        if (ie.getSource() == componentesFacturaCreate.getRegistrosCJCB()) {
            llenarTablaMantenimientoClientes("create");
            componentesFacturaCreate.paginarClientes();
        } else if (ie.getSource() == componentesFacturaCreate.getPaginaCJCB()) {
            llenarTablaMantenimientoClientes("create");
        }

        if (cfc != null) {
            if (ie.getSource() == cfc.getRegistrosCJCB()) {
                llenarTablaMantenimientoClientes("update");
                componentesFacturaCreate.paginarClientes();
            } else if (ie.getSource() == cfc.getPaginaCJCB()) {
                llenarTablaMantenimientoClientes("update");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (cfc == null) {
            cfc = new ComponentesFacturaCreate();
            ventana = new JFrame();
            ventana.setSize(new Dimension(360, 200));
            ventana.add(cfc.getTablaClienteJP());
            ventana.setLocationRelativeTo(null);
            cfc.getTablaClienteFactura().addMouseListener(this);
            cfc.getPaginaCJCB().addItemListener(this);
            cfc.getRegistrosCJCB().addItemListener(this);
            ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventana.setVisible(true);
        } else {
            ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventana.setVisible(true);
        }

        if (e.getSource() == cfc.getTablaClienteFactura()) {
            JTable tabla = (JTable) e.getSource();
            Point point = e.getPoint();
            int row = tabla.rowAtPoint(point);

            String idCliente = tabla.getValueAt(row, 0).toString();

            componentesFacturaUpdate.getIdClienteJTF().setText(idCliente);

        }
    }

    private void llenarTablaListado() {
        contenidoTablaFactura.limpiarTabla();
        List<Factura> lista = facturaDAO.paginar(String.valueOf(piePaginaListadoFactura.getComboNRegistros().getSelectedItem()), String.valueOf(principalMantenimientoFactura.getMenuMantenimientoFactura().getListadoFactura().getPiePaginaListadoFactura().getComboPaginas().getSelectedItem()));

        Factura[] miarray = new Factura[lista.size()];
        miarray = lista.toArray(miarray);

        Object producto[] = new Object[9];

        Stream.of(miarray).forEach(i -> {
            producto[0] = i.getId();
            producto[1] = i.getNProductos();
            producto[2] = i.getTotal();
            producto[3] = i.getIdCliente();
            producto[4] = i.getCreateAt();

            contenidoTablaFactura.getDtm().addRow(producto);
        });
        piePaginaListadoFactura.getEtiquetaContador().setText("Mostrando " + lista.size() + " de un total de " + facturaDAO.getLeer().numeroRegistrosTabla("facturas") + " registros  ");
    }

    private void llenarTablaMantenimientoProductos() {
        componentesFacturaCreate.limpiarTabla();
        List<Producto> lista = productoDAO.paginar(String.valueOf(componentesFacturaCreate.getRegistrosPJCB().getSelectedItem()), String.valueOf(componentesFacturaCreate.getPaginaPJCB().getSelectedItem()));

        Producto[] miarray = new Producto[lista.size()];
        miarray = lista.toArray(miarray);

        Object producto[] = new Object[5];

        Stream.of(miarray).forEach(i -> {
            producto[0] = i.getId();
            producto[1] = i.getNombre();
            producto[2] = i.getPrecio();
            producto[3] = i.getStock();
            producto[4] = i.getCreate_at();

            componentesFacturaCreate.getTablaProductoFactura().getDtm().addRow(producto);
        });
    }

    private void llenarTablaMantenimientoClientes(String tabla) {
        if (tabla.equalsIgnoreCase("create")) {
            componentesFacturaCreate.limpiarTablaClientes();

            List<Cliente> lista = clienteDAO.paginar(String.valueOf(componentesFacturaCreate.getRegistrosCJCB().getSelectedItem()), String.valueOf(componentesFacturaCreate.getPaginaCJCB().getSelectedItem()));
            Cliente[] miarray = new Cliente[lista.size()];
            miarray = lista.toArray(miarray);

            Object cliente[] = new Object[6];

            Stream.of(miarray).forEach(i -> {
                cliente[0] = i.getId_cliente();
                cliente[1] = i.getNombre();
                cliente[2] = i.getApellidoP();
                cliente[3] = i.getApellidoM();
                cliente[4] = i.getEdad();
                cliente[5] = i.getEmail();

                componentesFacturaCreate.getTablaClienteFactura().getDtm().addRow(cliente);
            });
        } else if (tabla.equalsIgnoreCase("update")) {
            cfc.limpiarTablaClientes();

            List<Cliente> lista = clienteDAO.paginar(String.valueOf(cfc.getRegistrosCJCB().getSelectedItem()), String.valueOf(cfc.getPaginaCJCB().getSelectedItem()));
            Cliente[] miarray = new Cliente[lista.size()];
            miarray = lista.toArray(miarray);

            Object cliente[] = new Object[6];

            Stream.of(miarray).forEach(i -> {
                cliente[0] = i.getId_cliente();
                cliente[1] = i.getNombre();
                cliente[2] = i.getApellidoP();
                cliente[3] = i.getApellidoM();
                cliente[4] = i.getEdad();
                cliente[5] = i.getEmail();

                cfc.getTablaClienteFactura().getDtm().addRow(cliente);
            });
        }

    }

}

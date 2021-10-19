/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.producto.menu.listado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import vista.crud.cliente.menu.listado.contenido.ContenidoTablaCliente;
import vista.crud.cliente.menu.listado.encabezado.HeaderListadoCliente;
import vista.crud.cliente.menu.listado.pie_pagina.PiePaginaListadoCliente;
import vista.crud.producto.menu.listado.contenido.TablaProducto;
import vista.crud.producto.menu.listado.encabezado.HeaderListadoProducto;
import vista.crud.producto.menu.listado.pie_pagina.PiePaginaListadoProducto;

/**
 *
 * @author javier
 */
public class ListadoProducto extends JPanel{
    
    private JPanel panelBarraBusqueda;
    private JPanel panelTabla;
    private JPanel panelFooter;
    
    private HeaderListadoProducto headerListadoProducto;
    
    private TablaProducto tablaProducto;
    
    private PiePaginaListadoProducto piePaginaListadoProducto;
    
    private final Color color = new Color(223, 230, 233);
    
    public ListadoProducto() {
        headerListadoProducto = new HeaderListadoProducto();
        
        setBackground(color);
        setLayout(new BorderLayout());
        panelBarraBusqueda();
        agregarBarraBusqueda(headerListadoProducto);
        panelTabla();
        agregarTabla();
        panelFooter();
        agregarFooter();
    }
    
    private void panelBarraBusqueda() {
        panelBarraBusqueda = new JPanel(new BorderLayout());
        panelBarraBusqueda.setPreferredSize(new Dimension(680, 60));
        add(panelBarraBusqueda, BorderLayout.NORTH);
    }
    
    private void agregarBarraBusqueda(JPanel panel) {
        panelBarraBusqueda.add(panel, BorderLayout.WEST);
    }
    
    private void panelTabla() {
        panelTabla = new JPanel(new BorderLayout());
        panelTabla.setPreferredSize(new Dimension(680, 340));
        add(panelTabla, BorderLayout.CENTER);
    }
    
    private  void agregarTabla() {
        tablaProducto = new TablaProducto();
        panelTabla.add(tablaProducto, BorderLayout.CENTER);
        panelTabla.add(new JScrollPane(tablaProducto));
    }
    
    private void panelFooter() {
        panelFooter = new JPanel(new BorderLayout());
        panelFooter.setPreferredSize(new Dimension(680, 110));
        add(panelFooter, BorderLayout.SOUTH);
    }
    
    private void agregarFooter() {
        piePaginaListadoProducto = new PiePaginaListadoProducto();
        panelFooter.add(piePaginaListadoProducto, BorderLayout.CENTER);
    }

    public HeaderListadoProducto getHeaderListadoProducto() {
        return headerListadoProducto;
    }

    public TablaProducto getTablaProducto() {
        return tablaProducto;
    }

    public PiePaginaListadoProducto getPiePaginaListadoProducto() {
        return piePaginaListadoProducto;
    }
    
}

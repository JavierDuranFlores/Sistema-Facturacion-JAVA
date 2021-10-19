/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.cliente.menu.listado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import vista.crud.cliente.menu.listado.contenido.ContenidoTablaCliente;
import vista.crud.cliente.menu.listado.encabezado.HeaderListadoCliente;
import vista.crud.cliente.menu.listado.pie_pagina.PiePaginaListadoCliente;

/**
 *
 * @author javier
 */
public class ListadoCliente extends JPanel{
    private JPanel panelBarraBusqueda;
    private JPanel panelTabla;
    private JPanel panelFooter;
    
    private HeaderListadoCliente headerListadoCliente;
    
    private ContenidoTablaCliente contenidioTablaCliente;
    
    private PiePaginaListadoCliente piePaginaListadoCliente;
    
    private final Color color = new Color(223, 230, 233);
    
    public ListadoCliente() {
        headerListadoCliente = new HeaderListadoCliente(this);
        
        setBackground(color);
        setLayout(new BorderLayout());
        panelBarraBusqueda();
        agregarBarraBusqueda(headerListadoCliente);
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
        contenidioTablaCliente = new ContenidoTablaCliente(this);
        panelTabla.add(contenidioTablaCliente, BorderLayout.CENTER);
        panelTabla.add(new JScrollPane(contenidioTablaCliente));
    }
    
    private void panelFooter() {
        panelFooter = new JPanel(new BorderLayout());
        panelFooter.setPreferredSize(new Dimension(680, 110));
        add(panelFooter, BorderLayout.SOUTH);
    }
    
    private void agregarFooter() {
        piePaginaListadoCliente = new PiePaginaListadoCliente();
        panelFooter.add(piePaginaListadoCliente, BorderLayout.CENTER);
    }

    public HeaderListadoCliente getHeaderListadoCliente() {
        return headerListadoCliente;
    }

    public ContenidoTablaCliente getContenidioTablaCliente() {
        return contenidioTablaCliente;
    }

    public PiePaginaListadoCliente getPiePaginaListadoCliente() {
        return piePaginaListadoCliente;
    }
    
    
    
}

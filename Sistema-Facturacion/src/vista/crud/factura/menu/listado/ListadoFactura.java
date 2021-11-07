/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.listado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import vista.crud.factura.menu.listado.contenido.ContenidoTablaFactura;
import vista.crud.factura.menu.listado.encabezado.HeaderListadoFactura;
import vista.crud.factura.menu.listado.pie_pagina.PiePaginaListadoFactura;

/**
 *
 * @author javier
 */
public class ListadoFactura extends JPanel{
    
    private JPanel panelBarraBusqueda;
    private JPanel panelTabla;
    private JPanel panelFooter;
    
    private final HeaderListadoFactura headerListadoFactura;
    
    private ContenidoTablaFactura contenidoTablaFactura;
    
    private PiePaginaListadoFactura piePaginaListadoFactura;
    
    private final Color color = new Color(223, 230, 233);
    
    public ListadoFactura() {
        headerListadoFactura = new HeaderListadoFactura(this);
        
        setBackground(color);
        setLayout(new BorderLayout());
        panelBarraBusqueda();
        agregarBarraBusqueda(headerListadoFactura);
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
        contenidoTablaFactura = new ContenidoTablaFactura(this);
        panelTabla.add(contenidoTablaFactura, BorderLayout.CENTER);
        panelTabla.add(new JScrollPane(contenidoTablaFactura));
    }
    
    private void panelFooter() {
        panelFooter = new JPanel(new BorderLayout());
        panelFooter.setPreferredSize(new Dimension(680, 110));
        add(panelFooter, BorderLayout.SOUTH);
    }
    
    private void agregarFooter() {
        piePaginaListadoFactura = new PiePaginaListadoFactura();
        panelFooter.add(piePaginaListadoFactura, BorderLayout.CENTER);
    }

    public HeaderListadoFactura getHeaderListadoFactura() {
        return headerListadoFactura;
    }

    public ContenidoTablaFactura getContenidioTablaFactura() {
        return contenidoTablaFactura;
    }

    public PiePaginaListadoFactura getPiePaginaListadoFactura() {
        return piePaginaListadoFactura;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.cliente;

import controlador.mantenimientocliente.ControladorMantenimientoCliente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import vista.crud.cliente.menu.MenuMantenimientoCliente;

/**
 *
 * @author javier
 */
public class VentanaMantenimientoCliente {
    
    private JPanel contenedor;
    
    private JPanel panelInicial;
    private JPanel panelCenter;
    private JPanel panelConsulta;
    private JPanel panelBuscador,
                   panelTabla,
                   panelFooter;
    
    private JPanel panelBarraBusqueda;
    
    private GridBagLayout gbl;
    private GridBagLayout gblPanelConsulta;
    
    private GridBagConstraints constraints;
    private GridBagConstraints constraintsPanelConsulta;
    
    /* PANEL MENU, INSTANCIA DE LA BARRA*/
    private MenuMantenimientoCliente menuMantenimientoCliente;
    
    private ControladorMantenimientoCliente controladorMantenimientoCliente;
    
    private final Color color = new Color(241, 242, 246);
    
    public JPanel inicializadorComponentes() {
        panelIncial();
        panelCenter();
        agregarMenu();
        inicializarControlador();
        return panelInicial;
    }
    
    private void contenedor() {
        contenedor = new JPanel();
        
    }
    
    public void panelIncial() {
        panelInicial = new JPanel();
        panelInicial.setBackground(color);
    }
    
    private void panelCenter() {
        panelCenter = new JPanel(new BorderLayout());
        panelCenter.setBackground(new Color (223, 230, 233));
        panelCenter.setPreferredSize(new Dimension(800,590));
        panelInicial.add(panelCenter);
    }
    
    private void agregarMenu() {
        menuMantenimientoCliente = new MenuMantenimientoCliente();
        panelCenter.add(menuMantenimientoCliente, BorderLayout.CENTER);
    }
    
    private void panelBuscador() {
        panelBuscador = new JPanel(new BorderLayout());
        panelBuscador.setPreferredSize(new Dimension(580, 100));
        panelConsulta.add(panelBuscador, BorderLayout.NORTH);
    }
    
    private void inicializarControlador() {
        controladorMantenimientoCliente = new ControladorMantenimientoCliente(this);
        this.controladorMantenimientoCliente.ventanaMantenimientoCliente.menuMantenimientoCliente.ventanaListadoCliente.getHeaderListadoCliente().buscar.addActionListener(controladorMantenimientoCliente);
        this.controladorMantenimientoCliente.ventanaMantenimientoCliente.menuMantenimientoCliente.ventanaListadoCliente.getPiePaginaListadoCliente().getComboPaginas().addItemListener(controladorMantenimientoCliente);
        this.controladorMantenimientoCliente.ventanaMantenimientoCliente.menuMantenimientoCliente.ventanaListadoCliente.getPiePaginaListadoCliente().getComboNRegistros().addItemListener(controladorMantenimientoCliente);
        this.controladorMantenimientoCliente.ventanaMantenimientoCliente.menuMantenimientoCliente.panelMantenimientoCliente.getMenuMantenimientoClienteCRUD().getClienteCreate().getComponentesClienteCreate().getGuardarJB().addActionListener(controladorMantenimientoCliente);
        this.controladorMantenimientoCliente.ventanaMantenimientoCliente.menuMantenimientoCliente.panelMantenimientoCliente.getMenuMantenimientoClienteCRUD().getClienteUpdate().getComponentesClienteUpdate().getBuscarJB().addActionListener(controladorMantenimientoCliente);
        this.controladorMantenimientoCliente.ventanaMantenimientoCliente.menuMantenimientoCliente.panelMantenimientoCliente.getMenuMantenimientoClienteCRUD().getClienteUpdate().getComponentesClienteUpdate().getBotonJB().addActionListener(controladorMantenimientoCliente);
        this.controladorMantenimientoCliente.ventanaMantenimientoCliente.menuMantenimientoCliente.panelMantenimientoCliente.getMenuMantenimientoClienteCRUD().getClienteUpdate().getComponentesClienteUpdate().getCancelarJB().addActionListener(controladorMantenimientoCliente);
        this.controladorMantenimientoCliente.ventanaMantenimientoCliente.menuMantenimientoCliente.panelMantenimientoCliente.getMenuMantenimientoClienteCRUD().getClienteDelete().getComponentesClienteDelete().getBuscarJB().addActionListener(controladorMantenimientoCliente);
        this.controladorMantenimientoCliente.ventanaMantenimientoCliente.menuMantenimientoCliente.panelMantenimientoCliente.getMenuMantenimientoClienteCRUD().getClienteDelete().getComponentesClienteDelete().getBotonJB().addActionListener(controladorMantenimientoCliente);
        this.controladorMantenimientoCliente.ventanaMantenimientoCliente.menuMantenimientoCliente.panelMantenimientoCliente.getMenuMantenimientoClienteCRUD().getClienteDelete().getComponentesClienteDelete().getCancelarJB().addActionListener(controladorMantenimientoCliente);
    }

    public MenuMantenimientoCliente getMenuMantenimientoCliente() {
        return menuMantenimientoCliente;
    }

    public ControladorMantenimientoCliente getControladorMantenimientoCliente() {
        return controladorMantenimientoCliente;
    }
    
    
    
}
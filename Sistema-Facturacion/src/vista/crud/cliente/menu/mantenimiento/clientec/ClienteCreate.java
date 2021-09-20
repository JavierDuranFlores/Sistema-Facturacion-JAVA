/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.cliente.menu.mantenimiento.clientec;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import vista.crud.cliente.menu.mantenimiento.ComponentesMantenimientoClienteErrores;

/**
 *
 * @author javier
 */
public class ClienteCreate extends JPanel{
    
    private JPanel panelCrear;
    private JPanel panelErrores;
    private ComponentesClienteCreate componentesClienteCreate;
    private ComponentesMantenimientoClienteErrores componentesClienteErrores;
    
    public ClienteCreate() {
        setLayout(new BorderLayout());
        
        panelCrear();
        panelErrores();
                
        addPanelComponentes();
        addPanelComponentesErrores();
    }
    
    private void panelCrear() {
        panelCrear = new JPanel(new BorderLayout());
        panelCrear.setPreferredSize(new Dimension(400,590));
        add(panelCrear, BorderLayout.WEST);
    }
    
    private void panelErrores() {
        panelErrores = new JPanel(new BorderLayout());
        panelErrores.setPreferredSize(new Dimension(400,590));
        add(panelErrores, BorderLayout.EAST);
    }
    
    private void addPanelComponentes() {
        componentesClienteCreate = new ComponentesClienteCreate();
        panelCrear.add(componentesClienteCreate, BorderLayout.NORTH);
    }
    
    private void addPanelComponentesErrores() {
        componentesClienteErrores = new ComponentesMantenimientoClienteErrores(37, 38, 44, 35, 40, 84);
        panelErrores.add(componentesClienteErrores, BorderLayout.EAST);
    }

    public ComponentesClienteCreate getComponentesClienteCreate() {
        return componentesClienteCreate;
    }

    public ComponentesMantenimientoClienteErrores getComponentesClienteErrores() {
        return componentesClienteErrores;
    }
    
    
    
}

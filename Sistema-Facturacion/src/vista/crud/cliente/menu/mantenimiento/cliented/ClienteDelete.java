/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.cliente.menu.mantenimiento.cliented;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import vista.crud.cliente.menu.mantenimiento.ComponentesClienteUpdateDelete;

/**
 *
 * @author javier
 */
public class ClienteDelete extends JPanel{
    private JPanel panelCrear;
    private ComponentesClienteUpdateDelete componentesClienteDelete;
    
    public ClienteDelete() {
        setLayout(new BorderLayout());
        
        panelCrear();
        addPanelComponentes();
    }
    
    private void panelCrear() {
        panelCrear = new JPanel(new BorderLayout());
        panelCrear.setPreferredSize(new Dimension(400,590));
        add(panelCrear, BorderLayout.WEST);
    }
    
    private void addPanelComponentes() {
        componentesClienteDelete = new ComponentesClienteUpdateDelete("Borrar");
        panelCrear.add(componentesClienteDelete, BorderLayout.NORTH);
    }

    public ComponentesClienteUpdateDelete getComponentesClienteDelete() {
        return componentesClienteDelete;
    }
    
    
}

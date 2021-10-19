/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.cliente.menu.mantenimiento.clienteu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import vista.crud.cliente.menu.mantenimiento.ComponentesClienteUpdateDelete;
import vista.crud.cliente.menu.mantenimiento.ComponentesMantenimientoClienteErrores;

/**
 *
 * @author javier
 */
public class ClienteUpdate extends JPanel{
    
    private JPanel panelCrear;
    private JPanel panelErrores;
    private ComponentesClienteUpdateDelete componentesClienteUpdate;
    private ComponentesMantenimientoClienteErrores comoComponentesMantenimientoClienteErrores;
    
    public ClienteUpdate() {
        setLayout(new BorderLayout());
        
        panelCrear();
        addPanelComponentes();
        
        panelErrores();
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
        componentesClienteUpdate = new ComponentesClienteUpdateDelete("Guardar");
        panelCrear.add(componentesClienteUpdate, BorderLayout.NORTH);
    }
    
    private void addPanelComponentesErrores() {
        comoComponentesMantenimientoClienteErrores = new ComponentesMantenimientoClienteErrores(92, 38, 41, 37,38, 85);
        panelErrores.add(comoComponentesMantenimientoClienteErrores, BorderLayout.EAST);
    }

    public ComponentesClienteUpdateDelete getComponentesClienteUpdate() {
        return componentesClienteUpdate;
    }

    public ComponentesMantenimientoClienteErrores getComponentesClienteErrores() {
        return comoComponentesMantenimientoClienteErrores;
    }
    
    
    
}

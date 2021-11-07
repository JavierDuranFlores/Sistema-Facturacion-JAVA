/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.mantenimiento.facturac;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import vista.crud.factura.menu.mantenimiento.ComponentesMantenimientoFacturaErrores;

/**
 *
 * @author javier
 */
public class FacturaCreate extends JPanel{
    
    private JPanel panelCrear;
    private JPanel panelErrores;
    private ComponentesFacturaCreate componentesFacturaCreate;
    private ComponentesMantenimientoFacturaErrores componentesMantenimientoFacturaErrores;
    
    public FacturaCreate() {
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
        panelErrores.setBackground(Color.yellow);
        add(panelErrores, BorderLayout.EAST);
    }
    
    private void addPanelComponentes() {
        componentesFacturaCreate = new ComponentesFacturaCreate();
        panelCrear.add(componentesFacturaCreate, BorderLayout.NORTH);
    }
    
    private void addPanelComponentesErrores() {
        componentesMantenimientoFacturaErrores = new ComponentesMantenimientoFacturaErrores(50, 25, 25, 50);
        panelErrores.add(componentesMantenimientoFacturaErrores, BorderLayout.EAST);
    }

    public ComponentesFacturaCreate getComponentesFacturaCreate() {
        return componentesFacturaCreate;
    }

    public ComponentesMantenimientoFacturaErrores getComponentesFacturaErrores() {
        return componentesMantenimientoFacturaErrores;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.mantenimiento.facturau;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.security.Principal;
import javax.swing.JPanel;
import vista.crud.factura.PrincipalMantenimientoFactura;
import vista.crud.factura.menu.mantenimiento.ComponentesFacturaUpdateDelete;
import vista.crud.factura.menu.mantenimiento.ComponentesMantenimientoFacturaErrores;

/**
 *
 * @author javier
 */
public class FacturaUpdate extends JPanel{
    
    private JPanel panelCrear;
    private JPanel panelErrores;
    private ComponentesFacturaUpdateDelete componentesFacturaUpdate;
    private ComponentesMantenimientoFacturaErrores componentesMantenimientoFacturaErrores;
    
    public FacturaUpdate() {
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
        componentesFacturaUpdate = new ComponentesFacturaUpdateDelete("Actualizar");
        panelCrear.add(componentesFacturaUpdate, BorderLayout.NORTH);
    }
    
    private void addPanelComponentesErrores() {
        componentesMantenimientoFacturaErrores = new ComponentesMantenimientoFacturaErrores(86, 40, 40, 40);
        panelErrores.add(componentesMantenimientoFacturaErrores, BorderLayout.EAST);
    }

    public ComponentesFacturaUpdateDelete getComponentesFacturaUpdate() {
        return componentesFacturaUpdate;
    }

    public ComponentesMantenimientoFacturaErrores getComponentesMantenimientoFacturaErrores() {
        return componentesMantenimientoFacturaErrores;
    }

    
}

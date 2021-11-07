/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.mantenimiento.facturad;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import vista.crud.factura.PrincipalMantenimientoFactura;
import vista.crud.factura.menu.mantenimiento.ComponentesFacturaUpdateDelete;
import vista.crud.factura.menu.mantenimiento.ComponentesMantenimientoFacturaErrores;

/**
 *
 * @author javier
 */
public class FacturaDelete extends JPanel{
    
    private JPanel panelCrear;
    private JPanel panelErrores;
    private ComponentesFacturaUpdateDelete componentesFacturaDelete;
    private ComponentesMantenimientoFacturaErrores componentesMantenimientoFacturaErrores;
    
    public FacturaDelete() {
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
        componentesFacturaDelete = new ComponentesFacturaUpdateDelete("Borrar");
        panelCrear.add(componentesFacturaDelete, BorderLayout.NORTH);
    }
    
    private void addPanelComponentesErrores() {
        componentesMantenimientoFacturaErrores = new ComponentesMantenimientoFacturaErrores(86, 40, 40, 40);
        panelErrores.add(componentesMantenimientoFacturaErrores, BorderLayout.EAST);
    }

    public ComponentesFacturaUpdateDelete getComponentesFacturaDelete() {
        return componentesFacturaDelete;
    }

    public ComponentesMantenimientoFacturaErrores getComponentesMantenimientoFacturaErrores() {
        return componentesMantenimientoFacturaErrores;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.producto.menu.mantenimiento.productou;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import vista.crud.producto.menu.mantenimiento.ComponentesMantenimientoProductoErrores;
import vista.crud.producto.menu.mantenimiento.ComponentesProductoUpdateDelete;

/**
 *
 * @author javier
 */
public class ProductoUpdate extends JPanel{
    
    private JPanel panelCrear;
    private JPanel panelErrores;
    private ComponentesProductoUpdateDelete componentesProductoUpdate;
    private ComponentesMantenimientoProductoErrores componentesMantenimientoProductoErrores;
    
    public ProductoUpdate() {
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
        componentesProductoUpdate = new ComponentesProductoUpdateDelete("Guardar");
        panelCrear.add(componentesProductoUpdate, BorderLayout.NORTH);
    }
    
    private void addPanelComponentesErrores() {
        componentesMantenimientoProductoErrores = new ComponentesMantenimientoProductoErrores(92, 38, 41/*, 37,38, 85*/);
        panelErrores.add(componentesMantenimientoProductoErrores, BorderLayout.EAST);
    }

    public ComponentesProductoUpdateDelete getComponentesProductoUpdate() {
        return componentesProductoUpdate;
    }

    public ComponentesMantenimientoProductoErrores getComponentesProductoErrores() {
        return componentesMantenimientoProductoErrores;
    }
    
}

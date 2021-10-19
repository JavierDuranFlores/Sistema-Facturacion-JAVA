/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.producto.menu.mantenimiento.productoc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import vista.crud.producto.menu.mantenimiento.ComponentesMantenimientoProductoErrores;

/**
 *
 * @author javier
 */
public class ProductoCreate extends JPanel{
    
    private JPanel panelCrear;
    private JPanel panelErrores;
    private ComponentesProductoCreate componentesProductoCreate;
    private ComponentesMantenimientoProductoErrores componentesClienteErrores;
    
    public ProductoCreate() {
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
        componentesProductoCreate = new ComponentesProductoCreate();
        panelCrear.add(componentesProductoCreate, BorderLayout.NORTH);
    }
    
    private void addPanelComponentesErrores() {
        componentesClienteErrores = new ComponentesMantenimientoProductoErrores(37, 38, 44/*, 35, 40, 84*/);
        panelErrores.add(componentesClienteErrores, BorderLayout.EAST);
    }

    public ComponentesProductoCreate getComponentesProductoCreate() {
        return componentesProductoCreate;
    }

    public ComponentesMantenimientoProductoErrores getComponentesProductoErrores() {
        return componentesClienteErrores;
    }
    
}

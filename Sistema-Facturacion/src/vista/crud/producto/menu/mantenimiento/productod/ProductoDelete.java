/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.producto.menu.mantenimiento.productod;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import vista.crud.producto.menu.mantenimiento.ComponentesProductoUpdateDelete;

/**
 *
 * @author javier
 */
public class ProductoDelete extends JPanel{
    private JPanel panelCrear;
    private ComponentesProductoUpdateDelete componentesProductoUpdateDelete;
    
    public ProductoDelete() {
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
        componentesProductoUpdateDelete = new ComponentesProductoUpdateDelete("Borrar");
        panelCrear.add(componentesProductoUpdateDelete, BorderLayout.NORTH);
    }

    public ComponentesProductoUpdateDelete getComponentesProductoDelete() {
        return componentesProductoUpdateDelete;
    }
}

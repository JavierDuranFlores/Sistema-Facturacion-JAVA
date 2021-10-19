/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.producto.menu.mantenimiento;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author javier
 */
public class MantenimientoProducto extends JPanel{
    
    private MenuMantenimientoProductoCRUD menuMantenimientoProductoCRUD;
    
    private final Color color = new Color(223, 230, 233);
    
    public MantenimientoProducto() {
        setBackground(color);
        setLayout(new BorderLayout());
        agregarMenu();
    }
    
    private void agregarMenu() {
        menuMantenimientoProductoCRUD = new MenuMantenimientoProductoCRUD();
        add(menuMantenimientoProductoCRUD, BorderLayout.CENTER);
    }

    public MenuMantenimientoProductoCRUD getMenuMantenimientoProductoCRUD() {
        return menuMantenimientoProductoCRUD;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.cliente.menu.mantenimiento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author javier
 */
public class PanelMantenimientoCliente extends JPanel{
    private JPanel panelMantenimiento;
    
    private JPanel panelMenu;
    
    private MenuMantenimientoClienteCRUD menuMantenimientoClienteCRUD;
    
    private final Color color = new Color(223, 230, 233);
    
    public PanelMantenimientoCliente() {
        setBackground(color);
        setLayout(new BorderLayout());
        agregarMenu();
    }
    
    private void agregarMenu() {
        menuMantenimientoClienteCRUD = new MenuMantenimientoClienteCRUD();
        add(menuMantenimientoClienteCRUD, BorderLayout.CENTER);
    }

    public MenuMantenimientoClienteCRUD getMenuMantenimientoClienteCRUD() {
        return menuMantenimientoClienteCRUD;
    }
    
}
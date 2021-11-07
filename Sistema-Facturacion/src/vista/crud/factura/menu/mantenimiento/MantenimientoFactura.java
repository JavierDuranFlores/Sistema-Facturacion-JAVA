/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.mantenimiento;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import vista.crud.factura.PrincipalMantenimientoFactura;

/**
 *
 * @author javier
 */
public class MantenimientoFactura extends JPanel{
    
    private MenuMantenimientoFacturaCRUD menuMantenimientoFacturaCRUD;
    
    private final Color color = new Color(223, 230, 233);
    
    public MantenimientoFactura() {
        setBackground(color);
        setLayout(new BorderLayout());
        agregarMenu();
    }
    
    private void agregarMenu() {
        menuMantenimientoFacturaCRUD = new MenuMantenimientoFacturaCRUD();
        add(menuMantenimientoFacturaCRUD, BorderLayout.CENTER);
    }

    public MenuMantenimientoFacturaCRUD getMenuMantenimientoFacturaCRUD() {
        return menuMantenimientoFacturaCRUD;
    }
}

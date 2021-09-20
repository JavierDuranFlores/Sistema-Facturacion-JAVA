/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.cliente.menu;

import javax.swing.JTabbedPane;
import vista.crud.cliente.menu.listado.PanelListadoCliente;
import vista.crud.cliente.menu.mantenimiento.PanelMantenimientoCliente;

/**
 *
 * @author javier
 */
public class MenuMantenimientoCliente extends JTabbedPane{
    
    public PanelListadoCliente ventanaListadoCliente;
    public PanelMantenimientoCliente panelMantenimientoCliente;
    
    public MenuMantenimientoCliente() {
        ventanaListadoCliente = new PanelListadoCliente();
        panelMantenimientoCliente = new PanelMantenimientoCliente();
        
        add("Listado",ventanaListadoCliente);
        add("Mantenimiento", panelMantenimientoCliente);
    }

    
}

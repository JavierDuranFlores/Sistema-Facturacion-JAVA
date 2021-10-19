/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.cliente.menu;

import javax.swing.JTabbedPane;
import vista.crud.cliente.menu.listado.ListadoCliente;
import vista.crud.cliente.menu.mantenimiento.MantenimientoCliente;

/**
 *
 * @author javier
 */
public class MenuMantenimientoCliente extends JTabbedPane{
    
    public ListadoCliente ventanaListadoCliente;
    public MantenimientoCliente panelMantenimientoCliente;
    
    public MenuMantenimientoCliente() {
        ventanaListadoCliente = new ListadoCliente();
        panelMantenimientoCliente = new MantenimientoCliente();
        
        add("Listado",ventanaListadoCliente);
        add("Mantenimiento", panelMantenimientoCliente);
    }

    
}

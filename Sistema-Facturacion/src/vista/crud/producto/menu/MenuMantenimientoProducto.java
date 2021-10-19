/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.producto.menu;

import javax.swing.JTabbedPane;
import vista.crud.producto.menu.mantenimiento.MantenimientoProducto;
import vista.crud.producto.menu.listado.ListadoProducto;

/**
 *
 * @author javier
 */
public class MenuMantenimientoProducto extends JTabbedPane{
    
    private ListadoProducto listadoProducto;
    private MantenimientoProducto mantenimientoProducto;
    
    public MenuMantenimientoProducto() {
        listadoProducto = new ListadoProducto();
        mantenimientoProducto = new MantenimientoProducto();
        
        add("Listado",listadoProducto);
        add("Mantenimiento", mantenimientoProducto);
    }

    public ListadoProducto getListadoProducto() {
        return listadoProducto;
    }

    public MantenimientoProducto getMantenimientoProducto() {
        return mantenimientoProducto;
    }
    
    
    
}

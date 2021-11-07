/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu;

import javax.swing.JTabbedPane;
import vista.crud.factura.PrincipalMantenimientoFactura;
import vista.crud.factura.menu.listado.ListadoFactura;
import vista.crud.factura.menu.mantenimiento.MantenimientoFactura;

/**
 *
 * @author javier
 */
public class MenuMantenimientoFactura extends JTabbedPane{
    
    public ListadoFactura listadoFactura;
    public MantenimientoFactura mantenimientoFactura;
    
    public MenuMantenimientoFactura() {
        listadoFactura = new ListadoFactura();
        mantenimientoFactura = new MantenimientoFactura();
        
        add("Listado",listadoFactura);
        add("Mantenimiento", mantenimientoFactura);
    }

    public ListadoFactura getListadoFactura() {
        return listadoFactura;
    }

    public void setListadoFactura(ListadoFactura listadoFactura) {
        this.listadoFactura = listadoFactura;
    }

    public MantenimientoFactura getMantenimientoFactura() {
        return mantenimientoFactura;
    }
    
}

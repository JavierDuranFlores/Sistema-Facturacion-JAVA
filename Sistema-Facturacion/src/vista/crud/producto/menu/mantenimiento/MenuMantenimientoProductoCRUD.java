/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.producto.menu.mantenimiento;

import javax.swing.JTabbedPane;
import vista.crud.producto.menu.mantenimiento.productoc.ProductoCreate;
import vista.crud.producto.menu.mantenimiento.productod.ProductoDelete;
import vista.crud.producto.menu.mantenimiento.productou.ProductoUpdate;

/**
 *
 * @author javier
 */
public class MenuMantenimientoProductoCRUD extends JTabbedPane{
    
    ProductoCreate productoCreate;
    ProductoUpdate productoUpdate;
    ProductoDelete productoDelete;
    
    public MenuMantenimientoProductoCRUD() {
        
        productoCreate = new ProductoCreate();
        productoUpdate = new ProductoUpdate();
        productoDelete = new ProductoDelete();
        
        add("Agregar", productoCreate);
        add("Actualizar", productoUpdate);
        add("Elimnar", productoDelete);
        
    }

    public ProductoCreate getProductoCreate() {
        return productoCreate;
    }

    public ProductoUpdate getProductoUpdate() {
        return productoUpdate;
    }

    public ProductoDelete getProductoDelete() {
        return productoDelete;
    }
    
    
}

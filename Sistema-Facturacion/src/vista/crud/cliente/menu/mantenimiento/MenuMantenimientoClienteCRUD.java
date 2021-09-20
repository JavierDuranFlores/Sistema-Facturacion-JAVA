/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.cliente.menu.mantenimiento;

import javax.swing.JTabbedPane;
import vista.crud.cliente.menu.mantenimiento.clientec.ClienteCreate;
import vista.crud.cliente.menu.mantenimiento.cliented.ClienteDelete;
import vista.crud.cliente.menu.mantenimiento.clienteu.ClienteUpdate;

/**
 *
 * @author javier
 */
public class MenuMantenimientoClienteCRUD extends JTabbedPane{
    
    ClienteCreate clienteCreate;
    ClienteUpdate clienteUpdate;
    ClienteDelete clienteDelete;
    
    public MenuMantenimientoClienteCRUD() {
        
        clienteCreate = new ClienteCreate();
        clienteUpdate = new ClienteUpdate();
        clienteDelete = new ClienteDelete();
        
        add("Agregar", clienteCreate);
        add("Actualizar", clienteUpdate);
        add("Elimnar", clienteDelete);
        
    }

    public ClienteCreate getClienteCreate() {
        return clienteCreate;
    }

    public ClienteUpdate getClienteUpdate() {
        return clienteUpdate;
    }

    public ClienteDelete getClienteDelete() {
        return clienteDelete;
    }
    
    
    
}

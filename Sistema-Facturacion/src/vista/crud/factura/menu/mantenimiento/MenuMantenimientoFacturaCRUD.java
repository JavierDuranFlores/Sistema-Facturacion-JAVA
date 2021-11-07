/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.mantenimiento;

import javax.swing.JTabbedPane;
import vista.crud.factura.PrincipalMantenimientoFactura;
import vista.crud.factura.menu.mantenimiento.facturac.FacturaCreate;
import vista.crud.factura.menu.mantenimiento.facturad.FacturaDelete;
import vista.crud.factura.menu.mantenimiento.facturau.FacturaUpdate;

/**
 *
 * @author javier
 */
public class MenuMantenimientoFacturaCRUD extends JTabbedPane{
    
    FacturaCreate facturaCreate;
    FacturaUpdate facturaUpdate;
    FacturaDelete facturaDelete;
    
    public MenuMantenimientoFacturaCRUD() {
        
        facturaCreate = new FacturaCreate();
        facturaUpdate = new FacturaUpdate();
        facturaDelete = new FacturaDelete();
        
        add("Agregar", facturaCreate);
        add("Actualizar", facturaUpdate);
        add("Elimnar", facturaDelete);
        
    }

    public FacturaCreate getFacturaCreate() {
        return facturaCreate;
    }

    public void setFacturaCreate(FacturaCreate facturaCreate) {
        this.facturaCreate = facturaCreate;
    }

    public FacturaUpdate getFacturaUpdate() {
        return facturaUpdate;
    }

    public void setFacturaUpdate(FacturaUpdate facturaUpdate) {
        this.facturaUpdate = facturaUpdate;
    }

    public FacturaDelete getFacturaDelete() {
        return facturaDelete;
    }

    public void setFacturaDelete(FacturaDelete facturaDelete) {
        this.facturaDelete = facturaDelete;
    }
    
    
}

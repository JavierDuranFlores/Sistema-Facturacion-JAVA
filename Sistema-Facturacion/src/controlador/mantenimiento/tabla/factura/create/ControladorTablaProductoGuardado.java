/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.mantenimiento.tabla.factura.create;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import vista.crud.factura.menu.mantenimiento.facturac.TablaProductoGuardado;

/**
 *
 * @author javier
 */
public class ControladorTablaProductoGuardado  extends MouseAdapter {
    
    private final TablaProductoGuardado tablaProductoGuardado;
    
    public ControladorTablaProductoGuardado(JTable tabla) {
        this.tablaProductoGuardado = (TablaProductoGuardado) tabla;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {

        JTable table = (JTable) e.getSource();
        Point point = e.getPoint();
        int row = table.rowAtPoint(point);
        
        tablaProductoGuardado.getDtm().removeRow(table.getSelectedRow());
        
        
    }
    
}

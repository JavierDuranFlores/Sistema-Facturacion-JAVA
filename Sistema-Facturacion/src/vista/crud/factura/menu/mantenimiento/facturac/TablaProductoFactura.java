/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.mantenimiento.facturac;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.dao.ProductoDAO;
import modelo.entidades.Producto;

/**
 *
 * @author javier
 */
public class TablaProductoFactura extends JTable {
    
    DefaultTableModel dtm;
    
    String columnas[] = {"ID", "Nombre", "Precio", "Stock", "Fecha"};
    
    private ProductoDAO productoDAO;
    
    private static TablaProductoFactura tablaProductoFactura;
    
    public TablaProductoFactura() {
        setDefaultEditor(Object.class, null);
        dtm = new DefaultTableModel();
        productoDAO = new ProductoDAO();
        setModel(dtm);
        colocarColumna();
        colocarFilas();
    }
    
    private void colocarColumna() {

        for (int i = 0; i < 5; i++) {
            dtm.addColumn(columnas[i]);
        }

    }
    
    private void colocarFilas() {
        List<Producto> lista = productoDAO.paginar("10", "1");
        Producto[] miarray = new Producto[lista.size()];
        miarray = lista.toArray(miarray);
        Object producto[] = new Object[5];

        for (Producto i : miarray) {
            producto[0] = i.getId();
            producto[1] = i.getNombre();
            producto[2] = i.getPrecio();
            producto[3] = i.getStock();
            producto[4] = i.getCreate_at();
            
            dtm.addRow(producto);
        }
    }
    
    public void limpiarTabla() {
        int nColumn = dtm.getRowCount() - 1;
        if (nColumn >= 0) {
            for (int i = nColumn; i >= 0; i--) {
                dtm.removeRow(i);
            }
        }
    }
    
    public int nProductos () {
        
        List<List<Object>> listaProductos = new ArrayList<List<Object>>();
        
        Integer nProductos = 0;

        for (int row = 0; row < this.getRowCount(); row++) {
            
            nProductos += Integer.valueOf(this.getValueAt(row, 2).toString());            
            
        }
        return nProductos;
    }
    
    public Double total() {
        Double total = 0.0;
        for (int row = 0; row < this.getRowCount(); row++) {
            total += Double.valueOf(this.getValueAt(row, 4).toString());
        }
        return total;
    }
    
    public DefaultTableModel getDtm() {
        return dtm;
    }
}

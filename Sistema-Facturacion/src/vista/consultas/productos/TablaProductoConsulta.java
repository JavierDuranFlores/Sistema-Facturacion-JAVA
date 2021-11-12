/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.consultas.productos;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ProductoDAO;
import modelo.entidades.Producto;

/**
 *
 * @author javier
 */
public class TablaProductoConsulta extends JTable {
    
    DefaultTableModel dtm;
    
    String columnas[] = {"ID", "Nombre", "Precio", "Stock", "Fecha"};
    
    public TablaProductoConsulta() {
        setDefaultEditor(Object.class, null);
        dtm = new DefaultTableModel();
        setModel(dtm);
        colocarColumna();
    }
    
    private void colocarColumna() {

        for (int i = 0; i < 5; i++) {
            dtm.addColumn(columnas[i]);
        }

    }
    
    public void colocarFilas(String fi, String ff) {
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> lista = productoDAO.filtarFechaCreado(fi, ff);
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
    
    public DefaultTableModel getDtm() {
        return dtm;
    }
}

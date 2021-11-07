/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.mantenimiento;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ProductoDAO;
import modelo.entidades.Producto;

/**
 *
 * @author javier
 */
public class TablaProductoUD extends JTable {

    DefaultTableModel dtm;

    String columnas[] = {"ID", "NO. Producto", "Nombre", "Precio", "Stock", "Fecha"};

    private ProductoDAO productoDAO;
    private List<List<Object>> listaProductos;

    private static TablaProductoUD Instance;

    public TablaProductoUD(List<Producto> lista) {
        setDefaultEditor(Object.class, null);
        dtm = new DefaultTableModel();
        productoDAO = new ProductoDAO();
        setModel(dtm);
        colocarColumna();
    }

    /*public static synchronized TablaProductoUD getInstance(List<Producto> lista) {
        if (Instance == null)
            Instance = new TablaProductoUD(lista);
        
        return Instance;
    }*/
    private void colocarColumna() {

        for (int i = 0; i < 6; i++) {
            dtm.addColumn(columnas[i]);
        }

    }

    public void poblarTabla(List<Producto> lista, List<Integer> nProductos) {
        if (lista != null) {
            limpiarTabla();
            for (int i = 0; i < lista.size(); i++) {
                Object producto[] = new Object[6];
                producto[0] = lista.get(i).getId();
                producto[1] = nProductos.get(i);
                producto[2] = lista.get(i).getNombre();
                producto[3] = lista.get(i).getPrecio();
                producto[4] = lista.get(i).getStock();
                producto[5] = lista.get(i).getCreate_at();
                dtm.addRow(producto);
            }
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

    private boolean productosTabla(JTable table, Producto buscar) {

        for (int row = 0; row < table.getRowCount(); row++) {
            if (String.valueOf(buscar.getId()).equals(table.getValueAt(row, 0).toString())) {
                return true;
            }
        }
        return false;
    }

    private int productosFila(JTable table, Producto buscar) {

        for (int row = 0; row < table.getRowCount(); row++) {

            if (String.valueOf(buscar.getId()).equals(table.getValueAt(row, 0).toString())) {
                return row;
            }
        }
        return 1000000;
    }

    public int nProductos() {

        List<List<Object>> listaProductos = new ArrayList<List<Object>>();

        Integer nProductos = 0;

        for (int row = 0; row < this.getRowCount(); row++) {

            nProductos += Integer.valueOf(this.getValueAt(row, 1).toString());

        }
        return nProductos;
    }

    public Double total() {
        Double total = 0.0;
        for (int row = 0; row < this.getRowCount(); row++) {
            for (int i = 0; i < Integer.valueOf(this.getValueAt(row, 1).toString()); i++) {
                total += Double.valueOf(this.getValueAt(row, 3).toString());
            }
        }
        return total;
    }

}

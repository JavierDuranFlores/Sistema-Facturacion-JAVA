/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.mantenimiento.facturac;

import controlador.mantenimiento.tabla.factura.create.ControladorTablaProductoGuardado;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.dao.FacturaDAO;
import modelo.dao.ProductoDAO;
import modelo.entidades.Producto;

/**
 *
 * @author javier
 */
public class TablaProductoGuardado extends JTable {

    DefaultTableModel dtm;

    String columnas[] = {"ID", "Nombre", "N. Productos", "Id_Factura", "Total"};

    private ProductoDAO productoDAO;
    private FacturaDAO facturaDAO;
    private Producto anterior;
    private List<List<Object>> listaProductos;

    private static TablaProductoGuardado instancia;
    
    private ControladorTablaProductoGuardado controladorTablaProductoGuardado;

    private TablaProductoGuardado() {
        setDefaultEditor(Object.class, null);
        dtm = new DefaultTableModel();
        productoDAO = new ProductoDAO();
        facturaDAO = new FacturaDAO();
        controladorTablaProductoGuardado = new ControladorTablaProductoGuardado(this);
        addMouseListener(controladorTablaProductoGuardado);
        setModel(dtm);
        colocarColumna();
    }

    public synchronized static TablaProductoGuardado getInstance() {

        if (instancia == null) {
            instancia = new TablaProductoGuardado();
        }

        return instancia;
    }

    private void colocarColumna() {

        for (int i = 0; i < 5; i++) {
            dtm.addColumn(columnas[i]);
        }

    }

    public void colocarFilas(Producto product, int row) {
        Object producto[] = new Object[5];

        /*
            Recuperar el id del producto
            Compararlo con el id de los productos de la tabla
            Recuperar la fila
            Cambiarla
         */
        if (productosTabla(this, product)) {

            getDtm().setValueAt(Integer.valueOf(
                    this.getDtm().getValueAt(productosFila(this, product), 2).toString()) + 1, // El dato
                    productosFila(this, product), // Fila
                    2); // Columna

            /*
                Necesitamos el precio del producto
                El numero de productos
             */
            List<Producto> productos = productoDAO.filtar("id_producto", this.getDtm().getValueAt(productosFila(this, product), 0).toString());
            Double precio = productos.get(0).getPrecio();
            Integer nProductos = Integer.valueOf(this.getDtm().getValueAt(productosFila(this, product), 2).toString());
            Double total = nProductos * precio;
            getDtm().setValueAt(total, productosFila(this, product), 4);

            productosTabla();
        } else {
            producto[0] = product.getId();
            producto[1] = product.getNombre();
            producto[2] = product.getStock();
            producto[3] = facturaDAO.getLeer().ultimoId("facturas") + 1;
            producto[4] = product.getPrecio();

            dtm.addRow(producto);
            productosTabla();
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

    public void productosTabla() {

        List<List<Object>> listaProductos = new ArrayList<List<Object>>();

        for (int row = 0; row < this.getRowCount(); row++) {
            List<Object> product = new ArrayList<>();
            product.add(this.getValueAt(row, 0).toString());
            product.add(this.getValueAt(row, 1).toString());
            product.add(this.getValueAt(row, 2).toString());
            product.add(this.getValueAt(row, 3).toString());
            product.add(this.getValueAt(row, 4).toString());
            
            listaProductos.add(product);
        }
        this.listaProductos = listaProductos;
    }
    
    public Double total() {
        Double total = 0.0;
        for (int row = 0; row < this.getRowCount(); row++) {
            total += Double.valueOf(this.getValueAt(row, 4).toString());
        }
        return total;
    }
    
    public int nProductos () {
        
        List<List<Object>> listaProductos = new ArrayList<List<Object>>();
        
        Integer nProductos = 0;

        for (int row = 0; row < this.getRowCount(); row++) {
            
            nProductos += Integer.valueOf(this.getValueAt(row, 2).toString());            
            
        }
        return nProductos;
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }

    public  List<List<Object>> getListaProductos() {
        return listaProductos;
    }

}

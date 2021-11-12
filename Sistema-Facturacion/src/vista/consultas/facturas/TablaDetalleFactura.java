/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.consultas.facturas;

import controlador.consultas.Facturas.tabla.ControladorConsultaFacturasTabla;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.dao.FacturaDAO;
import modelo.entidades.Factura;
import modelo.entidades.Producto;

/**
 *
 * @author javier
 */
public class TablaDetalleFactura extends JTable {

    DefaultTableModel dtm;
    String columnas[] = {"ID", "Nombre", "Precio", "Stock", "Fecha"};

    public TablaDetalleFactura() {
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

    public void colocarFilas(List<Producto> productos) {
        Object factura[] = new Object[5];

        for (Producto p : productos) {
            factura[0] = p.getId();
            factura[1] = p.getNombre();
            factura[2] = p.getPrecio();
            factura[3] = p.getStock();
            factura[4] = p.getCreate_at();
            dtm.addRow(factura);
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

    private void tamColumnas(Object clase) {
        getColumnModel().getColumn(0).setMaxWidth(35);
        getColumnModel().getColumn(1).setMaxWidth(130);
        getColumnModel().getColumn(2).setMaxWidth(400);
        getColumnModel().getColumn(3).setMaxWidth(400);
        getColumnModel().getColumn(4).setMaxWidth(40);
        getColumnModel().getColumn(5).setMaxWidth(600);
        getColumnModel().getColumn(6).setMaxWidth(350);
        getColumnModel().getColumn(7).setMaxWidth(200);
        getColumnModel().getColumn(8).setMaxWidth(140);
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }
}

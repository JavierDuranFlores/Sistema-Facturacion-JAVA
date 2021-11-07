/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.listado.contenido;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.dao.FacturaDAO;
import modelo.entidades.Factura;

/**
 *
 * @author javier
 */
public class ContenidoTablaFactura extends JTable {

    DefaultTableModel dtm;
    String columnas[] = {"ID", "No. Productos", "Total", "Cliente", "Fecha"};

    public ContenidoTablaFactura(Object clase) {
        setDefaultEditor(Object.class, null);
        dtm = new DefaultTableModel();
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
        FacturaDAO facturaDAO = new FacturaDAO();
        List<Factura> lista = facturaDAO.paginar("10", "1");
        Factura[] miarray = new Factura[lista.size()];
        miarray = lista.toArray(miarray);
        Object factura[] = new Object[5];

        for (Factura i : miarray) {
            factura[0] = i.getId();
            factura[1] = i.getNProductos();
            factura[2] = i.getTotal();
            factura[3] = i.getIdCliente();
            factura[4] = i.getCreateAt();

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

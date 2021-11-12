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

/**
 *
 * @author javier
 */
public class TablaFacturaConsulta extends JTable {

    DefaultTableModel dtm;
    String columnas[] = {"ID", "No. Productos", "Total", "Cliente", "Fecha"};
    private ControladorConsultaFacturasTabla controladorConsultaFacturasTabla;

    public TablaFacturaConsulta() {
        controladorConsultaFacturasTabla = new ControladorConsultaFacturasTabla();
        setDefaultEditor(Object.class, null);
        dtm = new DefaultTableModel();
        setModel(dtm);
        colocarColumna();
        addMouseListener(controladorConsultaFacturasTabla);
    }

    private void colocarColumna() {

        for (int i = 0; i < 5; i++) {
            dtm.addColumn(columnas[i]);
        }

    }

    public void colocarFilas(String fi, String ff, String tipoBusqueda) {
        FacturaDAO facturaDAO = new FacturaDAO();
        List<Factura> lista = null;
        if (tipoBusqueda.equalsIgnoreCase("fecha"))
            lista = facturaDAO.filtarFechaCreado(fi, ff);
        else 
            lista = facturaDAO.filtar("id_cliente", fi);
        
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.consultas.clientes;

import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ClienteDAO;
import modelo.entidades.Cliente;

/**
 *
 * @author javier
 */
public class TablaClientesConsulta extends JTable {
    
    DefaultTableModel dtm;
    String columnas[] = {"ID", "Nombre", "Apellido P.", "Apellido M.", "Edad", "Email", "Dirección", "Telefono", "Fecha"};

    public TablaClientesConsulta() {
        setDefaultEditor(Object.class, null);
        dtm = new DefaultTableModel();
        setModel(dtm);
        colocarColumna();
        //colocarFilas();
    }

    private void colocarColumna() {

        for (int i = 0; i < 9; i++) {
            dtm.addColumn(columnas[i]);
        }

    }

    public void colocarFilas(String fi, String ff) {
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> lista = clienteDAO.filtarFechaCreado(fi, ff);
        Cliente[] miarray = new Cliente[lista.size()];
        miarray = lista.toArray(miarray);
        Object cliente[] = new Object[9];

        for (Cliente i : miarray) {
            cliente[0] = i.getId_cliente();
            cliente[1] = i.getNombre();
            cliente[2] = i.getApellidoP();
            cliente[3] = i.getApellidoM();
            cliente[4] = i.getEdad();
            cliente[5] = i.getEmail();
            cliente[6] = i.getDireccion();
            cliente[7] = i.getTelefono();
            cliente[8] = i.getCreate_at();

            dtm.addRow(cliente);
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

    private void tamColumnas() {
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

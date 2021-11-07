/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.mantenimiento.facturac;

import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.dao.ClienteDAO;
import modelo.dao.ProductoDAO;
import modelo.entidades.Cliente;
import modelo.entidades.Producto;

/**
 *
 * @author javier
 */
public class TablaClienteFactura extends JTable {
    
    DefaultTableModel dtm;
    
    String columnas[] = {"ID", "Nombre", "Apellido P.", "Apellido M.", "Edad", "Email"};
    
    private ClienteDAO clienteDAO;
    
    public TablaClienteFactura() {
        setDefaultEditor(Object.class, null);
        dtm = new DefaultTableModel();
        clienteDAO = new ClienteDAO();
        setModel(dtm);
        colocarColumna();
        colocarFilas();
    }
    
    private void colocarColumna() {

        for (int i = 0; i < 6; i++) {
            dtm.addColumn(columnas[i]);
        }

    }
    
    private void colocarFilas() {
        List<Cliente> lista = clienteDAO.paginar("10", "1");
        Cliente[] miarray = new Cliente[lista.size()];
        miarray = lista.toArray(miarray);
        Object cliente[] = new Object[6];

        for (Cliente i : miarray) {
            cliente[0] = i.getId_cliente();
            cliente[1] = i.getNombre();
            cliente[2] = i.getApellidoP();
            cliente[3] = i.getApellidoM();
            cliente[4] = i.getEdad();
            cliente[5] = i.getEmail();
            
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
    
    public DefaultTableModel getDtm() {
        return dtm;
    }
}

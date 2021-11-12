/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.consultas.Facturas.tabla;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JTable;
import modelo.dao.ReporteDAO;
import modelo.entidades.Cliente;
import modelo.entidades.Factura;
import modelo.entidades.FacturaItem;
import modelo.entidades.Producto;
import modelo.entidades.Reporte;
import vista.consultas.facturas.DetallesFacturas;

/**
 *
 * @author javier
 */
public class ControladorConsultaFacturasTabla extends MouseAdapter {
    
    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point point = e.getPoint();
        int row = table.rowAtPoint(point);
        
        String idFactura = table.getValueAt(row, 0).toString();
        String nPF = table.getValueAt(row, 1).toString();
        String total = table.getValueAt(row, 2).toString();
        String idCliente = table.getValueAt(row, 3).toString();
        String fechaF = table.getValueAt(row, 4).toString();
        
        ReporteDAO reporteDAO = new ReporteDAO();
        List<Reporte> reportes = reporteDAO.lista(idCliente, idFactura);
        Factura facturas = reportes.get(0).getFactura();
        Cliente cliente = reportes.get(0).getCliente();
        List<FacturaItem> facturaItems = null;
        List<Producto> productos = null;
        for (int i = 0; i < reportes.size(); i++) {
            facturaItems = reportes.get(i).getFacturaItems();
            productos = reportes.get(i).getProductos();
        }
        /*System.out.println(facturas);
        System.out.println(clientes);
        System.out.println(facturaItems);
        for (FacturaItem fi : facturaItems) {
            System.out.println(fi + " ");
        }
        System.out.println(productos);*/
        
        DetallesFacturas detallesFacturas = new DetallesFacturas();
        detallesFacturas.getInfoFacturaJL()[0].setText("ID Cliente: " + idCliente);
        detallesFacturas.getInfoFacturaJL()[1].setText("Nombre: " + cliente.getNombre());
        detallesFacturas.getInfoFacturaJL()[2].setText("Apellido: " + cliente.getApellidoP() + " " + cliente.getApellidoM());
        detallesFacturas.getInfoFacturaJL()[3].setText("Edad: " + cliente.getEdad());
        detallesFacturas.getInfoFacturaJL()[4].setText("Telefono: " + cliente.getTelefono());
        detallesFacturas.getInfoFacturaJL()[5].setText("ID Factura: " + idFactura);
        detallesFacturas.getInfoFacturaJL()[6].setText("NO. Productos: " + nPF);
        detallesFacturas.getInfoFacturaJL()[7].setText("Total: " + total);
        detallesFacturas.getInfoFacturaJL()[8].setText("Fecha: " + fechaF);
        detallesFacturas.colocarFilasTabla(productos);
    }
    
}

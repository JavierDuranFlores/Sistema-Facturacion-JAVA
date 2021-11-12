/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.List;
import modelo.dao.ReporteDAO;
import modelo.entidades.Cliente;
import modelo.entidades.Factura;
import modelo.entidades.FacturaItem;
import modelo.entidades.Producto;
import modelo.entidades.Reporte;
import vista.inicio.Inicio;
import vista.login.LoginVentana;

/**
 *
 * @author javier
 */
public class Main {

    public static void main(String[] args) {
        Inicio vtn = new Inicio();
        vtn.iniciarComponentes();

        /*ReporteDAO reporteDAO = new ReporteDAO();
        List<Reporte> reportes = reporteDAO.lista("2", "36");
        Factura facturas = reportes.get(0).getFactura();
        Cliente clientes = reportes.get(0).getCliente();
        List<FacturaItem> facturaItems = null;
        List<Producto> productos = null;
        for (int i = 0; i < reportes.size(); i++) {
            facturaItems = reportes.get(i).getFacturaItems();
            productos = reportes.get(i).getProductos();
        }
        System.out.println(facturas);
        System.out.println(clientes);
        System.out.println(facturaItems);
        /*for (FacturaItem fi : facturaItems) {
            System.out.println(fi + " ");
        }
        System.out.println(productos);*/
    }
}

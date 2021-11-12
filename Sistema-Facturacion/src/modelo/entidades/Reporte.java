/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.util.Date;
import java.util.List;

/**
 *
 * @author javier
 */
public class Reporte {
    
   /*Short idFactura;
   Short nProductosFactura;
   Double totalFactura;
   Short idCliente;
   String nombreCliente;
   String apellidoPCliente;
   String apellidoMCliente;
   Integer edad;
   Date creatAtFactura;
   Short idFacturaItem;
   Integer cantidadFacturaItem;
   Short idProducto;
   String nombreProducto;
   Double precioProducto;
   Integer stock;
   Date createAtProducto;*/
    
    Factura factura;
    Cliente cliente;
    List<FacturaItem> facturaItems;
    List<Producto> productos;

    public Reporte(Factura factura, Cliente cliente, List<FacturaItem> facturaItems, List<Producto> productos) {
        this.factura = factura;
        this.cliente = cliente;
        this.facturaItems = facturaItems;
        this.productos = productos;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<FacturaItem> getFacturaItems() {
        return facturaItems;
    }

    public void setFacturaItems(List<FacturaItem> facturaItems) {
        this.facturaItems = facturaItems;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Reporte{" + "factura=" + factura + ", cliente=" + cliente + ", facturaItems=" + facturaItems + ", productos=" + productos + '}';
    }
    
}

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.util.List;

/**
 *
 * @author javier
 */
public class FacturaItem {
    
    private Short idFacturaItem;
    
    private Integer cantidad;
    
    private List<Factura> facturas;
    
    private List<Producto> productos;

    public FacturaItem(Short idFacturaItem, Integer cantidad) {
        this.idFacturaItem = idFacturaItem;
        this.cantidad = cantidad;
    }
    
    public Short getIdFacturaItem() {
        return idFacturaItem;
    }

    public void setIdFacturaItem(Short idFacturaItem) {
        this.idFacturaItem = idFacturaItem;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos    (List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "FacturaItem{" + "idFacturaItem=" + idFacturaItem + ", cantidad=" + cantidad + ", facturas=" + facturas + ", productos=" + productos + '}';
    }
    
    
}

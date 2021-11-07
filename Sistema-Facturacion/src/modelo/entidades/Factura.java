/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.sql.Date;

/**
 *
 * @author javier
 */
public class Factura {
    
    private Short id;
    private Short nProductos;
    private Double total;
    private Short idCliente;
    private Date create_at;

    public Factura() {}

    public Factura(Short id, Short nProductos, Double total, Short idCliente, Date create_at) {
        this.id = id;
        this.nProductos = nProductos;
        this.total = total;
        this.idCliente = idCliente;
        this.create_at = create_at;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getNProductos() {
        return nProductos;
    }

    public void setnProductos(Short nProductos) {
        this.nProductos = nProductos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Short getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Short idCliente) {
        this.idCliente = idCliente;
    }

    public Date getCreateAt() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "Factura{" + "id=" + id + ", nProductos=" + nProductos + ", total=" + total + ", idCliente=" + idCliente + ", create_at=" + create_at + '}';
    }
 
    
    
}

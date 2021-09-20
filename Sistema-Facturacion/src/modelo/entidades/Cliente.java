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
public class Cliente {
    
    private Short id_cliente;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private Integer edad;
    private String email;
    private String direccion;
    private String telefono;
    private Date create_at;
    
    public Cliente(){}

    public Cliente(Short id_cliente, String nombre, String apellidoP, String apellidoM, Integer edad, String email, String direccion, String telefono, Date create_at) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.edad = edad;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.create_at = create_at;
    }

    public Short getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Short id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id_cliente=" + id_cliente + ", nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM + ", edad=" + edad + ", email=" + email + ", direccion=" + direccion + ", telefono=" + telefono + ", create_at=" + create_at + '}';
    }
    
}

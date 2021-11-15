/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

/**
 *
 * @author javier
 */
public class Usuario {

    private short id;
    private String usuario;
    private String password;

    public Usuario() {
        
    }
    
    public Usuario(short id, String usuario, String password) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
    }
    
    public Usuario(String usuario, String password) {
        this.id = 0;
        this.usuario = usuario;
        this.password = password;
    }
    
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombreUsuario=" + usuario + ", password=" + password + '}';
    }

}
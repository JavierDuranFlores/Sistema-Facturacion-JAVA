/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Conexion;

/**
 *
 * @author javier
 */
public class InsertarCliente {
    
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public InsertarCliente() {
        CON = Conexion.getInstacia();
    }
    
    public void ingresar(String nombre, String apellidp, String apellidom, String edad, String email, String direccion, String telefono) {
        try {
            ps = CON.conectar().prepareStatement("SELECT ingresa_cliente(?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, nombre);
            ps.setString(2, apellidp);
            ps.setString(3, apellidom);
            ps.setString(4, edad);
            ps.setString(5, email);
            ps.setString(6, direccion);
            ps.setString(7, telefono);
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "CLIENTE INGRESADO");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "CLIENTE NO INGRESADO", "ERROR", 0);
        } finally {
            CON.desconectar();
        }
    }
    
}

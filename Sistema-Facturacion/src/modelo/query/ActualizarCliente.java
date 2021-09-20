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
public class ActualizarCliente {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public ActualizarCliente() {
        CON = Conexion.getInstacia();
    }
    
    public void actualizar(int id, String nombre, String apellidp, String apellidom, String edad, String email, String direccion, String telefono) {
        try {
            ps = CON.conectar().prepareStatement("SELECT actualizar_cliente(?, ?, ?, ?, ?, ?, ?, ?);");
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, apellidp);
            ps.setString(4, apellidom);
            ps.setString(5, edad);
            ps.setString(6, email);
            ps.setString(7, direccion);
            ps.setString(8, telefono);
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "CLIENTE ACTUALIZADO");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "CLIENTE NO ACTUALIZADO", "ERROR", 0);
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            CON.desconectar();
        }
    }
    
}

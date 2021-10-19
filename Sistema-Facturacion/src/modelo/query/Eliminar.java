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
public class Eliminar {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public Eliminar() {
        CON = Conexion.getInstacia();
    }
    
    public void eliminar(String tabla, int id) {
        try {
            if (tabla.equalsIgnoreCase("clientes")) 
                ps = CON.conectar().prepareStatement("SELECT eliminar_cliente(?);");
            if (tabla.equalsIgnoreCase("productos"))
                ps = CON.conectar().prepareStatement("SELECT eliminar_producto(?);");
            
            ps.setInt(1, id);
            ps.execute();
            
            if (tabla.equalsIgnoreCase("clientes")) 
                JOptionPane.showMessageDialog(null, "CLIENTE ELIMINADO");
            if (tabla.equalsIgnoreCase("productos")) 
                JOptionPane.showMessageDialog(null, "PRODUCTO ELIMINADO");
            
        } catch (SQLException ex) {
            if (tabla.equalsIgnoreCase("clientes")) 
                JOptionPane.showMessageDialog(null, "CLIENTE NO ELIMINADO", "ERROR", 0);
            if (tabla.equalsIgnoreCase("productos")) 
                JOptionPane.showMessageDialog(null, "PRODUCTO NO ELIMINADO", "ERROR", 0);
            ex.printStackTrace();
        } finally {
            CON.desconectar();
        }
    }
}

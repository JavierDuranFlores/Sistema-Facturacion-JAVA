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
public class EliminarCliente {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public EliminarCliente() {
        CON = Conexion.getInstacia();
    }
    
    public void eliminar(int id) {
        try {
            ps = CON.conectar().prepareStatement("SELECT eliminar_cliente(?);");
            ps.setInt(1, id);
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "CLIENTE ELIMINADO");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "CLIENTE NO ELIMINADO", "ERROR", 0);
            ex.printStackTrace();
        } finally {
            CON.desconectar();
        }
    }
}

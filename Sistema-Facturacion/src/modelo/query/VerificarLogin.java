/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.entidades.Usuario;

/**
 *
 * @author javier
 */
public class VerificarLogin {
    
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public VerificarLogin() {
        CON = Conexion.getInstacia();
    }
    
    public ResultSet sql(Usuario usuario) {
        
        try {
            
            ps = CON.conectar().prepareStatement("SELECT autenticacion (?, ?);");
            
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            rs = ps.executeQuery();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return rs;
        
    }
    
    
}

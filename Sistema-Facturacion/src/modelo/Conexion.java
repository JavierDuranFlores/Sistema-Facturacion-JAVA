/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author javier
 */
public class Conexion {
    
    private final String DRIVER = "org.postgresql.Driver";
    private final String URL = "jdbc:postgresql://localhost:5432/";
    private final String DB = "sistema_facturacion";
    private final String USER = "postgres";
    private final String PASSWORD = "Duran2001";
    
    private Connection conn;
    public static Conexion instancia;
    
    private Conexion() {
        this.conn = null; 
    }
    
    public Connection conectar() {
        
        
        try {
            
            Class.forName(DRIVER);
            
            this.conn = DriverManager.getConnection(URL.concat(DB), USER, PASSWORD);
            
        } catch (ClassNotFoundException | SQLException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
            
        } 
        return this.conn;
        
    }
    
    public void desconectar() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public synchronized static Conexion getInstacia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }
}

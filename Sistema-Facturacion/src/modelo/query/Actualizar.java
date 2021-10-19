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
public class Actualizar {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;

    public Actualizar() {
        CON = Conexion.getInstacia();
    }

    public void actualizar(String... datosParametro) {
        String datos[] = datosParametro;
        try {

            if (datos[0].equalsIgnoreCase("clientes")) {
                ps = CON.conectar().prepareStatement("SELECT actualizar_cliente(?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(2, datos[1]);
                ps.setString(3, datos[2]);
                ps.setString(4, datos[3]);
                ps.setString(5, datos[4]);
                ps.setString(6, datos[5]);
                ps.setString(7, datos[6]);
                ps.setString(8, datos[7]);
                ps.setString(9, datos[8]);
                ps.execute();

                JOptionPane.showMessageDialog(null, "CLIENTES ACTUALIZADO");
            }

            if (datos[0].equalsIgnoreCase("productos")) {
                ps = CON.conectar().prepareStatement("SELECT actualizar_producto(?, ?, ?, ?);");
                ps.setString(1, datos[1]);
                ps.setString(2, datos[2]);
                ps.setString(3, datos[3]);
                ps.setString(4, datos[4]);
                ps.execute();

                JOptionPane.showMessageDialog(null, "PRODUCTO ACTUALIZADO");
            }

        } catch (SQLException ex) {
            if (datos[0].equalsIgnoreCase("clientes")) {
                JOptionPane.showMessageDialog(null, "CLIENTE NO ACTUALIZADO", "ERROR", 0);
            }
            
            if (datos[0].equalsIgnoreCase("productos")) {
                JOptionPane.showMessageDialog(null, "PRODUCTO NO ACTUALIZADO", "ERROR", 0);
            }
            
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            CON.desconectar();
        }
    }

}

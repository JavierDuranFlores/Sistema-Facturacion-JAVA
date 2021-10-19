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
public class Crear {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;

    public Crear() {
        CON = Conexion.getInstacia();
    }

    public void ingresar(String ... datosParametro) {
        String datos[] = datosParametro;
        try {
            if (datos[0].equals("clientes")) {
                ps = CON.conectar().prepareStatement("SELECT ingresa_cliente(?, ?, ?, ?, ?, ?, ?);");
                ps.setString(1, datos[1]);
                ps.setString(2, datos[2]);
                ps.setString(3, datos[3]);
                ps.setString(4, datos[4]);
                ps.setString(5, datos[5]);
                ps.setString(6, datos[6]);
                ps.setString(7, datos[7]);
                ps.execute();

                JOptionPane.showMessageDialog(null, "CLIENTE INGRESADO");
            } else if(datos[0].equals("productos")) {
                ps = CON.conectar().prepareStatement("SELECT ingresa_producto(?, ?, ?);");
                ps.setString(1, datos[1]);
                ps.setString(2, datos[2]);
                ps.setString(3, datos[3]);
                ps.execute();

                JOptionPane.showMessageDialog(null, "PRODUCTO INGRESADO");
            }
            //TODO: las funciones en postgres
        } catch (SQLException ex) {
            switch (datos[0]) {
                case "clientes":
                    JOptionPane.showMessageDialog(null, "CLIENTE NO INGRESADO", "ERROR", 0);
                    break;
                case "productos":
                    JOptionPane.showMessageDialog(null, "PRODUCTO NO INGRESADO", "ERROR", 0);
                    break;
            }

        } finally {
            CON.desconectar();
        }
    }

}

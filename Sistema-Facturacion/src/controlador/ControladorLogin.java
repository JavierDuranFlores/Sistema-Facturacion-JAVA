/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.dao.UsuarioDAO;
import modelo.entidades.Usuario;
import vista.inicio.Inicio;
import vista.login.LoginVentana;
import vista.inicio.Inicio;

/**
 *
 * @author javier
 */
public class ControladorLogin implements ActionListener {

    private final LoginVentana loginVentana;
    private final UsuarioDAO DATOS;

    ResultSet rs;

    public ControladorLogin(LoginVentana loginVentana) {
        this.loginVentana = loginVentana;
        this.DATOS = new UsuarioDAO();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == loginVentana.getBotonLogin()) {

            Usuario user = new Usuario(loginVentana.getCampoUsername().getText(),
                    String.valueOf(loginVentana.getCampoPassword().getPassword()));

            String resultado = "";

            try {
                
                rs = DATOS.verificar(user);

                while (rs.next()) {
                    resultado = rs.getString(1);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

            Inicio inicio;
            
            if (!resultado.isEmpty()) {
                loginVentana.getVentana().dispose();
                inicio = new Inicio();
                inicio.iniciarComponentes();
            } else {
                JOptionPane.showMessageDialog(null, "CUENTA NO ENCONTRADA", "ERROR", 0);
            }
            
        } else if (ae.getSource() == loginVentana.getMostrarContra()) {
            
            if (loginVentana.getMostrarContra().isSelected()) {
                loginVentana.getCampoPassword().setEchoChar((char) 0);
            } else {
                loginVentana.getCampoPassword().setEchoChar('•');
            }

        }
    }

}

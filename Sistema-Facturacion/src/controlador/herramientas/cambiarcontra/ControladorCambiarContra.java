/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.herramientas.cambiarcontra;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.dao.UsuarioDAO;
import modelo.entidades.Usuario;
import vista.herramientas.cambiarcontra.ContenidoCambiarContrase;

/**
 *
 * @author javier
 */
public class ControladorCambiarContra implements ActionListener {

    private ContenidoCambiarContrase contenidoCambiarContrase;
    private UsuarioDAO usuarioDAO;

    private final Color colorErrrorFormularios = new Color(250, 177, 160);

    public ControladorCambiarContra(ContenidoCambiarContrase contenidoCambiarContrase) {
        this.contenidoCambiarContrase = contenidoCambiarContrase;
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == contenidoCambiarContrase.getGuardarJB()) {
            String contraNuevo = contenidoCambiarContrase.getContraNuevaJTF().getText();

            Usuario usuario = new Usuario();
            Short id = 1;
            usuario.setId(id);
            usuario.setUsuario("Javier");
            usuario.setPassword(contenidoCambiarContrase.getContraAnteriorJTF().getText());
            System.out.println(usuario.getPassword());

            ResultSet rs = usuarioDAO.verificar(usuario);
            String nomUsuario = "";

            try {
                while (rs.next()) {
                    nomUsuario = rs.getString(1);
                }
            } catch (SQLException e) {
            }

            if (contenidoCambiarContrase.getContraNuevaJTF().getText().equalsIgnoreCase(contenidoCambiarContrase.getContraNuevaNJTF().getText())) {
                if (nomUsuario.equals("Javier")) {
                    String idUsuario = String.valueOf(usuarioDAO.filtar("Javier"));
                    usuarioDAO.actualizar(contraNuevo, idUsuario);
                    JOptionPane.showMessageDialog(null, " Contraseña Actualizada");
                    contenidoCambiarContrase.getContraAnteriorJTF().setBackground(Color.white);
                } else {
                    JOptionPane.showMessageDialog(null, " Contraseña Incorrecta", "ERROR", 0);
                    contenidoCambiarContrase.getContraAnteriorJTF().setBackground(colorErrrorFormularios);
                }
                contenidoCambiarContrase.getContraNuevaJTF().setBackground(Color.white);
                contenidoCambiarContrase.getContraNuevaNJTF().setBackground(Color.white);
            } else {
                JOptionPane.showMessageDialog(null, " Contraseña No Coinciden", "ERROR", 0);
                contenidoCambiarContrase.getContraAnteriorJTF().setBackground(Color.white);
                contenidoCambiarContrase.getContraNuevaJTF().setBackground(colorErrrorFormularios);
                contenidoCambiarContrase.getContraNuevaNJTF().setBackground(colorErrrorFormularios);
            }

        } else if (ae.getSource() == contenidoCambiarContrase.getCancelarJB()) {
            contenidoCambiarContrase.getContraAnteriorJTF().setBackground(Color.white);
            contenidoCambiarContrase.getContraNuevaJTF().setBackground(Color.white);
            contenidoCambiarContrase.getContraNuevaNJTF().setBackground(Color.white);
            contenidoCambiarContrase.getContraAnteriorJTF().setText("");
            contenidoCambiarContrase.getContraNuevaJTF().setText("");
            contenidoCambiarContrase.getContraNuevaNJTF().setText("");
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.ResultSet;
import modelo.interfaces.LoginInterface;
import modelo.query.VerificarLogin;
import modelo.entidades.Usuario;
import modelo.query.Actualizar;
import modelo.query.Leer;

/**
 *
 * @author javier
 */
public class UsuarioDAO implements LoginInterface {

    private VerificarLogin vl;
    private Leer leer;
    private Actualizar actualizar;

    public UsuarioDAO() {
        vl = new VerificarLogin();
        leer = new Leer();
        actualizar = new Actualizar();
    }

    @Override
    public ResultSet verificar(Usuario usuario) {

        ResultSet rs = vl.sql(usuario);

        return rs;

    }

    @Override
    public void actualizar(String contra, String idUsuario) {
        actualizar.actualizar("usuarios", contra, idUsuario);
    }

    @Override
    public int filtar(String usuario) {
        return leer.filtarUsuario(usuario);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.ResultSet;
import modelo.Conexion;
import modelo.interfaces.LoginInterface;
import modelo.query.VerificarLogin;
import modelo.entidades.Usuario;

/**
 *
 * @author javier
 */
public class UsuarioDAO implements LoginInterface {

    private VerificarLogin vl;

    public UsuarioDAO() {
        vl = new VerificarLogin();
    }

    @Override
    public ResultSet verificar(Usuario usuario) {

        ResultSet rs = vl.sql(usuario);

        return rs;

    }

}

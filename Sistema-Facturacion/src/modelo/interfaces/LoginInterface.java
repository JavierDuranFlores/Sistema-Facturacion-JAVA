/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.interfaces;

import java.sql.ResultSet;
import modelo.entidades.Usuario;

/**
 *
 * @author javier
 */
public interface LoginInterface {
    public ResultSet verificar(Usuario usuario);
}

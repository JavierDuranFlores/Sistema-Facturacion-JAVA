/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Array;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.entidades.Cliente;
import modelo.interfaces.ICliente;
import modelo.query.ActualizarCliente;
import modelo.query.EliminarCliente;
import modelo.query.InsertarCliente;
import modelo.query.LeerCliente;

/**
 *
 * @author javier
 */
public class ClienteDAO implements ICliente{
    
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    
    
    private final LeerCliente leer;
    private final InsertarCliente insertar;
    private final ActualizarCliente actualizar;
    private final EliminarCliente eliminar;
    
    public ClienteDAO() {
        CON = Conexion.getInstacia();
        
        leer = new LeerCliente();
        insertar = new InsertarCliente();
        actualizar = new ActualizarCliente();
        eliminar = new EliminarCliente();
    }

    @Override
    public List<Cliente> listar() {
        
        List<Cliente> lista = leer.leer();
        
        return lista;
        
    }

    @Override
    public List<Cliente> filtar(String tipo,String busqueda) {
        List<Cliente> lista = leer.filtrar(tipo, busqueda);
        
        return lista;
    }

    @Override
    public List<Cliente> paginar(String limite, String pagina) {
        List<Cliente> lista = leer.paginar(limite, pagina);
        
        return lista;
    }

    public LeerCliente getLeer() {
        return leer;
    }

    @Override
    public void ingresar(String nombre, String apellidp, String apellidom, String edad, String email, String direccion, String telefono) {
        
        insertar.ingresar(nombre, apellidp, apellidom, edad, email, direccion, telefono);
        
    }

    @Override
    public Cliente filtar(int id) {
        Cliente cliente = leer.filtrar(id);
        
        return cliente;
    }

    @Override
    public void actualizar(int id, String nombre, String apellidp, String apellidom, String edad, String email, String direccion, String telefono) {
        actualizar.actualizar(id, nombre, apellidp, apellidom, edad, email, direccion, telefono);
    }

    @Override
    public void eliminar(int id) {
        eliminar.eliminar(id);
    }
    
}

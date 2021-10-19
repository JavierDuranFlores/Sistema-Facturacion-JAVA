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
import modelo.query.Actualizar;
import modelo.query.Eliminar;
import modelo.query.Crear;
import modelo.query.Leer;

/**
 *
 * @author javier
 */
public class ClienteDAO implements ICliente{
    
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    
    
    private final Leer leer;
    private final Crear crear;
    private final Actualizar actualizar;
    private final Eliminar eliminar;
    
    public ClienteDAO() {
        CON = Conexion.getInstacia();
        
        leer = new Leer();
        crear = new Crear();
        actualizar = new Actualizar();
        eliminar = new Eliminar();
    }

    @Override
    public List<Cliente> listar() {
        
        List<Cliente> lista = (List<Cliente>) ((List<?>) leer.leer("cliente"));
        
        return lista;
        
    }

    @Override
    public List<Cliente> filtar(String tipo,String busqueda) {
        List<Cliente> lista = (List<Cliente>) ((List<?>) leer.filtrar(tipo, busqueda, "clientes"));
        
        return lista;
    }

    @Override
    public List<Cliente> paginar(String limite, String pagina) {
        List<Cliente> lista = (List<Cliente>) ((List<?>) leer.paginar(limite, pagina, "clientes"));
        
        return lista;
    }

    public Leer getLeer() {
        return leer;
    }

    @Override
    public void ingresar(String nombre, String apellidp, String apellidom, String edad, String email, String direccion, String telefono) {
        
        crear.ingresar("clientes", nombre, apellidp, apellidom, edad, email, direccion, telefono);
        
    }

    @Override
    public Cliente filtar(int id) {
        Cliente cliente = (Cliente) leer.filtrar(id, "clientes");
        
        return cliente;
    }

    @Override
    public void actualizar(String id, String nombre, String apellidp, String apellidom, String edad, String email, String direccion, String telefono) {
        actualizar.actualizar("clientes", id, nombre, apellidp, apellidom, edad, email, direccion, telefono);
    }

    @Override
    public void eliminar(int id) {
        eliminar.eliminar("clientes", id);
    }
    
}

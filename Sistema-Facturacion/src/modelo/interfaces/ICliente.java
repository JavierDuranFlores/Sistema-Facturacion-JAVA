/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.interfaces;

import java.util.List;
import modelo.entidades.Cliente;

/**
 *
 * @author javier
 */
public interface ICliente {
 
    public List<Cliente> listar();
    
    public List<Cliente> filtar(String tipo,String busqueda);
    
    public Cliente filtar(int id);
    
    public List<Cliente> paginar(String limite, String pagina);
    
    public void ingresar(String nombre, String apellidp, String apellidom, String edad, String email, String direccion, String telefono);
    
    public void actualizar(String id, String nombre, String apellidp, String apellidom, String edad, String email, String direccion, String telefono);

    public void eliminar(int id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.interfaces;

import java.util.List;
import modelo.entidades.Producto;

/**
 *
 * @author javier
 */
public interface IProducto {
    public List<Producto> listar();
    
    public List<Producto> filtar(String tipo,String busqueda);
    
    public Producto filtar(int id);
    
    public List<Producto> filtarFechaCreado(String fi, String ff);
    
    public List<Producto> paginar(String limite, String pagina);
    
    public void ingresar(String nombre, String precio, String stock);
    
    public void actualizar(String id, String nombre, String precio, String stock);

    public void eliminar(int id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import modelo.Conexion;
import modelo.entidades.Producto;
import modelo.interfaces.IProducto;
import modelo.query.Actualizar;
import modelo.query.Crear;
import modelo.query.Eliminar;
import modelo.query.Leer;

/**
 *
 * @author javier
 */
public class ProductoDAO implements IProducto {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private final Leer leer;
    private final Crear crear;
    private final Actualizar actualizar;
    private final Eliminar eliminar;

    
    public ProductoDAO() {
        CON = Conexion.getInstacia();
        leer = new Leer();
        crear = new Crear();
        actualizar = new Actualizar();
        eliminar = new Eliminar();
    }
    
    @Override
    public List<Producto> listar() {
        List<Producto> lista = (List<Producto>) ((List<?>) leer.leer("producto"));
        
        return lista;
    }

    @Override
    public List<Producto> filtar(String tipo, String busqueda) {
        List<Producto> lista = (List<Producto>) ((List<?>)leer.filtrar(tipo, busqueda, "productos"));
        
        return lista;
    }

    @Override
    public Producto filtar(int id) {
        Producto producto = (Producto) leer.filtrar(id, "productos");
        
        return producto;
    }

    @Override
    public List<Producto> paginar(String limite, String pagina) {
        List<Producto> lista = (List<Producto>) ((List<?>) leer.paginar(limite, pagina, "productos"));
        
        return lista;
    }

    @Override
    public void ingresar(String nombre, String precio, String stock) {
        crear.ingresar("productos", nombre, precio, stock);
    }

    @Override
    public void actualizar(String id, String nombre, String precio, String stock) {
        actualizar.actualizar("productos", id, nombre, precio, stock);
    }

    @Override
    public void eliminar(int id) {
        eliminar.eliminar("productos", id);
    }

    public Leer getLeer() {
        return leer;
    }
    
}

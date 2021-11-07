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
import modelo.entidades.FacturaItem;
import modelo.interfaces.IFacturaItem;
import modelo.query.Actualizar;
import modelo.query.Crear;
import modelo.query.Eliminar;
import modelo.query.Leer;

/**
 *
 * @author javier
 */
public class FacturaItemDAO implements IFacturaItem{
    
    
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;

    private final Leer leer;
    private final Crear crear;
    private final Actualizar actualizar;
    private final Eliminar eliminar;
    
    public FacturaItemDAO() {
        CON = Conexion.getInstacia();

        crear = new Crear();
        leer = new Leer();
        actualizar = new Actualizar();
        eliminar = new Eliminar();
    }

    @Override
    public void ingresar(String cantidad, String idFactura, String idProducto) {
        
        crear.ingresar("facturas_items", cantidad, idFactura, idProducto);
        
    }

    @Override
    public List<FacturaItem> listar() {
        return (List<FacturaItem>) ((List<?>) leer.leer("facturas_items"));
    }

    @Override
    public List<FacturaItem> filtar(String idf) {
        return (List<FacturaItem>) ((List<?>) leer.filtrar("", idf, "facturas_items"));
    }

    @Override
    public void actualizar(String cantidad, String idFactura, String idProducto, String idFacturaItem) {
        actualizar.actualizar("facturas_items", cantidad, idFactura, idProducto, idFacturaItem);
    }

    @Override
    public void eliminar(String idFacturaItem) {
        eliminar.eliminar("factura_items", Integer.valueOf(idFacturaItem));
    }
    
}

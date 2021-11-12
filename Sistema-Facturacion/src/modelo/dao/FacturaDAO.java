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
import modelo.entidades.Factura;
import modelo.interfaces.IFactura;
import modelo.query.Actualizar;
import modelo.query.Crear;
import modelo.query.Eliminar;
import modelo.query.Leer;

/**
 *
 * @author javier
 */
public class FacturaDAO implements IFactura {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;

    private final Leer leer;
    private final Crear crear;
    private final Actualizar actualizar;
    private final Eliminar eliminar;

    public FacturaDAO() {
        CON = Conexion.getInstacia();

        leer = new Leer();
        crear = new Crear();
        actualizar = new Actualizar();
        eliminar = new Eliminar();
    }

    @Override
    public List<Factura> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Factura> filtar(String tipo, String busqueda) {
        List<Factura> lista = (List<Factura>) ((List<?>) leer.filtrar(tipo, busqueda, "facturas"));
        return lista;
    }

    @Override
    public Factura filtar(int id) {
        return (Factura) leer.filtrar(id, "facturas");
    }

    @Override
    public List<Factura> paginar(String limite, String pagina) {
        List<Factura> lista = (List<Factura>) ((List<?>) leer.paginar(limite, pagina, "facturas"));

        return lista;
    }

    @Override
    public void ingresar(String nProducto, String total, String idCliente) {
        crear.ingresar("facturas", nProducto, total, idCliente);
    }

    @Override
    public void actualizar(String nProductos, String total, String idCliente, String idFactura) {
        actualizar.actualizar("facturas", nProductos, total, idCliente, idFactura);
    }

    @Override
    public void eliminar(int id) {
        eliminar.eliminar("facturas", id);
    }

    public Leer getLeer() {
        return leer;
    }

    @Override
    public List<Factura> filtarFechaCreado(String fi, String ff) {
        return (List<Factura>) ((List <?>) leer.filtarByDate(fi, ff, "facturas"));
    }

    @Override
    public List<Factura> paginarByCliente(String limite, String pagina, String idCliente) {
        List<Factura> lista = (List<Factura>) ((List<?>) leer.paginarFacturaByCliente(limite, pagina, idCliente));

        return lista;
    }

}

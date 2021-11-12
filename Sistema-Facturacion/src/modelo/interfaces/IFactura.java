/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.interfaces;

import java.util.List;
import modelo.entidades.Factura;

/**
 *
 * @author javier
 */
public interface IFactura {

    public List<Factura> listar();

    public List<Factura> filtar(String tipo, String busqueda);

    public Factura filtar(int id);

    public List<Factura> filtarFechaCreado(String fi, String ff);
    
    public List<Factura> paginarByCliente(String limite, String pagina, String idCliente);

    public List<Factura> paginar(String limite, String pagina);

    public void ingresar(String nProducto, String total, String idCliente);

    public void actualizar(String nProductos, String total, String idCliente, String idFactura);

    public void eliminar(int id);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.interfaces;

import java.util.List;
import modelo.entidades.FacturaItem;

/**
 *
 * @author javier
 */
public interface IFacturaItem {
    
    public List<FacturaItem> listar(); 
    
    public List<FacturaItem> filtar(String idf);
    
    public void ingresar(String cantidad, String idFactura, String idProducto);
    
    public void actualizar(String cantidad, String idFactura, String idProducto, String idFacturaItem);
    
    public void eliminar(String idFacturaItem);
    
}

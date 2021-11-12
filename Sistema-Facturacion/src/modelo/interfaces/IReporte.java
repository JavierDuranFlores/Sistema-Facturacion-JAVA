/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.interfaces;

import java.util.List;
import modelo.entidades.Reporte;

/**
 *
 * @author javier
 */
public interface IReporte {
    
    public List<Reporte> lista(String idCliente, String idFactura);
    
}

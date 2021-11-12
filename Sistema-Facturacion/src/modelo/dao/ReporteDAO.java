/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.entidades.Reporte;
import modelo.interfaces.IReporte;
import modelo.query.Leer;

/**
 *
 * @author javier
 */
public class ReporteDAO implements IReporte{
    
    private final Leer leer;
    
    public ReporteDAO() {
        leer = new Leer();
    }

    @Override
    public List<Reporte> lista(String idCliente, String idFactura) {
        
        return leer.reporte(idCliente, idFactura);
        
    }
    
}

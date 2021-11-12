/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.consultas.productos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.consultas.productos.ContenidoConsultaProducto;

/**
 *
 * @author javier
 */
public class ControladorConsultaProductos implements ActionListener{
    
    private ContenidoConsultaProducto contenidoConsultaProducto;

    public ControladorConsultaProductos(ContenidoConsultaProducto contenidoConsultaProducto) {
        this.contenidoConsultaProducto = contenidoConsultaProducto;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == contenidoConsultaProducto.getBuscarJB()) {
            String fi =  String.valueOf(contenidoConsultaProducto.getCalendarI().getDate());
            String ff = String.valueOf(contenidoConsultaProducto.getCalendarF().getDate());
            contenidoConsultaProducto.getTablaProductosConsulta().limpiarTabla();
            contenidoConsultaProducto.getTablaProductosConsulta().colocarFilas(fi, ff);
       }
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.consultas.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import vista.consultas.clientes.ContenidoConsultaCliente;
import vista.consultas.clientes.ContenidoConsultaCliente;
import vista.consultas.productos.ContenidoConsultaProducto;

/**
 *
 * @author javier
 */
public class ControladorConsultaClientes implements ActionListener{
    
    private ContenidoConsultaCliente contenidoConsultaCliente;

    public ControladorConsultaClientes(ContenidoConsultaCliente contenidoConsultaCliente) {
        this.contenidoConsultaCliente = contenidoConsultaCliente;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == contenidoConsultaCliente.getBuscarJB()) {
            String fi =  String.valueOf(contenidoConsultaCliente.getCalendarI().getDate());
            String ff = String.valueOf(contenidoConsultaCliente.getCalendarF().getDate());
            contenidoConsultaCliente.getTablaClientesConsulta().limpiarTabla();
            contenidoConsultaCliente.getTablaClientesConsulta().colocarFilas(fi, ff);
       }
    }
    
}

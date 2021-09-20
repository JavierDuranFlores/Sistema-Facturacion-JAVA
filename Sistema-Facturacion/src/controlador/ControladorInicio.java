/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.crud.cliente.VentanaMantenimientoCliente;
import vista.inicio.Inicio;

/**
 *
 * @author javier
 */
public class ControladorInicio implements ActionListener{

    private final Inicio inicio;
    private final VentanaMantenimientoCliente ventanaMantenimientoCliente;
    
    public ControladorInicio(Inicio inicio) {
        this.inicio = inicio;
        ventanaMantenimientoCliente = new VentanaMantenimientoCliente();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == inicio.getClienteMantenimiento()) {
            inicio.addContenedor(ventanaMantenimientoCliente.inicializadorComponentes());
        }
        
    }
    
}

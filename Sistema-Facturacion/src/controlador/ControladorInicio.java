/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.crud.cliente.PrincipalMantenimientoCliente;
import vista.crud.factura.PrincipalMantenimientoFactura;
import vista.crud.producto.PrincipalMantenimientoProducto;
import vista.inicio.Inicio;

/**
 *
 * @author javier
 */
public class ControladorInicio implements ActionListener{

    private final Inicio inicio;
    private final PrincipalMantenimientoCliente principalMantenimientoCliente;
    private final PrincipalMantenimientoProducto principalMantenimientoProducto;
    private final PrincipalMantenimientoFactura principalMantenimientoFactura;
    
    public ControladorInicio(Inicio inicio) {
        this.inicio = inicio;
        principalMantenimientoCliente = new PrincipalMantenimientoCliente();
        principalMantenimientoProducto = new PrincipalMantenimientoProducto();
        principalMantenimientoFactura = PrincipalMantenimientoFactura.getInstance();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == inicio.getClienteMantenimiento()) {
            inicio.addContenedor(principalMantenimientoCliente);
        } else if (ae.getSource() == inicio.getProductoMantenimiento()) {
            inicio.addContenedor(principalMantenimientoProducto);
        } else if (ae.getSource() == inicio.getFacturaMantenimiento()) {
            inicio.addContenedor(principalMantenimientoFactura);
        }
        
    }
    
}

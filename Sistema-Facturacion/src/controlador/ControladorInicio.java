/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.reporte.Reporte;
import vista.consultas.clientes.PrincipalConsultaCliente;
import vista.consultas.facturas.PrincipalConsultaFacturaFecha;
import vista.consultas.facturas.PrincipalConsultaFacturaId;
import vista.consultas.productos.PrincipalConsultaProducto;
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
    
    private final PrincipalConsultaCliente principalConsultaCliente;
    private final PrincipalConsultaProducto principalConsultaProducto;
    private final PrincipalConsultaFacturaFecha principalConsultaFacturaFecha;
    private final PrincipalConsultaFacturaId principalConsultaFacturaId;
    
    private Reporte reporte;
    
    public ControladorInicio(Inicio inicio) {
        this.inicio = inicio;
        principalMantenimientoCliente = new PrincipalMantenimientoCliente();
        principalMantenimientoProducto = new PrincipalMantenimientoProducto();
        principalMantenimientoFactura = PrincipalMantenimientoFactura.getInstance();
        
        principalConsultaCliente = new PrincipalConsultaCliente();
        principalConsultaProducto = new PrincipalConsultaProducto();
        principalConsultaFacturaFecha = new PrincipalConsultaFacturaFecha();
        principalConsultaFacturaId = new PrincipalConsultaFacturaId();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == inicio.getClienteMantenimiento()) {
            inicio.addContenedor(principalMantenimientoCliente);
        } else if (ae.getSource() == inicio.getProductoMantenimiento()) {
            inicio.addContenedor(principalMantenimientoProducto);
        } else if (ae.getSource() == inicio.getFacturaMantenimiento()) {
            inicio.addContenedor(principalMantenimientoFactura);
        } else if (ae.getSource() == inicio.getClienteConsultas()) {
            inicio.addContenedor(principalConsultaCliente);
        } else if (ae.getSource() == inicio.getProductoConsultas()) {
            inicio.addContenedor(principalConsultaProducto);
        } else if (ae.getSource() == inicio.getFacturaFecha()) {
            inicio.addContenedor(principalConsultaFacturaFecha);
        } else if (ae.getSource() == inicio.getFacturaID()) {
            inicio.addContenedor(principalConsultaFacturaId);
        } else if (ae.getSource() == inicio.getReporte()) {
            reporte = new Reporte();
        }
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.listado.encabezado;

import controlador.mantenimiento.ControladorMantenimientoFactura;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author javier
 */
public class HeaderListadoFactura extends JPanel{
    
    private ControladorMantenimientoFactura controladorMantenimientoFactura;
    
    private Object arregloTipoBusqueda [] = {"id_factura","n_productos", "total", "id_cliente", "fecha"};
    private JComboBox tipoBusqueda = new JComboBox(arregloTipoBusqueda);
    public JTextField campo = new JTextField(15);
    public final JButton buscar = new JButton("Buscar");
    
    
    public HeaderListadoFactura(Object clase) {
        setLayout(new FlowLayout());
        add(tipoBusqueda);
        add(campo);
        add(buscar);
        
        buscar.addActionListener(controladorMantenimientoFactura);
    }
    
    public JTextField getCampo() {
        return campo;
    }

    public JButton getBuscar() {
        return buscar;
    }

    public JComboBox getTipoBusqueda() {
        return tipoBusqueda;
    }
}

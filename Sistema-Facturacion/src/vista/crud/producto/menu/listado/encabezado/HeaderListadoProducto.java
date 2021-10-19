/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.producto.menu.listado.encabezado;

import controlador.mantenimiento.ControladorMantenimientoCliente;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author javier
 */
public class HeaderListadoProducto extends JPanel {
    
    private ControladorMantenimientoCliente controladorMantenimiento;
    
    private Object arregloTipoBusqueda [] = {"id_producto","nombre", "precio", "stock", "fecha"};
    private JComboBox tipoBusqueda = new JComboBox(arregloTipoBusqueda);
    public JTextField campo = new JTextField(15);
    public final JButton buscar = new JButton("Buscar");
    
    
    public HeaderListadoProducto() {
        setLayout(new FlowLayout());
        add(tipoBusqueda);
        add(campo);
        add(buscar);
        
        buscar.addActionListener(controladorMantenimiento);
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

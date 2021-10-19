/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.cliente.menu.listado.encabezado;

import controlador.ControladorLogin;
import controlador.mantenimiento.ControladorMantenimientoCliente;
import java.awt.FlowLayout;
import java.util.stream.Stream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import vista.crud.cliente.menu.listado.contenido.ContenidoTablaCliente;
import vista.inicio.Inicio;

/**
 *
 * @author javier
 */
public class HeaderListadoCliente extends JPanel{
    
    private ControladorMantenimientoCliente controladorMantenimiento;
    
    private Object arregloTipoBusqueda [] = {"id_cliente","nombre", "apellidop", "apellidom", "edad", "email", "direccion", "telefono", "fecha"};
    private JComboBox tipoBusqueda = new JComboBox(arregloTipoBusqueda);
    public JTextField campo = new JTextField(15);
    public final JButton buscar = new JButton("Buscar");
    
    
    public HeaderListadoCliente(Object clase) {
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

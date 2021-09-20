/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.cliente.menu.listado.encabezado;

import controlador.ControladorLogin;
import controlador.mantenimientocliente.ControladorMantenimientoCliente;
import java.awt.FlowLayout;
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
    
    private final JLabel nombre = new JLabel("Nombre");
    private Object arregloTipoBusqueda [] = {"id_cliente","nombre", "apellidop", "apellidom", "edad", "email", "direccion", "telefono", "fecha"};
    private JComboBox tipoBusqueda = new JComboBox(arregloTipoBusqueda);
    public JTextField campo = new JTextField(15);
    public final JButton buscar = new JButton("Buscar");
    
    public final ContenidoTablaCliente contenidoTablaCliente;
    
    public HeaderListadoCliente() {
        this.contenidoTablaCliente = new ContenidoTablaCliente();
        setLayout(new FlowLayout());
        add(tipoBusqueda);
        add(campo);
        add(buscar);
        
        //inicializarControlado();
        
        buscar.addActionListener(controladorMantenimiento);
        //System.out.println("Clase header: "+this.contenidoTablaCliente);
    }
    
   /* private void inicializarControlado() {
        controladorMantenimiento = new ControladorMantenimientoCliente(this);
        System.out.println("Controlador Matenimiento desde Header" + controladorMantenimiento.headerListadoCliente.contenidoTablaCliente);
    }*/

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

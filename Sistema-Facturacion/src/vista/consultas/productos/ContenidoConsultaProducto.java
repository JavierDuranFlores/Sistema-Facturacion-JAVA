/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.consultas.productos;

import com.toedter.calendar.JDateChooser;
import controlador.consultas.clientes.ControladorConsultaClientes;
import controlador.consultas.productos.ControladorConsultaProductos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import vista.consultas.clientes.TablaClientesConsulta;

/**
 *
 * @author javier
 */
public class ContenidoConsultaProducto extends JPanel{
    
    private JPanel buscadorJP, tablaJP, contadorJP;
    
    private JPanel fip, ffp, buscarp;
    
    private JLabel fi, ff;
    
    private JDateChooser calendarI, calendarF;
    
    private JButton buscarJB;
    
    private TablaProductoConsulta tablaProductoConsulta; 
    
    private ControladorConsultaProductos controladorConsultaProductos;
    
    public ContenidoConsultaProducto() {
        controladorConsultaProductos = new ControladorConsultaProductos(this);
        setLayout(new BorderLayout());
        colocarPanelBuscador();
        colocarComponentesBuscardor();
        colocarPanelTabla();
        colocarTabla();
        colocarPanelContador();
    }
    
    
    private void colocarPanelBuscador() {
        buscadorJP = new JPanel(new GridLayout(1, 4));
        buscadorJP.setBorder(new EtchedBorder());
        buscadorJP.setPreferredSize(new Dimension(800, 70));
        add(buscadorJP, BorderLayout.NORTH);
    }
    
    private void colocarComponentesBuscardor() {
        /*gbl = new GridBagLayout();
        constraints = new GridBagConstraints();
        
        buscadorJP.setLayout(gbl);*/
        
        fip = new JPanel(new FlowLayout());
        fi = new JLabel("Fecha Inicio");
        calendarI = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
        fip.add(fi);
        fip.add(calendarI);
        buscadorJP.add(fip);
        
        ffp = new JPanel(new FlowLayout());
        ff = new JLabel("Fecha Fin");
        calendarF = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
        ffp.add(ff);
        ffp.add(calendarF);
        buscadorJP.add(ffp);
        
        buscarp = new JPanel(new FlowLayout());
        buscarJB = new JButton("Buscar");
        buscarp.add(buscarJB);
        buscadorJP.add(buscarp);
        
        buscadorJP.add(new JPanel());
        
        buscarJB.addActionListener(controladorConsultaProductos);
        
    }
    
    private void colocarPanelTabla() {
        tablaJP = new JPanel(new BorderLayout());
        tablaJP.setBackground(Color.red);
        tablaJP.setPreferredSize(new Dimension(800, 460));
        add(tablaJP, BorderLayout.CENTER);
    }
    
    private void colocarTabla() {
        tablaProductoConsulta = new TablaProductoConsulta();
        tablaJP.add(tablaProductoConsulta, BorderLayout.CENTER);
        tablaJP.add(new JScrollPane(tablaProductoConsulta));
    }
    
    private void colocarPanelContador() {
        contadorJP = new JPanel(new BorderLayout());
        contadorJP.setBorder(new EtchedBorder());
        contadorJP.setPreferredSize(new Dimension(800, 70));
        add(contadorJP, BorderLayout.SOUTH);
    }
    

    public JDateChooser getCalendarI() {
        return calendarI;
    }

    public JDateChooser getCalendarF() {
        return calendarF;
    }

    public JButton getBuscarJB() {
        return buscarJB;
    }

    public TablaProductoConsulta getTablaProductosConsulta() {
        return tablaProductoConsulta;
    }
    
}

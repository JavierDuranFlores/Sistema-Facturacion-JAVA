/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.consultas.clientes;

import vista.consultas.clientes.TablaClientesConsulta;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import controlador.consultas.clientes.ControladorConsultaClientes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author javier
 */
public class ContenidoConsultaCliente extends JPanel{
    
    private JPanel buscadorJP, tablaJP, contadorJP;
    
    private JPanel fip, ffp, buscarp;
    
    private JLabel fi, ff;
    
    private JDateChooser calendarI, calendarF;
    
    private JButton buscarJB;
    
    private TablaClientesConsulta tablaClientesConsulta; 
    
    private ControladorConsultaClientes controladorConsultaClientes;
    
    public ContenidoConsultaCliente() {
        controladorConsultaClientes = new ControladorConsultaClientes(this);
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
        
        buscarJB.addActionListener(controladorConsultaClientes);
        
    }
    
    private void colocarPanelTabla() {
        tablaJP = new JPanel(new BorderLayout());
        tablaJP.setBackground(Color.red);
        tablaJP.setPreferredSize(new Dimension(800, 460));
        add(tablaJP, BorderLayout.CENTER);
    }
    
    private void colocarTabla() {
        tablaClientesConsulta = new TablaClientesConsulta();
        tablaJP.add(tablaClientesConsulta, BorderLayout.CENTER);
        tablaJP.add(new JScrollPane(tablaClientesConsulta));
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

    public TablaClientesConsulta getTablaClientesConsulta() {
        return tablaClientesConsulta;
    }
    
}

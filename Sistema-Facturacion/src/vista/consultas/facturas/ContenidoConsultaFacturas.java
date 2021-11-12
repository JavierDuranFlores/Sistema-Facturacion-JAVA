/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.consultas.facturas;

import com.toedter.calendar.JDateChooser;
import controlador.consultas.Facturas.ControladorConsultaFacturas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author javier
 */
public class ContenidoConsultaFacturas extends JPanel{
    
    private JPanel buscadorJP, tablaJP, contadorJP;
    
    private JPanel fip, ffp, buscarp;
    
    private JLabel fi, ff;
    
    private JDateChooser calendarI, calendarF;
    
    private JButton buscarJB;
    
    private TablaFacturaConsulta tablaFacturaConsulta; 
    
    private JLabel idJL;
    
    private JTextField idJTF;
    
    private JPanel idJP;
    
    private ControladorConsultaFacturas controladorConsultaFacturas;
    
    private String tipoBusquda;
    
    public ContenidoConsultaFacturas(String tipoBusquda) {
        this.tipoBusquda = tipoBusquda;
        controladorConsultaFacturas = new ControladorConsultaFacturas(this);
        setLayout(new BorderLayout());
        colocarPanelBuscador();
        if (tipoBusquda.equals("fecha"))
            colocarComponentesBuscardorFecha();
        else 
            colocarComponentesBuscardorId();
                
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
    
    private void colocarComponentesBuscardorFecha() {
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
        
        buscarJB.addActionListener(controladorConsultaFacturas);
        
    }
    
    private void colocarComponentesBuscardorId() {
        idJP = new JPanel();
       
        idJL = new JLabel("Id");
        idJP.add(idJL);
        idJTF = new JTextField(10);
        idJP.add(idJTF);
        buscarJB = new JButton("Buscar");
        idJP.add(buscarJB);
        buscadorJP.add(idJP);
        
        buscadorJP.add(new JPanel());
        
        buscarJB.addActionListener(controladorConsultaFacturas);
        
        
    }
    
    private void colocarPanelTabla() {
        tablaJP = new JPanel(new BorderLayout());
        tablaJP.setBackground(Color.BLACK.red);
        tablaJP.setPreferredSize(new Dimension(800, 460));
        add(tablaJP, BorderLayout.CENTER);
    }
    
    private void colocarTabla() {
        tablaFacturaConsulta = new TablaFacturaConsulta();
        tablaJP.add(tablaFacturaConsulta, BorderLayout.CENTER);
        tablaJP.add(new JScrollPane(tablaFacturaConsulta));
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

    public TablaFacturaConsulta getTablaFacturaConsulta() {
        return tablaFacturaConsulta;
    }

    public String getTipoBusquda() {
        return tipoBusquda;
    }

    public JTextField getIdJTF() {
        return idJTF;
    }

    
}

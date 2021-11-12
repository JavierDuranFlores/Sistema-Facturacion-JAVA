/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.consultas.facturas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author javier
 */
public class PrincipalConsultaFacturaFecha extends JPanel{

    private JPanel panelCenter;
    private ContenidoConsultaFacturas contenidoConsultaFacturas;
    
    public PrincipalConsultaFacturaFecha() {
        panelCenter();
        contenido();
    }
    
    private void panelCenter() {
        panelCenter = new JPanel(new BorderLayout());
        panelCenter.setBackground(new Color(223, 230, 233));
        panelCenter.setPreferredSize(new Dimension(800, 600));
        add(panelCenter);
    }
    
    private void contenido() {
        contenidoConsultaFacturas = new ContenidoConsultaFacturas("fecha");
        panelCenter.add(contenidoConsultaFacturas, BorderLayout.CENTER);
    }
}

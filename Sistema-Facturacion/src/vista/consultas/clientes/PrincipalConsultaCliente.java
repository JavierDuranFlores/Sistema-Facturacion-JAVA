/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.consultas.clientes;

import vista.consultas.clientes.ContenidoConsultaCliente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author javier
 */
public class PrincipalConsultaCliente extends JPanel{

    private JPanel panelCenter;
    private ContenidoConsultaCliente contenidoConsultaCliente;
    
    public PrincipalConsultaCliente() {
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
        contenidoConsultaCliente = new ContenidoConsultaCliente();
        panelCenter.add(contenidoConsultaCliente, BorderLayout.CENTER);
    }
    
}

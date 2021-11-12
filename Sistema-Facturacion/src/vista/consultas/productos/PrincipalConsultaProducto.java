/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.consultas.productos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author javier
 */
public class PrincipalConsultaProducto extends JPanel{

    private JPanel panelCenter;
    private ContenidoConsultaProducto contenidoConsultaProducto;
    
    public PrincipalConsultaProducto() {
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
        contenidoConsultaProducto = new ContenidoConsultaProducto();
        panelCenter.add(contenidoConsultaProducto, BorderLayout.CENTER);
    }
}

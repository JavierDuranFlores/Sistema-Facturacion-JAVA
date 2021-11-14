/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.herramientas.cambiarcontra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author javier
 */
public class PrincipalCambiarContrase extends JPanel {
    
    private JPanel panelCenter;
    private ContenidoCambiarContrase contenidoCambiarContrase;
    
    public PrincipalCambiarContrase() {
        //setLayout(new GridLayout(1, 2));
        panelCenter();
        contenedio();
    }
    
    private void panelCenter() {
        panelCenter = new JPanel(new GridLayout(1, 2));
        panelCenter.setBackground(new Color(223, 230, 233));
        panelCenter.setPreferredSize(new Dimension(800, 600));
        add(panelCenter);
    }
    
    private void contenedio() {
        contenidoCambiarContrase = new ContenidoCambiarContrase();
        panelCenter.add(contenidoCambiarContrase);
    }

    public ContenidoCambiarContrase getContenidoCambiarContrase() {
        return contenidoCambiarContrase;
    }
    
}

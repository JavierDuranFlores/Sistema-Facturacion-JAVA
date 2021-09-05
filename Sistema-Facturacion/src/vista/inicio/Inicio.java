/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.inicio;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author javier
 */
public class Inicio {
    
    private JFrame ventanaInicio;
    
    private JPanel panelIncio;
    
    private JMenuBar barraMenu;
    
    public void iniciarComponentes() {
        ventana();
        panel();
        ventanaInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaInicio.setVisible(true);
    }
    
    private void ventana() {
        ventanaInicio = new JFrame();
        ventanaInicio.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
                              Toolkit.getDefaultToolkit().getScreenSize().height);
    }
    
    private void panel() {
        panelIncio = new JPanel(new BorderLayout());
        ventanaInicio.add(panelIncio);
    }
    
}

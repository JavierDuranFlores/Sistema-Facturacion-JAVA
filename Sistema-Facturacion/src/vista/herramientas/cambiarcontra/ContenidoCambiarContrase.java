/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.herramientas.cambiarcontra;

import controlador.ControladorLogin;
import controlador.herramientas.cambiarcontra.ControladorCambiarContra;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author javier
 */
public class ContenidoCambiarContrase extends JPanel {
    
    private JPanel arriba;
    
    private JLabel idUsuarioJL;
    private JTextField idUsuarioJTF;
    private JButton idUsuarioJB;
    private JPanel idUsuarioJP;
    
    private JPanel resultadoBusqueda;
    private JLabel nombreUsuario;

    private JPanel cambioContra;
    
    private JPanel contraAnteriorJP;
    private JLabel contraAnteriorJL;
    private JTextField contraAnteriorJTF;
    
    private JPanel contraNuevaJP;
    private JLabel contraNuevaJL;
    private JTextField contraNuevaJTF;
    
    private JPanel contraNuevaNJP;
    private JLabel contraNuevaNJL;
    private JTextField contraNuevaNJTF;
    
    private JPanel guardarJP;
    private JButton guardarJB, cancelarJB;
    
    private ControladorCambiarContra controladorCambiarContra;
    
    public ContenidoCambiarContrase() {
        controladorCambiarContra = new ControladorCambiarContra(this);
        setLayout(new BorderLayout());
        
        panelPrincipal();
        //colocarPanelBusqueda();
        //colocarPanelResultadoBusqueda();
        colocarCambioContra();
        colocarBotones();
    }
    
    private void panelPrincipal() {
        arriba = new JPanel(new GridLayout(4, 1));
        arriba.setBackground(Color.yellow);
        arriba.setPreferredSize(new Dimension(800, 300));
        add(arriba, BorderLayout.NORTH);
    }
    
    private void colocarPanelBusqueda() {
        idUsuarioJP = new JPanel(new BorderLayout());
        idUsuarioJL = new JLabel("ID:");
        idUsuarioJTF = new JTextField(3);
        idUsuarioJB = new JButton("Buscar");
        idUsuarioJP.setBackground(Color.red);
        
        JPanel aux = new JPanel();
        
        aux.add(idUsuarioJL);
        aux.add(idUsuarioJTF);
        aux.add(idUsuarioJB);
        
        idUsuarioJP.add(aux, BorderLayout.WEST);
        
        arriba.add(idUsuarioJP);
    }
    
    private void colocarPanelResultadoBusqueda() {
        resultadoBusqueda = new JPanel(new BorderLayout());
        nombreUsuario = new JLabel("s");
        
        JPanel aux = new JPanel();
        
        aux.add(nombreUsuario);
        
        resultadoBusqueda.add(aux, BorderLayout.WEST);
        
        arriba.add(resultadoBusqueda);
        
    }
    
    private void colocarCambioContra() {
        cambioContra = new JPanel(new GridLayout(2, 1));
        contraAnteriorJP = new JPanel(new BorderLayout());
        contraAnteriorJL = new JLabel("Contraseña Anterior");
        contraAnteriorJTF = new JTextField(10);
        
        JPanel aux1 = new JPanel();
        
        aux1.add(contraAnteriorJL);
        aux1.add(contraAnteriorJTF);
        
        contraAnteriorJP.add(aux1, BorderLayout.WEST);
        
        contraNuevaJP = new JPanel(new BorderLayout());
        contraNuevaJL = new JLabel("Contraseña Nueva");
        contraNuevaJTF = new JTextField(10);
        
        JPanel aux2 = new JPanel();
        
        aux2.add(contraNuevaJL);
        aux2.add(contraNuevaJTF);
        
        contraNuevaJP.add(aux2, BorderLayout.WEST);
        
        contraNuevaNJP = new JPanel(new BorderLayout());
        contraNuevaNJL = new JLabel("Contraseña Nueva");
        contraNuevaNJTF = new JTextField(10);
        
        JPanel aux3 = new JPanel();
        
        aux3.add(contraNuevaNJL);
        aux3.add(contraNuevaNJTF);
        
        contraNuevaNJP.add(aux3, BorderLayout.WEST);
        
        arriba.add(contraAnteriorJP);
        arriba.add(contraNuevaJP);
        arriba.add(contraNuevaNJP);
    }
    
    private void colocarBotones() {
        guardarJP = new JPanel(new BorderLayout());
        guardarJB = new JButton("Guardar");
        cancelarJB = new JButton("Cancelar");
        
        
        JPanel aux = new JPanel();
        
        aux.add(guardarJB);
        aux.add(cancelarJB);
        guardarJP.add(aux, BorderLayout.WEST);
        
        arriba.add(guardarJP);
        
        guardarJB.addActionListener(controladorCambiarContra);
        cancelarJB.addActionListener(controladorCambiarContra);
    }

    public JButton getGuardarJB() {
        return guardarJB;
    }

    public JButton getCancelarJB() {
        return cancelarJB;
    }

    public JTextField getContraAnteriorJTF() {
        return contraAnteriorJTF;
    }

    public JTextField getContraNuevaJTF() {
        return contraNuevaJTF;
    }

    public JTextField getContraNuevaNJTF() {
        return contraNuevaNJTF;
    }
 
    
    
}

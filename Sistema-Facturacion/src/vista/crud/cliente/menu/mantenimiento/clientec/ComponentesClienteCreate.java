/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.cliente.menu.mantenimiento.clientec;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author javier
 */
public class ComponentesClienteCreate extends JPanel{
    
    private JLabel nombreJL,
                   apellidoPJL,
                   apellidoMJL,
                   edadJL,
                   emailJL,
                   direccionJL,
                   telefonoJL;
    
    private JLabel mensaje;
    
    private JTextField nombreJTF,
                       apellidoPJTF,
                       apellidoMJTF,
                       edadJTF,
                       emailJTF,
                       direccionJTF,
                       telefonoJTF;
    
    private JPanel nombreJP,
                   apellidoPJP,
                   apellidoMJP,
                   edadJP,
                   emailJP,
                   direccionJP,
                   telefonoJP,
                   botones;
    
    private JButton guardarJB,
                    cancelarJB;
    
    private GridBagConstraints constraints;
    
    int x = 0;
    int y = 0;
    int w = 2;
    int h = 1;
    
    
    public ComponentesClienteCreate() {
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        colocarFormNombre();
        colocarFormApellidoP();
        colocarFormApellidoM();
        colocarFormEdad();
        colocarFormEmail();
        colocarFormDireccion();
        colocarFormTelefono();
        colocarMensaje();
        colocarBotones();
    }
    
    private void colocarFormNombre() {
        nombreJL = new JLabel("Nombre(*)");
        nombreJTF = new JTextField(12);
        
        nombreJP = new JPanel(new GridLayout(1, 2));
        
        nombreJP.add(nombreJL);
        nombreJP.add(nombreJTF);
        
        add(nombreJP, colocarContrains());
    }
    
    private void colocarFormApellidoP() {
        apellidoPJL = new JLabel("Apellido Paterno(*)");
        apellidoPJTF = new JTextField(12);
        
        apellidoPJP = new JPanel(new GridLayout(1, 2));
        
        apellidoPJP.add(apellidoPJL);
        apellidoPJP.add(apellidoPJTF);
        
        add(apellidoPJP, colocarContrains());
    }
    
    private void colocarFormApellidoM() {
        apellidoMJL = new JLabel("Apellido Paterno(*)");
        apellidoMJTF = new JTextField(12);
        
        apellidoMJP = new JPanel(new GridLayout(1, 2));
        
        apellidoMJP.add(apellidoMJL);
        apellidoMJP.add(apellidoMJTF);
        
        add(apellidoMJP, colocarContrains());
    }
    
    private void colocarFormEdad() {
        edadJL = new JLabel("Edad");
        edadJTF = new JTextField(12);
        
        edadJP = new JPanel(new GridLayout(1, 2));
        
        edadJP.add(edadJL);
        edadJP.add(edadJTF);
        
        add(edadJP, colocarContrains());
    }
    
    private void colocarFormEmail() {
        emailJL = new JLabel("Correo(*)");
        emailJTF = new JTextField(12);
        
        emailJP = new JPanel(new GridLayout(1, 2));
        
        emailJP.add(emailJL);
        emailJP.add(emailJTF);
        
        add(emailJP, colocarContrains());
    }
    
    private void colocarFormDireccion() {
        direccionJL = new JLabel("Direccion");
        direccionJTF = new JTextField(12);
        
        direccionJP = new JPanel(new GridLayout(1, 2));
        
        direccionJP.add(direccionJL);
        direccionJP.add(direccionJTF);
        
        add(direccionJP, colocarContrains());
    }
    
    private void colocarFormTelefono() {
        telefonoJL = new JLabel("Telefono(*)");
        telefonoJTF = new JTextField(12);
        
        telefonoJP = new JPanel(new GridLayout(1, 2));
        
        telefonoJP.add(telefonoJL);
        telefonoJP.add(telefonoJTF);
        
        add(telefonoJP, colocarContrains());
    }
    
    private void colocarBotones() {
        botones = new JPanel();
        guardarJB = new JButton("Guadar");
        cancelarJB = new JButton("Cancelar");
        
        botones.add(guardarJB);
        botones.add(cancelarJB);
        
        add(botones, colocarContrains());
    }
    
    private void colocarMensaje() {
        mensaje = new JLabel("(*) Indica que es un campo obligatorio");
        
        add(mensaje, colocarContrains());
    }
    
    private GridBagConstraints colocarContrains() {
    
        constraints.gridx = x; // El área de texto empieza en la columna cero.
        constraints.gridy = y; // El área de texto empieza en la fila cero
        constraints.gridwidth = w; // El área de texto ocupa dos columnas.
        constraints.gridheight = h; // El área de texto ocupa 2 filas.
        constraints.insets.top = 25;
        y++;
        
        return constraints;
        
    }
    
    public JButton getGuardarJB() {
        return guardarJB;
    }

    public JButton getCancelarJB() {
        return cancelarJB;
    }

    public JTextField getNombreJTF() {
        return nombreJTF;
    }

    public JTextField getApellidoPJTF() {
        return apellidoPJTF;
    }

    public JTextField getApellidoMJTF() {
        return apellidoMJTF;
    }

    public JTextField getEdadJTF() {
        return edadJTF;
    }

    public JTextField getEmailJTF() {
        return emailJTF;
    }

    public JTextField getDireccionJTF() {
        return direccionJTF;
    }

    public JTextField getTelefonoJTF() {
        return telefonoJTF;
    }
    
    
    
    
}

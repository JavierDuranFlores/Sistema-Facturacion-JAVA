/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.cliente.menu.mantenimiento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author javier
 */
public class ComponentesMantenimientoClienteErrores extends JPanel{
    
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    
    private JPanel panelErrorres;
    
    private JPanel panelErrorNombre,
                   panelErrorApellidoP,
                   panelErrorApellidoM,
                   panelErrorEdad,
                   panelErrorEmail,
                   panelErrorTelefono;
    
    private JLabel errorNombreJL,
                   errorApellidoMJL,
                   errorApellidoPJL,
                   errorEdadJL,
                   errorEmailJL,
                   errorTelefonoJL;
    
    public ComponentesMantenimientoClienteErrores(int y1, int y2, int y3, int y4, int y5, int y6) {
        colocarPanel();
        setLayout(new BorderLayout());
        setBackground(Color.yellow);
        setPreferredSize(new Dimension(400,590));
        
        panelError();
        
        panelErrorNombre(y1);
        panelErrorApellidoP(y2);
        panelErrorApellidoM(y3);
        panelEdad(y4);
        panelEmail(y5);
        panelTelefono(y6);
    }
    
    private void colocarPanel() {
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
    }
    
    private void panelError() {
        panelErrorres = new JPanel(new FlowLayout());
        add(panelErrorres, BorderLayout.CENTER);
    }
    
    private void panelErrorNombre(int y) {
        panelErrorNombre = new JPanel(new BorderLayout());
        panelErrorNombre.setPreferredSize(new Dimension(401, y));
        errorNombreJL = new JLabel();
        errorNombreJL.setForeground(Color.RED);
        errorNombreJL.setFont(new Font("Tahoma", 1, 10));
        
        panelErrorNombre.add(errorNombreJL, BorderLayout.SOUTH);
        
        panelErrorres.add(panelErrorNombre, gbc);
    }
    
    private void panelErrorApellidoP(int y) {
        panelErrorApellidoP = new JPanel(new BorderLayout());
        panelErrorApellidoP.setPreferredSize(new Dimension(401, y));
        errorApellidoPJL = new JLabel();
        errorApellidoPJL.setForeground(Color.RED);
        errorApellidoPJL.setFont(new Font("Tahoma", 1, 10));
        
        panelErrorApellidoP.add(errorApellidoPJL, BorderLayout.SOUTH);
        
        panelErrorres.add(panelErrorApellidoP);
    }
    
    private void panelErrorApellidoM(int y) {
        panelErrorApellidoM = new JPanel(new BorderLayout());
        panelErrorApellidoM.setPreferredSize(new Dimension(401, y));
        errorApellidoMJL = new JLabel();
        errorApellidoMJL.setForeground(Color.RED);
        errorApellidoMJL.setFont(new Font("Tahoma", 1, 10));
        
        panelErrorApellidoM.add(errorApellidoMJL, BorderLayout.SOUTH);
        
        panelErrorres.add(panelErrorApellidoM, gbc);
    }
    
    private void panelEdad(int y) {
        panelErrorEdad = new JPanel(new BorderLayout());
        panelErrorEdad.setPreferredSize(new Dimension(401, y));
        errorEdadJL = new JLabel();
        errorEdadJL.setForeground(Color.RED);
        errorEdadJL.setFont(new Font("Tahoma", 1, 10));
        
        panelErrorEdad.add(errorEdadJL, BorderLayout.SOUTH);
        
        panelErrorres.add(panelErrorEdad);
    }
    
    private void panelEmail(int y) {
        panelErrorEmail = new JPanel(new BorderLayout());
        panelErrorEmail.setPreferredSize(new Dimension(401, y));
        errorEmailJL = new JLabel();
        errorEmailJL.setForeground(Color.RED);
        errorEmailJL.setFont(new Font("Tahoma", 1, 10));
        
        panelErrorEmail.add(errorEmailJL, BorderLayout.SOUTH);
        
        panelErrorres.add(panelErrorEmail);
    }
    
    private void panelTelefono(int y) {
        panelErrorTelefono = new JPanel(new BorderLayout());
        panelErrorTelefono.setPreferredSize(new Dimension(401, y));
        errorTelefonoJL = new JLabel();
        errorTelefonoJL.setForeground(Color.RED);
        errorTelefonoJL.setFont(new Font("Tahoma", 1, 10));
        
        panelErrorTelefono.add(errorTelefonoJL, BorderLayout.SOUTH);
        
        panelErrorres.add(panelErrorTelefono);
    }

    public JLabel getErrorNombreJL() {
        return errorNombreJL;
    }

    public JLabel getErrorApellidoMJL() {
        return errorApellidoMJL;
    }

    public JLabel getErrorApellidoPJL() {
        return errorApellidoPJL;
    }

    public JLabel getErrorEdadJL() {
        return errorEdadJL;
    }

    public JLabel getErrorEmailJL() {
        return errorEmailJL;
    }

    public JLabel getErrorTelefonoJL() {
        return errorTelefonoJL;
    }

}

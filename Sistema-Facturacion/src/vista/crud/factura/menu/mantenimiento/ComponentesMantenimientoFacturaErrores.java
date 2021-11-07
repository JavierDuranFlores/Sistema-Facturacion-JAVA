/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.mantenimiento;

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
public class ComponentesMantenimientoFacturaErrores extends JPanel{
    
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    
    private JPanel panelErrorres;
    
    private JPanel panelErrorId,
                   panelErrorNProductos,
                   panelErrorTotal,
                   panelErrorIdCliente,
                   panelErrorCreateAt;
    
    private JLabel errorIdJL,
                   errorNProductosJL,
                   errorTotalJL,
                   errorIdClienteJL,
                   errorCreateAtJL;
    
    public ComponentesMantenimientoFacturaErrores(int y1, int y2, int y3, int y4) {
        colocarPanel();
        setLayout(new BorderLayout());
        setBackground(Color.yellow);
        setPreferredSize(new Dimension(400,590));
        
        panelError();
        
        //panelErrorId(y1);
        panelErrorNProductos(y1);
        panelTotal(y2);
        panelIdCliente(y3);
        panelCreateAt(y4);
        //panelCreateAt(y5);
        //panelTelefono(y6);
    }
    
    private void colocarPanel() {
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
    }
    
    private void panelError() {
        panelErrorres = new JPanel(new FlowLayout());
        add(panelErrorres, BorderLayout.CENTER);
    }
    
    private void panelErrorId(int y) {
        panelErrorId = new JPanel(new BorderLayout());
        panelErrorId.setPreferredSize(new Dimension(401, y));
        errorIdJL = new JLabel("");
        errorIdJL.setForeground(Color.RED);
        errorIdJL.setFont(new Font("Tahoma", 1, 10));
        
        panelErrorId.add(errorIdJL, BorderLayout.SOUTH);
        
        panelErrorres.add(panelErrorId, gbc);
    }
    
    private void panelErrorNProductos(int y) {
        panelErrorNProductos = new JPanel(new BorderLayout());
        panelErrorNProductos.setPreferredSize(new Dimension(401, y));
        errorNProductosJL = new JLabel("");
        errorNProductosJL.setForeground(Color.RED);
        errorNProductosJL.setFont(new Font("Tahoma", 1, 10));
        
        panelErrorNProductos.add(errorNProductosJL, BorderLayout.SOUTH);
        
        panelErrorres.add(panelErrorNProductos, gbc);
    }
    
    private void panelTotal(int y) {
        panelErrorTotal = new JPanel(new BorderLayout());
        panelErrorTotal.setPreferredSize(new Dimension(401, y));
        errorTotalJL = new JLabel("");
        errorTotalJL.setForeground(Color.RED);
        errorTotalJL.setFont(new Font("Tahoma", 1, 10));
        
        panelErrorTotal.add(errorTotalJL, BorderLayout.SOUTH);
        
        panelErrorres.add(panelErrorTotal);
    }
    
    private void panelIdCliente(int y) {
        panelErrorIdCliente = new JPanel(new BorderLayout());
        panelErrorIdCliente.setPreferredSize(new Dimension(401, y));
        errorIdClienteJL = new JLabel("");
        errorIdClienteJL.setForeground(Color.RED);
        errorIdClienteJL.setFont(new Font("Tahoma", 1, 10));
        
        panelErrorIdCliente.add(errorIdClienteJL, BorderLayout.SOUTH);
        
        panelErrorres.add(panelErrorIdCliente);
    }
    
    private void panelCreateAt(int y) {
        panelErrorCreateAt = new JPanel(new BorderLayout());
        panelErrorCreateAt.setPreferredSize(new Dimension(401, y));
        errorCreateAtJL = new JLabel();
        errorCreateAtJL.setForeground(Color.RED);
        errorCreateAtJL.setFont(new Font("Tahoma", 1, 10));
        
        panelErrorCreateAt.add(errorCreateAtJL, BorderLayout.SOUTH);
        
        panelErrorres.add(panelErrorCreateAt);
    }
    
    /*private void panelTelefono(int y) {
        panelErrorTelefono = new JPanel(new BorderLayout());
        panelErrorTelefono.setPreferredSize(new Dimension(401, y));
        errorTelefonoJL = new JLabel();
        errorTelefonoJL.setForeground(Color.RED);
        errorTelefonoJL.setFont(new Font("Tahoma", 1, 10));
        
        panelErrorTelefono.add(errorTelefonoJL, BorderLayout.SOUTH);
        
        panelErrorres.add(panelErrorTelefono);
    }*/

    public JLabel getErrorNProductosJL() {
        return errorNProductosJL;
    }

    public JLabel getErrorTotalJL() {
        return errorTotalJL;
    }

    public JLabel getErrorIdClienteJL() {
        return errorIdClienteJL;
    }

    public JLabel getErrorCreateAtJL() {
        return errorCreateAtJL;
    }
    
    
}

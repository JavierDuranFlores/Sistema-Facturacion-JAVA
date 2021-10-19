/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.producto.menu.mantenimiento;

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
public class ComponentesMantenimientoProductoErrores extends JPanel{
    
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    
    private JPanel panelErrorres;
    
    private JPanel panelErrorNombreJP,
                   panelErrorPrecioJP,
                   panelErrorStockJP;
    
    private JLabel errorNombreJL,
                   errorPrecioJL,
                   errorStockJL;
    
    public ComponentesMantenimientoProductoErrores(int y1, int y2, int y3) {
        colocarPanel();
        setLayout(new BorderLayout());
        setBackground(Color.yellow);
        setPreferredSize(new Dimension(400,590));
        
        panelError();
        
        panelErrorNombre(y1);
        panelErrorPrecio(y2);
        panelErrorStock(y3);
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
        panelErrorNombreJP = new JPanel(new BorderLayout());
        panelErrorNombreJP.setPreferredSize(new Dimension(401, y));
        errorNombreJL = new JLabel();
        errorNombreJL.setForeground(Color.RED);
        errorNombreJL.setFont(new Font("Tahoma", 1, 10));
        
        panelErrorNombreJP.add(errorNombreJL, BorderLayout.SOUTH);
        
        panelErrorres.add(panelErrorNombreJP, gbc);
    }
    
    private void panelErrorPrecio(int y) {
        panelErrorPrecioJP = new JPanel(new BorderLayout());
        panelErrorPrecioJP.setPreferredSize(new Dimension(401, y));
        errorPrecioJL = new JLabel();
        errorPrecioJL.setForeground(Color.RED);
        errorPrecioJL.setFont(new Font("Tahoma", 1, 10));
        
        panelErrorPrecioJP.add(errorPrecioJL, BorderLayout.SOUTH);
        
        panelErrorres.add(panelErrorPrecioJP);
    }
    
    private void panelErrorStock(int y) {
        panelErrorStockJP = new JPanel(new BorderLayout());
        panelErrorStockJP.setPreferredSize(new Dimension(401, y));
        errorStockJL = new JLabel();
        errorStockJL.setForeground(Color.RED);
        errorStockJL.setFont(new Font("Tahoma", 1, 10));
        
        panelErrorStockJP.add(errorStockJL, BorderLayout.SOUTH);
        
        panelErrorres.add(panelErrorStockJP, gbc);
    }

    public JLabel getErrorNombreJL() {
        return errorNombreJL;
    }

    public JLabel getErrorPrecioJL() {
        return errorPrecioJL;
    }

    public JLabel getErrorStockJL() {
        return errorStockJL;
    }
    
    
    
}

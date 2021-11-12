/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.consultas.facturas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import modelo.entidades.Producto;
import vista.crud.factura.menu.mantenimiento.facturac.TablaClienteFactura;

/**
 *
 * @author javier
 */
public class DetallesFacturas extends JFrame{
    
    private JPanel principalJP;
    private JPanel headerJP, menuJP, footerJP;
    private JPanel infoFacturaJP[];
    private JLabel infoFacturaJL[];
    
    private TablaDetalleFactura tablaDetalleFactura;
    
    /*private Color fondo = new Color(85, 239, 196);
    private Color tituloBorde = new Color(9, 132, 227);*/
    
    public DetallesFacturas(String ... args) {
        setSize(new Dimension(900, 430));
        setLocationRelativeTo(null);
        colocarPanelPrincipal();
        colocarPanelCabecera();
        colocarInfoFactura();
        
        colocarPanelTabla();
        colocarTabla();
        
        colocarPanelFooter();
        
        setDefaultCloseOperation(DetallesFacturas.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    private void colocarPanelPrincipal() {
        principalJP = new JPanel(new BorderLayout());
        principalJP.setPreferredSize(new Dimension(900, 430));
        add(principalJP);
    }
    
    private void colocarPanelCabecera() {
        headerJP = new JPanel(new GridLayout(2,4));
        //headerJP.setBackground(fondo);
        headerJP.setBorder(new TitledBorder(null, "Informacion Factura", 2, 0, new Font("arial", 1, 11)));
        headerJP.setPreferredSize(new Dimension(900, 80));
        principalJP.add(headerJP, BorderLayout.NORTH);
    }
    
    private void colocarInfoFactura(String ... args) {
        infoFacturaJP = new JPanel[9];
        infoFacturaJL = new JLabel[9];
        for (int i = 0; i < 9; i++) {
            infoFacturaJP[i] = new JPanel();
            //infoFacturaJP[i].setPreferredSize(new Dimension(175, 30));
            headerJP.add(infoFacturaJP[i]);
            infoFacturaJL[i] = new JLabel();
            infoFacturaJL[i].setFont(new Font("arial", 1, 10));
            infoFacturaJP[i].add(infoFacturaJL[i]);
        }
            
    }
    
    private void colocarPanelTabla() {
        menuJP = new JPanel(new BorderLayout());
        //menuJP.setBackground(fondo);
        menuJP.setBorder( new TitledBorder(null, "Lista Productos", 2, 0, new Font("arial", 1, 11)));
        menuJP.setPreferredSize(new Dimension(900, 290));
        principalJP.add(menuJP, BorderLayout.CENTER);
    }
    
    private void colocarTabla() {
        tablaDetalleFactura = new TablaDetalleFactura();
        menuJP.add(tablaDetalleFactura, BorderLayout.CENTER);
        menuJP.add(new JScrollPane(tablaDetalleFactura));
    }
    
    public void colocarFilasTabla(List<Producto> productos) {
        tablaDetalleFactura.colocarFilas(productos);
    }
    
    private void colocarPanelFooter() {
        footerJP = new JPanel();
        //footerJP.setBackground(fondo);
        footerJP.setPreferredSize(new Dimension(900, 60));
        principalJP.add(footerJP,BorderLayout.SOUTH);
    }

    public JLabel[] getInfoFacturaJL() {
        return infoFacturaJL;
    }
    
}

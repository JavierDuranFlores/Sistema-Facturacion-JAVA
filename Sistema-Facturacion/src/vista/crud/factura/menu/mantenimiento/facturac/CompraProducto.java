/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.mantenimiento.facturac;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author javier
 */
public class CompraProducto extends JFrame{
    
    private JPanel panelArriba;
    
    private JPanel panelCentro;
    
    private JLabel idProducoJL,
                   nombreProductoJL,
                   nProductosJL,
                   ifFacturaJL,
                   totalJL;
   
    private JPanel idProducoJP,
                   nombreProductoJP,
                   nProductosJP,
                   ifFacturJP,
                   totalJP;
    
    private JPanel nProductoJP;
    private JLabel nProductoJL;
    private SpinnerModel sm;
    private JSpinner spinner;
    
    private GridBagLayout gbl;
    private GridBagConstraints constraints;
    
    private TablaProductoGuardado tablaProductoGuardado;
    
    public CompraProducto() {
    }
    
    public void initComponent(String idProducto, String producto, String nProducto, String idFactura, String precio) {
        setSize(500, 450);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        gbl = new GridBagLayout();
        constraints = new GridBagConstraints();
        colocarPanelArriba();
        colocarIdProducto(idProducto);
        colocarNombreProducto(producto);
        colocarNProducto(nProducto);
        colocarIdFactura(idFactura);
        colocarTotal(precio);
        nProductosElegir();
        
        colocarPanelCentro();
        colocarTabla();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    private void colocarPanelArriba() {
        panelArriba = new JPanel();
        panelArriba.setLayout(gbl);
        panelArriba.setPreferredSize(new Dimension(500, 100));
        panelArriba.setBorder(BorderFactory.createTitledBorder("Producto"));
        add(panelArriba, BorderLayout.NORTH);
    }
    
    private void colocarIdProducto(String idProducto) {
        idProducoJP = new JPanel(new BorderLayout());
        idProducoJL = new JLabel("ID Producto: ".concat(idProducto));
        idProducoJL.setFont(new Font("Arial", 1, 10));
        idProducoJP.add(idProducoJL);
        panelArriba.add(idProducoJP, colocarPanelesLugar(0, 0, 1, 1));
    }
    
    private void colocarNombreProducto(String producto) {
        nombreProductoJP= new JPanel(new BorderLayout());
        nombreProductoJL = new JLabel("Producto: ".concat(producto));
        nombreProductoJL.setFont(new Font("Arial", 1, 10));
        nombreProductoJP.add(nombreProductoJL);
        panelArriba.add(nombreProductoJP, colocarPanelesLugar(1, 0, 1, 1));
    }
    
    private void colocarNProducto(String nProductos) {
        nProductosJP= new JPanel(new BorderLayout());
        nProductosJL = new JLabel("NO. Productos: ".concat(nProductos));
        nProductosJL.setFont(new Font("Arial", 1, 10));
        nProductosJP.add(nProductosJL);
        panelArriba.add(nProductosJP, colocarPanelesLugar(2, 0, 1, 1));
    }
    
    private void colocarIdFactura(String idFactura) {
        idProducoJP= new JPanel(new BorderLayout());
        ifFacturaJL = new JLabel("ID Factura: ".concat(idFactura));
        ifFacturaJL.setFont(new Font("Arial", 1, 10));
        idProducoJP.add(ifFacturaJL);
        panelArriba.add(idProducoJP, colocarPanelesLugar(0, 1, 1, 1));
    }
    
    private void colocarTotal(String total) {
        totalJP= new JPanel(new BorderLayout());
        totalJL = new JLabel("Total: ".concat(total));
        totalJL.setFont(new Font("Arial", 1, 10));
        totalJP.add(totalJL);
        panelArriba.add(totalJP, colocarPanelesLugar(1, 1, 1, 1));
    }
    
    private void nProductosElegir() {
        nProductoJP = new JPanel(new BorderLayout());
        nProductoJL = new JLabel("No. Producto");
        nProductoJL.setFont(new Font("Arial", 1, 10));
        sm = new SpinnerNumberModel(1, 0, 100, 1);
        spinner = new JSpinner(sm);
        nProductoJP.add(nProductoJL, BorderLayout.NORTH);
        nProductoJP.add(spinner, BorderLayout.CENTER);
        
        panelArriba.add(nProductoJP, colocarPanelesLugar(3, 1, 1, 1));
    }
    
    private void colocarPanelCentro() {
        panelCentro = new JPanel();
        panelCentro.setPreferredSize(new Dimension(500, 350));
        panelCentro.setLayout(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createTitledBorder("Productos Elegidos"));
        add(panelCentro, BorderLayout.CENTER);
    }
    
    private void colocarTabla() {
        tablaProductoGuardado = TablaProductoGuardado.getInstance();
        panelCentro.add(tablaProductoGuardado, BorderLayout.CENTER);
        panelCentro.add(new JScrollPane(tablaProductoGuardado));
    }
    
    private GridBagConstraints colocarPanelesLugar(int x, int y, int w, int h) {

        constraints.gridx = x; // El área de texto empieza en la columna cero.
        constraints.gridy = y; // El área de texto empieza en la fila cero
        constraints.gridwidth = w; // El área de texto ocupa dos columnas.
        constraints.gridheight = h; // El área de texto ocupa 2 filas.
        constraints.insets.bottom = 1; // El area de texto esta separado en el bottom por 1 fila
        constraints.insets.right = 30;
        return this.constraints;

    }

    public JLabel getIdProducoJL() {
        return idProducoJL;
    }

    public JLabel getNombreProductoJL() {
        return nombreProductoJL;
    }

    public JLabel getnProductosJL() {
        return nProductosJL;
    }

    public JLabel getIfFacturaJL() {
        return ifFacturaJL;
    }

    public JLabel getTotalJL() {
        return totalJL;
    }

    public JLabel getnProductoJL() {
        return nProductoJL;
    }

    public TablaProductoGuardado getTablaProductoGuardado() {
        return tablaProductoGuardado;
    }
    
}

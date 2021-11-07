/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.mantenimiento.facturau;

import controlador.mantenimiento.tabla.factura.update.ControladorTablaProductoUpdate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import modelo.dao.FacturaDAO;
import modelo.dao.ProductoDAO;
import modelo.entidades.Producto;
import vista.crud.factura.PrincipalMantenimientoFactura;
import vista.crud.factura.menu.mantenimiento.facturac.TablaProductoFactura;

/**
 *
 * @author javier
 */
public class FacturaUpdateProducto extends JFrame {

    private JPanel panel;
    private JLabel paginaProductoJL;
    private JLabel regitrosJL;
    private JComboBox paginaPJCB;
    private JComboBox registrosPJCB;
    private final Object registros[] = {10, 20, 50, 100, 200, 500};
    private JPanel paginarJP;
    public int row = 0;
    public Producto producto;
    
    private JPanel panelPaginadorComponentes;
    
    private JPanel panelAbajo;
    private JPanel panelNProductos;
    private JLabel nProductosEtiqueta;
    private JTextField nProductoCampo;
    private JButton guardar, actualizar, eliminar;
    
    private FacturaDAO facturaDAO;

    private ControladorTablaProductoUpdate controladorTablaProductoUpdate;

    private static FacturaUpdateProducto facturaUpdateProducto;
    private TablaProductoFactura tablaProductoFactura;

    private FacturaUpdateProducto(TablaProductoFactura tablaProductoFactura) {
        this.tablaProductoFactura = tablaProductoFactura;
        facturaDAO = new FacturaDAO();
        controladorTablaProductoUpdate = new ControladorTablaProductoUpdate(tablaProductoFactura, PrincipalMantenimientoFactura.getInstance());

        setSize(380, 250);
        setLocationRelativeTo(null);

        panel = new JPanel(new BorderLayout());

        panel.add(tablaProductoFactura, BorderLayout.CENTER);

        panel.add(new JScrollPane(tablaProductoFactura), BorderLayout.CENTER);

        add(panel);

        colocarPaginadorProducto();
        paginarProducto();
        colocarComponentesAbajo();

        getPaginaPJCB().addItemListener(controladorTablaProductoUpdate);
        getRegistrosPJCB().addItemListener(controladorTablaProductoUpdate);
        tablaProductoFactura.addMouseListener(controladorTablaProductoUpdate);
        guardar.addActionListener(controladorTablaProductoUpdate);
        actualizar.addActionListener(controladorTablaProductoUpdate);
        eliminar.addActionListener(controladorTablaProductoUpdate);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);

    }

    public static FacturaUpdateProducto getInstance(TablaProductoFactura tablaProductoFactura) {
        if (facturaUpdateProducto == null) {
            facturaUpdateProducto = new FacturaUpdateProducto(tablaProductoFactura);
        }

        return facturaUpdateProducto;
    }

    private void colocarPaginadorProducto() {
        paginaProductoJL = new JLabel("#Paginas");
        paginaProductoJL.setFont(new Font("Arial", 1, 9));
        regitrosJL = new JLabel("Total de registros por pagina");
        regitrosJL.setFont(new Font("Arial", 1, 9));

        paginaPJCB = new JComboBox();
        registrosPJCB = new JComboBox(registros);

        paginarJP = new JPanel();

        paginarJP.add(paginaProductoJL);
        paginarJP.add(paginaPJCB);
        paginarJP.add(regitrosJL);
        paginarJP.add(registrosPJCB);
        
        panelPaginadorComponentes = new JPanel(new BorderLayout());
        panelPaginadorComponentes.add(paginarJP, BorderLayout.NORTH);
         

        panel.add(panelPaginadorComponentes, BorderLayout.SOUTH);
    }

    public void paginarProducto() {

        Object paginas[];
        int cont = 0;
        if (getPaginaPJCB().getSelectedItem() == null) {
            paginas = facturaDAO.getLeer().paginas(10, "facturas");
        } else {
            paginas = facturaDAO.getLeer().paginas(Integer.valueOf(getRegistrosPJCB().getSelectedItem().toString()), "productos");
            cont = getPaginaPJCB().getItemCount();
        }
        System.out.println(paginas.length);
        for (int i = 0; i < paginas.length; i++) {
            this.getPaginaPJCB().addItem(paginas[i]);
        }

        borrarDatosComboProductos(cont);

    }
    
    private void colocarComponentesAbajo() {
        panelAbajo = new JPanel(new GridLayout(1, 4));
        panelNProductos = new JPanel(new GridLayout(2, 1));
        
        nProductosEtiqueta = new JLabel("NO. Productos");
        nProductosEtiqueta.setFont(new Font("Arial", 1, 10));
        JPanel nProductosEtiquetaJP = new JPanel(new FlowLayout());
        nProductosEtiquetaJP.add(nProductosEtiqueta);
        nProductoCampo = new JTextField(3);
        JPanel nProductoCampoJP = new JPanel(new FlowLayout());
        nProductoCampoJP.add(nProductoCampo);
        
        panelNProductos.add(nProductosEtiquetaJP);
        panelNProductos.add(nProductoCampoJP);
        panelAbajo.add(panelNProductos);
        
        guardar = new JButton("Guardar");
        guardar.setBackground(Color.GREEN);
        JPanel guardarJP = new JPanel(new FlowLayout());
        guardarJP.add(guardar);
        panelAbajo.add(guardarJP);
        
        actualizar = new JButton("Actualizar");
        actualizar.setBackground(Color.YELLOW);
        JPanel actualizarJP = new JPanel(new FlowLayout());
        actualizarJP.add(actualizar);
        panelAbajo.add(actualizarJP);
        
        eliminar = new JButton("Eliminar");
        eliminar.setBackground(Color.RED);
        JPanel eliminarJP = new JPanel(new FlowLayout());
        eliminarJP.add(eliminar);
        panelAbajo.add(eliminarJP);
        
        panelPaginadorComponentes.add(panelAbajo, BorderLayout.SOUTH);
    }

    public void borrarDatosComboProductos(int e) {
        for (int i = 0; i < e + 1; i++) {
            getPaginaPJCB().removeItem(i);
        }
    }

    public TablaProductoFactura getTablaProductoFactura() {
        return tablaProductoFactura;
    }

    public JLabel getPaginaProductoJL() {
        return paginaProductoJL;
    }

    public JLabel getRegitrosJL() {
        return regitrosJL;
    }

    public JComboBox getPaginaPJCB() {
        return paginaPJCB;
    }

    public JComboBox getRegistrosPJCB() {
        return registrosPJCB;
    }

    public JTextField getnProductoCampo() {
        return nProductoCampo;
    }

    public JButton getGuardar() {
        return guardar;
    }

    public JButton getActualizar() {
        return actualizar;
    }

    public JButton getEliminar() {
        return eliminar;
    }
    
    

}

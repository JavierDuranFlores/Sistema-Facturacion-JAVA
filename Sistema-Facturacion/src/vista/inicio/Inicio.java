/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.inicio;

import controlador.ControladorInicio;
import controlador.consultas.clientes.ControladorConsultaClientes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author javier
 */
public class Inicio {
    
    private JFrame ventanaInicio;
    
    private JPanel panelIncio;
    
    private JMenuBar barraMenu;
    
    private JMenu mantenimiento,
                  consultas,
                  reportes,
                  herramientas,
                  salir,
                  facturaConsultas;
    
    private JMenuItem clienteMantenimiento,
                      productoMantenimiento,
                      facturaMantenimiento,
                      clienteConsultas,
                      productoConsultas,
                      reporte,
                      cambiarContra,
                      salirItem;
    
    private JMenuItem facturaFecha,
                      facturaID;
    
    private JPanel contenedor;
    
    private final Color colorComponente = new Color(241, 242, 246);
    
    private ControladorInicio controladorInicio;
    
    public void iniciarComponentes() {
        ventana();
        panel();
        inicializarControlador();
        barraMenu();
        contenedor();
        menuMantenimiento();
        menuConsultas();
        menuReporte();
        menuHerramientas();
        menuSalir();
        
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
    
    private void inicializarControlador() {
        controladorInicio = new ControladorInicio(this);
    }

    private void barraMenu() {
        barraMenu = new JMenuBar();
        barraMenu.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 50));
        panelIncio.add(barraMenu, BorderLayout.NORTH);
    }
    
    private void contenedor() {
        contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(colorComponente);
        panelIncio.add(contenedor, BorderLayout.CENTER);
    }
    
    private void menuMantenimiento() {
        mantenimiento = new JMenu("MANTENIMIENTO");
        mantenimiento.setIcon(new ImageIcon("src//img//almacen.png"));
        
        clienteMantenimiento = new JMenuItem("CLIENTES");
        clienteMantenimiento.setIcon(new ImageIcon("src//img//cliente.png"));
        
        productoMantenimiento = new JMenuItem("PRODUCTOS");
        productoMantenimiento.setIcon(new ImageIcon("src//img//productos.png"));
        
        facturaMantenimiento = new JMenuItem("FACTURAS");
        facturaMantenimiento.setIcon(new ImageIcon("src//img//factura.png"));
        
        mantenimiento.add(clienteMantenimiento);
        mantenimiento.add(productoMantenimiento);
        mantenimiento.add(facturaMantenimiento);
        
        barraMenu.add(mantenimiento);
        
        clienteMantenimiento.addActionListener(controladorInicio);
        productoMantenimiento.addActionListener(controladorInicio);
        facturaMantenimiento.addActionListener(controladorInicio);
    }
    
    private void menuConsultas() {
        consultas = new JMenu("CONSULTAS");
        consultas.setIcon(new ImageIcon("src//img//consultas.png"));
        
        clienteConsultas = new JMenuItem("CLIENTES");
        clienteConsultas.setIcon(new ImageIcon("src//img//cliente.png"));
        
        productoConsultas = new JMenuItem("PRODUCTOS");
        productoConsultas.setIcon(new ImageIcon("src//img//productos.png"));
        
        facturaConsultas = new JMenu("FACTURAS");
        facturaConsultas.setIcon(new ImageIcon("src//img//factura.png"));
        facturaFecha = new JMenuItem("FECHA");
        facturaFecha.setIcon(new ImageIcon("src//img//fecha.png"));
        facturaID = new JMenuItem("ID Cliente");
        facturaID.setIcon(new ImageIcon("src//img//id.png"));
        
        facturaConsultas.add(facturaFecha);
        facturaConsultas.add(facturaID);
         
        consultas.add(clienteConsultas);
        consultas.add(productoConsultas);
        consultas.add(facturaConsultas);
        
        clienteConsultas.addActionListener(controladorInicio);
        productoConsultas.addActionListener(controladorInicio);
        facturaFecha.addActionListener(controladorInicio);
        facturaID.addActionListener(controladorInicio);
        
        barraMenu.add(consultas);
    }
    
    private void menuReporte() {
        reportes = new JMenu("REPORTE");
        reportes.setIcon(new ImageIcon("src//img//reporte.png"));
        
        reporte = new JMenuItem("REPORTE");
        reporte.setIcon(new ImageIcon("src//img//reporte.png"));
        
        reportes.add(reporte);
        
        reporte.addActionListener(controladorInicio);
        
        barraMenu.add(reportes);
    }
    
    private void menuHerramientas() {
        herramientas = new JMenu("HERRAMIENTAS");
        herramientas.setIcon(new ImageIcon("src//img//herramientas.png"));
        
        cambiarContra = new JMenuItem("CAMBIAR CONTRASEÑA");
        cambiarContra.setIcon(new ImageIcon("src//img//cambiar-contra.png"));
        
        herramientas.add(cambiarContra);
        
        barraMenu.add(herramientas);
    }
    
    private void menuSalir() {
        salir = new JMenu("SALIR");
        salir.setIcon(new ImageIcon("src//img/salir.png"));
        
        salirItem = new JMenuItem("SALIR");
        salirItem.setIcon(new ImageIcon("src//img/salir.png"));
        
        salir.add(salirItem);
        
        barraMenu.add(salir);
        
    }
    
    public void addContenedor(JPanel panel) {
        if  (contenedor.getComponentCount()>0) {
            contenedor.removeAll();
        }
        contenedor.add(panel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(ventanaInicio);
    }
    
    public JMenuItem getClienteConsultas() {
        return clienteConsultas;
    }

    public JMenuItem getProductoConsultas() {
        return productoConsultas;
    }
    
    public JMenuItem getFacturaConsultas() {
        return facturaConsultas;
    }

    public JMenuItem getFacturaFecha() {
        return facturaFecha;
    }

    public JMenuItem getFacturaID() {
        return facturaID;
    }
    
    

    public JMenuItem getClienteMantenimiento() {
        return clienteMantenimiento;
    }

    public JMenuItem getProductoMantenimiento() {
        return productoMantenimiento;
    }
    
    public JMenuItem getFacturaMantenimiento() {
        return facturaMantenimiento;
    }

    public JMenuItem getReporte() {
        return reporte;
    }

    public JMenuItem getCambiarContra() {
        return cambiarContra;
    }

    public JMenuItem getSalirItem() {
        return this.salirItem;
    }

    public JFrame getVentanaInicio() {
        return ventanaInicio;
    }
    
}

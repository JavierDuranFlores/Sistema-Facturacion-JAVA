/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.producto.menu.mantenimiento;

import java.awt.Font;
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
public class ComponentesProductoUpdateDelete extends JPanel{
    
    private JLabel idJL,
                   nombreJL,
                   precioJL,
                   stockJL;
    
    private JLabel mensaje;
    
    private JTextField idJTF,
                       nombreJTF,
                       precioJTF,
                       stockJTF;
    
    private JPanel idJP,
                   nombreJP,
                   precioJP,
                   stockJP,
                   botones;
    
    private JButton botonJB,
                    cancelarJB,
                    buscarJB;
    
    private GridBagConstraints constraints;
    
    int x = 0;
    int y = 0;
    int w = 2;
    int h = 1;
    
    
    public ComponentesProductoUpdateDelete(String texto) {
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        colocarBuscador();
        colocarFormNombre();
        colocarFormPrecio();
        colocarFormStock();
        colocarMensaje();
        colocarBotones(texto);
    
        if (texto.equalsIgnoreCase("Borrar")) {
            nombreJTF.setEditable(false);
            precioJTF.setEditable(false);           
            stockJTF.setEditable(false);
        } else {
            nombreJTF.setEditable(true);
            precioJTF.setEditable(true);           
            stockJTF.setEditable(true);
        }
            
    }
    
    private void colocarBuscador() {
        idJL = new JLabel("ID");
        idJTF = new JTextField(3);
        buscarJB = new JButton("Buscar");
        buscarJB.setFont(new Font("Arial", 1, 10));
        idJP = new JPanel(new GridLayout(1, 2));
        
        JPanel panelBuscar = new JPanel();
        
        panelBuscar.add(idJL);
        panelBuscar.add(idJTF);
        idJP.add(panelBuscar);
        idJP.add(buscarJB);
        
        constraints.gridx = x; // El área de texto empieza en la columna cero.
        constraints.gridy = y; // El área de texto empieza en la fila cero
        constraints.gridwidth = w; // El área de texto ocupa dos columnas.
        constraints.gridheight = h; // El área de texto ocupa 2 filas.
        constraints.insets.top = 25;
        y++;
        
        add(idJP, constraints);
        
    }
    
    private void colocarFormNombre() {
        nombreJL = new JLabel  ("Nombre(*)");
        nombreJTF = new JTextField(15);
        
        nombreJP = new JPanel(new GridLayout(1, 2));
        
        nombreJP.add(nombreJL);
        nombreJP.add(nombreJTF);
        
        
        
        add(nombreJP, colocarContrains());
    }
    
    private void colocarFormPrecio() {
        precioJL = new JLabel("Precio(*)");
        precioJTF = new JTextField(15);
        
        precioJP = new JPanel(new GridLayout(1, 2));
        
        precioJP.add(precioJL);
        precioJP.add(precioJTF);
        
        add(precioJP, colocarContrains());
    }
    
    private void colocarFormStock() {
        stockJL = new JLabel("Stock(*)");
        stockJTF = new JTextField(15);
        
        stockJP = new JPanel(new GridLayout(1, 2));
        
        stockJP.add(stockJL);
        stockJP.add(stockJTF);
        
        add(stockJP, colocarContrains());
    }
    
    private void colocarBotones(String texto) {
        botones = new JPanel();
        botonJB = new JButton(texto);
        cancelarJB = new JButton("Cancelar");
        
        botones.add(botonJB);
        botones.add(cancelarJB);
        
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        y++;
        
        add(botones, constraints);
    }
    
    private void colocarMensaje() {
        mensaje = new JLabel("(*) Indica que es un campo obligatorio");
        
        constraints.gridx = x; // El área de texto empieza en la columna cero.
        constraints.gridy = y; // El área de texto empieza en la fila cero
        constraints.gridwidth = w; // El área de texto ocupa dos columnas.
        constraints.gridheight = h; // El área de texto ocupa 2 filas.
        //constraints.insets.bottom = 1;
        y++;
        
        add(mensaje, constraints);
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

    public JLabel getIdJL() {
        return idJL;
    }

    public JLabel getNombreJL() {
        return nombreJL;
    }

    public JLabel getPrecioJL() {
        return precioJL;
    }

    public JLabel getStockJL() {
        return stockJL;
    }

    public JLabel getMensaje() {
        return mensaje;
    }

    public JTextField getIdJTF() {
        return idJTF;
    }

    public JTextField getNombreJTF() {
        return nombreJTF;
    }

    public JTextField getPrecioJTF() {
        return precioJTF;
    }

    public JTextField getStockJTF() {
        return stockJTF;
    }

    public JButton getBotonJB() {
        return botonJB;
    }

    public JButton getCancelarJB() {
        return cancelarJB;
    }

    public JButton getBuscarJB() {
        return buscarJB;
    }
    
    
}

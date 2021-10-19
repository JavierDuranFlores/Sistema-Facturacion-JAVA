/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.producto.menu.mantenimiento.productoc;

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
public class ComponentesProductoCreate extends JPanel{
    
    private JLabel nombreJL,
                   precioJL,
                   stockJL,
                   fechaJL;
    
    private JLabel mensaje;
    
    private JTextField nombreJTF,
                       precioJTF,
                       stockJTF,
                       fechaJTF;
    
    private JPanel nombreJP,
                   precioJP,
                   stockJP,
                   fechaJP,
                   botones;
    
    private JButton guardarJB,
                    cancelarJB;
    
    private GridBagConstraints constraints;
    
    int x = 0;
    int y = 0;
    int w = 2;
    int h = 1;
    
    
    public ComponentesProductoCreate() {
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        colocarFormNombre();
        colocarFormPrecio();
        colocarFormStock();
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
    
    private void colocarFormPrecio() {
        precioJL = new JLabel("Precio(*)");
        precioJTF = new JTextField(12);
        
        precioJP = new JPanel(new GridLayout(1, 2));
        
        precioJP.add(precioJL);
        precioJP.add(precioJTF);
        
        add(precioJP, colocarContrains());
    }
    
    private void colocarFormStock() {
        stockJL = new JLabel("Stock(*)");
        stockJTF = new JTextField(12);
        
        stockJP = new JPanel(new GridLayout(1, 2));
        
        stockJP.add(stockJL);
        stockJP.add(stockJTF);
        
        add(stockJP, colocarContrains());
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

    public JTextField getPrecioJTF() {
        return precioJTF;
    }

    public JTextField getStockJTF() {
        return stockJTF;
    }

    
    
}

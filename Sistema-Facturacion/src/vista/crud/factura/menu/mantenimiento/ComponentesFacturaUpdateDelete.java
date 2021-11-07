/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.mantenimiento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import vista.crud.factura.PrincipalMantenimientoFactura;

/**
 *
 * @author javier
 */
public class ComponentesFacturaUpdateDelete extends JPanel {

    private JLabel idJL,
            nProductosJL,
            totalJL,
            idClienteJL,
            creatAtJL;

    private JLabel mensaje;

    private JTextField idJTF,
            nProductosJTF,
            totalJTF,
            idClienteJTF,
            creatAtJTF;

    private JPanel idJP,
            nProductosJP,
            totalJP,
            idClienteJP,
            creatAtJP,
            tablaJP,
            botones;

    private JButton botonJB,
            cancelarJB,
            buscarJB;

    private TablaProductoUD tablaProductoUD;

    private GridBagConstraints constraints;

    int x = 0;
    int y = 0;
    int w = 2;
    int h = 1;

    public ComponentesFacturaUpdateDelete(String texto) {
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        colocarBuscador();
        colocarFormNProductos();
        colocarFormApellidoP();
        colocarFormApellidoM();
        colocarFormEdad();
        colocarTabla();
        colocarMensaje();
        colocarBotones(texto);

        nProductosJTF.setEditable(false);
        totalJTF.setEditable(false);
        idClienteJTF.setEditable(false);
        creatAtJTF.setEditable(false);

    }

    private void colocarBuscador() {
        idJL = new JLabel("ID");
        idJTF = new JTextField(3);
        idJTF.setBackground(Color.WHITE);
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

    private void colocarFormNProductos() {
        nProductosJL = new JLabel("No. Productos(*)");
        nProductosJTF = new JTextField(15);
        nProductosJTF.setBackground(Color.WHITE);

        nProductosJP = new JPanel(new GridLayout(1, 2));

        nProductosJP.add(nProductosJL);
        nProductosJP.add(nProductosJTF);

        add(nProductosJP, colocarContrains());
    }

    private void colocarFormApellidoP() {
        totalJL = new JLabel("Total(*)");
        totalJTF = new JTextField(15);
        totalJTF.setBackground(Color.WHITE);

        totalJP = new JPanel(new GridLayout(1, 2));

        totalJP.add(totalJL);
        totalJP.add(totalJTF);

        add(totalJP, colocarContrains());
    }

    private void colocarFormApellidoM() {
        idClienteJL = new JLabel("ID Cliente(*)");
        idClienteJTF = new JTextField(15);
        idClienteJTF.setBackground(Color.WHITE);

        idClienteJP = new JPanel(new GridLayout(1, 2));

        idClienteJP.add(idClienteJL);
        idClienteJP.add(idClienteJTF);

        add(idClienteJP, colocarContrains());
    }

    private void colocarFormEdad() {
        creatAtJL = new JLabel("Fecha");
        creatAtJTF = new JTextField(15);
        creatAtJTF.setBackground(Color.WHITE);
        creatAtJP = new JPanel(new GridLayout(1, 2));

        creatAtJP.add(creatAtJL);
        creatAtJP.add(creatAtJTF);

        add(creatAtJP, colocarContrains());
    }

    private void colocarTabla() {
        tablaJP = new JPanel(new BorderLayout());
        tablaJP.setPreferredSize(new Dimension(380, 160));

        tablaProductoUD = new TablaProductoUD(null);

        tablaJP.add(tablaProductoUD, BorderLayout.CENTER);
        tablaJP.add(new JScrollPane(tablaProductoUD), BorderLayout.CENTER);

        add(tablaJP, colocarContrains());
    }

    /*private void colocarFormDireccion() {
        direccionJL = new JLabel("Direccion");
        direccionJTF = new JTextField(15);

        direccionJP = new JPanel(new GridLayout(1, 2));

        direccionJP.add(direccionJL);
        direccionJP.add(direccionJTF);

        add(direccionJP, colocarContrains());
    }

    private void colocarFormTelefono() {
        telefonoJL = new JLabel("Telefono(*)");
        telefonoJTF = new JTextField(15);

        telefonoJP = new JPanel(new GridLayout(1, 2));

        telefonoJP.add(telefonoJL);
        telefonoJP.add(telefonoJTF);

        add(telefonoJP, colocarContrains());
    }*/
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

    public JButton getBotonJB() {
        return botonJB;
    }

    public JButton getCancelarJB() {
        return cancelarJB;
    }

    public JButton getBuscarJB() {
        return buscarJB;
    }

    public JTextField getIdJTF() {
        return idJTF;
    }

    public JTextField getnProductosJTF() {
        return nProductosJTF;
    }

    public JTextField getTotalJTF() {
        return totalJTF;
    }

    public JTextField getIdClienteJTF() {
        return idClienteJTF;
    }

    public JTextField getCreatAtJTF() {
        return creatAtJTF;
    }

    public TablaProductoUD getTablaProductoUD() {
        return tablaProductoUD;
    }

}

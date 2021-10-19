/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.cliente.menu.listado.pie_pagina;

import controlador.mantenimiento.ControladorMantenimientoCliente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.stream.Stream;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelo.dao.ClienteDAO;
import vista.crud.cliente.PrincipalMantenimientoCliente;

/**
 *
 * @author javier
 */
public class PiePaginaListadoCliente extends JPanel {

    private JPanel panelPaginacion;
    private JLabel pagina;
    public JComboBox comboPaginas;
    private JLabel nRegistros;
    private JComboBox comboNRegistros;
    private final Object resgistros[] = {10, 20, 50, 100, 200, 500};
    private JPanel panelContador;
    private JLabel etiquetaContador;
    
    private final ClienteDAO clienteDAO;

    private JPanel panelNClienteMostrados;
    
    public PiePaginaListadoCliente() {
        clienteDAO = new ClienteDAO();
        setLayout(new GridLayout(2, 1));

        panelPaginacion();
        componentesPanelPaginacion();
        panelNClientesMostrados();
        colocarPanelContador();
        paginar();
        colocarEtiqueta();
    }
    
    private void panelPaginacion() {
        panelPaginacion = new JPanel(new BorderLayout());
        add(panelPaginacion);
    }

    private void componentesPanelPaginacion() {
        pagina = new JLabel("#Paginas");
        comboPaginas = new JComboBox();
        nRegistros = new JLabel("Total de registros por pagina");
        comboNRegistros = new JComboBox(resgistros);

        JPanel panelComponentes = new JPanel();

        panelComponentes.add(pagina);
        panelComponentes.add(comboPaginas);
        panelComponentes.add(nRegistros);
        panelComponentes.add(comboNRegistros);

        panelPaginacion.add(panelComponentes, BorderLayout.WEST);

    }

    private void panelNClientesMostrados() {
        panelNClienteMostrados = new JPanel(new BorderLayout());
        add(panelNClienteMostrados);
    }

    public void borrarDatosCombo(int e) {
            for (int i = 0; i < e+1; i++) {
                comboPaginas.removeItem(i);
            }
    }
    
    private void colocarPanelContador() {
        panelContador = new JPanel(new BorderLayout());
        panelNClienteMostrados.add(panelContador, BorderLayout.CENTER);
    }
    
    public void colocarEtiqueta() {
        etiquetaContador = new JLabel();
        panelContador.add(etiquetaContador, BorderLayout.EAST);
    }

    public JComboBox getComboPaginas() {
        return comboPaginas;
    }

    public JComboBox getComboNRegistros() {
        return comboNRegistros;
    }

    public void paginar() {
        
        Object paginas[];
        int cont=0;
        if (getComboPaginas().getSelectedItem() == null){
            paginas = clienteDAO.getLeer().paginas(10, "clientes");
        }else {
            paginas = clienteDAO.getLeer().paginas(Integer.valueOf(getComboNRegistros().getSelectedItem().toString()), "clientes");
            cont = getComboPaginas().getItemCount();
        }

        for (int i = 0; i < paginas.length; i++) {
            this.getComboPaginas().addItem(paginas[i]);
        }
        
        borrarDatosCombo(cont);
            
    }

    public JLabel getEtiquetaContador() {
        return etiquetaContador;
    }
    
}

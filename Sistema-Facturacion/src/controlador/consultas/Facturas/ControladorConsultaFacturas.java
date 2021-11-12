/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.consultas.Facturas;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.dao.FacturaDAO;
import modelo.entidades.Factura;
import vista.consultas.facturas.ContenidoConsultaFacturas;
import vista.crud.factura.menu.listado.contenido.ContenidoTablaFactura;

/**
 *
 * @author javier
 */
public class ControladorConsultaFacturas implements ActionListener {

    private ContenidoConsultaFacturas contenidoConsultaFacturas;
    private FacturaDAO facturaDAO;

    public ControladorConsultaFacturas(ContenidoConsultaFacturas contenidoConsultaFacturas) {
        this.contenidoConsultaFacturas = contenidoConsultaFacturas;
        facturaDAO = new FacturaDAO();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == contenidoConsultaFacturas.getBuscarJB()) {
            if (contenidoConsultaFacturas.getTipoBusquda().equals("fecha")) {
                String fi = String.valueOf(contenidoConsultaFacturas.getCalendarI().getDate());
                String ff = String.valueOf(contenidoConsultaFacturas.getCalendarF().getDate());
                contenidoConsultaFacturas.getTablaFacturaConsulta().limpiarTabla();
                contenidoConsultaFacturas.getTablaFacturaConsulta().colocarFilas(fi, ff, "fecha");
            } else {
                String id = contenidoConsultaFacturas.getIdJTF().getText();
                if (isNumber(id)) {
                    contenidoConsultaFacturas.getTablaFacturaConsulta().limpiarTabla();
                    contenidoConsultaFacturas.getTablaFacturaConsulta().colocarFilas(id, "", "id");
                }
            }

        }

    }

    public boolean isNumber(String valor) {
        boolean isNumber = false;
        try {
            Integer.valueOf(valor);
            isNumber = true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "   Busqueda Invalida", "ERROR", 0);
        }

        return isNumber;
    }


}

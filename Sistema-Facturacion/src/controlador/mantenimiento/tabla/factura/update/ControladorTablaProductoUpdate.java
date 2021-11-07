/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.mantenimiento.tabla.factura.update;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.dao.ProductoDAO;
import modelo.entidades.Producto;
import vista.crud.factura.PrincipalMantenimientoFactura;
import vista.crud.factura.menu.mantenimiento.ComponentesFacturaUpdateDelete;
import vista.crud.factura.menu.mantenimiento.facturac.ComponentesFacturaCreate;
import vista.crud.factura.menu.mantenimiento.facturac.TablaProductoFactura;
import vista.crud.factura.menu.mantenimiento.facturau.FacturaUpdateProducto;

/**
 *
 * @author javier
 */
public class ControladorTablaProductoUpdate extends MouseAdapter implements ItemListener, ActionListener {

    private TablaProductoFactura tablaProductoFactura;
    private FacturaUpdateProducto facturaUpdateProducto;
    private ProductoDAO productoDAO;
    private PrincipalMantenimientoFactura principalMantenimientoFactura;
    private final ComponentesFacturaUpdateDelete componentesFacturaUpdate;
    private ComponentesFacturaCreate componentesFacturaCreate;
    private JTable table;
    private Point point;

    public ControladorTablaProductoUpdate(TablaProductoFactura tablaProductoFactura, PrincipalMantenimientoFactura principalMantenimientoFactura) {
        productoDAO = new ProductoDAO();
        this.tablaProductoFactura = tablaProductoFactura;
        this.principalMantenimientoFactura = principalMantenimientoFactura;
        this.componentesFacturaUpdate = principalMantenimientoFactura.getMenuMantenimientoFactura().getMantenimientoFactura().getMenuMantenimientoFacturaCRUD().getFacturaUpdate().getComponentesFacturaUpdate();
        this.componentesFacturaCreate = principalMantenimientoFactura.getMenuMantenimientoFactura().getMantenimientoFactura().getMenuMantenimientoFacturaCRUD().getFacturaCreate().getComponentesFacturaCreate();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        table = (JTable) e.getSource();
        point = e.getPoint();
        this.facturaUpdateProducto = FacturaUpdateProducto.getInstance(tablaProductoFactura);

        if (e.getSource() == componentesFacturaUpdate.getTablaProductoUD()) {
            facturaUpdateProducto.setVisible(true);
            facturaUpdateProducto.row = table.rowAtPoint(point);
            facturaUpdateProducto.producto = new Producto();
            facturaUpdateProducto.producto.setId(Short.valueOf(table.getValueAt(table.rowAtPoint(point), 0).toString()));
            facturaUpdateProducto.producto.setNombre(table.getValueAt(table.rowAtPoint(point), 2).toString());
            facturaUpdateProducto.producto.setPrecio(Double.valueOf(table.getValueAt(table.rowAtPoint(point), 3).toString()));
            facturaUpdateProducto.producto.setStock(Integer.valueOf(table.getValueAt(table.rowAtPoint(point), 4).toString()));
            facturaUpdateProducto.producto.setCreate_at(Date.valueOf(table.getValueAt(table.rowAtPoint(point), 5).toString()));
        } else if (e.getSource() == facturaUpdateProducto.getTablaProductoFactura()) {
            facturaUpdateProducto.producto.setId(Short.valueOf(table.getValueAt(table.rowAtPoint(point), 0).toString()));
            facturaUpdateProducto.producto.setNombre(table.getValueAt(table.rowAtPoint(point), 1).toString());
            facturaUpdateProducto.producto.setPrecio(Double.valueOf(table.getValueAt(table.rowAtPoint(point), 2).toString()));
            facturaUpdateProducto.producto.setStock(Integer.valueOf(table.getValueAt(table.rowAtPoint(point), 3).toString()));
            facturaUpdateProducto.producto.setCreate_at(Date.valueOf(table.getValueAt(table.rowAtPoint(point), 4).toString()));
        }
        
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        this.facturaUpdateProducto = FacturaUpdateProducto.getInstance(tablaProductoFactura);
        if (ie.getSource() == facturaUpdateProducto.getRegistrosPJCB()) {
            llenarTablaListado();
            facturaUpdateProducto.paginarProducto();
        } else if (ie.getSource() == facturaUpdateProducto.getPaginaPJCB()) {
            llenarTablaListado();
        }

    }

    private void llenarTablaListado() {
        tablaProductoFactura.limpiarTabla();
        List<Producto> lista = productoDAO.paginar(String.valueOf(facturaUpdateProducto.getRegistrosPJCB().getSelectedItem()), String.valueOf(facturaUpdateProducto.getPaginaPJCB().getSelectedItem()));
        Producto[] miarray = new Producto[lista.size()];
        miarray = lista.toArray(miarray);

        Object producto[] = new Object[9];

        Stream.of(miarray).forEach(i -> {
            producto[0] = i.getId();
            producto[1] = i.getNombre();
            producto[2] = i.getPrecio();
            producto[3] = i.getStock();
            producto[4] = i.getCreate_at();

            tablaProductoFactura.getDtm().addRow(producto);
        });
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.facturaUpdateProducto = FacturaUpdateProducto.getInstance(tablaProductoFactura);

        if (ae.getSource() == facturaUpdateProducto.getEliminar()) {
            componentesFacturaUpdate.getTablaProductoUD().getDtm().removeRow(facturaUpdateProducto.row);
        } else {
            if (!facturaUpdateProducto.getnProductoCampo().getText().isEmpty()) {
                if (facturaUpdateProducto.getnProductoCampo().getText().matches("^[1-9][0-9]*$")) {
                    if (ae.getSource() == facturaUpdateProducto.getGuardar()) {

                        if (point != null) {
                            Object producto[] = new Object[6];
                            producto[0] = facturaUpdateProducto.producto.getId();
                            producto[1] = facturaUpdateProducto.getnProductoCampo().getText();
                            producto[2] = facturaUpdateProducto.producto.getNombre();
                            producto[3] = facturaUpdateProducto.producto.getPrecio();
                            producto[4] = facturaUpdateProducto.producto.getStock();
                            producto[5] = facturaUpdateProducto.producto.getCreate_at();
                            componentesFacturaUpdate.getTablaProductoUD().getDtm().addRow(producto);
                        } else {
                            JOptionPane.showMessageDialog(null, "   Elija un Producto", "ERROR", 0);
                        }

                    } else if (ae.getSource() == facturaUpdateProducto.getActualizar()) {
                        if (point != null) {
                            facturaUpdateProducto.producto.setId(Short.valueOf(table.getValueAt(table.rowAtPoint(point), 0).toString()));
                            facturaUpdateProducto.producto.setNombre(table.getValueAt(table.rowAtPoint(point), 1).toString());
                            facturaUpdateProducto.producto.setPrecio(Double.valueOf(table.getValueAt(table.rowAtPoint(point), 2).toString()));
                            facturaUpdateProducto.producto.setStock(Integer.valueOf(table.getValueAt(table.rowAtPoint(point), 3).toString()));
                            facturaUpdateProducto.producto.setCreate_at(Date.valueOf(table.getValueAt(table.rowAtPoint(point), 4).toString()));

                            componentesFacturaUpdate.getTablaProductoUD().setValueAt(facturaUpdateProducto.producto.getId(), facturaUpdateProducto.row, 0);
                            componentesFacturaUpdate.getTablaProductoUD().setValueAt(facturaUpdateProducto.getnProductoCampo().getText(), facturaUpdateProducto.row, 1);
                            componentesFacturaUpdate.getTablaProductoUD().setValueAt(facturaUpdateProducto.producto.getNombre(), facturaUpdateProducto.row, 2);
                            componentesFacturaUpdate.getTablaProductoUD().setValueAt(facturaUpdateProducto.producto.getPrecio(), facturaUpdateProducto.row, 3);
                            componentesFacturaUpdate.getTablaProductoUD().setValueAt(facturaUpdateProducto.producto.getStock(), facturaUpdateProducto.row, 4);
                            componentesFacturaUpdate.getTablaProductoUD().setValueAt(facturaUpdateProducto.producto.getCreate_at(), facturaUpdateProducto.row, 5);
                        } else {
                            JOptionPane.showMessageDialog(null, "   Elija un Producto", "ERROR", 0);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "   Cantidad no valida", "ERROR", 0);
                }
            } else {
                JOptionPane.showMessageDialog(null, " Digite cantidad de Productos", "ERROR", 0);
            }
        }

        componentesFacturaUpdate.getnProductosJTF().setText(String.valueOf(componentesFacturaUpdate.getTablaProductoUD().nProductos()));
        componentesFacturaUpdate.getTotalJTF().setText(String.valueOf(componentesFacturaUpdate.getTablaProductoUD().total()));

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.mantenimiento.tabla.factura.create;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import modelo.dao.FacturaDAO;
import modelo.entidades.Producto;
import vista.crud.factura.PrincipalMantenimientoFactura;
import vista.crud.factura.menu.mantenimiento.facturac.ComponentesFacturaCreate;
import vista.crud.factura.menu.mantenimiento.facturac.CompraProducto;
import vista.crud.factura.menu.mantenimiento.facturac.FacturaCreate;

/**
 *
 * @author javier
 */
public class ControladorTablaProducto extends MouseAdapter {

    private final PrincipalMantenimientoFactura principalMantenimientoFactura;
    //private EditorCeldas tableCellRenderer;
    private CompraProducto compraProducto;
    private ComponentesFacturaCreate componentesFacturaCreate;
    private boolean primeravez = true;
    private Producto producto;
    private Double total = 0.0;
    private FacturaDAO facturaDAO;

    public ControladorTablaProducto(PrincipalMantenimientoFactura principalMantenimientoFactura) {
        this.principalMantenimientoFactura = principalMantenimientoFactura;
        this.compraProducto = new CompraProducto();
        this.componentesFacturaCreate = principalMantenimientoFactura.getMenuMantenimientoFactura().getMantenimientoFactura().getMenuMantenimientoFacturaCRUD().getFacturaCreate().getComponentesFacturaCreate();
        producto = new Producto();
        this.facturaDAO = new FacturaDAO();

    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point point = e.getPoint();
        int row = table.rowAtPoint(point);

        if (e.getSource() == componentesFacturaCreate.getTablaProductoFactura()) {
            String id = table.getValueAt(row, 0).toString();
            String nombre = table.getValueAt(row, 1).toString();
            String precio = table.getValueAt(row, 2).toString();
            String stock = "1";
            String creatAt = table.getValueAt(row, 4).toString();
            String nProductos = "1";
            String idFactura = "";

            /*System.out.println(table.getValueAt(row, 2).toString());
        System.out.println(table.getValueAt(row, 3).toString());
        System.out.println(table.getValueAt(row, 4).toString());*/
            if (primeravez) {

                compraProducto.initComponent(id, nombre, nProductos, idFactura, precio);
                compraProducto.getIdProducoJL().setText("ID Producto: ".concat(id));
                compraProducto.getNombreProductoJL().setText("Producto: ".concat(nombre));
                compraProducto.getnProductosJL().setText("NO. Productos: ".concat(nProductos));
                compraProducto.getIfFacturaJL().setText("ID Factura: ".concat(idFactura));
                compraProducto.getTotalJL().setText("Total: ".concat(precio));

                producto.setId(Short.valueOf(id));
                producto.setNombre(nombre);
                producto.setPrecio(Double.valueOf(precio));
                producto.setStock(Integer.valueOf(stock));
                producto.setCreate_at(Date.valueOf(creatAt));

                compraProducto.getTablaProductoGuardado().colocarFilas(producto, row);

                primeravez = false;

            } else {
                compraProducto.getIdProducoJL().setText("ID Producto: ".concat(id));
                compraProducto.getNombreProductoJL().setText("Producto: ".concat(nombre));
                compraProducto.getnProductosJL().setText("NO. Productos: ".concat(nProductos));
                compraProducto.getIfFacturaJL().setText("ID Factura: ".concat(idFactura));
                compraProducto.getTotalJL().setText("Total: ".concat(precio));

                producto.setId(Short.valueOf(id));
                producto.setNombre(nombre);
                producto.setPrecio(Double.valueOf(precio));
                producto.setStock(Integer.valueOf(stock));
                producto.setCreate_at(Date.valueOf(creatAt));

                compraProducto.getTablaProductoGuardado().colocarFilas(producto, row);

                compraProducto.setVisible(true);
            }

            componentesFacturaCreate.getnProductosJTF().setText(String.valueOf(compraProducto.getTablaProductoGuardado().nProductos()));
            componentesFacturaCreate.getTotalJTF().setText(String.valueOf(compraProducto.getTablaProductoGuardado().total()));
        } else {
            String id = table.getValueAt(row, 0).toString();
            componentesFacturaCreate.getIdClienteJTF().setText(String.valueOf(id));
        }

    }

}

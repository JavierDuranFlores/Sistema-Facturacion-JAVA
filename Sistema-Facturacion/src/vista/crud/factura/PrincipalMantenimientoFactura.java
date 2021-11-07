/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura;

import controlador.mantenimiento.ControladorMantenimientoFactura;
import controlador.mantenimiento.tabla.factura.create.ControladorTablaProducto;
import controlador.mantenimiento.tabla.factura.update.ControladorTablaProductoUpdate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import vista.crud.factura.menu.MenuMantenimientoFactura;
import vista.crud.factura.menu.mantenimiento.facturac.TablaProductoFactura;

/**
 *
 * @author javier
 */
public class PrincipalMantenimientoFactura extends JPanel {

    private JPanel contenedor;

    private JPanel panelCenter,
            panelTabla,
            panelFooter;

    private JPanel panelBarraBusqueda;

    private GridBagLayout gbl;
    private GridBagLayout gblPanelConsulta;

    private GridBagConstraints constraints;
    private GridBagConstraints constraintsPanelConsulta;

    /* PANEL MENU, INSTANCIA DE LA BARRA*/
    private MenuMantenimientoFactura menuMantenimientoFactura;
    
    private ControladorTablaProducto controladorTablaProducto;

    private ControladorMantenimientoFactura controladorMantenimientoFactura;
    private ControladorTablaProductoUpdate controladorTablaProductoUpdate;
    
    private static PrincipalMantenimientoFactura principalMantenimientoFactura;
    

    private final Color color = new Color(241, 242, 246);

    private PrincipalMantenimientoFactura() {
        panelCenter();
        agregarMenu();
        inicializarControlador();
    }
    
    public static synchronized PrincipalMantenimientoFactura getInstance() {
        if (principalMantenimientoFactura == null)
            principalMantenimientoFactura = new PrincipalMantenimientoFactura();
        
        return principalMantenimientoFactura;
    }

    private void panelCenter() {
        panelCenter = new JPanel(new BorderLayout());
        panelCenter.setBackground(new Color(223, 230, 233));
        panelCenter.setPreferredSize(new Dimension(800, 590));
        add(panelCenter);
    }

    private void agregarMenu() {
        menuMantenimientoFactura = new MenuMantenimientoFactura();
        panelCenter.add(menuMantenimientoFactura, BorderLayout.CENTER);
    }

    private void inicializarControlador() {
        controladorMantenimientoFactura = new ControladorMantenimientoFactura(this);
        controladorTablaProducto = new ControladorTablaProducto(this);
        controladorTablaProductoUpdate = new ControladorTablaProductoUpdate(new TablaProductoFactura(), this);
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().listadoFactura.getHeaderListadoFactura().buscar.addActionListener(controladorMantenimientoFactura);
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().listadoFactura.getPiePaginaListadoFactura().getComboPaginas().addItemListener(controladorMantenimientoFactura);
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().listadoFactura.getPiePaginaListadoFactura().getComboNRegistros().addItemListener(controladorMantenimientoFactura);
        
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaCreate().getComponentesFacturaCreate().getPaginaPJCB().addItemListener(controladorMantenimientoFactura);
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaCreate().getComponentesFacturaCreate().getRegistrosPJCB().addItemListener(controladorMantenimientoFactura);
    
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaCreate().getComponentesFacturaCreate().getPaginaCJCB().addItemListener(controladorMantenimientoFactura);
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaCreate().getComponentesFacturaCreate().getRegistrosCJCB().addItemListener(controladorMantenimientoFactura);
        
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaCreate().getComponentesFacturaCreate().getTablaProductoFactura().addMouseListener(controladorTablaProducto);
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaCreate().getComponentesFacturaCreate().getTablaClienteFactura().addMouseListener(controladorTablaProducto);
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaCreate().getComponentesFacturaCreate().getGuardarJB().addActionListener(controladorMantenimientoFactura);
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaCreate().getComponentesFacturaCreate().getCancelarJB().addActionListener(controladorMantenimientoFactura);
        
        /* MANTENIMIENTO UPDATE */
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaUpdate().getComponentesFacturaUpdate().getBuscarJB().addActionListener(controladorMantenimientoFactura);
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaUpdate().getComponentesFacturaUpdate().getBotonJB().addActionListener(controladorMantenimientoFactura);
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaUpdate().getComponentesFacturaUpdate().getCancelarJB().addActionListener(controladorMantenimientoFactura);
        /* MANTENIMIENTO DELETE */
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaDelete().getComponentesFacturaDelete().getBuscarJB().addActionListener(controladorMantenimientoFactura);
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaDelete().getComponentesFacturaDelete().getBotonJB().addActionListener(controladorMantenimientoFactura);
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaDelete().getComponentesFacturaDelete().getCancelarJB().addActionListener(controladorMantenimientoFactura);
        
        /* TABLA UPDATE */
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaUpdate().getComponentesFacturaUpdate().getTablaProductoUD().addMouseListener(controladorTablaProductoUpdate);
        
        /* TABLA PRODUCTO FACTURA UPDATE */
        
        this.controladorMantenimientoFactura.principalMantenimientoFactura.getMenuMantenimientoFactura().mantenimientoFactura.getMenuMantenimientoFacturaCRUD().getFacturaUpdate().getComponentesFacturaUpdate().getIdClienteJTF().addMouseListener(controladorMantenimientoFactura);
    }

    public MenuMantenimientoFactura getMenuMantenimientoFactura() {
        return menuMantenimientoFactura;
    }

    public ControladorMantenimientoFactura getControladorMantenimientoCliente() {
        return controladorMantenimientoFactura;
    }

}

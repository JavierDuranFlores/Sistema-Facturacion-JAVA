/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.producto;

import controlador.mantenimiento.ControladorMantenimientoProducto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import vista.crud.producto.menu.MenuMantenimientoProducto;

/**
 *
 * @author javier
 */
public class PrincipalMantenimientoProducto extends JPanel {

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
    private MenuMantenimientoProducto menuMantenimientoProducto;

    private ControladorMantenimientoProducto controladorMantenimientoProducto;

    private final Color color = new Color(241, 242, 246);

    public PrincipalMantenimientoProducto() {
        panelCenter();
        agregarMenu();
        inicializarControlador();
    }

    private void panelCenter() {
        panelCenter = new JPanel(new BorderLayout());
        panelCenter.setBackground(new Color(223, 230, 233));
        panelCenter.setPreferredSize(new Dimension(800, 590));
        add(panelCenter);
    }

    private void agregarMenu() {
        menuMantenimientoProducto = new MenuMantenimientoProducto();
        panelCenter.add(menuMantenimientoProducto, BorderLayout.CENTER);
    }

    private void inicializarControlador() {
        controladorMantenimientoProducto = new ControladorMantenimientoProducto(this);

        this.controladorMantenimientoProducto.principalMantenimientoProducto.menuMantenimientoProducto.getListadoProducto().getPiePaginaListadoProducto().getComboPaginas().addItemListener(controladorMantenimientoProducto);
        this.controladorMantenimientoProducto.principalMantenimientoProducto.menuMantenimientoProducto.getListadoProducto().getPiePaginaListadoProducto().getComboNRegistros().addItemListener(controladorMantenimientoProducto);
        this.controladorMantenimientoProducto.principalMantenimientoProducto.menuMantenimientoProducto.getListadoProducto().getHeaderListadoProducto().getBuscar().addActionListener(controladorMantenimientoProducto);
        this.controladorMantenimientoProducto.principalMantenimientoProducto.menuMantenimientoProducto.getMantenimientoProducto().getMenuMantenimientoProductoCRUD().getProductoCreate().getComponentesProductoCreate().getGuardarJB().addActionListener(controladorMantenimientoProducto);
        this.controladorMantenimientoProducto.principalMantenimientoProducto.menuMantenimientoProducto.getMantenimientoProducto().getMenuMantenimientoProductoCRUD().getProductoCreate().getComponentesProductoCreate().getCancelarJB().addActionListener(controladorMantenimientoProducto);
        this.controladorMantenimientoProducto.principalMantenimientoProducto.menuMantenimientoProducto.getMantenimientoProducto().getMenuMantenimientoProductoCRUD().getProductoUpdate().getComponentesProductoUpdate().getBuscarJB().addActionListener(controladorMantenimientoProducto);
        this.controladorMantenimientoProducto.principalMantenimientoProducto.menuMantenimientoProducto.getMantenimientoProducto().getMenuMantenimientoProductoCRUD().getProductoUpdate().getComponentesProductoUpdate().getBotonJB().addActionListener(controladorMantenimientoProducto);
        this.controladorMantenimientoProducto.principalMantenimientoProducto.menuMantenimientoProducto.getMantenimientoProducto().getMenuMantenimientoProductoCRUD().getProductoUpdate().getComponentesProductoUpdate().getCancelarJB().addActionListener(controladorMantenimientoProducto);
        this.controladorMantenimientoProducto.principalMantenimientoProducto.menuMantenimientoProducto.getMantenimientoProducto().getMenuMantenimientoProductoCRUD().getProductoDelete().getComponentesProductoDelete().getBuscarJB().addActionListener(controladorMantenimientoProducto);
        this.controladorMantenimientoProducto.principalMantenimientoProducto.menuMantenimientoProducto.getMantenimientoProducto().getMenuMantenimientoProductoCRUD().getProductoDelete().getComponentesProductoDelete().getBotonJB().addActionListener(controladorMantenimientoProducto);
        this.controladorMantenimientoProducto.principalMantenimientoProducto.menuMantenimientoProducto.getMantenimientoProducto().getMenuMantenimientoProductoCRUD().getProductoDelete().getComponentesProductoDelete().getCancelarJB().addActionListener(controladorMantenimientoProducto);
    }

    public MenuMantenimientoProducto getMenuMantenimientoProducto() {
        return menuMantenimientoProducto;
    }

    public ControladorMantenimientoProducto getControladorMantenimientoProducto() {
        return controladorMantenimientoProducto;
    }

}

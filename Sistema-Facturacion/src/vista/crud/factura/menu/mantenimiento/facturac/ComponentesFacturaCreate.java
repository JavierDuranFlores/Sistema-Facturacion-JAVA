/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.crud.factura.menu.mantenimiento.facturac;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import modelo.dao.ClienteDAO;
import modelo.dao.FacturaDAO;
import modelo.dao.ProductoDAO;
import vista.crud.producto.menu.listado.contenido.TablaProducto;

/**
 *
 * @author javier
 */
public class ComponentesFacturaCreate extends JPanel{
    
    private JLabel idJL,
                   nProductosJL,
                   totalJL,
                   idClienteJL,
                   createAtJL,
                   tituloTablaProducto,
                   tituloTablaCliente,
                   paginaProductoJL,
                   paginaClienteJL,
                   regitrosJL;
    
    private JLabel mensaje;
    
    private JTextField idJTF,
                       nProductosJTF,
                       totalJTF,
                       idClienteJTF,
                       createAtJTF;
    
    private JPanel idJP,
                   nProductosJP,
                   totalJP,
                   idClienteJP,
                   createAtJP,
                   tablaProductoJP,
                   tablaClienteJP,
                   tituloProductoJP,
                   tituloClienteJP,
                   paginarJP,
                   botones;
    
    private JButton guardarJB,
                    cancelarJB;
    
    private JComboBox paginaPJCB, registrosPJCB;
    private JComboBox paginaCJCB, registrosCJCB;

    private TablaProductoFactura tablaProductoFactura;
    
    private TablaClienteFactura tablaClienteFactura;
    
    private GridBagConstraints constraints;
    
    private FacturaDAO facturaDAO;
    private ProductoDAO productoDAO;
    private ClienteDAO clienteDAO;
    
    private final Object registros[] = {10, 20, 50, 100, 200, 500};
    
    int x = 0;
    int y = 0;
    int w = 2;
    int h = 1;
    
    
    public ComponentesFacturaCreate() {
        facturaDAO = new FacturaDAO();
        productoDAO = new ProductoDAO();
        clienteDAO = new ClienteDAO();
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        colocarFormId();
        colocarFormNProductos();
        colocarFormTotal();
        colocarFormIdCliente();
        //colocarFormCreateAt();
        
        colocarPanelTablaCliente();
        colocarPaginadorCliente();
        
        colocarPanelTablaProducto();
        colocarPaginadorProducto();
        
        
        /*colocarFormTelefono();*/
        colocarMensaje();
        colocarBotones();
        
        paginarProducto();
        paginarClientes();
    }
    
    private void colocarFormId() {
        idJL = new JLabel("ID Factura(*)");
        idJTF = new JTextField(12);
        idJTF.setEditable(false);
        idJTF.setBackground(Color.WHITE);
        getIdJTF().setText(String.valueOf(facturaDAO.getLeer().ultimoId("facturas")+1));
        
        idJP = new JPanel(new GridLayout(1, 2));
        
        idJP.add(idJL);
        idJP.add(idJTF);
        
        add(idJP, colocarContrains());
    }
    
    private void colocarFormNProductos() {
        nProductosJL = new JLabel("NO. Productos(*)");
        nProductosJTF = new JTextField(12);
        nProductosJTF.setEditable(false);
        nProductosJTF.setBackground(Color.WHITE);
        
        nProductosJP = new JPanel(new GridLayout(1, 2));
        
        nProductosJP.add(nProductosJL);
        nProductosJP.add(nProductosJTF);
        
        add(nProductosJP, colocarContrains());
    }
    
    private void colocarFormTotal() {
        totalJL = new JLabel("Total(*)");
        totalJTF = new JTextField(12);
        totalJTF.setBackground(Color.WHITE);
        totalJTF.setEditable(false);
        
        totalJP = new JPanel(new GridLayout(1, 2));
        
        totalJP.add(totalJL);
        totalJP.add(totalJTF);
        
        add(totalJP, colocarContrains());
    }
    
    private void colocarFormIdCliente() {
        idClienteJL = new JLabel("ID Cliente(*)");
        idClienteJTF = new JTextField(12);
        idClienteJTF.setBackground(Color.WHITE);
        idClienteJTF.setEditable(false);
        
        idClienteJP = new JPanel(new GridLayout(1, 2));
        
        idClienteJP.add(idClienteJL);
        idClienteJP.add(idClienteJTF);
        
        add(idClienteJP, colocarContrains());
    }
    
    private void colocarFormCreateAt() {
        createAtJL = new JLabel("Fecha(*)");
        createAtJTF = new JTextField(12);
        
        createAtJP = new JPanel(new GridLayout(1, 2));
        
        createAtJP.add(createAtJL);
        createAtJP.add(createAtJTF);
        
        add(createAtJP, colocarContrains());
    }
    
    private void colocarPanelTablaProducto() {
        tablaProductoJP = new JPanel(new BorderLayout());
        tablaProductoJP.setPreferredSize(new Dimension(380, 160));
        
        tablaProductoFactura = new TablaProductoFactura();
        
        tituloProductoJP = new JPanel();
        tituloTablaProducto = new JLabel("PRODUCTOS");
        tituloProductoJP.add(tituloTablaProducto);
        
        tablaProductoJP.add(tituloProductoJP, BorderLayout.NORTH);
        
        tablaProductoJP.add(tablaProductoFactura, BorderLayout.CENTER);
        tablaProductoJP.add(new JScrollPane(tablaProductoFactura),BorderLayout.CENTER);
        
        add(tablaProductoJP, colocarContrains());
    }
    
    private void colocarPaginadorProducto() {
        paginaProductoJL = new JLabel("#Paginas");
        paginaProductoJL.setFont(new Font("Arial", 1, 9));
        regitrosJL = new JLabel("Total de registros por pagina");
        regitrosJL.setFont(new Font("Arial", 1, 9));
        
        paginaPJCB = new JComboBox();
        registrosPJCB = new JComboBox(registros);
        
        paginarJP = new JPanel();
        
        paginarJP.add(paginaProductoJL);
        paginarJP.add(paginaPJCB);
        paginarJP.add(regitrosJL);
        paginarJP.add(registrosPJCB);
        
        tablaProductoJP.add(paginarJP, BorderLayout.SOUTH);
    }
    
    private void colocarPanelTablaCliente() {
        tablaClienteJP = new JPanel(new BorderLayout());
        tablaClienteJP.setPreferredSize(new Dimension(380, 160));
        tablaClienteFactura = new TablaClienteFactura();
        tituloClienteJP = new JPanel();
        tituloTablaCliente = new JLabel("CLIENTES");
        tituloClienteJP.add(tituloTablaCliente);
        tablaClienteJP.add(tituloClienteJP, BorderLayout.NORTH);
        tablaClienteJP.add(tablaClienteFactura, BorderLayout.CENTER);
        tablaClienteJP.add(new JScrollPane(tablaClienteFactura),BorderLayout.CENTER);
        add(tablaClienteJP, colocarContrains());
    }
    
    private void colocarPaginadorCliente() {
        paginaClienteJL = new JLabel("#Paginas");
        paginaClienteJL.setFont(new Font("Arial", 1, 9));
        regitrosJL = new JLabel("Total de registros por pagina");
        regitrosJL.setFont(new Font("Arial", 1, 9));
        
        paginaCJCB = new JComboBox();
        registrosCJCB = new JComboBox(registros);
        
        paginarJP = new JPanel();
        
        paginarJP.add(paginaClienteJL);
        paginarJP.add(paginaCJCB);
        paginarJP.add(regitrosJL);
        paginarJP.add(registrosCJCB);
        
        tablaClienteJP.add(paginarJP, BorderLayout.SOUTH);
    }
    
    /*private void colocarFormTelefono() {
        telefonoJL = new JLabel("Telefono(*)");
        telefonoJTF = new JTextField(12);
        
        telefonoJP = new JPanel(new GridLayout(1, 2));
        
        telefonoJP.add(telefonoJL);
        telefonoJP.add(telefonoJTF);
        
        add(telefonoJP, colocarContrains());
    }*/
    
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
        constraints.insets.top = 10;
        y++;
        
        return constraints;
        
    }
    
    public void borrarDatosComboProductos(int e) {
            for (int i = 0; i < e+1; i++) {
                getPaginaPJCB().removeItem(i);
            }
    }
    
    public void paginarProducto() {
        
        Object paginas[];
        int cont=0;
        if (getPaginaPJCB().getSelectedItem() == null){
            paginas = productoDAO.getLeer().paginas(10, "productos");
        }else {
            paginas = productoDAO.getLeer().paginas(Integer.valueOf(getRegistrosPJCB().getSelectedItem().toString()), "productos");
            cont = getPaginaPJCB().getItemCount();
        }
        for (int i = 0; i < paginas.length; i++) {
            this.getPaginaPJCB().addItem(paginas[i]);
        }
        
        borrarDatosComboProductos(cont);
            
    }
    
    public void limpiarTabla() {
        int nColumn = tablaProductoFactura.getDtm().getRowCount() - 1;
        if (nColumn >= 0) {
            for (int i = nColumn; i >= 0; i--) {
                tablaProductoFactura.getDtm().removeRow(i);
            }
        }

    }

    /*--*/
    
    public void borrarDatosComboClientes(int e) {
            for (int i = 0; i < e+1; i++) {
                getPaginaCJCB().removeItem(i);
            }
    }
    
    public void paginarClientes() {
        
        Object paginas[];
        int cont=0;
        if (getPaginaCJCB().getSelectedItem() == null){
            paginas = clienteDAO.getLeer().paginas(10, "clientes");
        }else {
            paginas = clienteDAO.getLeer().paginas(Integer.valueOf(getRegistrosCJCB().getSelectedItem().toString()), "clientes");
            cont = getPaginaCJCB().getItemCount();
        }
        for (int i = 0; i < paginas.length; i++) {
            this.getPaginaCJCB().addItem(paginas[i]);
        }
        
        borrarDatosComboClientes(cont);
            
    }
    
    public void limpiarTablaClientes() {
        int nColumn = tablaClienteFactura.getDtm().getRowCount() - 1;
        if (nColumn >= 0) {
            for (int i = nColumn; i >= 0; i--) {
                tablaClienteFactura.getDtm().removeRow(i);
            }
        }

    }
    
    public JComboBox getPaginaPJCB() {
        return paginaPJCB;
    }

    public JComboBox getRegistrosPJCB() {
        return registrosPJCB;
    }

    public JComboBox getPaginaCJCB() {
        return paginaCJCB;
    }

    public JComboBox getRegistrosCJCB() {
        return registrosCJCB;
    }

    public TablaProductoFactura getTablaProductoFactura() {
        return tablaProductoFactura;
    }

    public TablaClienteFactura getTablaClienteFactura() {
        return tablaClienteFactura;
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

    public JTextField getCreateAtJTF() {
        return createAtJTF;
    }

    public JButton getGuardarJB() {
        return guardarJB;
    }

    public JButton getCancelarJB() {
        return cancelarJB;
    }

    public JPanel getTablaClienteJP() {
        return tablaClienteJP;
    }

    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.query;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.entidades.Cliente;
import modelo.entidades.Factura;
import modelo.entidades.FacturaItem;
import modelo.entidades.Producto;
import modelo.entidades.Reporte;

/**
 *
 * @author javier
 */
public class Leer {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private Object paginas[] = null;

    public Leer() {
        CON = Conexion.getInstacia();
    }

    public List<Object> leer(String tabla) {
        List<Object> lista = new ArrayList<>();
        try {
            if (tabla.equals("cliente")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM leer_clientes ();");
                rs = ps.executeQuery();

                while (rs.next()) {
                    Cliente cliente = new Cliente(rs.getShort(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getDate(9));

                    lista.add(cliente);
                }

            } else if (tabla.equals("productos")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM leer_productos ();");
                rs = ps.executeQuery();

                while (rs.next()) {
                    Producto producto = new Producto(rs.getShort(1),
                            rs.getString(2),
                            rs.getDouble(3),
                            rs.getInt(4),
                            rs.getDate(5));

                    lista.add(producto);
                }
            } else if (tabla.equals("facturas")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM leer_facturas ();");
                rs = ps.executeQuery();

                while (rs.next()) {
                    Factura factura = new Factura(rs.getShort(1),
                            rs.getShort(2),
                            rs.getDouble(3),
                            rs.getShort(4),
                            rs.getDate(5));

                    lista.add(factura);
                }
            } else if (tabla.equals("facturas_items")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM leer_facturas_items();");
                rs = ps.executeQuery();
                int idf = 0;
                while (rs.next()) {

                    /* idfi | cantidadfi */
                    FacturaItem facturaItem = new FacturaItem(rs.getShort(6), rs.getInt(7));
                    List<Producto> productos = new ArrayList<>();
                    List<Factura> facturas = new ArrayList<>();
                    //while (rs.getShort(1) == idf) {

                    /* idp | nombrep | preciop | stockp | cap */
                    productos.add(new Producto(rs.getShort(8),
                            rs.getString(9),
                            rs.getDouble(10),
                            rs.getInt(11),
                            rs.getDate(12)));
                    facturaItem.setProductos(productos);

                    /*  idf | np | totalf | idc | caf |*/
                    facturas.add(new Factura(rs.getShort(1),
                            rs.getShort(2),
                            rs.getDouble(3),
                            rs.getShort(4),
                            rs.getDate(5)));
                    facturaItem.setFacturas(facturas);

                    //}
                    //idf++;
                    lista.add(facturaItem);
                }
            }

            CON.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return lista;
    }

    public List<Object> filtrar(String tipo, String busqueda, String tabla) {
        List<Object> lista = new ArrayList<>();
        if (tipo.equals("fecha")) {
            tipo = "create_at";
        }
        try {
            if (tabla.equals("clientes")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM filtrar_clientes (?, ?);");
                ps.setString(1, tipo);
                ps.setString(2, busqueda);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Cliente cliente = new Cliente(rs.getShort(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getDate(9));

                    lista.add(cliente);

                }
            } else if (tabla.equals("productos")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM filtrar_productos (?, ?);");
                ps.setString(1, tipo);
                ps.setString(2, busqueda);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Producto producto = new Producto(rs.getShort(1),
                            rs.getString(2),
                            rs.getDouble(3),
                            rs.getInt(4),
                            rs.getDate(5));

                    lista.add(producto);

                }
            } else if (tabla.equals("productosFactura")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM filtrar_productos_factura (?);");
                ps.setString(1, busqueda);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Producto producto = new Producto(rs.getShort(1),
                            rs.getString(2),
                            rs.getDouble(3),
                            rs.getInt(4),
                            rs.getDate(5));

                    lista.add(producto);

                }
            } else if (tabla.equals("facturas")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM filtrar_facturas (?, ?);");
                ps.setString(1, tipo);
                ps.setString(2, busqueda);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Factura producto = new Factura(rs.getShort(1),
                            rs.getShort(2),
                            rs.getDouble(3),
                            rs.getShort(4),
                            rs.getDate(5));

                    lista.add(producto);

                }
            } else if (tabla.equals("facturas_items")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM filtrar_facturas_items_ByIdFactura(?);");
                ps.setString(1, busqueda);
                rs = ps.executeQuery();
                while (rs.next()) {

                    /* idfi | cantidadfi */
                    FacturaItem facturaItem = new FacturaItem(rs.getShort(6), rs.getInt(7));
                    List<Producto> productos = new ArrayList<>();
                    List<Factura> facturas = new ArrayList<>();
                    //while (rs.getShort(1) == idf) {

                    /* idp | nombrep | preciop | stockp | cap */
                    productos.add(new Producto(rs.getShort(8),
                            rs.getString(9),
                            rs.getDouble(10),
                            rs.getInt(11),
                            rs.getDate(12)));
                    facturaItem.setProductos(productos);

                    /*  idf | np | totalf | idc | caf |*/
                    facturas.add(new Factura(rs.getShort(1),
                            rs.getShort(2),
                            rs.getDouble(3),
                            rs.getShort(4),
                            rs.getDate(5)));
                    facturaItem.setFacturas(facturas);

                    //}
                    //idf++;
                    lista.add(facturaItem);
                }
            }

            CON.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return lista;
    }

    public Object filtrar(int id, String tabla) {
        Object objeto = null;
        try {
            if (tabla.equals("clientes")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM filtrar_clientes (?);");
                ps.setInt(1, id);
                rs = ps.executeQuery();

                while (rs.next()) {
                    objeto = new Cliente(rs.getShort(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getDate(9));

                }
            } else if (tabla.equals("productos")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM filtrar_productos (?);");
                ps.setInt(1, id);
                rs = ps.executeQuery();

                while (rs.next()) {
                    objeto = new Producto(rs.getShort(1),
                            rs.getString(2),
                            rs.getDouble(3),
                            rs.getInt(4),
                            rs.getDate(5));

                }
            } else if (tabla.equals("facturasId")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM filtrar_facturas (?);");
                ps.setInt(1, id);
                rs = ps.executeQuery();

                while (rs.next()) {
                    objeto = new Factura(rs.getShort(1),
                            rs.getShort(2),
                            rs.getDouble(3),
                            rs.getShort(4),
                            rs.getDate(5));

                }
            } else if (tabla.equals("facturas_items")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM filtrar_facturas (?);");
                ps.setInt(1, id);
                rs = ps.executeQuery();

                while (rs.next()) {
                    objeto = new Factura(rs.getShort(1),
                            rs.getShort(2),
                            rs.getDouble(3),
                            rs.getShort(4),
                            rs.getDate(5));

                }
            }

            CON.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return objeto;
    }
    
    public int filtarUsuario(String usuario) {
        int id = 0;
        try {
            
            ps = CON.conectar().prepareStatement("SELECT * FROM filtrar_usuario (?);");
                ps.setString(1, usuario);
                rs = ps.executeQuery();

                while (rs.next()) {
                    id = rs.getInt(1);
                }
            
        } catch (Exception e) {
        }
        return id;
    }

    public List<Object> paginar(String limite, String pagina, String tabla) {
        List<Object> lista = new ArrayList<>();
        try {
            if (tabla.equals("clientes")) {

                ps = CON.conectar().prepareStatement("SELECT * FROM consulta_paginada_clientes (?, ?);");
                ps.setString(1, limite);
                ps.setString(2, pagina);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Cliente cliente = new Cliente(rs.getShort(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getDate(9));

                    lista.add(cliente);
                }

            } else if (tabla.equals("productos")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM consulta_paginada_productos (?, ?);");
                ps.setString(1, limite);
                ps.setString(2, pagina);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Producto producto = new Producto(rs.getShort(1),
                            rs.getString(2),
                            rs.getDouble(3),
                            rs.getInt(4),
                            rs.getDate(5));

                    lista.add(producto);
                }

            } else {
                ps = CON.conectar().prepareStatement("SELECT * FROM consulta_paginada_facturas (?, ?);");
                ps.setString(1, limite);
                ps.setString(2, pagina);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Factura factura = new Factura(rs.getShort(1),
                            rs.getShort(2),
                            rs.getDouble(3),
                            rs.getShort(4),
                            rs.getDate(5));

                    lista.add(factura);
                }
            }
            CON.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace(System.out);
        }

        return lista;
    }
    
    public List<Object> paginarFacturaByCliente(String limite, String pagina, String idCliente) {
        List<Object> lista = new ArrayList<>();
        try{
        ps = CON.conectar().prepareStatement("SELECT * FROM consulta_paginada_facturas_byIdCliente (?, ?, ?);");
                ps.setString(1, limite);
                ps.setString(2, pagina);
                ps.setString(3, idCliente);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Factura factura = new Factura(rs.getShort(1),
                            rs.getShort(2),
                            rs.getDouble(3),
                            rs.getShort(4),
                            rs.getDate(5));

                    lista.add(factura);
                }
            
            CON.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace(System.out);
        }

        return lista;
    }

    public int numeroRegistrosTabla(String tabla) {

        int registros = 0;

        try {
            if (tabla.equals("clientes")) {
                ps = CON.conectar().prepareStatement("SELECT COUNT(*) FROM clientes;");
            } else if (tabla.equals("productos")) {
                ps = CON.conectar().prepareStatement("SELECT COUNT(*) FROM productos;");
            } else {
                ps = CON.conectar().prepareStatement("SELECT COUNT(*) FROM facturas;");
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                registros = rs.getInt(1);
            }
            CON.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return registros;
    }

    public int ultimoId(String tabla) {
        int registros = 0;

        try {
            ps = CON.conectar().prepareStatement("SELECT id_factura FROM facturas ORDER BY id_factura DESC LIMIT 1;");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros = rs.getInt(1);
            }
        } catch (SQLException e) {
        }

        return registros;
    }

    public Object[] paginas(int limite, String tabla) {
        int pagina = numeroRegistrosTabla(tabla) / limite;
        int mod = numeroRegistrosTabla(tabla) % limite;
        if (mod != 0) {
            pagina = pagina + 1;
        }

        paginas = new Object[pagina];
        for (int i = 0; i < paginas.length; i++) {
            paginas[i] = i + 1;
        }

        return paginas;
    }

    public int numeroRegistrosTablaFacturaByCliente(String idCliente) {

        int registros = 0;

        try {
            ps = CON.conectar().prepareStatement("SELECT COUNT(*) FROM filtrar_facturas_byCliente ('?');");
            ps.setString(1, idCliente);
            rs = ps.executeQuery();

            while (rs.next()) {
                registros = rs.getInt(1);
            }
            CON.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return registros;
    }

    public Object[] paginasByIdCliente(int limite, String idCliente) {
        int pagina = numeroRegistrosTablaFacturaByCliente(idCliente) / limite;
        int mod = numeroRegistrosTablaFacturaByCliente(idCliente) % limite;
        if (mod != 0) {
            pagina = pagina + 1;
        }

        paginas = new Object[pagina];
        for (int i = 0; i < paginas.length; i++) {
            paginas[i] = i + 1;
        }

        return paginas;
    }

    public Object[] getPaginas() {
        return paginas;
    }

    public List<Object> filtarByDate(String fi, String ff, String tabla) {
        List<Object> lista = new ArrayList<>();
        try {
            if (tabla.equalsIgnoreCase("clientes")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM filtar_clientes_creatAT(?, ?);");

                ps.setString(1, fi);
                ps.setString(2, ff);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Cliente cliente = new Cliente(rs.getShort(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getDate(9));
                    lista.add(cliente);
                }
            } else if (tabla.equalsIgnoreCase("productos")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM filtar_productos_creatAT(?, ?);");

                ps.setString(1, fi);
                ps.setString(2, ff);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Producto producto = new Producto(rs.getShort(1),
                            rs.getString(2),
                            rs.getDouble(3),
                            rs.getInt(4),
                            rs.getDate(5));

                    lista.add(producto);
                }

            }
            if (tabla.equalsIgnoreCase("facturas")) {
                ps = CON.conectar().prepareStatement("SELECT * FROM filtar_facturas_creatAT(?, ?);");

                ps.setString(1, fi);
                ps.setString(2, ff);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Factura factura = new Factura(rs.getShort(1),
                            rs.getShort(2),
                            rs.getDouble(3),
                            rs.getShort(4),
                            rs.getDate(5));

                    lista.add(factura);
                }
            }

        } catch (SQLException e) {
        }

        return lista;
    }

    public List<Reporte> reporte(String idCliente, String idFactura) {

        List<Reporte> lista = new ArrayList<>();

        try {
            ps = CON.conectar().prepareStatement("SELECT * FROM leer_facturas_items_clientes(?, ?);");

            ps.setString(1, idCliente);
            ps.setString(2, idFactura);
            rs = ps.executeQuery();

            while (rs.next()) {
                String idFacturaItem = String.valueOf(rs.getShort(3));
                String idProducto = String.valueOf(rs.getShort(4));
                Factura factura = (Factura) this.filtrar(Integer.valueOf(idFactura), "facturasId");
                Cliente cliente = (Cliente) this.filtrar(Integer.valueOf(idCliente), "clientes");
                List<FacturaItem> facturaItem = (List<FacturaItem>) ((List< ?>) this.filtrar("", idFactura, "facturas_items"));
                List<Producto> listaProductos = (List<Producto>) ((List< ?>) this.filtrar("", idFactura, "productosFactura"));
                Reporte reporte = new Reporte(factura, cliente, facturaItem, listaProductos);

                lista.add(reporte);
            }
        } catch (Exception e) {
        }

        return lista;
    }

}

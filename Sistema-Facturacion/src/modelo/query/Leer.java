/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.entidades.Cliente;
import modelo.entidades.Producto;

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

            } else {
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
            }
            
            CON.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return objeto;
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
                ps = CON.conectar().prepareStatement("SELECT COUNT(*) FROM clientes;");
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                registros = rs.getInt(1);
            }
            CON.desconectar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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

    public Object[] getPaginas() {
        return paginas;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.entidades.Cliente;

/**
 *
 * @author javier
 */
public class LeerCliente {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private Object paginas[] = null;

    public LeerCliente() {
        CON = Conexion.getInstacia();
    }

    public List<Cliente> leer() {
        List<Cliente> listaCliente = new ArrayList<>();
        try {
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

                listaCliente.add(cliente);
            }
            CON.desconectar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return listaCliente;
    }

    public  List<Cliente> filtrar(String tipo,String busqueda) {
        List<Cliente> lista = new ArrayList<>();
        Cliente cliente = null;
        if (tipo.equals("fecha")) {
            tipo = "create_at";
        }
        try {
            ps = CON.conectar().prepareStatement("SELECT * FROM filtrar_clientes (?, ?);");
            ps.setString(1, tipo);
            ps.setString(2, busqueda);
            rs = ps.executeQuery();

            while (rs.next()) {
                cliente = new Cliente(rs.getShort(1),
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
            CON.desconectar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return lista;
    }

    public Cliente filtrar(int id) {
        Cliente cliente = null;
        try {
            ps = CON.conectar().prepareStatement("SELECT * FROM filtrar_clientes (?);");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                cliente = new Cliente(rs.getShort(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9));

            }
            CON.desconectar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return cliente;
    }

    public List<Cliente> paginar(String limite, String pagina) {
        List<Cliente> listaCliente = new ArrayList<>();
        try {
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

                listaCliente.add(cliente);
            }

            paginas(Integer.parseInt(limite));
            CON.desconectar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return listaCliente;
    }

    public int numeroRegistrosTabla() {
        int registros = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT COUNT(*) FROM clientes;");
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

    public Object[] paginas(int limite) {
        int pagina = numeroRegistrosTabla() / limite;
        int mod = numeroRegistrosTabla() % limite;
        if (mod != 0) {
            pagina = pagina + 1;
        }

        paginas = new Object[pagina];
        //System.out.println("Leer Cliente");
        //System.out.println(pagina);
        for (int i = 0; i < paginas.length; i++) {
            paginas[i] = i + 1;
        }

        return paginas;
    }

    public Object[] getPaginas() {
        return paginas;
    }

}

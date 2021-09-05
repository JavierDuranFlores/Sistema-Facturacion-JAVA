/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.login;

import controlador.ControladorLogin;
import vista.utilidades.RedondearBoton;
import vista.utilidades.RedondearTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import vista.utilidades.RedondearPasswordField;

/**
 *
 * @author javier
 */
public class LoginVentana {

    private JFrame ventana;
    private JPanel panel;

    private GridBagLayout gbl;
    protected GridBagConstraints constraints;

    protected JPanel panelLoginRegistro;

    protected JLabel etiquetaTitulo;

    protected JLabel etiquetaImagenUsername;
    protected JPanel panelUsername;

    protected JLabel etiquetaImagenPassword;
    protected JPanel panelPassword;

    protected RedondearTextField campoUsername;
    protected RedondearPasswordField campoPassword;

    protected JPanel panelAyuda;
    protected RedondearBoton botonContraseñaOlvidada;
    protected RedondearBoton botonCrearCuenta;

    protected JPanel panelBotonLogin;
    protected RedondearBoton botonLogin;

    protected ControladorLogin controladorLogin;

    protected JRadioButton mostrarContra;

    private final Color colorLogin = new Color(0, 98, 102);
    
    
    public void iniciarComponentes() {
        ventana();
        panel();
        panelLoginRegistro();
        layout();
        inicializarControlado();
        colocarEtiqueta();
        panelUsername();
        campoUsername();
        panelPassword();
        campoPassword();
        radioBotonMostrarContra();
        panelBotonLogin();
        botonLogin();
        panelAyuda();
        botonContraseñaOlvidada();
        botonCrearCuenta();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }

    public void ventana() {

        ventana = new JFrame();

        ventana.setSize(350, 350);

        ventana.setLocationRelativeTo(null);

    }

    private void panel() {

        panel = new JPanel(new BorderLayout());
        panel.setBackground(colorLogin);

        ventana.add(panel);

    }

    private void panelLoginRegistro() {

        panelLoginRegistro = new JPanel();
        panelLoginRegistro.setBackground(colorLogin);

        panel.add(panelLoginRegistro, BorderLayout.CENTER);

    }

    private void layout() {

        gbl = new GridBagLayout();
        constraints = new GridBagConstraints();

        panelLoginRegistro.setLayout(gbl);

    }
    
    private void inicializarControlado() {
        controladorLogin = new ControladorLogin(this);
    }
    
    private GridBagConstraints colocarPanelesLugar(int x, int y, int w, int h) {

        constraints.gridx = x; // El área de texto empieza en la columna cero.
        constraints.gridy = y; // El área de texto empieza en la fila cero
        constraints.gridwidth = w; // El área de texto ocupa dos columnas.
        constraints.gridheight = h; // El área de texto ocupa 2 filas.
        constraints.insets.bottom = 1; // El area de texto esta separado en el bottom por 1 fila

        return this.constraints;

    }
    
    public void colocarEtiqueta() {

        etiquetaTitulo = new JLabel("INICIO SESIÓN");
        etiquetaTitulo.setFont(new Font("Verdana", 1, 22));
        etiquetaTitulo.setForeground(new Color(255, 255, 255));
        constraints = colocarPanelesLugar(0, 0, 5, 2);
        constraints.insets.bottom = 15;

        panelLoginRegistro.add(etiquetaTitulo, constraints);

    }

    private void panelUsername() {

        panelUsername = new JPanel(new FlowLayout());
        panelUsername.setBackground(colorLogin);

        panelLoginRegistro.add(panelUsername, colocarPanelesLugar(1, 3, 3, 1));

    }

    public void campoUsername() {

        campoUsername = new RedondearTextField(15);
        campoUsername.setFont(new Font("Tahoma", 1, 10));
        campoUsername.setImage(new ImageIcon("src//img//user.png").getImage());
        campoUsername.setBackground(new Color(223, 230, 233));

        panelUsername.add(campoUsername);

    }

    private void panelPassword() {

        panelPassword = new JPanel(new FlowLayout());
        panelPassword.setBackground(colorLogin);

        panelLoginRegistro.add(panelPassword, colocarPanelesLugar(1, 4, 3, 1));

    }

    public void campoPassword() {

        campoPassword = new RedondearPasswordField(15);
        campoPassword.setFont(new Font("Tahoma", 1, 10));
        campoPassword.setImage(new ImageIcon("src//img//password.png").getImage());
        campoPassword.setBackground(new Color(223, 230, 233));

        panelPassword.add(campoPassword);

    }

    private void radioBotonMostrarContra() {
        mostrarContra = new JRadioButton();
        mostrarContra.setBackground(colorLogin);
        panelLoginRegistro.add(mostrarContra, colocarPanelesLugar(5, 4, 1, 1));
        mostrarContra.addActionListener(controladorLogin);
    }

    public void panelBotonLogin() {

        panelBotonLogin = new JPanel(new FlowLayout());
        panelBotonLogin.setBackground(colorLogin);
        constraints = colocarPanelesLugar(0, 5, 4, 1);
        constraints.insets.top = 15;

        panelLoginRegistro.add(panelBotonLogin, constraints);

    }

    public void botonLogin() {

        botonLogin = new RedondearBoton(179, 30);
        botonLogin.setFont(new Font("Tahoma", 1, 10));
        botonLogin.setText("INICIAR SESSIÓN");
        botonLogin.setBackground(new Color(223, 230, 233));

        panelBotonLogin.add(botonLogin);

        botonLogin.addActionListener(controladorLogin);

    }

    private void panelAyuda() {

        panelAyuda = new JPanel(new GridLayout(1, 2));
        panelAyuda.setBackground(colorLogin);
        panel.add(panelAyuda, BorderLayout.SOUTH);

    }

    private void botonContraseñaOlvidada() {

        botonContraseñaOlvidada = new RedondearBoton(105, 20);

        botonContraseñaOlvidada.setText("Olvide la contraseña");
        botonContraseñaOlvidada.setFont(new Font("Tahoma", 1, 8));
        botonContraseñaOlvidada.setForeground(new Color(223, 230, 233));
        botonContraseñaOlvidada.setBackground(new Color(41, 128, 185));

        JPanel panelFlow = new JPanel(new FlowLayout());
        panelFlow.setBackground(colorLogin);
        panelFlow.add(botonContraseñaOlvidada);

        panelAyuda.add(panelFlow);

    }

    private void botonCrearCuenta() {

        botonCrearCuenta = new RedondearBoton(80, 20);
        botonCrearCuenta.setText("Crear Cuenta");
        botonCrearCuenta.setFont(new Font("Tahoma", 1, 8));
        botonCrearCuenta.setForeground(new Color(223, 230, 233));
        botonCrearCuenta.setBackground(new Color(41, 128, 185));
        JPanel panelFlow = new JPanel(new FlowLayout());
        panelFlow.setBackground(colorLogin);

        panelFlow.add(botonCrearCuenta);

        panelAyuda.add(panelFlow);

    }

    public RedondearTextField getCampoUsername() {
        return campoUsername;
    }

    public RedondearPasswordField getCampoPassword() {
        return campoPassword;
    }

    public JFrame getVentana() {
        return ventana;
    }

    public JButton getBotonLogin() {
        return botonLogin;
    }

    public JRadioButton getMostrarContra() {
        return mostrarContra;
    }

    
}

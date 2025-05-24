package shop.swing;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class TiendaVirtualGUI extends JFrame {

    //labels
    private JLabel txtUsuario;
    private JLabel titulo;
    private JLabel txtFecha;

    //botones
    private JButton btnCerrarSesion;
    private JButton btnVerUsuarios;
    private JButton boton;

    //imagen
    private ImageIcon originalIcon;
    private Image scaledImage;
    private ImageIcon resizedIcon;

    //estilos
    Font labelFont = new Font("SansSerif", Font.PLAIN, 14);

    public TiendaVirtualGUI() {
        setTitle("PowerApp");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(60,60,60)); // Fondo oscuro

        // Etiqueta usuario
        txtUsuario = new JLabel("¡Hola! <Username>");
        txtUsuario.setBounds(50, 20, 250, 30);
        txtUsuario.setFont(new Font("Arial", Font.BOLD, 20));
        txtUsuario.setForeground(Color.WHITE);
        add(txtUsuario);

        // Botón Cerrar Sesión
        btnCerrarSesion = new RoundedButton("Cerrar sesión", 15);
        btnCerrarSesion.setBounds(320, 20, 120, 30);
        btnCerrarSesion.setBackground( new Color(240, 80, 80));
        btnCerrarSesion.setForeground(Color.WHITE);
        add(btnCerrarSesion);

        // Botón Ver Usuarios
        btnVerUsuarios = new RoundedButton("Ver Perfil", 15);
        btnVerUsuarios.setBounds(700, 20, 120, 30);
        btnVerUsuarios.setBackground(Color.DARK_GRAY);
        btnVerUsuarios.setForeground(Color.WHITE);
        btnVerUsuarios.setBorderPainted(false);
        add(btnVerUsuarios);
        btnVerUsuarios.addActionListener(e -> {
            Profile profile = new Profile();
            profile.setVisible(true);
            this.dispose();
        });

        // Título
        titulo = new JLabel("BIENVENIDO A TU TIENDA VIRTUAL");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(280, 60, 400, 30);
        add(titulo);

        // Paneles de funciones
        addPanelBoton("VENDER", "/resources/images/metodo-de-pago.png", 150, 120, new Color(240, 80, 80), new Sell());
        addPanelBoton("COMPRAR", "/resources/images/comercio.png", 470, 120, new Color(0, 170, 200), new Buy());
        addPanelBoton("GENERAR REPORTE", "/resources/images/reporte-de-negocios.png", 150, 300, new Color(255, 165, 0), null);
        addPanelBoton("VER INVENTARIO", "/resources/images/inventario.png", 470, 300, new Color(220, 200, 50), new Inventory());

        // Fecha
        txtFecha = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtFecha.setBounds(700, 450, 250, 30);
        txtFecha.setFont(new Font("Arial", Font.BOLD, 20));
        txtFecha.setForeground(Color.WHITE);
        add(txtFecha);
    }

    private void addPanelBoton(String texto, String icono, int x, int y, Color colorBoton, Object move) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 275, 140);
        panel.setBackground(new Color(100, 100, 100));

        // Icono

        originalIcon = new ImageIcon(getClass().getResource(icono));
        scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(scaledImage);

        JLabel logo = new JLabel(resizedIcon);
        logo.setBounds(x+15, y+20, 100, 100);
        add(logo);

        // Botón funcional
        boton = new RoundedButton(texto, 15);
        boton.setBounds(140, 50, 110, 40);
        boton.setBackground(colorBoton);
        boton.setForeground(Color.WHITE);
        panel.add(boton);
        if (move != null){
            if (move instanceof JFrame) {
                boton.addActionListener(e -> {
                    ((JFrame) move).setVisible(true);
                    this.dispose();
                });
            }
        }
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TiendaVirtualGUI().setVisible(true);
        });
    }
}


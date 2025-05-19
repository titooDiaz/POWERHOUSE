package shop.swing;

import javax.swing.*;
import java.awt.*;

public class TiendaVirtualGUI extends JFrame {

    public TiendaVirtualGUI() {
        setTitle("PowerApp");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(60,60,60)); // Fondo oscuro

        // Etiqueta usuario
        JLabel txtUsuario = new JLabel("¡Hola! <Username>");
        txtUsuario.setBounds(50, 20, 250, 30);
        txtUsuario.setFont(new Font("Arial", Font.BOLD, 20));
        txtUsuario.setForeground(Color.WHITE);
        add(txtUsuario);

        // Botón Cerrar Sesión
        RoundedButton btnCerrarSesion = new RoundedButton("Cerrar sesión", 15);
        btnCerrarSesion.setBounds(320, 20, 120, 30);
        btnCerrarSesion.setBackground( new Color(240, 80, 80));
        btnCerrarSesion.setForeground(Color.WHITE);
        add(btnCerrarSesion);

        // Botón Ver Usuarios
        RoundedButton btnVerUsuarios = new RoundedButton("Ver Perfil", 15);
        btnVerUsuarios.setBounds(650, 20, 120, 30);
        btnVerUsuarios.setBackground(Color.DARK_GRAY);
        btnVerUsuarios.setForeground(Color.WHITE);
        btnVerUsuarios.setBorderPainted(false);
        add(btnVerUsuarios);

        // Título
        JLabel titulo = new JLabel("BIENVENIDO A TU TIENDA VIRTUAL");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(200, 60, 400, 30);
        add(titulo);

        // Paneles de funciones
        addPanelBoton("VENDER", "metodo-de-pago.png", 100, 120, new Color(240, 80, 80));
        addPanelBoton("COMPRAR", "comercio.png", 420, 120, new Color(0, 170, 200));
        addPanelBoton("GENERAR REPORTE", ".png", 100, 300, new Color(255, 165, 0));
        addPanelBoton("VER INVENTARIO", "inventario.png", 420, 300, new Color(220, 200, 50));

        // Fecha
        JLabel fecha = new JLabel("<current date>");
        fecha.setForeground(Color.LIGHT_GRAY);
        fecha.setBounds(670, 520, 120, 20);
        add(fecha);
    }

    private void addPanelBoton(String texto, String icono, int x, int y, Color colorBoton) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 250, 140);
        panel.setBackground(new Color(100, 100, 100));

        // Icono
        JLabel iconoLabel = new JLabel();
        iconoLabel.setBounds(10, 10, 100, 100);
        iconoLabel.setIcon(new ImageIcon(icono)); // Asegúrate de tener la imagen
        panel.add(iconoLabel);

        // Botón funcional
        RoundedButton boton = new RoundedButton(texto, 15);
        boton.setBounds(120, 50, 110, 40);
        boton.setBackground(colorBoton);
        boton.setForeground(Color.WHITE);
        panel.add(boton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TiendaVirtualGUI().setVisible(true);
        });
    }
}


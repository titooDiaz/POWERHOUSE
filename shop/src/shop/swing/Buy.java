package shop.swing;

import javax.swing.*;
import java.awt.*;
import java.nio.ByteBuffer;

public class Buy extends JFrame {

    public Buy() {
        setTitle("PowerApp - Vender");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Colores personalizados
        Color darkGray = new Color(45, 45, 45);
        Color lightGray = new Color(230, 230, 230);
        Color accent = new Color(0, 200, 150);

        Font font = new Font("SansSerif", Font.PLAIN, 14);

        // Panel izquierdo: información del usuario
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setPreferredSize(new Dimension(200, 0));
        panelIzquierdo.setBackground(darkGray);

        JLabel foto = new JLabel(new ImageIcon("usuario.png"));
        JLabel usuario = new JLabel("<Usuario>");
        JLabel username = new JLabel("<username>");
        JLabel email = new JLabel("<email>");
        JLabel fecha = new JLabel("<current date>");

        for (JLabel label : new JLabel[]{usuario, username, email, fecha}) {
            label.setForeground(Color.WHITE);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(font);
        }

        panelIzquierdo.add(Box.createVerticalStrut(20));
        panelIzquierdo.add(foto);
        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(usuario);
        panelIzquierdo.add(username);
        panelIzquierdo.add(email);
        panelIzquierdo.add(fecha);

        // Panel central: formulario de venta
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(Color.WHITE);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        JComboBox<String> selectorProducto = new JComboBox<>(new String[]{"Seleccione un producto", "Producto 1", "Producto 2"});
        JTextField codigo = new JTextField();
        JTextField metodoPago1 = new JTextField();
        JTextField metodoPago2 = new JTextField();
        JButton botonAgregar = new JButton("Agregar");

        Dimension fieldSize = new Dimension(300, 30);
        selectorProducto.setMaximumSize(fieldSize);
        codigo.setMaximumSize(fieldSize);
        metodoPago1.setMaximumSize(fieldSize);
        metodoPago2.setMaximumSize(fieldSize);

        JLabel labelProducto = new JLabel("Seleccione un producto:");
        JLabel labelCodigo = new JLabel("Código:");
        JLabel labelMetodo1 = new JLabel("Método de pago (1):");
        JLabel labelMetodo2 = new JLabel("Método de pago (2):");

        for (JLabel label : new JLabel[]{labelProducto, labelCodigo, labelMetodo1, labelMetodo2}) {
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            label.setFont(font);
        }

        panelCentral.add(labelProducto);
        panelCentral.add(selectorProducto);
        panelCentral.add(Box.createVerticalStrut(10));

        panelCentral.add(labelCodigo);
        panelCentral.add(codigo);
        panelCentral.add(Box.createVerticalStrut(10));

        panelCentral.add(labelMetodo1);
        panelCentral.add(metodoPago1);
        panelCentral.add(Box.createVerticalStrut(10));

        panelCentral.add(labelMetodo2);
        panelCentral.add(metodoPago2);
        panelCentral.add(Box.createVerticalStrut(20));

        botonAgregar.setBackground(new Color(255, 211, 77));
        botonAgregar.setFont(font);
        botonAgregar.setFocusPainted(false);
        botonAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(botonAgregar);

        // Panel derecho: resumen de productos
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setPreferredSize(new Dimension(200, 0));
        panelDerecho.setBackground(lightGray);
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel resumenLabel = new JLabel("Resumen");
        resumenLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        resumenLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea resumenArea = new JTextArea();
        resumenArea.setEditable(false);
        resumenArea.setFont(font);
        resumenArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        panelDerecho.add(resumenLabel, BorderLayout.NORTH);
        panelDerecho.add(new JScrollPane(resumenArea), BorderLayout.CENTER);

        // Panel inferior: total y botón de confirmar
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setPreferredSize(new Dimension(900, 70));
        panelInferior.setBackground(darkGray);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel totalProductos = new JLabel("TOTAL PRODUCTOS: <total>");
        JLabel totalGeneral = new JLabel("TOTAL: <total>");
        totalProductos.setForeground(Color.WHITE);
        totalGeneral.setForeground(Color.WHITE);
        totalProductos.setFont(font);
        totalGeneral.setFont(font);

        JPanel panelTotales = new JPanel(new GridLayout(2, 1));
        panelTotales.setOpaque(false);
        panelTotales.add(totalProductos);
        panelTotales.add(totalGeneral);

        JButton confirmarVenta = new JButton("Confirmar venta");
        confirmarVenta.setPreferredSize(new Dimension(180, 40));
        confirmarVenta.setBackground(accent);
        confirmarVenta.setForeground(Color.WHITE);
        confirmarVenta.setFont(font);
        confirmarVenta.setFocusPainted(false);

        panelInferior.add(panelTotales, BorderLayout.WEST);
        panelInferior.add(confirmarVenta, BorderLayout.EAST);

        // Agregar todo al JFrame
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);
        add(panelDerecho, BorderLayout.EAST);
        add(panelInferior, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Buy().setVisible(true));
    }
}


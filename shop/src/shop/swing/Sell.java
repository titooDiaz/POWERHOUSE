package shop.swing;

import javax.swing.*;
import java.awt.*;


public class Sell extends JFrame {

    public Sell() {
        setTitle("PowerApp - Sell");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(60,60,60));

        // Colores personalizados
        Color darkGray = new Color(45, 45, 45);
        Color lightGray = new Color(230, 230, 230);
        Color accent = new Color(0, 200, 150);

        Font font = new Font("SansSerif", Font.PLAIN, 14);

        // Panel izquierdo: información del usuario
        RoundedPanel panelIzquierdo = new RoundedPanel(30);
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setPreferredSize(new Dimension(200, 0));
        panelIzquierdo.setBackground(new Color(100,100,100));

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

         // CENTER
        JPanel panelCentral = new JPanel(null);
        panelCentral.setBackground(Color.GRAY);
        panelCentral.setPreferredSize(new Dimension(500, 0));
        JLabel titulo = new JLabel("Vender");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(180, 5, 200, 40);
        panelCentral.add(titulo);
        String[] campos = {
            "Tipo", "Codigo", "Producto", "Cantidad"
        };
        for (int i = 0; i < campos.length; i++) {
            JTextField campo = new JTextField(campos[i]);
            campo.setBounds((i % 2 == 0 ? 20 : 260), 60 + (i / 2) * 60, 200, 40);
            panelCentral.add(campo);
        }
        
        RoundedButton agregarBtn = new RoundedButton("Agregar", 15);
        agregarBtn.setBackground(Color.YELLOW);
        agregarBtn.setBounds(180, 310, 140, 40);
        panelCentral.add(agregarBtn);
        panelCentral.add(Box.createVerticalStrut(30));
        // Panel derecho: resumen de productos
        RoundedPanel panelDerecho = new RoundedPanel(15,new BorderLayout());
        panelDerecho.setPreferredSize(new Dimension(200, 0));
        panelDerecho.setBackground(lightGray);
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel resumenLabel = new JLabel("Resumen");
        resumenLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        resumenLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel resumenArea = new JLabel();
        resumenArea.setFont(font);
        panelDerecho.add(resumenLabel, BorderLayout.NORTH);
        panelDerecho.add(new JScrollPane(resumenArea), BorderLayout.CENTER);

        // Panel inferior: total y botón de confirmar
        RoundedPanel panelInferior = new RoundedPanel(16, new BorderLayout());
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

        RoundedButton confirmarVenta = new RoundedButton("Confirmar venta", 15);
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


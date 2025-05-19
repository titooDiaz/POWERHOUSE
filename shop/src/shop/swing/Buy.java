package shop.swing;

import javax.swing.*;
import java.awt.*;


public class Buy extends JFrame {

    public Buy() {
        setTitle("PowerApp - Comprar");
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
        JPanel panelCentral = new JPanel(); 
        panelCentral.setBackground(darkGray);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        Font labelFont = new Font("SansSerif", Font.BOLD, 24);
        panelCentral.add(createFieldPanel("Tipo:", new JComboBox<>(new String[]{"Producto", "Servicio"}), labelFont));
        panelCentral.add(Box.createVerticalStrut(30));
        panelCentral.add(createFieldPanel("Nombre:", new JTextField(), labelFont));
        panelCentral.add(Box.createVerticalStrut(30));
        panelCentral.add(createFieldPanel("Código:", new JTextField(), labelFont));
        panelCentral.add(Box.createVerticalStrut(30));
        panelCentral.add(createFieldPanel("Cantidad", new JTextField(), labelFont));
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
        private JPanel createFieldPanel(String label, JComponent field, Font labelFont) {
        RoundedPanel panel = new RoundedPanel(15);
        panel.setLayout(new BorderLayout(0,10)); // espacio entre label y field
        panel.setBackground(new Color(150,150,150));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // padding interno

        JLabel lbl = new JLabel(label);
        lbl.setFont(labelFont);
        panel.add(lbl, BorderLayout.NORTH);

        field.setPreferredSize(new Dimension(400, 35));
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Buy().setVisible(true));
    }
}


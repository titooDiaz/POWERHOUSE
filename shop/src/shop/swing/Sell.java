package shop.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;


public class Sell extends JFrame {

    public Sell() {
        setTitle("PowerApp - Vender");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(60,60,60));

        // Colores personalizados
        Color darkGray = new Color(45, 45, 45);
        Color lightGray = new Color(230, 230, 230);
        Color accent = new Color(0, 200, 150);
        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        Font font = new Font("SansSerif", Font.PLAIN, 14);

        // Panel izquierdo: información del usuario
        JPanel panelIzquierdo = new JPanel();
            panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
            panelIzquierdo.setPreferredSize(new Dimension(200, 0));
            panelIzquierdo.setBackground(new Color(100,100,100));
            panelIzquierdo.setBorder(new EmptyBorder(20, 10, 20, 10));
            panelIzquierdo.setBorder(BorderFactory.createLineBorder(new Color(60,60,60) , 10));

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

           //Panel central
        RoundedPanel panelCentral = new RoundedPanel(100);      
            panelCentral.setBackground(Color.GRAY);
            panelCentral.setPreferredSize(new Dimension(500, 0));
            panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
            panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            panelCentral.setBorder(BorderFactory.createLineBorder(new Color(60,60,60) , 10));
             
            JPanel columnasPanel = new JPanel(new GridLayout(6, 0, 10, 5)); 
            columnasPanel.setBackground(Color.GRAY);
            columnasPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            //COLUMNAS DE A DOS CADA UNO
            columnasPanel.add(createFieldPanel("Tipo:", new JComboBox<>(), labelFont)); 
            columnasPanel.add(createFieldPanel("Nombre del Producto:", new JTextField(), labelFont));
            columnasPanel.add(createFieldPanel("Producto:", new JComboBox<>(), labelFont));
            columnasPanel.add(createFieldPanel("Código:", new JTextField(), labelFont));
            columnasPanel.add(createFieldPanel("Cantidad:", new JTextField(), labelFont));
            columnasPanel.add(createFieldPanel("Método de pago:", new JTextField(), labelFont));
           
            panelCentral.add(columnasPanel);
          
            RoundedButton agregarBtn = new RoundedButton("Agregar", 15);
                panelCentral.add(Box.createVerticalStrut(10));
                agregarBtn.setBackground(new Color(255, 211, 77));
                agregarBtn.setBounds(180, 280, 140, 40);
                panelCentral.add(agregarBtn);
                panelCentral.add(Box.createVerticalStrut(10));
                agregarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Panel derecho: resumen de productos
        JPanel panelDerecho = new JPanel(new BorderLayout());
            panelDerecho.setPreferredSize(new Dimension(200, 0));
            panelDerecho.setBackground(lightGray);
            panelDerecho.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
            panelDerecho.setBorder(BorderFactory.createLineBorder(new Color(60,60,60) , 10));

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
            panel.setBackground(Color.GRAY);
       

        JLabel lbl = new JLabel(label);
            lbl.setFont(labelFont);
            panel.add(lbl, BorderLayout.NORTH);

       
            panel.add(field, BorderLayout.CENTER);
    return panel;
    }
        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Sell().setVisible(true));
    }
}




package shop.swing;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class registerProduct extends JFrame {

    public registerProduct() {
        setTitle("PowerApp - Registrar Producto/Servicio");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Colores y fuentes
        Color darkGray = new Color(60, 60, 60);
        Font headerFont= new Font("SansSerif", Font.BOLD, 28);
        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);

        // NORTH: Barra superior
        JPanel barraSuperior = new JPanel(new BorderLayout());
        barraSuperior.setBackground(darkGray);
        barraSuperior.setPreferredSize(new Dimension(900, 60));
        // Boton volver
        JButton volver = new RoundedButton("< volver", 15);
        volver.setBounds(10, 10, 100, 30);
        volver.setBackground(Color.DARK_GRAY);
        volver.setForeground(Color.WHITE);
        volver.setBorderPainted(false);
        barraSuperior.add(volver);
        // Fecha
        JLabel txtFecha = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtFecha.setBounds(750, 10, 250, 30);
        txtFecha.setFont(new Font("Arial", Font.BOLD, 20));
        txtFecha.setForeground(Color.WHITE);
        barraSuperior.add(txtFecha);
        //titulo
        JLabel titulo = new JLabel("REGISTRAR PRODUCTO/SERVICIO", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(headerFont);
        barraSuperior.add(titulo, BorderLayout.CENTER);
        add(barraSuperior, BorderLayout.NORTH);

        // CENTER
        JPanel formWrapper = new JPanel(); // 30 es el radio de las esquinas
        formWrapper.setBackground(darkGray);
        formWrapper.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));
        formWrapper.setLayout(new BoxLayout(formWrapper, BoxLayout.Y_AXIS));

        formWrapper.add(createFieldPanel("Tipo de registro:", new JComboBox<>(new String[]{"Producto", "Servicio"}), labelFont));
        formWrapper.add(Box.createVerticalStrut(30));
        formWrapper.add(createFieldPanel("Nombre:", new JTextField(), labelFont));
        formWrapper.add(Box.createVerticalStrut(30));
        formWrapper.add(createFieldPanel("Código:", new JTextField(), labelFont));
        formWrapper.add(Box.createVerticalStrut(30));
        formWrapper.add(createFieldPanel("Categoría:", new JTextField(), labelFont));
        formWrapper.add(Box.createVerticalStrut(30));
        formWrapper.add(createFieldPanel("Precio de venta:", new JTextField(), labelFont));

        add(formWrapper, BorderLayout.CENTER);

        // SOUTH: Barra inferior
        JPanel barraInferior = new JPanel(new BorderLayout());
        barraInferior.setBackground(darkGray);
        barraInferior.setPreferredSize(new Dimension(900, 80));
        barraInferior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        RoundedButton btnConfirmar = new RoundedButton("CONFIRMAR",15);
        btnConfirmar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnConfirmar.setBackground(new Color(0, 200, 100));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setPreferredSize(new Dimension(200, 50));
        btnConfirmar.setFocusPainted(false);
        barraInferior.add(btnConfirmar, BorderLayout.CENTER);
        add(barraInferior, BorderLayout.SOUTH);
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
        SwingUtilities.invokeLater(() -> new registerProduct().setVisible(true));
    }
}

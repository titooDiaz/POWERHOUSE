package shop.swing;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

import shop.models.*;

public class registerProduct extends JFrame {

    private JComboBox<String> comboTipo;
    private JTextField campoNombre;
    private JTextField campoCodigo;
    private JTextField campoCategoria;
    private JTextField campoPrecio;
    private int idProductService = 0;
    private Inventory inventario;

    public registerProduct(Inventory inventorio) {
        this.inventario = inventorio;
        comboTipo     = new JComboBox<>(new String[]{"producto", "servicio"});
        campoNombre   = new JTextField();
        campoCodigo   = new JTextField();
        campoCategoria= new JTextField();
        campoPrecio   = new JTextField();

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
            volver.addActionListener(e -> {
                TiendaVirtualGUI tienda = new TiendaVirtualGUI();
                tienda.setVisible(true);
                this.dispose();
            });
        
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

            formWrapper.add(createFieldPanel("Tipo de registro:", comboTipo , labelFont));
            formWrapper.add(Box.createVerticalStrut(10));
            formWrapper.add(createFieldPanel("Nombre:", campoNombre, labelFont));
            formWrapper.add(Box.createVerticalStrut(10));
            formWrapper.add(createFieldPanel("Código:", campoCodigo, labelFont));
            formWrapper.add(Box.createVerticalStrut(10));
            formWrapper.add(createFieldPanel("Categoría:", campoCategoria, labelFont));
            formWrapper.add(Box.createVerticalStrut(10));
            formWrapper.add(createFieldPanel("Precio de venta:", campoPrecio, labelFont));

        add(formWrapper, BorderLayout.CENTER);

        // SOUTH: Barra inferior
        JPanel  barraInferior = new JPanel(new BorderLayout());
            barraInferior.setBackground(darkGray);
            barraInferior.setPreferredSize(new Dimension(900, 80));
            barraInferior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        //Boton confirmar
            RoundedButton btnConfirmar = new RoundedButton("CONFIRMAR",15);
            btnConfirmar.setFont(new Font("SansSerif", Font.BOLD, 16));
            btnConfirmar.setBackground(new Color(0, 200, 100));
            btnConfirmar.setForeground(Color.WHITE);
            btnConfirmar.setPreferredSize(new Dimension(200, 50));
            btnConfirmar.setFocusPainted(false);
            barraInferior.add(btnConfirmar, BorderLayout.CENTER);
        add(barraInferior, BorderLayout.SOUTH);

        // LÓGICA REGISTRAR 
        btnConfirmar.addActionListener(e -> {
            String tipo     = (String) comboTipo.getSelectedItem();
            String nombre   = campoNombre.getText().trim();
            String codigo   = campoCodigo.getText().trim();
            String categoria= campoCategoria.getText().trim();
            float precio;

            try {
            precio = Float.parseFloat(campoPrecio.getText().trim());
            } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.");
            return;
            }

            if (nombre.isEmpty() || codigo.isEmpty() || categoria.isEmpty() || campoPrecio.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
            return;
        }

            if ("producto".equalsIgnoreCase(tipo)) {
                Products productos = new Products(idProductService, nombre, codigo, precio, true);
                inventario.agregarProducto(productos);
                JOptionPane.showMessageDialog(this, "Producto registrado correctamente...");
                    this.dispose();
                    inventario.setVisible(true);
            } else {
            Services servicios= new Services(idProductService, precio, nombre, true);
                inventario.agregarServicio(servicios);
                JOptionPane.showMessageDialog(this, "Servicio registrado correctamente...");
                    this.dispose();
                    inventario.setVisible(true);
            }
            });
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
        SwingUtilities.invokeLater(() -> new registerProduct(null).setVisible(true));
    }
}

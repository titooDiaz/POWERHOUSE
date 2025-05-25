package shop.swing;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import shop.CSVwriter.*;
import shop.models.*;

public class registerProduct extends JFrame {

    //paneles
    private JPanel panelSuperior;
    private JPanel panelCentral;
    private JPanel panelInferior;

    //labels
    private JLabel titulo;
    private JLabel txtFecha;

    //botones
    private JButton btnConfirmar;
    private JButton volver;

    //estilos
    private Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
    private Color darkGray = new Color(60, 60, 60);
    private Font headerFont= new Font("SansSerif", Font.BOLD, 28);

    //cuadros de texto
    private JComboBox<String> comboTipo;
    private JTextField campoNombre;
    private JTextField campoCodigo;
    private JTextField campoPrecio;
    private Inventory inventario;

    

    public registerProduct(Inventory inventorio) {

        this.inventario = inventorio;
        comboTipo     = new JComboBox<>(new String[]{"producto", "servicio"});
        campoNombre   = new JTextField();
        campoCodigo   = new JTextField();
        campoPrecio   = new JTextField();

        setTitle("PowerApp - Registrar Producto/Servicio");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // NORTH: Barra superior
            panelSuperior = new JPanel(new BorderLayout());
            panelSuperior.setBackground(darkGray);
            panelSuperior.setPreferredSize(new Dimension(900, 60));

        // Boton volver
            volver = new RoundedButton("< volver", 15);
            volver.setBounds(10, 10, 100, 30);
            volver.setBackground(Color.DARK_GRAY);
            volver.setForeground(Color.WHITE);
            volver.setBorderPainted(false);
            panelSuperior.add(volver);
            volver.addActionListener(e -> {
                TiendaVirtualGUI tienda = new TiendaVirtualGUI();
                tienda.setVisible(true);
                this.dispose();
            });
        
        // Fecha
            txtFecha = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            txtFecha.setBounds(750, 10, 250, 30);
            txtFecha.setFont(new Font("Arial", Font.BOLD, 20));
            txtFecha.setForeground(Color.WHITE);
        panelSuperior.add(txtFecha);

        //titulo
            titulo = new JLabel("REGISTRAR PRODUCTO/SERVICIO", SwingConstants.CENTER);
            titulo.setForeground(Color.WHITE);
            titulo.setFont(headerFont);
        panelSuperior.add(titulo, BorderLayout.CENTER);
        add(panelSuperior, BorderLayout.NORTH);

        // CENTER
            panelCentral = new JPanel(); // 30 es el radio de las esquinas
            panelCentral.setBackground(darkGray);
            panelCentral.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));
            panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
            //TextFields
            panelCentral.add(createFieldPanel("Tipo de registro:", comboTipo , labelFont));
            panelCentral.add(Box.createVerticalStrut(15));
            panelCentral.add(createFieldPanel("Nombre:", campoNombre, labelFont));
            panelCentral.add(Box.createVerticalStrut(15));
            panelCentral.add(createFieldPanel("Código:", campoCodigo, labelFont));
            panelCentral.add(Box.createVerticalStrut(15));
            panelCentral.add(createFieldPanel("Precio de venta:", campoPrecio, labelFont));

        add(panelCentral, BorderLayout.CENTER);

        // inferior
            panelInferior = new JPanel(new BorderLayout());
            panelInferior.setBackground(darkGray);
            panelInferior.setPreferredSize(new Dimension(900, 80));
            panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            
        //Boton confirmar
            btnConfirmar = new RoundedButton("CONFIRMAR",15);
            btnConfirmar.setFont(new Font("SansSerif", Font.BOLD, 16));
            btnConfirmar.setBackground(new Color(0, 200, 100));
            btnConfirmar.setForeground(Color.WHITE);
            btnConfirmar.setPreferredSize(new Dimension(200, 50));
            btnConfirmar.setFocusPainted(false);
            panelInferior.add(btnConfirmar, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // LÓGICA REGISTRAR 
        btnConfirmar.addActionListener(e -> {
            String tipo     = (String) comboTipo.getSelectedItem();
            String nombre   = campoNombre.getText().trim();
            String codigo   = campoCodigo.getText().trim();
            float precio;

            try {
            precio = Float.parseFloat(campoPrecio.getText().trim());
            } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.");
            return;
            }

            if (nombre.isEmpty() || codigo.isEmpty() || campoPrecio.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
            return;
        }

            if ("producto".equalsIgnoreCase(tipo)) {
                JOptionPane.showMessageDialog(this, "Producto registrado correctamente...");
                Products productos = new Products(nombre, codigo, precio, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), true);
                WriterProducts.appendProductCSV(productos);

                inventario.panelesProducto();
                this.dispose();
                inventario.setVisible(true);
            } else {
                Services servicios= new Services(nombre, codigo, precio, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), true);
                WriterSerivices.appendServicesCSV(servicios);

                //inventario.agregarServicio(servicios);
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

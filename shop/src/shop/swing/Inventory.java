package shop.swing;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Inventory extends JFrame {

    private JPanel catPanel1;      // para productos
    private JPanel panelServicios; // para servicios


    Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
    public Inventory() {
        setTitle("PowerApp - Inventario");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(60,60,60));

        // Estilos
        Color darkGray   = new Color(60, 60, 60);
        Color midGray    = new Color(100, 100, 100);
        Color lightGray  = new Color(200, 200, 200);
        Color btnGreen   = new Color(0, 200, 100);
        Color btnYellow  = new Color(255, 211, 77);
        Color btnRed     = new Color(220, 60, 60);
        Font  headerFont = new Font("SansSerif", Font.BOLD, 28);
        Font  labelFont  = new Font("SansSerif", Font.BOLD, 16);
        Font  buttonFont = new Font("SansSerif", Font.BOLD, 14);

        // barra superior 
        JPanel north = new JPanel(new BorderLayout());
        north.setBackground(darkGray);
        north.setPreferredSize(new Dimension(900, 60));

        // Boton volver
        JButton volver = new RoundedButton("< volver", 15);
        volver.setBounds(10, 10, 100, 30);
        volver.setBackground(Color.DARK_GRAY);
        volver.setForeground(Color.WHITE);
        volver.setBorderPainted(false);
        add(volver);
        volver.addActionListener(e -> {
            TiendaVirtualGUI tienda = new TiendaVirtualGUI();
            tienda.setVisible(true);
            this.dispose();
        });

        // Fecha
        JLabel txtFecha = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtFecha.setBounds(700, 20, 250, 30);
        txtFecha.setFont(new Font("Arial", Font.BOLD, 20));
        txtFecha.setForeground(Color.WHITE);
        add(txtFecha);

        JLabel titulo = new JLabel("INVENTARIO", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(headerFont);
        north.add(titulo, BorderLayout.CENTER);

        add(north, BorderLayout.NORTH);

        // productos y servicios
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(darkGray);
        centerWrapper.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(centerWrapper, BorderLayout.CENTER);

        GridBagConstraints c = new GridBagConstraints();
        c.fill    = GridBagConstraints.BOTH;
        c.insets  = new Insets(5, 5, 5, 5);

        // Panel Productos 
        RoundedPanel panelProductos = new RoundedPanel(30);
        panelProductos.setLayout(new BoxLayout(panelProductos, BoxLayout.Y_AXIS));
        panelProductos.setBackground(midGray);
        panelProductos.setBorder(new EmptyBorder(15, 15, 15, 15));

        catPanel1 = createCategoriaPanel("Categoría Producto", labelFont, buttonFont, lightGray, btnRed);
        panelProductos.add(catPanel1);
        panelProductos.add(Box.createVerticalStrut(10));

        LinkedList<RoundedPanel> productos= new LinkedList<RoundedPanel>();

        c.gridx   = 0;
        c.gridy   = 0;
        c.weightx = 0.7;
        c.weighty = 1.0;
        centerWrapper.add(panelProductos, c);

        

        //Panel Servicios 
        panelServicios = new RoundedPanel(30);
        panelServicios.setLayout(new BoxLayout(panelServicios, BoxLayout.Y_AXIS));
        panelServicios.setBackground(midGray);
        panelServicios.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblServ = new JLabel("SERVICIOS", SwingConstants.CENTER);
        lblServ.setFont(labelFont);
        lblServ.setForeground(Color.WHITE);
        lblServ.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelServicios.add(lblServ);
        panelServicios.add(Box.createVerticalStrut(10));

        LinkedList<RoundedPanel> servicios = new LinkedList<RoundedPanel>();

        c.gridx   = 1;
        c.gridy   = 0;
        c.weightx = 0.3;
        centerWrapper.add(panelServicios, c);

        // botones 
        JPanel south = new JPanel(new GridLayout(1, 3, 10, 0));
        south.setBackground(darkGray);
        south.setBorder(new EmptyBorder(10, 10, 10, 10));
        south.setPreferredSize(new Dimension(900, 70));

        RoundedButton btnRegistrar = new RoundedButton("REGISTRAR", 15);
        btnRegistrar.setBackground(btnGreen);
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(buttonFont);
        btnRegistrar.setFocusPainted(false);
        /*btnRegistrar.addActionListener(e -> {
                    ((JFrame) new registerProduct() ).setVisible(true);
                    this.dispose();
        
        }); */
        south.add(btnRegistrar);

        RoundedButton btnEditar = new RoundedButton("EDITAR", 15);
        btnEditar.setBackground(btnYellow);
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setFont(buttonFont);
        btnEditar.setFocusPainted(false);  
        btnEditar.addActionListener(e -> {

            editarFilaProductAndServicio(servicios, btnYellow, "editar");
            editarFilaProductAndServicio(productos, btnYellow, "editar");


        });
        south.add(btnEditar);


        RoundedButton btnEliminar = new RoundedButton("ELIMINAR", 15);
        btnEliminar.setBackground(btnRed);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(buttonFont);
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(e -> {

            editarFilaProductAndServicio(servicios, btnRed, "eliminar");
            editarFilaProductAndServicio(productos, btnRed, "eliminar");

        });

        south.add(btnEliminar);

        add(south,BorderLayout.SOUTH);
        
               // LÓGICA REGISTRAR 
        btnRegistrar.addActionListener(e -> {
            // Pregunta tipo
            String[] opciones = {"Producto", "Servicio"};
            int sel = JOptionPane.showOptionDialog(
                this,
                "¿Qué deseas registrar?",
                "Seleccionar tipo",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );
            if (sel == 0) {
                // Producto
                String nombre = JOptionPane.showInputDialog(this, "Nombre del producto:");
                if (nombre == null || nombre.trim().isEmpty()) return;
                String cantidad = JOptionPane.showInputDialog(this, "Cantidad:");
                if (cantidad == null || cantidad.trim().isEmpty()) return;
                RoundedPanel nuevaFila = createFilaProducto(labelFont, buttonFont, lightGray, Color.orange, 15);
                catPanel1.add(nuevaFila);
                catPanel1.add(Box.createVerticalStrut(5));
                catPanel1.revalidate();
                catPanel1.repaint();
                productos.add(nuevaFila);

            } else if (sel == 1) {
                // Servicio;
                String nombre = JOptionPane.showInputDialog(this, "Nombre del servicio:");
                if (nombre == null || nombre.trim().isEmpty()) return;
                RoundedPanel nuevaFila = createFilaServicio( labelFont, buttonFont, lightGray, Color.orange, 15);
                panelServicios.add(nuevaFila);
                panelServicios.add(Box.createVerticalStrut(8));
                panelServicios.revalidate();
                panelServicios.repaint();
                servicios.add(nuevaFila);

            }
        });
    }

    // crea categoría vacía
    private JPanel createCategoriaPanel(String tituloCat,
                                        Font labelFont, Font btnFont,
                                        Color bgRow, Color btnColor) {
        JPanel catPanel = new JPanel();
        catPanel.setLayout(new BoxLayout(catPanel, BoxLayout.Y_AXIS));
        catPanel.setBackground(new Color(100, 100, 100));  // midGray

        JLabel lbl = new JLabel(tituloCat);
        lbl.setFont(labelFont);
        lbl.setForeground(Color.WHITE);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        catPanel.add(lbl);
        catPanel.add(Box.createVerticalStrut(8));

        return catPanel;
    }

    // fila producto
    private RoundedPanel createFilaProducto( Font labelFont, Font btnFont,
                                      Color bgRow, Color btnColor, int radius) {
        RoundedPanel row = new RoundedPanel(15);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        row.setBackground(bgRow);
        row.setBorder(new EmptyBorder(5, 10, 5, 10));
        row.setLayout(new BorderLayout(10, 0));

        JLabel lblNombre = new JLabel("product");
        lblNombre.setFont(labelFont);
        row.add(lblNombre, BorderLayout.WEST);

        RoundedButton btnDet = new RoundedButton("detalles", 15);
        btnDet.setFont(btnFont);
        btnDet.setBackground(btnColor);
        btnDet.setForeground(Color.WHITE);
        btnDet.setFocusPainted(false);
        row.add(btnDet, BorderLayout.EAST);

        JLabel lblCant = new JLabel("8");
        lblCant.setFont(labelFont);
        row.add(lblCant, BorderLayout.CENTER);

        return row;
    }

    // fila servicio
    private RoundedPanel createFilaServicio(
                                      Font labelFont, Font btnFont,
                                      Color bgRow, Color btnColor, int radius) {
        RoundedPanel row = new RoundedPanel(15);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        row.setBackground(bgRow);
        row.setBorder(new EmptyBorder(5, 10, 5, 10));
        row.setLayout(new BorderLayout(10, 0));

        JLabel nombre = new JLabel("Servicio");
        nombre.setFont(labelFont);
        row.add(nombre, BorderLayout.WEST);

        RoundedButton btnDet = new RoundedButton("detalles", radius);
        btnDet.setFont(btnFont);
        btnDet.setBackground(btnColor);
        btnDet.setForeground(Color.WHITE);
        btnDet.setFocusPainted(false);
        row.add(btnDet, BorderLayout.EAST);

        return row;
    }
    private LinkedList<RoundedPanel> editarFilaProductAndServicio(LinkedList<RoundedPanel> list, Color color, String textoButton) {

            for(JPanel ser: list){

            if (list != null) {
                RoundedButton btn = (RoundedButton) ser.getComponent(1);
                btn.setText(textoButton); // cambia el texto
                btn.setBackground(color); // cambia el color
            } else {
                JOptionPane.showMessageDialog(this, "No hay no hay servicios/productos para " + textoButton);
            }

            }
        return list;
        
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Inventory().setVisible(true);
        });
    }
}

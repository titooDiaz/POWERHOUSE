package shop.swing;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import shop.CSVwriter.WriterProducts;
import shop.CSVwriter.WriterSerivices;
import shop.models.Products;
import shop.models.Services;

public class  Inventory extends JFrame {
    
    // paneles
    private JPanel north;
    private JPanel south;
    private JPanel panelCentral;
    private JScrollPane panelProductos;      // para productos
    private JScrollPane panelServicios;
    private JPanel contenidoProductos; 
    private JPanel contenidoServicios; // para servicios

    //labels
    private JLabel txtFecha;
    private JLabel titulo;
    private JLabel lblServ;

    //botones
    private JButton volver;
    private RoundedButton btnEliminar;
    private RoundedButton btnEditar;
    private RoundedButton btnRegistrar;

    //relojes
    private int relojEditar;
    private int relojEliminar;

    //listas
    private WriterProducts wp = new WriterProducts();
    private WriterSerivices ws = new WriterSerivices();
    private LinkedList<Services> listaServicios = new LinkedList<>();
    private LinkedList<Products> listaProductos = new LinkedList<>();
    private LinkedList<RoundedPanel> productos= new LinkedList<RoundedPanel>();
    private LinkedList<RoundedPanel> servicios = new LinkedList<RoundedPanel>();

    // Estilos
    Color darkGray   = new Color(60, 60, 60);
    Color midGray    = new Color(100, 100, 100);
    Color lightGray  = new Color(200, 200, 200);
    Color btnGreen   = new Color(0, 200, 100);
    Color btnYellow  = new Color(255, 211, 77);
    Color btnRed     = new Color(220, 60, 60);
    Color btnBlue = new Color(30, 100, 200);
    Font  headerFont = new Font("SansSerif", Font.BOLD, 28);
    Font  buttonFont = new Font("SansSerif", Font.BOLD, 14);
    Font labelFont = new Font("SansSerif", Font.PLAIN, 14);




    public Inventory() {

        //leer cvs para los paneles
        listaServicios = ws.loadFromCSV(listaServicios);
        listaProductos = wp.loadFromCSV(listaProductos);
        
        setTitle("PowerApp - Inventario");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(60,60,60));


        // barra superior 
            north = new JPanel(new BorderLayout());
            north.setBackground(darkGray);
            north.setPreferredSize(new Dimension(900, 60));

        // Boton volver
            volver = new RoundedButton("< volver", 15);
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
            txtFecha = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            txtFecha.setBounds(700, 20, 250, 30);
            txtFecha.setFont(new Font("Arial", Font.BOLD, 20));
            txtFecha.setForeground(Color.WHITE);
        add(txtFecha);

            titulo = new JLabel("INVENTARIO", SwingConstants.CENTER);
            titulo.setForeground(Color.WHITE);
            titulo.setFont(headerFont);
            north.add(titulo, BorderLayout.CENTER);

        add(north, BorderLayout.NORTH);

        // productos y servicios
            panelCentral = new JPanel(new GridBagLayout());
            panelCentral.setBackground(darkGray);
            panelCentral.setBorder(new EmptyBorder(10, 10, 10, 10));
            add(panelCentral, BorderLayout.CENTER);

        GridBagConstraints c = new GridBagConstraints();
            c.fill    = GridBagConstraints.BOTH;
            c.insets  = new Insets(5, 5, 5, 5);

       // Panel Productos con scroll
        contenidoProductos = new JPanel();
        contenidoProductos.setLayout(new BoxLayout(contenidoProductos, BoxLayout.Y_AXIS));
        contenidoProductos.setBackground(midGray);
        contenidoProductos.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblProd = new JLabel("PRODUCTOS");
        lblProd.setFont(labelFont);
        lblProd.setForeground(Color.WHITE);
        lblProd.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenidoProductos.add(lblProd);
        contenidoProductos.add(Box.createVerticalStrut(10));

        panelProductos = new JScrollPane(contenidoProductos);
        panelProductos.setPreferredSize(new Dimension(300, 350));
        panelProductos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelProductos.setBorder(BorderFactory.createEmptyBorder());

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.7;
        c.weighty = 1.0;
        panelCentral.add(panelProductos, c);

        // Panel Servicios con scroll
        contenidoServicios = new JPanel();
        contenidoServicios.setLayout(new BoxLayout(contenidoServicios, BoxLayout.Y_AXIS));
        contenidoServicios.setBackground(midGray);
        contenidoServicios.setBorder(new EmptyBorder(15, 15, 15, 15));

        lblServ = new JLabel("SERVICIOS");
        lblServ.setFont(labelFont);
        lblServ.setForeground(Color.WHITE);
        lblServ.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenidoServicios.add(lblServ);
        contenidoServicios.add(Box.createVerticalStrut(10));

        panelServicios = new JScrollPane(contenidoServicios);
        panelServicios.setPreferredSize(new Dimension(300, 350));
        panelServicios.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelServicios.setBorder(BorderFactory.createEmptyBorder());

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.3;
        panelCentral.add(panelServicios, c);


        // botones 
            south = new JPanel(new GridLayout(1, 3, 10, 0));
            south.setBackground(darkGray);
            south.setBorder(new EmptyBorder(10, 10, 10, 10));
            south.setPreferredSize(new Dimension(900, 70));

            btnRegistrar = new RoundedButton("REGISTRAR", 15);
            btnRegistrar.setBackground(btnGreen);
            btnRegistrar.setForeground(Color.WHITE);
            btnRegistrar.setFont(buttonFont);
            btnRegistrar.setFocusPainted(false);
            btnRegistrar.addActionListener(e -> {
                registerProduct registro = new registerProduct(); // le pasas "this" como Inventory
                registro.setVisible(true);
                this.dispose(); // O this.dispose() si quieres cerrarla
            });
        south.add(btnRegistrar);

            btnEditar = new RoundedButton("EDITAR", 15);
            btnEditar.setBackground(btnYellow);
            btnEditar.setForeground(Color.WHITE);
            btnEditar.setFont(buttonFont);
            btnEditar.setFocusPainted(false);  
            btnEditar.addActionListener(e -> {
                if (relojEditar == 0) {
                    editarFilaProductAndServicio(servicios, btnYellow, "editar");
                    editarFilaProductAndServicio(productos, btnYellow, "editar");
                    btnEditar.setBackground(btnBlue);
                    btnEditar.setText("DETALLES");
                    relojEditar = 1;
                    if(relojEliminar==1){
                    btnEliminar.setBackground(btnRed);
                    btnEliminar.setText("ELIMINAR");
                      relojEliminar = 0;
                    }
                    
                }else{
                    editarFilaProductAndServicio(servicios, btnBlue, "detalles");
                    editarFilaProductAndServicio(productos, btnBlue, "detalles");
                    btnEditar.setBackground(btnYellow);
                    btnEditar.setText("EDITAR");
                    relojEditar = 0;
                }
            });
 
            btnEliminar = new RoundedButton("ELIMINAR", 15);
            btnEliminar.setBackground(btnRed);
            btnEliminar.setForeground(Color.WHITE);
            btnEliminar.setFont(buttonFont);
            btnEliminar.setFocusPainted(false);
            btnEliminar.addActionListener(e -> {

                if (relojEliminar == 0) {
                    editarFilaProductAndServicio(servicios, btnRed, "eliminar");
                    editarFilaProductAndServicio(productos, btnRed, "eliminar");
                    btnEliminar.setBackground(btnBlue);
                    btnEliminar.setText("CANCELAR");
                    relojEliminar = 1;
                    if(relojEliminar==1){
                    btnEditar.setBackground(btnYellow);
                    btnEditar.setText("EDITAR");
                    relojEditar = 0;
                    }
                    
                }else{
                    editarFilaProductAndServicio(servicios, btnBlue, "detalles");
                    editarFilaProductAndServicio(productos, btnBlue, "detalles");
                    btnEliminar.setBackground(btnRed);
                    btnEliminar.setText("ELIMINAR");
                    relojEliminar = 0;
                }

            });
        south.add(btnEditar);
        south.add(btnEliminar);

        add(south,BorderLayout.SOUTH);
        // fin botones
        // recordar paneles de productos/servicios
        panelesProducto();
        panelesServicio();
        
    }

    public void panelesProducto() {
        for (Products p : listaProductos) {    
        RoundedPanel nuevaFila = createFilaProducto(p.getName(), p.cantProducts(), p.getPrice(), p.getCode(), labelFont, labelFont, lightGray, btnBlue, 15);
        panelProductos.add(nuevaFila);
        panelProductos.add(Box.createVerticalStrut(5));
        panelProductos.revalidate();
        panelProductos.repaint();
        contenidoProductos.add(nuevaFila);
        contenidoProductos.add(Box.createVerticalStrut(10));
        productos.add(nuevaFila);
        }
    }
         

    public void panelesServicio() {
        for (Services s : listaServicios) {    
        RoundedPanel nuevaFila = createFilaServicio(s.getName(), s.getPrice(),labelFont, labelFont, lightGray, btnBlue, 15);
        panelServicios.add(nuevaFila);
        panelServicios.add(Box.createVerticalStrut(10));
        panelServicios.revalidate();
        panelServicios.repaint();
        contenidoServicios.add(nuevaFila);
        contenidoServicios.add(Box.createVerticalStrut(10));
        servicios.add(nuevaFila);
        }
    }
    
    // fila producto
    private RoundedPanel createFilaProducto(String name, int cant, float price, String code, Font labelFont, Font btnFont,
                                      Color bgRow, Color btnColor, int radius) {
        RoundedPanel row = new RoundedPanel(15);
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            row.setBackground(bgRow);
            row.setBorder(new EmptyBorder(5, 10, 5, 10));
            row.setLayout(new BorderLayout(10, 0));

        JLabel lblNombre = new JLabel(name);
            lblNombre.setFont(labelFont);
            row.add(lblNombre, BorderLayout.WEST);

        RoundedButton btnDet = new RoundedButton("detalles", 15);
            btnDet.setFont(btnFont);
            btnDet.setBackground(btnColor);
            btnDet.setForeground(Color.WHITE);
            btnDet.setFocusPainted(false);
            btnDet.addActionListener(e -> {
            if (btnDet.getText().equalsIgnoreCase("editar")) {
                mostrarDialogoProducto(name);
            } else {
                if(btnDet.getText().equalsIgnoreCase("detalles")){
                    mostrarDetallesProducto(name, cant, price, code);
                }else{

                }    
            }
        });
        row.add(btnDet, BorderLayout.EAST);


        JLabel lblCant = new JLabel("Cantidad: " + cant);
            lblCant.setFont(labelFont);
        row.add(lblCant, BorderLayout.CENTER);

        return row;
    }

    // fila servicio
    private RoundedPanel createFilaServicio(String name, float price,
                                      Font labelFont, Font btnFont,
                                      Color bgRow, Color btnColor, int radius) {
        RoundedPanel row = new RoundedPanel(15);
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
            row.setBackground(bgRow);
            row.setBorder(new EmptyBorder(5, 10, 5, 10));
            row.setLayout(new BorderLayout(10, 0));

        JLabel nombre = new JLabel(name);
            nombre.setFont(labelFont);
            row.add(nombre, BorderLayout.WEST);


            RoundedButton btnDet = new RoundedButton("detalles", radius);
            btnDet.setFont(btnFont);
            btnDet.setBackground(btnColor);
            btnDet.setForeground(Color.WHITE);
            btnDet.setFocusPainted(false);
            btnDet.addActionListener(e -> {
            if (btnDet.getText().equalsIgnoreCase("editar")) {
                 mostrarDialogServicio(name);
                }else{
                    if(btnDet.getText().equalsIgnoreCase("detalles")){
                        mostrarDetallesServicio(name, price);
                    }
                }
            });
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

    private void mostrarDialogoProducto(String nombre) {
        JDialog dialogo = new JDialog(this, "Editar Producto", true);
        dialogo.setSize(300, 250);
        dialogo.setResizable(false);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout(10, 10));

        JPanel camposPanel = new JPanel();
        camposPanel.setLayout(new BoxLayout(camposPanel, BoxLayout.Y_AXIS));
        camposPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nombreLabel = new JLabel("Nombre Producto:");
        JTextField nombreField = new JTextField(nombre);
        nombreField.setMaximumSize(new Dimension(200, 50)); 

        camposPanel.add(nombreLabel);
        camposPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        camposPanel.add(nombreField);

        dialogo.add(camposPanel, BorderLayout.CENTER);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton guardarBtn = new JButton("Guardar");
        JButton cerrarBtn = new JButton("Cerrar");

        guardarBtn.addActionListener(e -> {
            // falta colocar para guardar información
            dialogo.dispose();
        });

        cerrarBtn.addActionListener(e -> dialogo.dispose());

        botonesPanel.add(guardarBtn);
        botonesPanel.add(cerrarBtn);

        dialogo.add(botonesPanel, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }

    private void mostrarDialogServicio(String nombre) {
        JDialog dialogo = new JDialog(this, "Editar Servicio", true);
        dialogo.setSize(300, 250);
        dialogo.setResizable(false);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout(10, 10));

        JPanel camposPanel = new JPanel();
        camposPanel.setLayout(new BoxLayout(camposPanel, BoxLayout.Y_AXIS));
        camposPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nombreLabel = new JLabel("Nombre Servicio:");
        JTextField nombreField = new JTextField(nombre);
        nombreField.setMaximumSize(new Dimension(200, 50)); 

        camposPanel.add(nombreLabel);
        camposPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        camposPanel.add(nombreField);

        dialogo.add(camposPanel, BorderLayout.CENTER);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton guardarBtn = new JButton("Guardar");
        JButton cerrarBtn = new JButton("Cerrar");

        guardarBtn.addActionListener(e -> {
            // falta colocar para guardar información
            dialogo.dispose();
        });

        cerrarBtn.addActionListener(e -> dialogo.dispose());

        botonesPanel.add(guardarBtn);
        botonesPanel.add(cerrarBtn);

        dialogo.add(botonesPanel, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    // Crea un JLabel con fondo blanco y tamaño fijo
    private JLabel crearCampoFijo(String texto, Dimension size, Border border) {
        JLabel label = new JLabel(texto);
        label.setBorder(border);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setPreferredSize(size);
        label.setMinimumSize(size);
        label.setMaximumSize(size);
        return label;
    }

    private void mostrarDetallesProducto(String nombre, int cantidad, float precio, String code){
        JDialog dialogo = new JDialog(this, "Detalles Producto", true);
        dialogo.setSize(300, 250);
        dialogo.setResizable(false);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout(10, 10));
        
        //panel para colocar los labels
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Border border = BorderFactory.createLineBorder(Color.GRAY);
        
        //label producto
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombre Producto:"), gbc);
        gbc.gridx = 1;
        JLabel nombreField = crearCampoFijo(nombre, new Dimension(225,30), border);
        nombreField.setBorder(border);
        nombreField.setOpaque(true);
        nombreField.setBackground(Color.WHITE);
        panel.add(nombreField, gbc);

        //label cantidad
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Cantidad:"), gbc);
        gbc.gridx = 1;
        JLabel cantidadField = crearCampoFijo(String.valueOf(cantidad),new Dimension(225,30), border);
        cantidadField.setBorder(border);
        cantidadField.setOpaque(true);
        cantidadField.setBackground(Color.WHITE);
        panel.add(cantidadField, gbc);
        
        //label codigo
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Codigo:"));
        gbc.gridx = 1;
        JLabel codigoField = crearCampoFijo(code, new Dimension(225,30), border);
        codigoField.setBorder(border);
        codigoField.setOpaque(true);
        codigoField.setBackground(Color.WHITE);
        panel.add(codigoField, gbc);

        //label precio
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Precio:"));
        gbc.gridx = 1;
        JLabel precioField = crearCampoFijo(String.format("%.2f", precio),new Dimension(225,30), border);
        precioField.setBorder(border);
        precioField.setOpaque(true);
        precioField.setBackground(Color.WHITE);
        panel.add(precioField, gbc);

        dialogo.add(panel);
        dialogo.setVisible(true);

    }

    private void mostrarDetallesServicio(String nombre, float precio){
        JDialog dialogo = new JDialog(this, "Detalles Servicio", true);
        dialogo.setSize(300, 250);
        dialogo.setResizable(false);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout(10, 10));
        
        //panel para colocar los labels
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Border border = BorderFactory.createLineBorder(Color.GRAY);
        
        //label servicio
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombre Servicio:"), gbc);
        gbc.gridx = 1;
        JLabel nombreField = crearCampoFijo(nombre, new Dimension(225,30), border);
        nombreField.setBorder(border);
        nombreField.setOpaque(true);
        nombreField.setBackground(Color.WHITE);
        panel.add(nombreField, gbc);

        //label precio
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Precio:"));
        gbc.gridx = 1;
        JLabel precioField = crearCampoFijo(String.format("%.2f", precio),new Dimension(225,30), border);
        precioField.setBorder(border);
        precioField.setOpaque(true);
        precioField.setBackground(Color.WHITE);
        panel.add(precioField, gbc);

        dialogo.add(panel);
        dialogo.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Inventory().setVisible(true);
        });
    }
}

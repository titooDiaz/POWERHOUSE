package shop.swing;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import shop.CSVwriter.WriterProduct;
import shop.CSVwriter.WriterProducts;
import shop.CSVwriter.WriterSerivices;
import shop.models.Products;
import shop.models.Services;

public class  Inventory extends JFrame {
    
    // paneles
    private JPanel north;
    private JPanel south;
    private JPanel panelCentral;
    private JScrollPane panelProductos;// para productos
    private JScrollPane panelServicios;
    private JPanel contenidoProductos; 
    private JPanel contenidoServicios;// para servicios

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



    //Contructor Inventario
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


            //ESTABLECIMIENTO DE LOS BOTONES 
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
                registerProduct registro = new registerProduct(); 
                registro.setVisible(true);
                this.dispose(); 
            });
        south.add(btnRegistrar);

            //BoOTON EDITAR INVENTARIO
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
            
            //BOTON ELIMINAR INVENTARIO
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

        //BOTON PARA BUSCAR EN EL INVENTARIO PRODUCTO O SERVICIOS
        JButton btnBuscar = new RoundedButton("BUSCAR", 15);

        btnBuscar.setFont(buttonFont);
        btnBuscar.setBackground(new Color(0, 120, 215));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setPreferredSize(new Dimension(200, 50));
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(e -> buscarProductoOServicio());
        south.add(btnBuscar); 

        add(south,BorderLayout.SOUTH);
        panelesProducto();
        panelesServicio();
        
    }

    //PANEL EN DONDE SE MUESTRAN LOS PRODUCTOS
    public void panelesProducto() {
        for (Products p : listaProductos) {  
            if(!p.getActive())continue;  //con esto se verifica si está en true o false para aparecer o no en el panel
                RoundedPanel nuevaFila = createFilaProducto(p.getName(), p.getPrice(), p.getCode(), labelFont, labelFont, lightGray, btnBlue, 15);
                panelProductos.add(nuevaFila);
                panelProductos.add(Box.createVerticalStrut(5));
                panelProductos.revalidate();
                panelProductos.repaint();
                contenidoProductos.add(nuevaFila);
                contenidoProductos.add(Box.createVerticalStrut(10));
                productos.add(nuevaFila);
        }
    }
         
    //PANEL EN DONDE SE MUESTRAN LOS SERVICIOS
    public void panelesServicio() {
        for (Services s : listaServicios) {   
            if(!s.isActive())continue;  //verificacion para aparecer en el panel como en productos
                RoundedPanel nuevaFila = createFilaServicio(s.getName(), s.getPrice(), s.getCode(), labelFont, labelFont, lightGray, btnBlue, 15);
                panelServicios.add(nuevaFila);
                panelServicios.add(Box.createVerticalStrut(10));
                panelServicios.revalidate();
                panelServicios.repaint();
                contenidoServicios.add(nuevaFila);
                contenidoServicios.add(Box.createVerticalStrut(10));
                servicios.add(nuevaFila);
        }
    }
    
    // BOTONES PARA LOS PRODUCTOS MOSTRADOS
    private RoundedPanel createFilaProducto(String name, float price, String code, Font labelFont, Font btnFont,
                                      Color bgRow, Color btnColor, int radius) {
        int cantidadDisponible = WriterProduct.contarProductosDisponiblesPorCodigo(code);                                

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
                mostrarDialogoProducto(name, price, code);
            } else {
                if(btnDet.getText().equalsIgnoreCase("detalles")){
                    int cantidadActualizada = WriterProduct.contarProductosDisponiblesPorCodigo(code);
                    mostrarDetallesProducto(name,cantidadActualizada, price, code);
                }else{
                    if (confirmarEliminacionProducto(name)){
                        TiendaVirtualGUI home = new TiendaVirtualGUI();
                        home.setVisible(true);
                        this.dispose();
                    }
                }    
            }
        });
        row.add(btnDet, BorderLayout.EAST);


        JLabel lblCant = new JLabel("Cantidad: " + cantidadDisponible);
            lblCant.setFont(labelFont);
        row.add(lblCant, BorderLayout.CENTER);

        return row;
    }

    // BOTONES PARA LOS SERVICIOS MOSTRADOS
    private RoundedPanel createFilaServicio(String name, float price, String code,
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
                 mostrarDialogServicio(name, price);
                }else{
                    if(btnDet.getText().equalsIgnoreCase("detalles")){
                        mostrarDetallesServicio(name, price, code);
                    }else{
                        confirmarEliminacionServicio(name);
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

    //J DIALOG PARA CUANDO SE PRESIONA EL BOTON DE EDITAR EN ALGUN PRODUCTO PARA PODER CAMBIAR LOS DATOS Y GUARDARLOS EN EL CSV
    private void mostrarDialogoProducto(String nombre, float price, String code) {
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
        nombreField.setMaximumSize(new Dimension(200, 25)); 

        JLabel precioPanel = new JLabel("Precio Producto:");
        JTextField precioField = new JTextField(String.format("%.5f", price));
        precioField.setMaximumSize(new Dimension(200, 25));

        JLabel codigoPanel = new JLabel("Codigo Producto:");
        JTextField codigoField = new JTextField(code);
        codigoField.setMaximumSize(new Dimension(200, 25));

        camposPanel.add(nombreLabel);
        camposPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        camposPanel.add(nombreField);
        camposPanel.add(precioPanel);
        camposPanel.add(precioField);
        camposPanel.add(codigoPanel);
        camposPanel.add(codigoField);

        dialogo.add(camposPanel);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton guardarBtn = new JButton("Guardar");
        JButton cerrarBtn = new JButton("Cerrar");

        guardarBtn.addActionListener(e -> {
            try {
                String nuevoCodigo = codigoField.getText().trim();
                String nuevoNombre = nombreField.getText().trim();
                String precioTexto = precioField.getText().trim().replace(",", ".");
                float nuevoPrecio = Float.parseFloat(precioTexto);

                if (!nuevoNombre.isEmpty() && !nuevoCodigo.isEmpty()) {
                    actualizarProductoEnCSV(nombre, nuevoCodigo, nuevoNombre, nuevoPrecio);
                    dialogo.dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "Código y Nombre son obligatorios");
                }
            }catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El precio debe ser un número válido");
            }
        });

        cerrarBtn.addActionListener(e -> dialogo.dispose());

        botonesPanel.add(guardarBtn);
        botonesPanel.add(cerrarBtn);

        dialogo.add(botonesPanel, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }

    //FUNCION PARA GUARDAR LOS METODOS EN EL CSV SIGUIENDO UNA RUTA PARA LOS PRODUCTOS
    private void actualizarProductoEnCSV(String nombreOriginal, String nuevoCodigo, String nuevoNombre, float nuevoPrecio) {
        try {
            File archivo = new File("shop/src/resources/data/Categories/Product/products.csv");
            List<String> lineas = Files.readAllLines(archivo.toPath());
            List<String> nuevasLineas = new ArrayList<>();

            for (String linea : lineas) {
                String[] partes = linea.split(",");
                if (partes.length >= 6 && partes[1].equals(nombreOriginal)) {
                    // Actualiza solo los campos requeridos
                    partes[1] = nuevoNombre;    // Nombre
                    partes[2] = nuevoCodigo;    // Código
                    partes[3] = String.valueOf(nuevoPrecio); // Precio
                    linea = String.join(",", partes);
                }
                nuevasLineas.add(linea);
            }

                Files.write(archivo.toPath(), nuevasLineas);
                JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
       
            }catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar los datos");
            }
    }

    //J DIALOG PARA CUANDO SE PRESIONA EL BOTON DE EDITAR EN ALGUN SERVICIO PARA PODER CAMBIAR LOS DATOS Y GUARDARLOS EN EL CSV
    private void mostrarDialogServicio(String nombre, float price) {
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
        nombreField.setMaximumSize(new Dimension(200, 25)); 

        JLabel precioPanel = new JLabel("Precio Servicio:");
        JTextField precioField = new JTextField(String.format("%.5f", price));
        precioField.setMaximumSize(new Dimension(200, 25));

        camposPanel.add(nombreLabel);
        camposPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        camposPanel.add(nombreField);
        camposPanel.add(precioPanel);
        camposPanel.add(precioField);

        dialogo.add(camposPanel, BorderLayout.CENTER);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton guardarBtn = new JButton("Guardar");
        JButton cerrarBtn = new JButton("Cerrar");

        guardarBtn.addActionListener(e -> {
            String nuevoNombre = nombreField.getText().trim();
        
            try {
                float nuevoPrecio = Float.parseFloat(precioField.getText().replace(",", "."));
            
                if (!nuevoNombre.isEmpty()) {
                    actualizarServicioEnCSV(nombre, nuevoNombre, nuevoPrecio);
                    dialogo.dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "El nombre es obligatorio");
                }
            }catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El precio debe ser un número válido");
            }
        });

        cerrarBtn.addActionListener(e -> dialogo.dispose());

        botonesPanel.add(guardarBtn);
        botonesPanel.add(cerrarBtn);

        dialogo.add(botonesPanel, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }

    //FUNCION PARA GUARDAR LOS METODOS EN EL CSV SIGUIENDO UNA RUTA PARA LOS SERVICIOS
    private void actualizarServicioEnCSV(String nombreOriginal, String nuevoNombre, float nuevoPrecio) {
        try {
            File archivo = new File("shop/src/resources/data/Categories/Service/services.csv");
            List<String> lineas = Files.readAllLines(archivo.toPath());
            List<String> nuevasLineas = new ArrayList<>();

            for (String linea : lineas) {
                String[] partes = linea.split(",");
                if (partes.length == 6 && partes[1].equals(nombreOriginal)) {
                    partes[1] = nuevoNombre;
                    partes[3] = String.valueOf(nuevoPrecio);
                    linea = String.join(",", partes);
                }
                nuevasLineas.add(linea);
            }

                Files.write(archivo.toPath(), nuevasLineas);
                JOptionPane.showMessageDialog(null, "Servicio actualizado correctamente");
            }catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar los datos");
                }
    }

    // Crea un JLabel con fondo blanco y tamaño fijo para el panel de detalles
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

    //DETALLES PARA LOS PRODUCTOS
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

    //DETALLES PARA LOS SERVICIOS
    private void mostrarDetallesServicio(String nombre, float precio, String code){
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


    // ELIMINACIONES
    private boolean confirmarEliminacionProducto(String nombre) {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el Producto \"" + nombre + "\"?", "Eliminar Producto",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.WARNING_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                File archivo = new File("shop/src/resources/data/Categories/Product/products.csv");
                List<String> lineas = Files.readAllLines(archivo.toPath());
                List<String> nuevasLineas = new ArrayList<>();
                boolean seModifico = false;

                for (String linea : lineas) {
                    String[] partes = linea.split(",");
                    if (partes.length >= 6 && partes[1].equals(nombre)) {
                        int cantidadActual = WriterProduct.contarProductosDisponiblesPorCodigo(partes[2]);
                        if (cantidadActual == 0) {
                            partes[5] = "false"; // Cambia el estado 'active' a false
                            seModifico = true;
                            // reconstruir la línea con el cambio
                            linea = String.join(",", partes);
                        }
                    }
                    nuevasLineas.add(linea); // agregar la línea (modificada o no)
                }

                if (seModifico) {
                    Files.write(archivo.toPath(), nuevasLineas);
                    JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede eliminar el producto porque hay stock disponible.");
                    return false;
                }

                return true;
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al eliminar el producto.");
            }
        }
        return false;
    }



    private boolean confirmarEliminacionServicio(String nombre){
        int opcion = JOptionPane.showConfirmDialog(null,"¿Está seguro de eliminar el Servicio \"" + nombre + "\"?", "Eliminar Servicio",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.WARNING_MESSAGE);
    
        if (opcion == JOptionPane.YES_OPTION) {
            try {
                File archivo = new File("shop/src/resources/data/Categories/Service/services.csv");
                List<String> lineas = Files.readAllLines(archivo.toPath());
                List<String> nuevasLineas = new ArrayList<>();

                for (String linea : lineas) {
                    String[] partes = linea.split(",");
                    if (partes.length >= 6 && partes[1].equals(nombre)) {
                        partes[5] = "false"; // Cambia el estado active a false
                        linea = String.join(",", partes);
                    }
                    nuevasLineas.add(linea);
                }

                Files.write(archivo.toPath(), nuevasLineas);
                JOptionPane.showMessageDialog(null, "Servicio eliminado correctamente.");

                return true;
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al eliminar el servicio.");
            }
        }
        return false;
    }


    //OPCION PARA BUSCAR PRODUCTOS O SERVICIOS
    private void buscarProductoOServicio() {
    String input = JOptionPane.showInputDialog(this, "Ingrese el Nombre o Código del Producto/Servicio", "Bucar Producto o Servicio", JOptionPane.QUESTION_MESSAGE);
    
        if (input == null || input.trim().isEmpty()) {
            return;
        }

        String buscado = input.trim().toLowerCase();
        boolean encontrado = false;

        // Buscar en productos
        for (Products p : listaProductos) {
            if ((p.getName().equalsIgnoreCase(buscado) || p.getCode().equalsIgnoreCase(buscado))) {
                String estado = p.getActive() ? "Activo" : "Inactivo";
                int cantidadActual = WriterProduct.contarProductosDisponiblesPorCodigo(p.getCode());
                JOptionPane.showMessageDialog(this,
                    "Producto encontrado:\n\nNombre: " + p.getName() +
                    "\nCódigo: " + p.getCode() +
                    "\nCantidad: " + cantidadActual +
                    "\nPrecio: $" + p.getPrice() +
                    "\nEstado: " + estado,
                    "Resultado de búsqueda",
                    JOptionPane.INFORMATION_MESSAGE
                    );
                encontrado = true;
            break;
            }
        }

        // Si no fue encontrado en productos, buscar en servicios
        if (!encontrado) {
            for (Services s : listaServicios) {
                if ((s.getName().equalsIgnoreCase(buscado) || s.getCode().equalsIgnoreCase(buscado))) {
                    String estado = s.isActive() ? "Activo" : "Inactivo";
                    JOptionPane.showMessageDialog(this,
                        "Servicio encontrado:\n\nNombre: " + s.getName() +
                        "\nCódigo: " + s.getCode() +
                        "\nPrecio: $" + s.getPrice() +
                        "\nEstado: " + estado,
                        "Resultado de búsqueda",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    encontrado = true;
                break;
                }
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(this,
                "No se encontró ningún producto o servicio con ese nombre o código.",
                "Sin resultados",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Inventory().setVisible(true);
        });
    }
}

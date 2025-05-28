package shop.swing;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import javax.swing.*;
import shop.CSVwriter.*;
import shop.models.PaidMethod;
import shop.models.Product;
import shop.models.Products;
import shop.models.Service;
import shop.models.Services;


public class Buy extends JFrame {

    //paneles
    private final JPanel panelIzquierdo;
    private final JPanel panelCentral;
    private final JPanel columnasPanel;
    private final JPanel panelDerecho;
    private final JPanel panelInferior;
    private final JPanel panelTotales;
    private final JPanel panelBotonMetodo;

    // Atributos de la imagen 
    private ImageIcon originalIcon;
    private Image scaledImage;
    private ImageIcon resizedIcon;

    //labels
    private final JLabel foto;
    private final JLabel usuario;
    private final JLabel username;
    private final JLabel email;
    private final JLabel resumenLabel;
    private final JLabel resumenArea;
    private final JLabel totalProductos;
    private final JLabel totalGeneral;

    //botones
    private final JButton volver;
    private final JButton btnConfirmar;
  
    //estilos
    private final Color darkGray = new Color(45, 45, 45);
    private final Color lightGray = new Color(230, 230, 230);
    private final Color accent = new Color(0, 200, 150);
    private final Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
    private final Font font = new Font("SansSerif", Font.PLAIN, 14);


    public Buy() {

        setTitle("PowerApp - Comprar");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(60,60,60));
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Boton volver
        volver = new RoundedButton("< volver", 15);
        volver.setBounds(50, 30, 100, 30);
        volver.setBackground(Color.DARK_GRAY);
        volver.setForeground(Color.WHITE);
        volver.setBorderPainted(false);
        add(volver);
        volver.addActionListener(e -> {
            TiendaVirtualGUI tienda = new TiendaVirtualGUI();
            tienda.setVisible(true);
            this.dispose();
        });

        // Panel izquierdo: información del usuario
        panelIzquierdo = new RoundedPanel(30);
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setPreferredSize(new Dimension(200, 0));
        panelIzquierdo.setBackground(new Color(100,100,100));
        panelIzquierdo.setBorder(BorderFactory.createLineBorder(new Color(60,60,60) , 10));

            String name = WriterUsers.obtenerCampoPorPk(2);
            String usernameString = WriterUsers.obtenerCampoPorPk(1);
            String emailString= WriterUsers.obtenerCampoPorPk(4);

        // imagen de el profile
            originalIcon = new ImageIcon(getClass().getResource("/resources/images/administrador.png"));
            scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            resizedIcon = new ImageIcon(scaledImage);

            foto = new JLabel(resizedIcon);
            foto.setAlignmentX(Component.CENTER_ALIGNMENT);

            usuario = new JLabel(name);
            username = new JLabel(usernameString);
            email = new JLabel(emailString);

        // agregar elementos al panel izquierdo
            for (JLabel label : new JLabel[]{usuario, username, email}) {
                label.setForeground(Color.WHITE);
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                label.setFont(font);
            }

        panelIzquierdo.add(Box.createVerticalStrut(60));
        panelIzquierdo.add(foto);
        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(usuario);
        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(username);
        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(email);
        panelIzquierdo.add(Box.createVerticalStrut(10));

        //Panel central
        panelCentral = new JPanel();      
        panelCentral.setBackground(Color.GRAY);
        panelCentral.setPreferredSize(new Dimension(500, 0));
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelCentral.setBorder(BorderFactory.createLineBorder(new Color(60,60,60) , 20));

        //Panel que contine los text fileds
            columnasPanel = new JPanel(new GridLayout(4, 2, 100, 30)); 
            columnasPanel.setBackground(Color.GRAY);
            columnasPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //dato tipo
                JComboBox<String> tipo = new JComboBox<>();
                tipo.addItem("producto");
                tipo.addItem("servicio");
            columnasPanel.add(createFieldPanel("Tipo:", tipo, labelFont));

        //Devuelve los productos y servicios registrados
                JComboBox<Products> productosLabel = new JComboBox<>();
                JPanel productService = createFieldPanel("Producto:", productosLabel, labelFont);
            columnasPanel.add(productService);

        // agregar valores por defecto iniciales
                WriterProducts gp = new WriterProducts();
                LinkedList<Products> productosList = gp.loadFromCSV(new LinkedList<>());
                    for (Products producto : productosList) {
                        productosLabel.addItem(producto);
                     }

                JTextField cantidad = new JTextField();
                JPanel cantidadPanel = createFieldPanel("Cantidad:", cantidad, labelFont);
            columnasPanel.add(cantidadPanel);

        //lista de metodos de pago
                LinkedList<PaidMethod> metodos = WriterPaidMethod.loadPaidMethodsFromCSV();
                JComboBox<PaidMethod> metodoPagoCombo = new JComboBox<>();
                JPanel metodoP = createFieldPanel("Método de pago:", metodoPagoCombo, labelFont);
            columnasPanel.add(metodoP);

            for (PaidMethod metodo : metodos) {
                metodoPagoCombo.addItem(metodo);
            }

                JTextField precio = new JTextField();
                JPanel precioPanel = createFieldPanel("precio de compra:", precio, labelFont);
            columnasPanel.add(precioPanel);

        //boton de agregar metodo de pago
                RoundedButton abrirDialogo = new RoundedButton("+Metodo",15);
                abrirDialogo.setBackground(new Color(50, 100, 200));
                abrirDialogo.setFont(new Font("SansSerif", Font.PLAIN, 12));
                abrirDialogo.setForeground(Color.WHITE);
                abrirDialogo.setFocusPainted(false);
                abrirDialogo.setPreferredSize(new Dimension(140, 30));

        //panel contenedor de boton de metodo
                panelBotonMetodo = new JPanel();
                panelBotonMetodo.setOpaque(false);
                panelBotonMetodo.setLayout(new FlowLayout(FlowLayout.CENTER));
                panelBotonMetodo.add(abrirDialogo);
            columnasPanel.add(panelBotonMetodo);
                abrirDialogo.addActionListener(e -> {
                    AddPaidMethod dialog = new AddPaidMethod(new Buy());
                    dialog.setVisible(true); // Abre la ventana emergente
                });
            
        panelCentral.add(columnasPanel);
                
        // Panel derecho: resumen de productos
        panelDerecho = new RoundedPanel(15,new BorderLayout());
        panelDerecho.setPreferredSize(new Dimension(200, 0));
        panelDerecho.setBackground(lightGray);
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelDerecho.setBorder(BorderFactory.createLineBorder(new Color(60,60,60) , 10));

            resumenLabel = new JLabel("Resumen");
            resumenLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            resumenLabel.setHorizontalAlignment(SwingConstants.CENTER);

            resumenArea = new JLabel();
            resumenArea.setFont(font);

        panelDerecho.add(resumenLabel, BorderLayout.NORTH);
        panelDerecho.add(new JScrollPane(resumenArea), BorderLayout.CENTER);

        // Panel inferior: total y botón de confirmar
        panelInferior = new RoundedPanel(16, new BorderLayout());
        panelInferior.setPreferredSize(new Dimension(900, 70));
        panelInferior.setBackground(darkGray);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            totalProductos = new JLabel("TOTAL PRODUCTOS: <total>");
            totalGeneral = new JLabel("TOTAL: <total>");
            totalProductos.setForeground(Color.WHITE);
            totalGeneral.setForeground(Color.WHITE);
            totalProductos.setFont(font);
            totalGeneral.setFont(font);

        panelTotales = new JPanel(new GridLayout(2, 1));
        panelTotales.setOpaque(false);
        panelTotales.add(totalProductos);
        panelTotales.add(totalGeneral);

            btnConfirmar = new RoundedButton("Confirmar compras", 15);
            btnConfirmar.setBackground(accent);
            btnConfirmar.setForeground(Color.WHITE);
            btnConfirmar.setFont(font);
            btnConfirmar.setFocusPainted(false);
            btnConfirmar.addActionListener(e -> {
        // get all data
        String canString = getComponentByType(cantidadPanel, JTextField.class).getText();
        int canINT = Integer.parseInt(canString);
        PaidMethod paidmt = (PaidMethod) getComponentByType(metodoP, JComboBox.class).getSelectedItem();
        int metINT = (int) paidmt.getPk();
        String preString = getComponentByType(precioPanel, JTextField.class).getText();
        float preINT = Float.parseFloat(preString);
        String select = (String) tipo.getSelectedItem();
        if (select.equals("producto")){
            Products p = (Products) getComponentByType(productService, JComboBox.class).getSelectedItem();
            int pPK = p.getPk();
            System.out.print(pPK);
                for (int i = 0; i < canINT; i++){
                        // guardar cada producto comprado
                        Product newProduct = new Product(preINT, date, metINT, pPK);
                        WriterProduct.appendProductCSV(newProduct);
                }  
        }else{
            Services s = (Services) getComponentByType(productService, JComboBox.class).getSelectedItem();
            int sPK = (int) s.getPk();
            float price = (float) s.getPrice();
            System.out.print(price);
                for (int i = 0; i < canINT; i++){
                        // guardar cada producto comprado
                        Service newService = new Service(date, metINT, sPK, price);
                        WriterService.appendProductCSV(newService);
                }  

            }
            });

            panelInferior.add(panelTotales, BorderLayout.WEST);
            panelInferior.add(btnConfirmar, BorderLayout.EAST);

        // Agregar todo al JFrame
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);
        add(panelDerecho, BorderLayout.EAST);
        add(panelInferior, BorderLayout.SOUTH);

                tipo.addActionListener(e -> {
                    JLabel label = getComponentByType(productService, JLabel.class);
                    Component oldField = getComponentByType(productService, JComboBox.class);
                    String seleccionado = (String) tipo.getSelectedItem();
                    JTextField cantPanel = getComponentByType(precioPanel, JTextField.class);

                        if (label != null && oldField != null) {
                            if ("producto".equals(seleccionado)) {
                                WriterProducts writer = new WriterProducts();
                                LinkedList<Products> productos = writer.loadFromCSV(new LinkedList<>());
                                label.setText("Producto:");

                                JComboBox<Products> nuevoCombo = new JComboBox<>();
                                    for (Products producto : productos) {
                                nuevoCombo.addItem(producto);
                                    }

                            // Agrega un listener si también quieres manejar producto seleccionado
                                nuevoCombo.addActionListener(ev -> {
                            // aquí puedes poner lógica si necesitas cuando se seleccione un producto
                                        cantPanel.setEditable(true);
                                        cantPanel.setText(""); // o setear algo relacionado con el producto
                        });

                    productService.remove(oldField);
                    productService.add(nuevoCombo, BorderLayout.CENTER);
                    cantPanel.setEditable(true);
                    cantPanel.setText("");

                } else if ("servicio".equals(seleccionado)) {
                    WriterSerivices writer = new WriterSerivices();
                    LinkedList<Services> servicios = writer.loadFromCSV(new LinkedList<>());
                    label.setText("Servicio:");

                    JComboBox<Services> nuevoCombo = new JComboBox<>();
                        for (Services servicio : servicios) {
                            nuevoCombo.addItem(servicio);
                        }

                    // reescribir funcion
                    nuevoCombo.addActionListener(ev -> {
                        Services s = (Services) nuevoCombo.getSelectedItem();
                            if (s != null) {
                                float price = (float) s.getPrice();
                                String priceStr = String.valueOf(price);
                                cantPanel.setText(priceStr);
                            }
                    });
                    productService.remove(oldField);
                    productService.add(nuevoCombo, BorderLayout.CENTER);
                    cantPanel.setEditable(false);
                }
                productService.revalidate();
                productService.repaint();
            }
        });


    }
    private JPanel createFieldPanel(String label, JComponent field, Font labelFont) {
        RoundedPanel panel = new RoundedPanel(15);
        panel.setLayout(new BorderLayout(0,10)); // espacio entre label y field
        panel.setBackground(Color.GRAY);
        field.setMinimumSize(new Dimension(20,30));
       
        JLabel lbl = new JLabel(label);
        lbl.setFont(labelFont);
        panel.add(lbl, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);

        return panel;
    }
        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Buy().setVisible(true));
    }


    private <T extends Component> T getComponentByType(JPanel panel, Class<T> type) {
        for (Component comp : panel.getComponents()) {
            if (type.isInstance(comp)) {
                return type.cast(comp);
            }
        }
        return null;
    }
}
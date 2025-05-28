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

public class Sell extends JFrame {

    private final JPanel panelIzquierdo;
    private final JPanel panelCentral;
    private final JPanel columnasPanel;
    private final JPanel panelDerecho;
    private final JPanel panelInferior;
    private final JPanel panelTotales;
    private final JPanel panelBotonMetodo;

    private ImageIcon originalIcon;
    private Image scaledImage;
    private ImageIcon resizedIcon;

    private final JLabel foto;
    private final JLabel usuario;
    private final JLabel username;
    private final JLabel email;
    private final JLabel resumenLabel;
    private final JLabel resumenArea;
    private final JLabel totalProductos;
    private final JLabel totalGeneral;

    private final JButton volver;
    private final JButton btnConfirmar;

    private final Color darkGray = new Color(45, 45, 45);
    private final Color lightGray = new Color(230, 230, 230);
    private final Color accent = new Color(0, 200, 150);
    private final Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
    private final Font font = new Font("SansSerif", Font.PLAIN, 14);

    public Sell() {

        setTitle("PowerApp - Vender");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(60, 60, 60));
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        //boton volver
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

        //panel de datos
        panelIzquierdo = new RoundedPanel(30);
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setPreferredSize(new Dimension(200, 0));
        panelIzquierdo.setBackground(new Color(100, 100, 100));
        panelIzquierdo.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 10));

        String name = WriterUsers.obtenerCampoPorPk(2);
        String usernameString = WriterUsers.obtenerCampoPorPk(1);
        String emailString = WriterUsers.obtenerCampoPorPk(4);

        originalIcon = new ImageIcon(getClass().getResource(""));
        scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(scaledImage);

        foto = new JLabel(resizedIcon);
        foto.setAlignmentX(Component.CENTER_ALIGNMENT);

        usuario = new JLabel(name);
        username = new JLabel(usernameString);
        email = new JLabel(emailString);

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
        // panel de info
        panelCentral = new JPanel();
        panelCentral.setBackground(Color.GRAY);
        panelCentral.setPreferredSize(new Dimension(500, 0));
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 20));
        //panel de columnas donde va cada dato
        columnasPanel = new JPanel(new GridLayout(6, 0, 10, 5));
        columnasPanel.setBackground(Color.GRAY);
        columnasPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel tipo = new JLabel("Producto");
        tipo.setOpaque(true);
        tipo.setBackground(Color.WHITE);
        columnasPanel.add(createFieldPanel("Tipo:", tipo, labelFont));

        JComboBox<Products> productosLabel = new JComboBox<>();
        JPanel productService = createFieldPanel("Producto:", productosLabel, labelFont);
        columnasPanel.add(productService);
        // añadir productos al combo box
        WriterProducts writer = new WriterProducts();
        LinkedList<Products> productos = writer.loadFromCSV(new LinkedList<>());
        for (Products producto : productos) {
            productosLabel.addItem(producto);
        }

        JTextField cantidad = new JTextField();
        JPanel cantidadPanel = createFieldPanel("Cantidad:", cantidad, labelFont);
        columnasPanel.add(cantidadPanel);

        LinkedList<PaidMethod> metodos = WriterPaidMethod.loadPaidMethodsFromCSV();
        JComboBox<PaidMethod> metodoPagoCombo = new JComboBox<>();
        JPanel metodoP = createFieldPanel("Método de pago:", metodoPagoCombo, labelFont);
        columnasPanel.add(metodoP);

        for (PaidMethod metodo : metodos) {
            metodoPagoCombo.addItem(metodo);
        }

        JTextField precio = new JTextField();
        precio.setEditable(false);
        JPanel precioPanel = createFieldPanel("Precio:", precio, labelFont);
        columnasPanel.add(precioPanel);
        //logica para que se ponga el precio
        productosLabel.addActionListener(ev -> {
            Products seleccionado = (Products) productosLabel.getSelectedItem();
            if (seleccionado != null) {
                float price = (float) seleccionado.getPrice();
                precio.setText(String.valueOf(price));
            }
        });

        RoundedButton abrirDialogo = new RoundedButton("+Metodo", 15);
        abrirDialogo.setBackground(new Color(50, 100, 200));
        abrirDialogo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        abrirDialogo.setForeground(Color.WHITE);
        abrirDialogo.setFocusPainted(false);
        abrirDialogo.setPreferredSize(new Dimension(140, 30));

        panelBotonMetodo = new JPanel();
        panelBotonMetodo.setOpaque(false);
        panelBotonMetodo.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotonMetodo.add(abrirDialogo);
        columnasPanel.add(panelBotonMetodo);
        abrirDialogo.addActionListener(e -> {
            AddPaidMethod dialog = new AddPaidMethod(new Buy());
            dialog.setVisible(true);
        });

        panelCentral.add(columnasPanel);
        // panel derecho
        panelDerecho = new RoundedPanel(15, new BorderLayout());
        panelDerecho.setPreferredSize(new Dimension(200, 0));
        panelDerecho.setBackground(lightGray);
        panelDerecho.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 10));

        resumenLabel = new JLabel("Resumen");
        resumenLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        resumenLabel.setHorizontalAlignment(SwingConstants.CENTER);

        resumenArea = new JLabel();
        resumenArea.setFont(font);
        panelDerecho.add(resumenLabel, BorderLayout.NORTH);
        panelDerecho.add(new JScrollPane(resumenArea), BorderLayout.CENTER);

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
            String canString = cantidad.getText();
            int canINT = Integer.parseInt(canString);
            PaidMethod paidmt = (PaidMethod) metodoPagoCombo.getSelectedItem();
            int metINT = paidmt.getPk();
            float preINT = Float.parseFloat(precio.getText());
            Products p = (Products) productosLabel.getSelectedItem();
            if (p != null) {
                int pPK = p.getPk();
                for (int i = 0; i < canINT; i++) {
                    Product newProduct = new Product(preINT, date, metINT, pPK);
                   // WriterProduct.appendProductCSV(newProduct);
                }
            }
        });

        panelInferior.add(panelTotales, BorderLayout.WEST);
        panelInferior.add(btnConfirmar, BorderLayout.EAST);

        add(panelIzquierdo, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);
        add(panelDerecho, BorderLayout.EAST);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private JPanel createFieldPanel(String label, JComponent field, Font labelFont) {
        RoundedPanel panel = new RoundedPanel(15);
        panel.setLayout(new BorderLayout(0, 10));
        panel.setBackground(Color.GRAY);
        field.setMinimumSize(new Dimension(20, 30));

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

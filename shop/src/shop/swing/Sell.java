package shop.swing;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import javax.swing.*;
import shop.CSVwriter.*;
import shop.models.PaidMethod;
import shop.models.Products;

public class Sell extends JFrame {

    private final JPanel panelIzquierdo;
    private final JPanel panelCentral;
    private final JPanel columnasPanel;
    private final JPanel panelInferior;
    private final JPanel panelBotonMetodo;

    private ImageIcon originalIcon;
    private Image scaledImage;
    private ImageIcon resizedIcon;

    private final JLabel foto;
    private final JLabel usuario;
    private final JLabel username;
    private final JLabel email;

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

        originalIcon = new ImageIcon(getClass().getResource("/resources/images/administrador.png"));
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
        columnasPanel = new JPanel(new GridLayout(4, 2, 100, 30));
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
            if (producto.getActive()){
                productosLabel.addItem(producto);
            }
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
        precio.setEditable(true);
        JPanel precioPanel = createFieldPanel("Precio de Venta:", precio, labelFont);
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


        panelInferior = new RoundedPanel(16, new BorderLayout());
        panelInferior.setPreferredSize(new Dimension(900, 70));
        panelInferior.setBackground(darkGray);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));


        btnConfirmar = new RoundedButton("Vender", 15);
        btnConfirmar.setBackground(accent);
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFont(font);
        btnConfirmar.setPreferredSize(new Dimension(100, 50));
        btnConfirmar.setFocusPainted(false);


        btnConfirmar.addActionListener(e -> {     
            String mensaje = "";
            try{
                String canString = getComponentByType(cantidadPanel, JTextField.class).getText();
                    int canINT = Integer.parseInt(canString);

                String preString = getComponentByType(precioPanel, JTextField.class).getText();
                    float preINT = Float.parseFloat(preString);
                
                PaidMethod paidmt = (PaidMethod) getComponentByType(metodoP, JComboBox.class).getSelectedItem();
                    int metINT = (int) paidmt.getPk();
                
                Products pName = (Products) getComponentByType(productService, JComboBox.class).getSelectedItem();

                mensaje = "¿Deseas confirmar la VENTA de " + canINT + " " +
                pName.getName() + " a $ " + preINT + " cada uno?\n" +
                "Total: $" + (canINT * preINT) + ".";

                String[] opciones = {"Confirmar", "Cancelar"};
                
            int sel = JOptionPane.showOptionDialog(
                null,
                mensaje,
                "Confirmar venta",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );
            Products p = (Products) productosLabel.getSelectedItem();
            if (p != null) {
                int pPK = p.getPk();
                WriterProduct.moverLineasPorPK(pPK, canINT, preINT, metINT);
            } 

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Cantidad o precio inválido.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al procesar la venta.");
            }
            
        });

        panelInferior.add(btnConfirmar, BorderLayout.CENTER);

        add(panelIzquierdo, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private <T extends Component> T getComponentByType(JPanel panel, Class<T> type) {
        for (Component comp : panel.getComponents()) {
            if (type.isInstance(comp)) {
                return type.cast(comp);
            }
        }
        return null;
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

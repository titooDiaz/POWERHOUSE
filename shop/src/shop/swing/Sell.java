package shop.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Sell extends JFrame {

    //paneles
    private JPanel panelIzquierdo;
    private JPanel panelCentral;
    private JPanel columnasPanel;
    private JPanel panelDerecho;
    private JPanel panelInferior;
    private JPanel panelTotales;

    //labels
    private JLabel foto;
    private JLabel usuario;
    private JLabel username;
    private JLabel fecha;
    private JLabel email;
    private JLabel resumenLabel;
    private JLabel resumenArea;
    private JLabel totalProductos;
    private JLabel totalGeneral;

    //botones
    private JButton volver;
    private JButton btnAgregar;
    private JButton btnConfirmar;
  
    //estilos
    private Color darkGray = new Color(45, 45, 45);
    private Color lightGray = new Color(230, 230, 230);
    private Color accent = new Color(0, 200, 150);
    private Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
    private Font font = new Font("SansSerif", Font.PLAIN, 14);

    public Sell() {
        setTitle("PowerApp - Vender");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(60,60,60));

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
            panelIzquierdo = new JPanel();
            panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
            panelIzquierdo.setPreferredSize(new Dimension(200, 0));
            panelIzquierdo.setBackground(new Color(100,100,100));
            panelIzquierdo.setBorder(new EmptyBorder(20, 10, 20, 10));
            panelIzquierdo.setBorder(BorderFactory.createLineBorder(new Color(60,60,60) , 10));

            foto = new JLabel(new ImageIcon("usuario.png"));
            usuario = new JLabel("<Usuario>");
            username = new JLabel("<username>");
            email = new JLabel("<email>");
            fecha = new JLabel("<current date>");


        for (JLabel label : new JLabel[]{usuario, username, email, fecha}) {
            label.setForeground(Color.WHITE);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(font);
        }

            panelIzquierdo.add(Box.createVerticalStrut(50));
            panelIzquierdo.add(foto);
            panelIzquierdo.add(Box.createVerticalStrut(10));
            panelIzquierdo.add(usuario);
            panelIzquierdo.add(username);
            panelIzquierdo.add(email);
            panelIzquierdo.add(fecha);

           //Panel central
            panelCentral = new RoundedPanel(100);      
            panelCentral.setBackground(Color.GRAY);
            panelCentral.setPreferredSize(new Dimension(500, 0));
            panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
            panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            panelCentral.setBorder(BorderFactory.createLineBorder(new Color(60,60,60) , 10));
             
            columnasPanel = new JPanel(new GridLayout(6, 0, 10, 5)); 
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
          
                btnAgregar = new RoundedButton("Agregar", 15);
                panelCentral.add(Box.createVerticalStrut(10));
                btnAgregar.setBackground(new Color(255, 211, 77));
                btnAgregar.setBounds(180, 280, 140, 40);
                panelCentral.add(btnAgregar);
                panelCentral.add(Box.createVerticalStrut(10));
                btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel derecho: resumen de productos
            panelDerecho = new JPanel(new BorderLayout());
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

            btnConfirmar = new RoundedButton("Confirmar venta", 15);
            btnConfirmar.setBackground(accent);
            btnConfirmar.setForeground(Color.WHITE);
            btnConfirmar.setFont(font);
            btnConfirmar.setFocusPainted(false);

            panelInferior.add(panelTotales, BorderLayout.WEST);
            panelInferior.add(btnConfirmar, BorderLayout.EAST);

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




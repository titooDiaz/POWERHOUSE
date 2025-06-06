package shop.swing;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import shop.CSVwriter.*;

public class TiendaVirtualGUI extends JFrame {

    //labels
    private JLabel txtUsuario;
    private JLabel titulo;
    private JLabel txtFecha;

    //botones
    private JButton btnCerrarSesion;
    private JButton btnVerUsuarios;
    private JButton boton;

    //imagen
    private ImageIcon originalIcon;
    private Image scaledImage;
    private ImageIcon resizedIcon;

    //estilos
    Font labelFont = new Font("SansSerif", Font.PLAIN, 14);

    public TiendaVirtualGUI() {
        setTitle("PowerApp");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(60,60,60)); // Fondo oscuro

        // Etiqueta usuario
            String username = WriterUsers.obtenerCampoPorPk(2);
            txtUsuario = new JLabel("¡Hola, " + username + "!");
            txtUsuario.setBounds(50, 20, 250, 30);
            txtUsuario.setFont(new Font("Arial", Font.BOLD, 20));
            txtUsuario.setForeground(Color.WHITE);
        add(txtUsuario);

        // Botón Cerrar Sesión
        btnCerrarSesion = new RoundedButton("Cerrar sesión", 15);
        btnCerrarSesion.setBounds(387, 20, 120, 30);
        btnCerrarSesion.setBackground( new Color(240, 80, 80));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.addActionListener(e -> {
            
            // Pregunta tipo
            String[] opciones = {"confirmar", "cancelar"};
            int sel = JOptionPane.showOptionDialog(
                this,
                "Seguro que quieres cerrar la sesión?",
                "Cerrar Sesión",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );
            if (sel == 0) {
                // Producto
                Login inicio = new Login();
                inicio.setVisible(true);
                this.dispose();
                WriterUsers.sobrescribirConCero();
            } 
        });
        add(btnCerrarSesion);

        // Botón Ver Usuarios
        btnVerUsuarios = new RoundedButton("Ver Perfil", 15);
        btnVerUsuarios.setBounds(700, 20, 120, 30);
        btnVerUsuarios.setBackground(Color.DARK_GRAY);
        btnVerUsuarios.setForeground(Color.WHITE);
        btnVerUsuarios.setBorderPainted(false);
        
        btnVerUsuarios.addActionListener(e -> {
            Profile profile = new Profile();
            profile.setVisible(true);
            this.dispose();
        });
        add(btnVerUsuarios);

        // Título
            titulo = new JLabel("BIENVENIDO A TU TIENDA VIRTUAL");
            titulo.setFont(new Font("Arial", Font.BOLD, 20));
            titulo.setForeground(Color.WHITE);
            titulo.setBounds(280, 60, 400, 30);
        add(titulo);

        // Paneles de funciones
        addPanelBoton("VENDER", "/resources/images/metodo-de-pago.png", 150, 120, new Color(240, 80, 80), new Sell());
        addPanelBoton("COMPRAR", "/resources/images/comercio.png", 470, 120, new Color(0, 170, 200), new Buy());
        addPanelBoton("GENERAR REPORTE", "/resources/images/reporte-de-negocios.png", 150, 300, new Color(255, 165, 0), null);
        addPanelBoton("VER INVENTARIO", "/resources/images/inventario.png", 470, 300, new Color(220, 200, 50), new Inventory());

    
        // Fecha
            txtFecha = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            txtFecha.setBounds(700, 450, 250, 30);
            txtFecha.setFont(new Font("Arial", Font.BOLD, 20));
            txtFecha.setForeground(Color.WHITE);
        add(txtFecha);
    }
    // funcion para crear los recuadros principles
    private void addPanelBoton(String texto, String icono, int x, int y, Color colorBoton, Object move) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 275, 140);
        panel.setBackground(new Color(100, 100, 100));

        // Icono
        originalIcon = new ImageIcon(getClass().getResource(icono));
        scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(scaledImage);

        JLabel logo = new JLabel(resizedIcon);
        logo.setBounds(x+15, y+20, 100, 100);
        add(logo);

        // Botón funcional
        boton = new RoundedButton(texto, 15);
        boton.setBounds(140, 50, 110, 40);
        boton.setBackground(colorBoton);
        boton.setForeground(Color.WHITE);
        panel.add(boton);

        if (move instanceof JFrame) {
            boton.addActionListener(e -> {
                ((JFrame) move).setVisible(true);
                this.dispose();
            });
        } else if (texto.equals("GENERAR REPORTE")) {
            boton.addActionListener(e -> modalreport());
        }

        add(panel);
    }

    public void modalreport() {
        // Crear el diálogo
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Generar reporte de...");
        dialogo.setSize(300, 150);
        dialogo.setLocationRelativeTo(null); // Centrar ventana
        dialogo.setModal(true); // Bloquea hasta que se cierre

        // Crear panel y botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        JLabel label = new JLabel("¿Qué mes quieres reportar?", SwingConstants.CENTER);
        JButton btnEsteMes = new JButton("Este mes");
        JButton btnMesPasado = new JButton("Mes pasado");

        // Acciones para los botones
        btnEsteMes.addActionListener(e -> {
            dialogo.dispose();
            String mesActual = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
            String ruta = Paths.get("shop/src/resources/data/mes.txt").toString();
            // Escribir en el archivo
            try (FileWriter writer = new FileWriter(ruta)) {
                writer.write(mesActual);
            } catch (IOException a) {
                a.printStackTrace();
            }
            GenerateReport.generate();
        });

        btnMesPasado.addActionListener(e -> {
            dialogo.dispose(); // Cierra el diálogo
            String mesPasado = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
            String ruta = Paths.get("shop/src/resources/data/mes.txt").toString();
            try (FileWriter writer = new FileWriter(ruta)) {
                writer.write(mesPasado);
            } catch (IOException a) {
                a.printStackTrace();
            }
            GenerateReport.generate();
        });

        // Agregar componentes al panel
        panel.add(label);
        panel.add(btnEsteMes);
        panel.add(btnMesPasado);

        // Agregar panel al diálogo
        dialogo.add(panel);
        dialogo.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TiendaVirtualGUI().setVisible(true);
        });
    }
}


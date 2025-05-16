package shop.swing;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class login extends JFrame {

    public login() {
        setTitle("PowerApp");
        setSize(900, 550);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(50, 50, 50));

        // Main title
        JLabel titulo = new JLabel("BIENVENIDO A POWERAPP");
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(250, 30, 500, 30);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo);

        // Login panel with rounded background
        JPanel panelLogin = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(80, 80, 80));
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
            }
        };
        panelLogin.setBounds(80, 100, 300, 300);
        panelLogin.setOpaque(false);
        add(panelLogin);

        // "LOGIN" label
        JLabel loginLabel = new JLabel("INICIAR SESIÓN");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 18));
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setBounds(60, 20, 200, 30);
        panelLogin.add(loginLabel);

        // Username and password fields
        JTextField usuario = crearInput("Usuario", 70);
        JPasswordField contraseña = crearPasswordField(120);
        panelLogin.add(usuario);
        panelLogin.add(contraseña);

        // "Ingresar" button (green)
        JButton ingresar = crearBoton("Ingresar", new Color(0, 204, 102));
        ingresar.setBounds(30, 180, 240, 35);
        panelLogin.add(ingresar);

        // "Recuperar contraseña" button (red)
        JButton recuperar = crearBoton("recuperar contraseña", new Color(255, 102, 102));
        recuperar.setBounds(30, 230, 240, 35);
        panelLogin.add(recuperar);

        // Logo with 3D effect (drop shadow)
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/resources/images/icon.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JLabel logo = new JLabel(resizedIcon);
        logo.setBounds(510, 110, 300, 300);
        add(logo);
        
        JLabel shadow = new JLabel();
        shadow.setBounds(510, 110, 300, 300);
        shadow.setOpaque(true);
        shadow.setBackground(Color.WHITE);
        shadow.setBorder(BorderFactory.createEmptyBorder());
        shadow.setLocation(510 + 8, 110 + 8); // slight offset for 3D effect
        add(shadow);

        // Footer date (placeholder)
        JLabel fecha = new JLabel("<current date>");
        fecha.setFont(new Font("Arial", Font.PLAIN, 12));
        fecha.setForeground(Color.LIGHT_GRAY);
        fecha.setBounds(750, 520, 150, 20);
        add(fecha);

        setVisible(true);
    }

    // Creates a styled JTextField with 3D effect
    private JTextField crearInput(String placeholder, int y) {
        JTextField input = new JTextField(placeholder);
        input.setBounds(30, y, 240, 35);
        input.setBackground(Color.WHITE);
        input.setFont(new Font("Arial", Font.PLAIN, 14));
        input.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 4, 4, Color.LIGHT_GRAY), // shadow effect
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return input;
    }

    // Creates a styled password field
    private JPasswordField crearPasswordField(int y) {
        JPasswordField password = new JPasswordField();
        password.setBounds(30, y, 240, 35);
        password.setFont(new Font("Arial", Font.PLAIN, 14));
        password.setEchoChar('*');
        password.setBackground(Color.WHITE);
        password.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 4, 4, Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return password;
    }

    // Creates a styled JButton
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setForeground(Color.WHITE);
        boton.setBackground(color);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 4, 4, color.darker()), // bottom-right shadow
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(login::new);
    }
}

package shop.swing;

import java.awt.*;
import javax.swing.*;

public class AddUser extends JFrame {
    public AddUser() {
        setTitle("PowerApp");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(60, 60, 60));

        // tecxto superior
        JLabel titulo = new JLabel("Crea un nuevo vendedor");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(250, 20, 500, 30);
        add(titulo);

        // Boton volver
        JButton volver = new JButton("< volver");
        volver.setBounds(10, 10, 100, 30);
        volver.setBackground(Color.DARK_GRAY);
        volver.setForeground(Color.WHITE);
        volver.setBorderPainted(false);
        add(volver);

        // Panel de formulario
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(100, 100, 100)); // gris medio
        panel.setBounds(50, 80, 400, 350);
        add(panel);

        JLabel subtitulo = new JLabel("REGISTRAR VENDEDOR");
        subtitulo.setFont(new Font("Arial", Font.BOLD, 18));
        subtitulo.setForeground(Color.WHITE);
        subtitulo.setBounds(90, 10, 300, 30);
        panel.add(subtitulo);

        String[] placeholders = {
            "Ingrese su nombre de usuario",
            "Ingrese su nombre completo",
            "Ingrese su correo electrónico",
            "Ingrese su Contraseña",
            "Confirme su contraseña"
        };

        JTextField[] campos = new JTextField[placeholders.length];

        for (int i = 0; i < placeholders.length; i++) {
            JTextField campo;
            if (placeholders[i].toLowerCase().contains("contraseña")) {
                campo = new JPasswordField();
            } else {
                campo = new JTextField();
            }
            campo.setBounds(50, 50 + i * 50, 300, 35);
            campo.setText(placeholders[i]);
            campo.setFont(new Font("Arial", Font.PLAIN, 13));
            campo.setForeground(Color.GRAY);
            campo.setBackground(Color.WHITE);
            campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            panel.add(campo);
            campos[i] = campo;
        }

        // Boton registrar
        JButton registrar = new JButton("REGISTRAR");
        registrar.setBounds(100, 310, 200, 35);
        registrar.setBackground(new Color(0, 200, 100)); // verde
        registrar.setForeground(Color.WHITE);
        registrar.setFont(new Font("Arial", Font.BOLD, 14));
        registrar.setFocusPainted(false);
        registrar.setBorder(BorderFactory.createEmptyBorder());
        panel.add(registrar);

        // Icono de usuario
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/addUser.png"));
        Image imagenOriginal = icon.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        // Escalarss
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        // Colocamos en JLabel
        JLabel imagen = new JLabel(iconoEscalado);
        imagen.setBounds(500, 100, 300, 300);
        add(imagen);

        // Fecha en esquina
        JLabel fecha = new JLabel("<current date>");
        fecha.setForeground(Color.WHITE);
        fecha.setBounds(780, 480, 120, 30);
        add(fecha);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AddUser().setVisible(true);
        });
    }
}

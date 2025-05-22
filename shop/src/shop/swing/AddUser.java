package shop.swing;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import shop.CSVwriter.WriterUsers;
import shop.models.User;

public class AddUser extends JFrame {
    Font labelFont = new Font("SansSerif", Font.PLAIN, 14);

    public AddUser() {
        setTitle("PowerApp");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(60, 60, 60));

        // texto superior
        JLabel titulo = new JLabel("Crea un nuevo ADMINISTRADOR");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(290, 20, 500, 40);
        add(titulo);

        // Boton volver
        JButton volver = new RoundedButton("< volver", 15);
        volver.setBounds(10, 10, 100, 30);
        volver.setBackground(Color.DARK_GRAY);
        volver.setForeground(Color.WHITE);
        volver.setBorderPainted(false);
        add(volver);

        // Fecha
        JLabel txtFecha = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtFecha.setBounds(700, 450, 250, 30);
        txtFecha.setFont(new Font("Arial", Font.BOLD, 20));
        txtFecha.setForeground(Color.WHITE);
        add(txtFecha);

        // Panel de formulario
        JPanel panel = new RoundedPanel(30);
        panel.setLayout(null);
        panel.setBackground(new Color(100, 100, 100)); // gris medio
        panel.setBounds(50, 80, 400, 350);
        add(panel);

        JLabel subtitulo = new JLabel("REGISTRAR ADMINISTRADOR");
        subtitulo.setFont(new Font("Arial", Font.BOLD, 18));
        subtitulo.setForeground(Color.WHITE);
        subtitulo.setBounds(60, 10, 300, 30);
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
            String placeholder = placeholders[i];
        
            if (placeholder.toLowerCase().contains("contraseña")) {
                campo = new JPasswordField();
            } else {
                campo = new JTextField();
            }
        
            campo.setBounds(50, 50 + i * 50, 300, 35);
            campo.setFont(new Font("Arial", Font.PLAIN, 13));
            campo.setForeground(Color.GRAY);
            campo.setBackground(Color.WHITE);
            campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
        
            campo.setText(placeholder);
        
            // Simula el placeholder
            JTextField finalCampo = campo; // necesario para usar en clase interna
            campo.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    if (finalCampo.getText().equals(placeholder)) {
                        finalCampo.setText("");
                        finalCampo.setForeground(Color.BLACK);
                    }
                }
        
                public void focusLost(FocusEvent e) {
                    if (finalCampo.getText().isEmpty()) {
                        finalCampo.setText(placeholder);
                        finalCampo.setForeground(Color.GRAY);
                    }
                }
            });
        
            panel.add(campo);
            campos[i] = campo;
        }

        // Boton registrar
        RoundedButton registrar = new RoundedButton("REGISTRAR", 15);
            registrar.setBounds(100, 310, 200, 35);
            registrar.setBackground(new Color(0, 200, 100)); // verde
            registrar.setForeground(Color.WHITE);
            registrar.setFont(new Font("Arial", Font.BOLD, 14));
            registrar.setFocusPainted(false);
            registrar.setBorder(BorderFactory.createEmptyBorder());
            panel.add(registrar);


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

        // llamar al boton
        registrar.addActionListener(e -> {
            boolean todoValido = true;
        
            for (int i = 0; i < campos.length; i++) {
                String texto;
        
                if (campos[i] instanceof JPasswordField) {
                    texto = new String(((JPasswordField) campos[i]).getPassword());
                } else {
                    texto = campos[i].getText();
                }
        
                if (texto.equals(placeholders[i]) || texto.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Campo " + i + " vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                    todoValido = false;
                    continue;
                }
        
                switch (i) {
                    case 0: // nombre de usuario
                        if (texto.contains(" ")) {
                            JOptionPane.showMessageDialog(null, "El nombre de usuario no puede contener espacios.", "Error", JOptionPane.ERROR_MESSAGE);
                            todoValido = false;
                        }
                        break;
        
                    case 1: // nombre completo
                        if (texto.contains(",")) {
                            JOptionPane.showMessageDialog(null, "El nombre completo no debe contener comas.", "Error", JOptionPane.ERROR_MESSAGE);
                            todoValido = false;
                        }
                        break;
        
                    case 2: // correo electronico
                        if (!texto.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                            JOptionPane.showMessageDialog(null, "Correo electrónico inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                            todoValido = false;
                        }
                        break;
                }
            }
        
            // Verificar que las dos contrasenhas coincidan
            String pass1 = new String(((JPasswordField) campos[3]).getPassword());
            String pass2 = new String(((JPasswordField) campos[4]).getPassword());
        
            if (!pass1.equals(pass2)) {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                todoValido = false;
            }
        
            if (todoValido) {
                JOptionPane.showMessageDialog(null, "Usuario registrado", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // guardado de datos
                User user1 = new User(campos[1].getText(), campos[0].getText(), pass2, campos[2].getText());
                WriterUsers.appendUserToCSV(user1);

                // cuando el susuario se registre cambiaremos de ventana!
                Login main = new Login();
                main.setVisible(true);
                // Cierra esta ventana
                this.dispose();
            }
        });      

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AddUser().setVisible(true);
        });
    }
}

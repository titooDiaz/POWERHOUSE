package shop.swing;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import shop.CSVwriter.WriterUsers;

public class Login extends JFrame {

    //paneles
    private JPanel panelLogin;

    //labels
    private JLabel titulo;
    private JLabel loginLabel;
    private JLabel txtFecha;
    private JLabel logo;
    private JLabel shadow;

    //botones
    private JButton ingresar;
    private JButton recuperar;

    //imagen
    private ImageIcon originalIcon;
    private Image scaledImage;
    private ImageIcon resizedIcon;

    public Login() {
        setTitle("PowerApp");
        setSize(900, 550);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(50, 50, 50));

        // Main title
        titulo = new JLabel("BIENVENIDO A POWERAPP");
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(250, 30, 500, 30);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo);

        // Login panel with rounded background
        panelLogin = new RoundedPanel(30);
        panelLogin.setForeground(Color.BLACK);
        panelLogin.setBackground(Color.GRAY);
        panelLogin.setLayout(null);
        panelLogin.setBounds(80, 100, 300, 300);
        panelLogin.setOpaque(false);
        add(panelLogin);

        // "LOGIN" label
        loginLabel = new JLabel("INICIAR SESIÓN");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 18));
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setBounds(60, 20, 200, 30);
        panelLogin.add(loginLabel);

        // Fecha
        txtFecha = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtFecha.setBounds(700, 450, 250, 30);
        txtFecha.setFont(new Font("Arial", Font.BOLD, 20));
        txtFecha.setForeground(Color.WHITE);
        add(txtFecha);

        //Logica de introducir datos
        String[] placeholders = {
            "Ingrese usuario",
            "Ingrese contraseña"

        };
        
        JTextField[] campos = new JTextField[placeholders.length];
        
        for (int i = 0; i < placeholders.length; i++) {
            JTextField campo;
            String placeholder = placeholders[i];
        
            if (placeholder.toLowerCase().contains("contraseña")) {
                campo = new JPasswordField();
            }else {
                campo = new JTextField(); 
            }
        
            campo.setBounds(30, 70 + i * 50, 240, 35);
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
                @Override
                public void focusGained(FocusEvent e) {
                    if (finalCampo.getText().equals(placeholder)) {
                        finalCampo.setText("");
                        finalCampo.setForeground(Color.BLACK);
                    }
                }
        
                @Override
                public void focusLost(FocusEvent e) {
                    if (finalCampo.getText().isEmpty()) {
                        finalCampo.setText(placeholder);
                        finalCampo.setForeground(Color.GRAY);
                    }
                }
            });
        
            panelLogin.add(campo);
            campos[i] = campo;
        }

        // "Ingresar" button (green)
        ingresar = new RoundedButton("Ingresar", 15);
        ingresar.setBounds(30, 180, 240, 35);
        ingresar.setBackground(new Color(0, 200, 100)); // verde
        ingresar.setForeground(Color.WHITE);
        panelLogin.add(ingresar);

        // al dar clic...
        ingresar.addActionListener(e -> {
            Boolean valid = WriterUsers.login(campos[0].getText(),campos[1].getText());
            if (valid) {
                // si es válido, mostrar el panel de bienvenida
                TiendaVirtualGUI home = new TiendaVirtualGUI();
                home.setVisible(true);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "La contraseña o el nombre de usuario no es valido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        // Recuperar contraseña 
        recuperar = new RoundedButton("recuperar contraseña", 15);
        recuperar.setBackground(new Color(255, 102, 102)); // verde
        recuperar.setForeground(Color.WHITE);
        recuperar.setBounds(30, 230, 240, 35);
        panelLogin.add(recuperar);

        // logica de las imagenes
        originalIcon = new ImageIcon(getClass().getResource("/resources/images/icon.png"));
        scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(scaledImage);

        logo = new JLabel(resizedIcon);
        logo.setBounds(510, 110, 300, 300);
        add(logo);
        
        shadow = new JLabel();
        shadow.setBounds(510, 110, 300, 300);
        shadow.setOpaque(true);
        shadow.setBackground(Color.WHITE);
        shadow.setBorder(BorderFactory.createEmptyBorder());
        shadow.setLocation(510 + 8, 110 + 8); // slight offset for 3D effect
        add(shadow);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}

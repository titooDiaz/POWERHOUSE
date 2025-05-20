package shop.swing;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Profile extends JFrame {
    public Profile() {
        setTitle("PowerApp");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(60, 60, 60));


        // Botón volver
        JButton volver = new RoundedButton("< volver", 15);
        volver.setBounds(10, 10, 100, 30);
        volver.setBackground(Color.DARK_GRAY);
        volver.setForeground(Color.WHITE);
        volver.setBorderPainted(false);
        add(volver);

        // Título
        JLabel titulo = new JLabel("Mi Perfil");
        titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(360, 20, 300, 40);
        add(titulo);

        // Panel lateral izquierdo (más alto)
        RoundedPanel panelInfo = new RoundedPanel(30);
        panelInfo.setBackground(new Color(102, 102, 102));
        panelInfo.setBounds(60, 80, 420, 420);  // altura aumentada de 370 a 420
        panelInfo.setLayout(null);
        add(panelInfo);

        //fecha
        JLabel txtFecha = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtFecha.setBounds(720, 480, 250, 30);
        txtFecha.setFont(new Font("Arial", Font.BOLD, 20));
        txtFecha.setForeground(Color.WHITE);
        add(txtFecha);

        // Datos del perfil
        String[] campos = {"Nombre", "nombre de usuario", "Correo electrónico", "Tipo de usuario", "Estado"};
        int y = 20;

        for (String campo : campos) {
            // Etiqueta del campo
            JLabel etiqueta = new JLabel(campo);
            etiqueta.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            etiqueta.setForeground(Color.WHITE);
            etiqueta.setBounds(20, y, 200, 20);
            panelInfo.add(etiqueta);

            // Valor del campo (JLabel blanco)
            JLabel valor = new JLabel("  Aquí va el dato...");
            valor.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            valor.setOpaque(true);
            valor.setBackground(Color.WHITE);
            valor.setBounds(20, y + 25, 380, 30);
            panelInfo.add(valor);

            y += 75;
        }

        // Área para imagen o avatar a la derecha (coordenada y igual que panel)
        JLabel avatar = new JLabel();
        avatar.setBounds(550, 110, 300, 300);
        avatar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        add(avatar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Profile().setVisible(true));
    }
}

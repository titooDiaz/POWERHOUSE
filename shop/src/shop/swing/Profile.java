package shop.swing;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

import shop.CSVwriter.WriterUsers;

public class Profile extends JFrame {
    //paneles
    private JPanel panelInfo;

    //labels
    private JLabel titulo;
    private JLabel txtFecha;
    private JLabel avatar;

    //a
    //botones
    private JButton volver;

    //imagen
    private ImageIcon originalIcon;
    private Image scaledImage;
    private ImageIcon resizedIcon;
    
    //estilos
    Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
    public Profile() {
        setTitle("PowerApp");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(60, 60, 60));


        // Botón volver
        volver = new RoundedButton("< volver", 15);
        volver.setBounds(10, 10, 100, 30);
        volver.setBackground(Color.DARK_GRAY);
        volver.setForeground(Color.WHITE);
        volver.setBorderPainted(false);
        add(volver);
        volver.addActionListener(e -> {
            TiendaVirtualGUI tienda = new TiendaVirtualGUI();
            tienda.setVisible(true);
            this.dispose();
        });

        // Título
        titulo = new JLabel("Mi Perfil");
        titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(360, 20, 300, 40);
        add(titulo);

        // Panel lateral izquierdo (más alto)
        panelInfo = new RoundedPanel(30);
        panelInfo.setBackground(new Color(102, 102, 102));
        panelInfo.setBounds(60, 80, 420, 420);  // altura aumentada de 370 a 420
        panelInfo.setLayout(null);
        add(panelInfo);

        //fecha
        txtFecha = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtFecha.setBounds(720, 480, 250, 30);
        txtFecha.setFont(new Font("Arial", Font.BOLD, 20));
        txtFecha.setForeground(Color.WHITE);
        add(txtFecha);

        // Datos del perfil
        String[] campos = {"Nombre", "nombre de usuario", "Correo electrónico", "Tipo de usuario", "Estado"};
        int y = 20;

        int i = 1;
        for (String campo : campos) {
            
            // Etiqueta del campo
            JLabel etiqueta = new JLabel(campo);
            etiqueta.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            etiqueta.setForeground(Color.WHITE);
            etiqueta.setBounds(20, y, 200, 20);
            panelInfo.add(etiqueta);

            if (i==3){
            // Valor del campo (JLabel blanco)
            i++;
            String use = WriterUsers.obtenerCampoPorPk("1", i);
            JLabel valor = new JLabel(use);
            valor.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            valor.setOpaque(true);
            valor.setBackground(Color.WHITE);
            valor.setBounds(20, y + 25, 380, 30);
            panelInfo.add(valor);
            
            }

            else if(i==5){
            // Valor del campo (JLabel blanco)
            JLabel valor = new JLabel("Administrador");
            valor.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            valor.setOpaque(true);
            valor.setBackground(Color.WHITE);
            valor.setBounds(20, y + 25, 380, 30);
            panelInfo.add(valor);
            
            }

            else if(i==6){
            JLabel valor = new JLabel("activo");
            valor.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            valor.setOpaque(true);
            valor.setBackground(Color.WHITE);
            valor.setBounds(20, y + 25, 380, 30);
            panelInfo.add(valor);
            
            }

            else{

            String user = WriterUsers.obtenerCampoPorPk("1", i);
            JLabel valor = new JLabel(user);
            valor.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            valor.setOpaque(true);
            valor.setBackground(Color.WHITE);
            valor.setBounds(20, y + 25, 380, 30);
            
            panelInfo.add(valor);
            
            }
            i++;
            y += 75;
           
        }

            originalIcon = new ImageIcon(getClass().getResource("/resources/images/user-interface.png"));
            scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            resizedIcon = new ImageIcon(scaledImage);

            avatar = new JLabel(resizedIcon);
            avatar.setBounds(510, 110, 300, 300);
            add(avatar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Profile().setVisible(true));
    }
}

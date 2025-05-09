package shop;

import javax.swing.JButton;
import javax.swing.JFrame;
import shop.CSVwriter.CSVWriter;
import shop.models.User;

public class Shop {
    public static void main(String[] args) {
        System.out.println("hola mundo");

        // create user
        User user1 = new User("Name", "UserName", "Password", "Email@example.com");
        CSVWriter.appendUserToCSV(user1);

        // Crear la ventana
        JFrame frame = new JFrame("Mi primera GUI con Swing");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un botón
        JButton boton = new JButton("Haz clic aquí");

        // Agregar el botón a la ventana
        frame.add(boton);

        // Hacer visible la ventana
        frame.setVisible(true);
    }
}

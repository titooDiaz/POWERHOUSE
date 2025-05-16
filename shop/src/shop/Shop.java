package shop;

import javax.swing.JButton;
import javax.swing.JFrame;
import shop.CSVwriter.WriterUsers;
import shop.models.User;
import shop.swing.*;

public class Shop {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            AddUser login = new AddUser();
            login.setVisible(true);
        });
    }
}


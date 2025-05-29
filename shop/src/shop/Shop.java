package shop;

import javax.swing.JOptionPane;
import shop.CSVwriter.PkManager;
import shop.CSVwriter.WriterUsers;
import shop.swing.*;

public class Shop {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            int currentPk = PkManager.getLastPk(0);
            if (currentPk == -1) {
                JOptionPane.showMessageDialog(null, "Error, no se obtuvo el pk", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (currentPk != 1){
                    if (WriterUsers.IsInSession()){
                        TiendaVirtualGUI home = new TiendaVirtualGUI();
                        home.setVisible(true);
                    }else{
                        Login login = new Login();
                        login.setVisible(true);
                    }
                }else{
                    AddUser register = new AddUser();
                    register.setVisible(true);
                }
            }
        });
    }
}

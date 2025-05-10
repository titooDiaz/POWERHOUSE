package shop.CSVwriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import shop.models.User;

public class WriterUsers {
    public static void appendUserToCSV(User user) {
        String basePath = System.getProperty("user.dir");
        String filePath = basePath + "/POWERHOUSE/shop/src/resources/data/Users/user.csv";

        boolean fileExists = new java.io.File(filePath).exists();

        try (FileWriter fw = new FileWriter(filePath, true);
             PrintWriter pw = new PrintWriter(fw)) {

            // Write header only if the file does not exist
            if (!fileExists) {
                pw.println("pk,name,username,password,email,type,active,key");
            }

            int currentPk = PkManager.getAndIncrementPk(0);
            if (currentPk ==-1){
                JOptionPane.showMessageDialog(null, "Error al procesar el guardado", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                String line = "\n" + currentPk++ + "," +
                    user.getName() + "," +
                    user.getUsername() + "," +
                    user.getPassword() + "," +
                    user.getEmail() + "," +
                    "default" + "," +
                    "true" + "," +
                    generateRandomKey();

            pw.print(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateRandomKey() {
        return java.util.UUID.randomUUID().toString();
    }
}
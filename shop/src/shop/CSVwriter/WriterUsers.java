package shop.CSVwriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import shop.models.User;

public class WriterUsers {
    static String basePath = System.getProperty("user.dir");
    static String filePath = basePath + "/shop/src/resources/data/Users/user.csv";
    static String filePath2 = basePath + "/shop/src/resources/data/currentUser.txt";

    public static void appendUserToCSV(User user) {

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
    
    // logica de inicio de sesion
    static public Boolean AproveUser(String username, String pass){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            String[] fields = line.split(",", -1);
            if (fields.length > 3) {
                String existingUsername = fields[2].trim();
                String password = fields[3].trim();
                if (existingUsername.equals(username)) {
                    if (password.equals(pass)) {
                        guardarUsuarioActual(fields[0]);
                        return true;
                    } else {
                        return false;
                    }
                  }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
 
    public static Boolean login(String username, String pass) {
        if (AproveUser(username, pass)){
            return true;
        }
        return false;
    }
 
    private static String generateRandomKey() {
        return java.util.UUID.randomUUID().toString();
    }

    public static void guardarUsuarioActual(String pk) { 
        try (FileWriter escritor = new FileWriter(filePath2, false)) { 
            escritor.write(obtenerLineaPorPk(pk));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static String obtenerLineaPorPk(String pkBuscado) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            if (!linea.trim().isEmpty()) {
                String[] partes = linea.split(",");
                if (partes.length >= 1 && partes[0].equals(pkBuscado)) {
                    return linea; // Retorna la línea completa
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return null; 
    }

    public static void sobrescribirConCero() {
        try (FileWriter escritor = new FileWriter(filePath2, false)) {
            escritor.write("0\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String obtenerCampoPorPk(String pkBuscado, int posicionCampo) {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            if (!linea.trim().isEmpty()) {
                String[] partes = linea.split(",");
                if (partes.length > posicionCampo && partes[0].equals(pkBuscado)) {
                    return partes[posicionCampo]; // Retorna el campo deseado
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return null;
}
    
}
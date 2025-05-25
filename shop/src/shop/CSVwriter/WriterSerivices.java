package shop.CSVwriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import shop.models.Services;

public class WriterSerivices {
    static String basePath = System.getProperty("user.dir");
    static String filePath = basePath + "/shop/src/resources/data/Categories/Service/services.csv";

    public static void appendServicesCSV(Services services) {

        boolean fileExists = new java.io.File(filePath).exists();

        try (FileWriter fw = new FileWriter(filePath, true);
             PrintWriter pw = new PrintWriter(fw)) {

            // Write header only if the file does not exist
            if (!fileExists) {
                pw.println("pk,name,code,price,date,active");
            }


            int currentPk = PkManager.getAndIncrementPk(2);
            if (currentPk ==-1){
                JOptionPane.showMessageDialog(null, "Error al procesar el guardado", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                String line = "\n" + currentPk++ + "," +
                    services.getName() + "," +
                    services.getCode() + "," +
                    services.getPrice() + "," +
                    services.getDate() + "," +
                    services.isActive();
            pw.print(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Services> loadFromCSV(LinkedList<Services> services) {
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // 
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int pk = Integer.parseInt(parts[0]);
                String name = parts[1];
                String code = parts[2];
                float price = Float.parseFloat(parts[3]);
                String date = parts[4];
                boolean active = Boolean.parseBoolean(parts[5]);
                // Crear un nuevo objeto Products y agregarlo a la lista
                Services servicioNuevo = new Services(name, code, price, date, active);
                servicioNuevo.setPk(pk);
                services.add(servicioNuevo);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        }
        return services;
    }
}

package shop.CSVwriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
                    services.isActive() + ",";
            pw.print(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

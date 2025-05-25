package shop.CSVwriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import shop.models.Service;

public class WriterService {
    static String basePath = System.getProperty("user.dir");
    static String filePath = basePath + "/shop/src/resources/data/Categories/Service/service.csv";

    public static void appendProductCSV(Service service) {

        boolean fileExists = new java.io.File(filePath).exists();

        try (FileWriter fw = new FileWriter(filePath, true);
             PrintWriter pw = new PrintWriter(fw)) {

            // Write header only if the file does not exist
            if (!fileExists) {
                pw.println("pk,date,price,paidMethod,servicesPK");
            }

            int currentPk = PkManager.getAndIncrementPk(3);
            if (currentPk ==-1){
                JOptionPane.showMessageDialog(null, "Error al procesar el guardado", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                // pk,date,price,paidMethod,servicesPK
                String line = "\n" + currentPk++ + "," +
                    service.getDate() + "," +
                    service.getPrice() + "," +
                    service.getPaidMethod() + "," +
                    service.getServices();

            pw.print(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

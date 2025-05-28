package shop.CSVwriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import shop.models.Product;

public class WriterProduct {
    static String basePath = System.getProperty("user.dir");
    static String filePath = basePath + "/shop/src/resources/data/Categories/Product/product.csv";

    public static void appendProductCSV(Product products) {

        boolean fileExists = new java.io.File(filePath).exists();

        try (FileWriter fw = new FileWriter(filePath, true);
             PrintWriter pw = new PrintWriter(fw)) {

            // Write header only if the file does not exist
            if (!fileExists) {
                pw.println("pk,purchase_price,date,price,purchased,paidMethod,productPK");
            }

            int currentPk = PkManager.getAndIncrementPk(5);
            if (currentPk ==-1){
                JOptionPane.showMessageDialog(null, "Error al procesar el guardado", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                // pk,purchase_price,date,price,purchased,paidMethod,productPK
                String line = "\n" + currentPk++ + "," +
                    "-1," +
                    products.getDate() + "," +
                    products.getPrice() + "," +
                    "false" + "," +
                    products.getPaidMethod() + "," +
                    products.getProducts();

            pw.print(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package shop.CSVwriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import shop.models.Product;
import shop.models.Products;

public class WriterProduct {
    static String basePath = System.getProperty("user.dir");
    static String filePath = basePath + "/shop/src/resources/data/Categories/Product/product.csv";

    public static void appendProductCSV(Products listProducts, int pKs) {

        boolean fileExists = new java.io.File(filePath).exists();

        try (FileWriter fw = new FileWriter(filePath, true);
             PrintWriter pw = new PrintWriter(fw)) {

            // Escribir encabezado si el archivo no existe
            if (!fileExists) {
                pw.println("pk,purchase_price,dateBuy,price,purchased,paidMethod,productPK");
            }

            int currentPk = PkManager.getAndIncrementPk(5);
            if (currentPk == -1) {
                JOptionPane.showMessageDialog(null, "Error al procesar el guardado", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Escribir productos

                for (Product product : listProducts.products) {
                    String line = "\n" + currentPk++ + "," +
                        product.getPurchase_price() + "," +
                        product.getDateBuy() + "," +
                        product.getPrice() + "," +
                        product.isPurchased() + "," +
                        product.getPaidMethod() + "," +
                        product.getPk() +
                        "," + pKs ;
                    pw.print(line);
                }
               
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

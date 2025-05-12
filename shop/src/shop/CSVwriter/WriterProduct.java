package shop.CSVwriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import shop.models.Product;

public class WriterProduct {
    public static void appendUserToCSV(Product product) {
        String basePath = System.getProperty("user.dir");
        String filePath = basePath + "/POWERHOUSE/shop/src/resources/data/Product/product.csv";
    }
}
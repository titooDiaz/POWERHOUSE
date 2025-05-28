package shop.CSVwriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import shop.models.Product;
import shop.models.Products;

public class WriterProduct {
    static String basePath = System.getProperty("user.dir");
    static String filePath = basePath + "/shop/src/resources/data/Categories/Product/product.csv";
    static String filePath2 = basePath + "/shop/src/resources/data/sale.csv";

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

    public static void moverLineasPorPK(int pk, int cantidad) {
    int contador = 0;

    try (
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath2, false)))
    ) {
        // Verifica si el archivo de destino ya existe
        File archivoDestino = new File(filePath2);
        boolean fileExists = archivoDestino.exists();

        // Si no existe, escribe el encabezado
        if (!fileExists || archivoDestino.length() == 0) {
            pw.println("pk,name,code,price,date,active"); // encabezado 
        }

        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length > 6) {
                try {
                    int pkEncontrado = Integer.parseInt(partes[6]);

                    if (pkEncontrado == pk && contador < cantidad) {
                        pw.println(linea); // Escribe la línea
                        contador++;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Error: PK inválida -> " + partes[6]);
                }
            } else {
            }
        }

        pw.flush();

    } catch (IOException e) {
        System.out.println("Error de E/S: " + e.getMessage());
    }
}

}

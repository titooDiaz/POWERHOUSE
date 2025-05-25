package shop.CSVwriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import shop.models.*;;

public class WriterPaidMethod {
    static String basePath = System.getProperty("user.dir");
    static String filePath = basePath + "/shop/src/resources/data/Categories/paidMetoh.csv";

    public static void appendPaidCSV(PaidMethod metodoPago) {

        boolean fileExists = new java.io.File(filePath).exists();

        try (FileWriter fw = new FileWriter(filePath, true);
             PrintWriter pw = new PrintWriter(fw)) {

            // Write header only if the file does not exist
            if (!fileExists) {
                pw.println("pk,name");
            }

            int currentPk = PkManager.getAndIncrementPk(7);
            if (currentPk ==-1){
                JOptionPane.showMessageDialog(null, "Error al procesar el guardado", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                String line = "\n" + currentPk++ + "," +
                    metodoPago.getName();

            pw.print(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<PaidMethod> loadPaidMethodsFromCSV() {
        LinkedList<PaidMethod> lista = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Saltar encabezado
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    try {
                        int pk = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        PaidMethod metodo = new PaidMethod(pk, name);
                        lista.add(metodo);
                    } catch (NumberFormatException ex) {
                        System.err.println("Línea inválida: " + line);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

}


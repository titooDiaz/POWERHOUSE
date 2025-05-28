package shop.CSVwriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GenerateReport {
    public static void generate(){
        try {
            // Cambia 'python3' por 'python' si estás en Windows o depende de tu instalación
            ProcessBuilder pb = new ProcessBuilder("python3", "shop/src/shop/CSVwriter/reports.py");
            pb.redirectErrorStream(true); // Combina stdout y stderr

            Process process = pb.start();

            // Leer la salida del script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println("Python dijo: " + linea);
            }

            int exitCode = process.waitFor();
            System.out.println("El script Python terminó con código: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

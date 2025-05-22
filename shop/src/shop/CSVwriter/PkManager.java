package shop.CSVwriter;

import java.io.*;

public class PkManager {
    private static final String filePathPk = System.getProperty("user.dir") + "/shop/src/resources/data/currentPk.csv";

    public static int getAndIncrementPk(int index) {
        try {
            // Leer archivo
            BufferedReader reader = new BufferedReader(new FileReader(filePathPk));
            String headerLine = reader.readLine(); // ignorar header
            String dataLine = reader.readLine();  // <- informacion
            reader.close();

            String[] values = dataLine.split(",");

            if (index < 0 || index >= values.length) {
                throw new IllegalArgumentException("Índice fuera de rango: " + index);
            }

            int currentPk = Integer.parseInt(values[index]);
            values[index] = String.valueOf(currentPk + 1); // el indice actual + 1

            // Reescribir el archivo
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePathPk));
            writer.write(headerLine);
            writer.newLine();
            writer.write(String.join(",", values));
            writer.close();

            return currentPk;

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getLastPk(int index) {
        try {
            System.out.println(filePathPk);
            // Leer archivo
            BufferedReader reader = new BufferedReader(new FileReader(filePathPk));
            String headerLine = reader.readLine(); // ignorar header
            String dataLine = reader.readLine();  // <- informacion
            reader.close();

            String[] values = dataLine.split(",");

            if (index < 0 || index >= values.length) {
                throw new IllegalArgumentException("Índice fuera de rango: " + index);
            }

            int currentPk = Integer.parseInt(values[index]);
            return currentPk;

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }
}

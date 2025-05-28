package shop.CSVwriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import shop.models.Product;

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
                    if (pkEncontrado == pk) {
                        contador ++;
                        System.out.println(contador);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: PK inválida -> " + partes[6]);
                }
            } 
        }
        //pw.println(linea);
        if (contador >= cantidad) {
            List<String> todasLasLineas = new ArrayList<>();
            BufferedReader br2 = new BufferedReader(new FileReader(filePath));
            String linea2;
            int contadorModificados = 0;

            while ((linea2 = br2.readLine()) != null) {
                String[] partes = linea2.split(",");

                // Verifica que tenga al menos 7 columnas
                if (partes.length > 6) {
                    try {
                        int pkEncontrado = Integer.parseInt(partes[6]);
                        if (pkEncontrado == pk && contadorModificados < cantidad) {
                            if (partes[4].equals("false")){
                                partes[4] = "true"; // cambiamos "purchased" a true
                                contadorModificados++;
                                linea2 = String.join(",", partes);
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: PK inválida -> " + partes[6]);
                    }
                }

                todasLasLineas.add(linea2);
            }
            br2.close();

            // Reescribimos el archivo con las líneas modificadas
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            for (String l : todasLasLineas) {
                bw.write(l);
                bw.newLine();
            }
            bw.close();

            System.out.println("Modificados " + contadorModificados + " registros.");
        }
        pw.flush();

    } catch (IOException e) {
        System.out.println("Error de E/S: " + e.getMessage());
    }
}

    public static int contarVendidosPorMes(int pk, String mes) {
        int vendidos = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length >= 7) {
                    String fecha = partes[2].trim(); // formato: dd/MM/yyyy
                    String purchased = partes[4].trim();
                    int productPK = Integer.parseInt(partes[6].trim());

                    if (productPK == pk && purchased.equalsIgnoreCase("true")) {
                        System.out.println("mas");
                        String[] fechaPartes = fecha.split("/");
                        if (fechaPartes.length == 3 && fechaPartes[1].equals(mes)) {
                            vendidos++;
                        }
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer o procesar el archivo: " + e.getMessage());
        }

        return vendidos;
    }

    public static int contarProductosDisponiblesPorCodigo(String codigo) {
    // Primero obtener el PK del producto con este código
    int productPK = obtenerPKPorCodigo(codigo);
    if (productPK == -1) return 0; // No existe producto con este código
    
    int contador = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        br.readLine(); // Saltar cabecera
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 7 && 
                parts[4].equalsIgnoreCase("false") && // No vendido
                parts[6].equals(String.valueOf(productPK))) { // Coincide con el PK
                contador++;
            }
        }
    } catch (IOException e) {
        System.out.println("Error leyendo product.csv: " + e.getMessage());
    }
    return contador;
    }

    private static int obtenerPKPorCodigo(String codigo) {
    String productsFilePath = basePath + "/shop/src/resources/data/Categories/Product/products.csv";
    try (BufferedReader br = new BufferedReader(new FileReader(productsFilePath))) {
        String line;
        br.readLine(); // Saltar cabecera
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 3 && parts[2].equals(codigo)) {
                return Integer.parseInt(parts[0]); // Retornar el PK
            }
        }
    } catch (IOException | NumberFormatException e) {
        System.out.println("Error leyendo products.csv: " + e.getMessage());
    }
    return -1; // No encontrado
    }

}

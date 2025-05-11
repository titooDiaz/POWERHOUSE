<p align="center">
  <img width="150px" src="https://i.ibb.co/bXvzjXm/LOGO-h1.png" alt="Logo de h1">
</p>

# 📁 Documentación de la carpeta `data`

La carpeta `data` es el espacio designado para almacenar toda la información relevante del sistema. Está organizada en diferentes categorías, tales como:

* 👤 `users`
* 🗂️ `categories`
* 📄 Archivos en formato `.csv`

## 📌 `currentPk.csv`

Uno de los archivos más importantes dentro del directorio principal de `data` es `currentPk.csv`. Este archivo cumple la función de almacenar los índices o claves primarias (PK) actuales para cada tipo de entidad. Cada vez que se realiza una nueva agregación a alguno de los archivos CSV del sistema, este archivo se actualiza para mantener el control de los identificadores únicos.

### 🗺️ Ubicación:

```bash
data/currentPk.csv
```

### 🧱 Estructura:

Encabezados:

```csv
user,categorie,services,service,products,product,sale
```

### 🛠️ Clase asociada: `PkManager`

Para la manipulación de este archivo existe una clase especializada ubicada en:

```bash
shop/CSVwriter/PkManager.java
```

### 🔁 Método: `getAndIncrementPk(int index)`

Este método recibe un parámetro entero entre `0` y `6`, y realiza las siguientes operaciones:

1. 🔍 Obtiene el valor actual del campo correspondiente en `currentPk.csv`.
2. 📤 Retorna ese valor.
3. 🧮 Incrementa el valor en el archivo, garantizando que el próximo registro tenga un PK único.

### 🧭 Mapeo de índices:

| ⬆️ Índice | 🏷️ Campo |
| --------: | --------- |
|         0 | user      |
|         1 | categorie |
|         2 | services  |
|         3 | service   |
|         4 | products  |
|         5 | product   |
|         6 | sale      |

### 🧪 Ejemplo de uso para usuarios
El siguiente ejemplo muestra cómo obtener el identificador actual para un nuevo usuario, manejar errores y continuar con el proceso:

```java
  int currentPk = PkManager.getAndIncrementPk(0);
  if (currentPk == -1) {
      JOptionPane.showMessageDialog(null, "Error al procesar el guardado", "Error", JOptionPane.ERROR_MESSAGE);
  } else {
      // Continuar con el proceso de guardado utilizando currentPk
      // Por ejemplo: escribir en el CSV de usuarios
  }
```
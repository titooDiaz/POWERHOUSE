<p align="center">
  <img width="150px" src="https://i.ibb.co/bXvzjXm/LOGO-h1.png" alt="Logo de h1">
</p>


### 📂 Estructura de la carpeta `resources`

La carpeta `resources/` está destinada a almacenar toda la información persistente del sistema en formato **CSV**. Dentro de ella existen dos subdirectorios principales:

---

#### 📁 `data/`
- Contiene los archivos **activos del sistema**, es decir, los datos que actualmente están en uso por la aplicación.
- Aquí se encuentran los registros de usuarios, ventas, categorías, productos y servicios.

---

#### 📁 `initialData/`
- Contiene una **copia base o de respaldo** de los datos iniciales del sistema.
- Se utiliza como fuente cuando se desea **formatear o reiniciar** el sistema a su estado original.
- Al realizar este proceso, los archivos de `initialData/` son copiados a `data/`, sobrescribiendo la información actual.

---

### 📄 Archivos contenidos

Dentro de ambas carpetas (`data/` y `initialData/`), los archivos `.csv` están organizados en subdirectorios temáticos, incluyendo:

- `Categories/`
  - `categories.csv` – Categorías principales.
  - `Product/` – Productos:
    - `product.csv`, `products.csv`
  - `Service/` – Servicios:
    - `service.csv`, `services.csv`

- `Users/`
  - `admin.csv`, `seller.csv`, `user.csv` – Información segmentada por tipo de usuario.

- `sale.csv` – Registro de ventas realizadas.
- `currentPk.csv` – Control de claves primarias (IDs) actuales del sistema.

---

Esta estructura busca mantener una **clara separación entre los datos operativos y los datos de respaldo**, facilitando la restauración del sistema y el manejo organizado de la información.
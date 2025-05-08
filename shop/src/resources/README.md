<p align="center">
  <img width="150px" src="https://i.ibb.co/bXvzjXm/LOGO-h1.png" alt="Logo de h1">
</p>

# 📂 Estructura de la carpeta `resources`

La carpeta `resources/` está destinada a almacenar toda la información persistente del sistema en formato **CSV** y **imágenes**. Dentro de ella existen varios subdirectorios, cada uno con un propósito específico.

---

## 📁 `data/`
- Contiene los archivos **activos del sistema**, es decir, los datos que actualmente están en uso por la aplicación.
- Aquí se encuentran los registros de usuarios, ventas, categorías, productos y servicios.

---

## 📁 `images/`
- Esta carpeta está destinada a almacenar todas las **imágenes** que se utilizan en el proyecto.
- Las imágenes en esta carpeta pueden ser de varios tipos: iconos, logotipos, fondos o cualquier recurso visual necesario para la interfaz de usuario.

### Estructura de la carpeta `images/`:


---

## 📄 Archivos contenidos en `data/`

Dentro de la carpeta `data/`, los archivos `.csv` están organizados en subdirectorios temáticos, incluyendo:

- **Categories/**
  - `categories.csv` – Categorías principales.
- **Product/**
  - `product.csv`, `products.csv` – Productos.
- **Service/**
  - `service.csv`, `services.csv` – Servicios.
- **Users/**
  - `admin.csv`, `seller.csv`, `user.csv` – Información segmentada por tipo de usuario.
- `sale.csv` – Registro de ventas realizadas.
- `currentPk.csv` – Control de claves primarias (IDs) actuales del sistema.

---

Esta estructura busca mantener una **clara separación entre los datos operativos** y los recursos visuales, facilitando el acceso a la información y la organización del sistema.

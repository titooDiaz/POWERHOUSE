import os
import pandas as pd
import matplotlib.pyplot as plt
from matplotlib.backends.backend_pdf import PdfPages

# Guardar la ruta original
original_dir = os.getcwd()

# Paths relativos a los CSV
producto_path = os.path.normpath("shop/src/resources/data/Categories/Product/product.csv")
servicio_path = os.path.normpath("shop/src/resources/data/Categories/Service/service.csv")
productos_path = os.path.normpath("shop/src/resources/data/Categories/Product/products.csv")
servicios_path = os.path.normpath("shop/src/resources/data/Categories/Service/services.csv")
metod_pago_path = os.path.normpath("shop/src/resources/data/Categories/paidMetoh.csv")
ARCHIVO = os.path.normpath("shop/src/resources/data/mes.txt")

# Cambiar al directorio del script
script_dir = os.path.dirname(os.path.abspath(__file__))
os.chdir(script_dir)

# Leer archivos CSV
df_product = pd.read_csv(os.path.join(original_dir, producto_path))
df_service = pd.read_csv(os.path.join(original_dir, servicio_path))
df_productos = pd.read_csv(os.path.join(original_dir, productos_path))
df_servicios = pd.read_csv(os.path.join(original_dir, servicios_path))
df_pago = pd.read_csv(os.path.join(original_dir, metod_pago_path))

# Convertir fechas
for df in [df_product, df_service, df_productos, df_servicios]:
    df['date'] = pd.to_datetime(df['date'], format='%d/%m/%Y')

# Leer mes
with open(os.path.join(original_dir, ARCHIVO), 'r', encoding='utf-8') as f:
    mes = f.read().strip()

# Filtrar por mes
df_product_mes = df_product[df_product['date'].dt.strftime('%m') == mes]
df_service_mes = df_service[df_service['date'].dt.strftime('%m') == mes]
df_productos_mes = df_productos[df_productos['date'].dt.strftime('%m') == mes]
df_servicios_mes = df_servicios[df_servicios['date'].dt.strftime('%m') == mes]

# Verificar si hay datos suficientes
if df_product_mes.empty and df_service_mes.empty and df_productos_mes.empty and df_servicios_mes.empty:
    # PDF vacío
    with PdfPages("../../../../powerapp.pdf") as pdf:
        fig, ax = plt.subplots(figsize=(8.5, 11))
        ax.axis('off')
        ax.text(0.5, 0.5, "No hubo datos suficientes en el mes seleccionado.",
                ha='center', va='center', fontsize=16)
        fig.suptitle("POWERAPP", fontsize=18)
        pdf.savefig(fig, bbox_inches='tight')
        plt.close(fig)
    print("PDF generado con mensaje de datos insuficientes.")
    os.chdir(original_dir)
    exit()

# Función para obtener nombre de método de pago
def get_paid_method_name(pk):
    if pk in df_pago['pk'].values:
        return df_pago[df_pago['pk'] == pk]['name'].values[0]
    return "Desconocido"

# Función para dibujar tabla
def draw_table(title, data):
    fig, ax = plt.subplots(figsize=(10, 0.5 + len(data) * 0.4))
    ax.set_axis_off()
    plt.title(title, fontsize=14, fontweight='bold')
    table = ax.table(cellText=data.values, colLabels=data.columns,
                     loc='center', cellLoc='center')
    table.scale(1, 1.5)
    return fig

# =================== GENERAR PDF BONITO ===================
try:
    # GRAFICA 1: Tortas
    vendidos = df_product_mes[df_product_mes['purchased'] == True].shape[0]
    no_vendidos = df_product_mes[df_product_mes['purchased'] == False].shape[0]

    fig1, ax1 = plt.subplots()
    ax1.pie([vendidos, no_vendidos],
            labels=['Vendidos', 'No vendidos'],
            autopct='%1.1f%%',
            startangle=90,
            colors=['#4CAF50', '#F44336'])
    ax1.axis('equal')
    fig1.suptitle("POWERAPP", fontsize=16)
    plt.title(f'Productos vendidos vs no vendidos (mes {mes})')

    # GRAFICA 2: Comparativa
    compras_productos = vendidos + no_vendidos
    compras_servicios = df_service_mes.shape[0]

    fig2, ax2 = plt.subplots()
    ax2.bar(['Productos', 'Servicios'],
            [compras_productos, compras_servicios],
            color=['#2196F3', '#FFC107'])
    plt.title(f'Comparación de compras en mes {mes}')
    plt.ylabel('Cantidad')
    fig2.suptitle("POWERAPP", fontsize=16)

    with PdfPages("../../../../powerapp.pdf") as pdf:
        pdf.savefig(fig1, bbox_inches='tight')
        pdf.savefig(fig2, bbox_inches='tight')

        # Agregar método de pago
        df_servicios_mes['paidMethodName'] = df_service_mes['paidMethod'].apply(get_paid_method_name)
        df_productos_mes['paidMethodName'] = df_product_mes['paidMethod'].apply(get_paid_method_name)

        # Tablas Servicios
        for _, padre in df_servicios_mes.iterrows():
            hijos = df_service_mes[df_service_mes['servicesPK'] == padre['pk']].copy()
            if not hijos.empty:
                hijos['paidMethodName'] = hijos['paidMethod'].apply(get_paid_method_name)
                tabla = hijos[['date', 'price', 'paidMethodName']].copy()
                tabla.columns = ['Fecha', 'Precio', 'Método de Pago']
                fig = draw_table(f"Servicio: {padre['name']} (Código: {padre['code']})", tabla)
                pdf.savefig(fig, bbox_inches='tight')
                plt.close(fig)

        # Tablas Productos
        for _, padre in df_productos_mes.iterrows():
            hijos = df_product_mes[df_product_mes['productPK'] == padre['pk']].copy()
            if not hijos.empty:
                hijos['paidMethodName'] = hijos['paidMethod'].apply(get_paid_method_name)
                tabla = hijos[['date', 'price', 'paidMethodName', 'purchased', 'purchase_price']].copy()
                tabla.columns = ['Fecha', 'Precio de Compra', 'Método de Pago', 'Vendido', 'Precio de Venta']
                fig = draw_table(f"Producto: {padre['name']} (Código: {padre['code']})", tabla)
                pdf.savefig(fig, bbox_inches='tight')
                plt.close(fig)

    print("PDF generado exitosamente en: powerapp.pdf")

except Exception as e:
    print("Ocurrió un error inesperado:", e)

# Restaurar ruta
os.chdir(original_dir)

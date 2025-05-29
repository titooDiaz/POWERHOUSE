import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import os

original_dir = os.getcwd()
ARCHIVO = os.path.normpath("shop/src/resources/data/sendemail.txt")
ARCHIVO1 = os.path.normpath("shop/src/resources/data/senduser.txt")
ARCHIVO2 = os.path.normpath("shop/src/resources/data/sendkey.txt")

with open(os.path.join(original_dir, ARCHIVO1), 'r', encoding='utf-8') as f:
    name = f.read().strip()

with open(os.path.join(original_dir, ARCHIVO2), 'r', encoding='utf-8') as f:
    key = f.read().strip()

with open(os.path.join(original_dir, ARCHIVO), 'r', encoding='utf-8') as f:
    to = f.read().strip()

# Información del remitente
from_email = "powerapph1@gmail.com"
to_email = to
app_password = "qexytouwpysfyafd"

html = f"""
<div style="background-color: #FF8C42; padding: 20px; border-radius: 15px; width: 450px; font-family: Arial, sans-serif;">
    <h2 style="color: black; text-align: center;">POWERAPP</h2>
    <div style="background-color: #ddd; padding: 15px; border-radius: 15px; text-align: center;">
        <p>Hola <b>{name}</b>!<br>Parece que olvidaste tu contraseña. Puedes usar esta para iniciar sesión.</p>
    </div>
    <div style="margin-top: 20px; text-align: center;">
        <a href="#" style="background-color: white; color: black; padding: 10px 20px; border-radius: 15px; text-decoration: none; font-weight: bold;">{key}</a>
    </div>
</div>
"""

# Crear el mensaje
msg = MIMEMultipart("alternative")
msg["Subject"] = "Recuperación de contraseña"
msg["From"] = from_email
msg["To"] = to_email
msg.attach(MIMEText(html, "html"))

# Enviar el mensaje
try:
    with smtplib.SMTP_SSL("smtp.gmail.com", 465) as server:
        server.login(from_email, app_password)
        server.sendmail(from_email, to_email, msg.as_string())
    print("true")
except Exception as e:
    print("Error al enviar el correo:", e)

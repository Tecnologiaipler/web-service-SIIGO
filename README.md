# Servicio Web para Integración SIIP - SIIGO

## Descripción General

Este proyecto implementa un servicio web para la integración entre el sistema SIIP y la plataforma contable SIIGO. Permite recibir la información de facturas desde SIIP en formato JSON, procesarla y enviarla a SIIGO, además de gestionar el envío de notificaciones por correo electrónico.

El servicio expone un endpoint REST que acepta un payload JSON con los detalles de la factura y realiza la creación de la factura en SIIGO mediante su API oficial.

---

## Documentación del Payload y API SIIGO

Toda la información referente al formato del payload, campos, y operaciones disponibles en la API de SIIGO puede consultarse en su documentación oficial:

- Documentación SIIGO Facturas:  
  https://siigoapi.docs.apiary.io/#reference/facturas-de-venta/crear-factura

- Colección Postman con todos los endpoints para pruebas:  
  https://siigonube.portaldeclientes.siigo.com/instalar-postman-api/

---

## Despliegue

Para desplegar el servicio web, siga los siguientes pasos:

1. Generar el archivo `WAR` con el build del proyecto.

2. Copiar el archivo `.war` generado al servidor de despliegue en la siguiente ruta:  /opt/bitnami/tomcat/data

3. El servidor donde se realiza el despliegue es: ç
    IP: 34.148.228.174
    Hostname: respaldo-siip
   
4. El despliegue se realiza usando Tomcat. Simplemente colocando el archivo `.war` en la ruta indicada, Tomcat detecta y despliega automáticamente la aplicación.

5. Para gestionar permisos de copia, puede usar el usuario superusuario (`sudo su`).

---

## Logs y Auditoría

Los logs generados por el webservice se encuentran en la siguiente ruta del servidor:
/home/rsa-key-20240627/auditoria_alegra


Se recomienda revisar estos logs para diagnóstico y verificación del correcto funcionamiento.

---

## Flujo y Componentes Relacionados en SIIP

Es importante tener conocimiento sobre los servicios y componentes que conectan SIIP con este webservice para entender el flujo completo:

- Componentes clave:

  - **AlegraConector**  
  - **InvoiceEndpoint**  
  - **FacturasV2CrearFactura**

Estos componentes son parte del sistema SIIP y manejan la orquestación y lógica antes de enviar datos a este webservice.

---

## Trazabilidad en Base de Datos

Para garantizar la trazabilidad de las facturas enviadas desde SIIP, la información queda registrada en la base de datos:

- Tabla: `Facturas_envio_Alegra`  
  Guarda un registro de las facturas enviadas al sistema Alegra/SIIGO.

- Tabla: `factura`  
  Contiene columnas al final para registrar el ID de factura, prefijo, y booleanos para:  
  - Factura Creada  
  - Factura Electrónica  
  - Factura Alegra  

Se recomienda verificar estas tablas para asegurar el correcto funcionamiento y registro de las facturas.

---

## Autor

Brayan Bernal  
Fecha: 09 Julio 2025  
Contacto: tecnología@ipler.edu.co

---

## Notas

- El servicio fue desarrollado para integración específica entre SIIP y SIIGO.  
- Se basa en la API oficial de SIIGO para la creación y envío de facturas electrónicas.  
- El manejo de errores y auditoría se realiza a través de registros en base de datos y logs.

---






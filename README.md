# interoperabilidad
Proyecto de análisis para aprender a usar el patrón Adapter

# Paso 1- Implementación ingenua (acopada)

## Descripción del sistema

Sistema básico de procesamiento de pagos en Java, donde existe un sistema principal (sistema de la empresa) y un sistema proveedor. El sistema principal se encarga de crear ordenes y procesar pagos mediante el proveedor, el sistema actual en el PASO 1 permite:
- Registrar usuario
- Ejecutar transacciones de pago
- Simular la comunicación con un proveedor externo
- Guardar registros de las transacciones en un archivo JSON

## Arquitectura actual

El sistema está organizado en los siguientes paquetes:

![alt text](imagenes/image.png)

- **empresa**  
  Contiene las clases principales del sistema, como la gestión de clientes y órdenes.

- **proveedor**  
  Simula el proveedor externo de pagos que procesa las transacciones.

- **data**  
  Archivos JSON utilizados para almacenar clientes y registros de transacciones.
  - clientes: Guarda el id y el nombre de los clientes
  - pago: se encarga de guardar la información que llega al proveedor, almacenando el authId, estado y la fecha con hora en la que se realizó el pagó
  - registros se encarga de guardar el monto del pago realizado, junto con la divisa en la que se realizó el pago, asociando el id del usuario y el id automatico del proveedor, con la finalidad de tener información completa de quien realizó el pago, fecha y hora, monto, divisas y el proveedor que Id le asignó al pagó

- **Main**  
  Punto de entrada de la aplicación.

- **Diagrama simple**
![alt text](imagenes/image-1.png)

- **Main**  
  Punto de entrada de la aplicación. Donde se reciben los datos para relizar los pagos

- **SistemaOrdenes**
  En esta clase el sistema principal de la empresa se comunica con el sistema del proveedor para procesar el pagó, ademas de eso se realiza el respectivo registro del cliente en el archivo Json (clientes.json)


- **PaymentProvider** (Proveedor externo)
    En esta clase se ejecutan las transacciones y se realiza el respectivo registro de la información en los archivos Json (pagos.json y registros.json)

-**registros.json**
    Aqui se guarda el registro de los pagos realizados, asociando el id del cliente, el id automático que genera el proveedor y guardando información relevante como el monto y la divisa en la que se realizó el pago

-**Problema de acoplamiento**
  Actualmente hay un alto acoplamiento, ya que la el sistema principal depende directamente de la clase "PaymentProvider" del sistema del proveedor y de la estructura de datos que utiliza el mismo para procesar transacciones, de como este maneja los datos y los registra. Por ende el sistema principal debe acoplarse a la interfaz del proveedor, la cual usa el metodo executeTransaction(user, amount, currency) y retorna una estructura específica con los campos "authId" y "timestamp". 

  Debido a esta dependencia directa, cualquier cambio de la interfaz del proveedor obligaría a modificar el sistema principal. 


-**Evidencia de ejecución**

Ejecución del programa
![alt text](imagenes/image-2.png)

Información guardada en clientes.json
![alt text](imagenes/image-3.png)

Información guardada en pagos.json
![alt text](imagenes/image-4.png)

Información guardada en registros.json
![alt text](imagenes/image-5.png)

-------------------------------------------------------------------------------------------------------------
# Paso 2 – Análisis de impacto del cambio
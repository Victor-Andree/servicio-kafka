# servicio-kafka
Kafka Microservices Integration - Spring Boot & Docker
Este proyecto demuestra la implementación de una arquitectura de mensajería asíncrona utilizando Apache Kafka como broker, con dos microservicios desarrollados en Spring Boot 3.5.10-SNAPSHOT.

Requisitos Previos
Antes de ejecutar los microservicios, es necesario contar con las siguientes herramientas:

Docker Desktop: Para gestionar el contenedor de Kafka.

Java 22: Versión de JDK utilizada para el desarrollo.

Maven: Para la gestión de dependencias y construcción del proyecto.

Configuración de Infraestructura
1. Instalación de Kafka con Docker
Para instalar y correr la última versión de Kafka, ejecuta el siguiente comando en tu terminal (PowerShell o CMD):

Bash
docker run -d --name kafka -p 9092:9092 apache/kafka:latest
Nota: Asegúrate de que el contenedor esté en estado "Running" antes de proceder.

2. Liberar Puertos
El microservicio Provider utiliza el puerto 8080. Asegúrate de que no esté ocupado por otro proceso (como bases de datos u otros servidores web) para evitar errores al levantar la aplicación.

Ejecución del Proyecto
1. Levantar los Microservicios
Desde tu IDE (IntelliJ IDEA recomendado), ejecuta las aplicaciones en este orden:

SpringBootConsumerApplication: Se levantará en el puerto 8081.

SpringBootProviderApplication: Se levantará en el puerto 8080.

2. Escuchar Mensajes desde la Terminal (Modo Escucha)
Para verificar que los mensajes están llegando correctamente al broker de Kafka, abre una terminal y ejecuta el comando de consumo:

Bash
docker exec -it kafka /opt/kafka/bin/kafka-console-consumer.sh --topic kafkaprueba.Topic --from-beginning --bootstrap-server localhost:9092

Detalles Técnicos
Configuración del Tópico
El tópico se crea automáticamente al levantar el Provider con las siguientes especificaciones:

Nombre: kafkaprueba.Topic

Particiones: 2

Réplicas: 1 (Configurado para entorno local de un solo nodo)

Flujo de Datos
Producer: Envía un mensaje de prueba al arrancar mediante un CommandLineRunner.

Consumer: Utiliza la anotación @KafkaListener para procesar y mostrar los mensajes en la consola de IntelliJ.

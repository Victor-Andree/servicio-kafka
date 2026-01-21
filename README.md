# servicio-kafka-springboot

## Kafka Microservices Integration – Spring Boot & Docker

Este proyecto demuestra la implementación de una **arquitectura de mensajería asíncrona** utilizando **Apache Kafka** como broker, con **dos microservicios desarrollados en Spring Boot**. El objetivo principal es mostrar un flujo básico *producer–consumer* en un entorno local, orientado a fines educativos y de aprendizaje de microservicios.

---

## Descripción General

La solución está compuesta por:

* **Provider (Producer)**: Microservicio encargado de publicar mensajes en un tópico de Kafka.
* **Consumer**: Microservicio que consume y procesa los mensajes publicados en el broker.
* **Apache Kafka**: Broker de mensajería levantado mediante Docker.

El flujo de datos es completamente asíncrono y desacoplado, permitiendo una comunicación eficiente entre microservicios.

---

## Tecnologías Utilizadas

* **Java 22**
* **Spring Boot 3.5.10-SNAPSHOT**
* **Apache Kafka**
* **Docker & Docker Desktop**
* **Maven**

---

## Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de contar con lo siguiente:

* Docker Desktop instalado y en ejecución.
* JDK 22 configurado correctamente.
* Maven instalado y accesible desde la terminal.
* Un IDE compatible con Spring Boot (se recomienda IntelliJ IDEA).

---

## Configuración de Infraestructura

### 1. Instalación y Ejecución de Kafka con Docker

Para levantar un broker de Kafka en tu entorno local, ejecuta el siguiente comando en tu terminal (PowerShell o CMD):

```bash
docker run -d --name kafka -p 9092:9092 apache/kafka:latest
```

Verifica que el contenedor se encuentre en estado **Running** antes de continuar.

---

### 2. Configuración de Puertos

* **Provider**: Puerto `8080`
* **Consumer**: Puerto `8081`
* **Kafka Broker**: Puerto `9092`

Asegúrate de que estos puertos no estén siendo utilizados por otros procesos para evitar conflictos al iniciar las aplicaciones.

---

## Ejecución del Proyecto

### 1. Levantar los Microservicios

Desde tu IDE, ejecuta los microservicios en el siguiente orden:

1. **SpringBootConsumerApplication**
   Se levantará en el puerto `8081` y quedará a la espera de mensajes.

2. **SpringBootProviderApplication**
   Se levantará en el puerto `8080` y enviará un mensaje de prueba al iniciar la aplicación.

---

### 2. Verificación de Mensajes desde la Terminal

Para confirmar que los mensajes están llegando correctamente al broker de Kafka, abre una terminal y ejecuta el siguiente comando:

```bash
docker exec -it kafka /opt/kafka/bin/kafka-console-consumer.sh \
--topic kafkaprueba.Topic \
--from-beginning \
--bootstrap-server localhost:9092
```

Este comando permite escuchar los mensajes publicados en el tópico desde el inicio.

---

## Detalles Técnicos

### Configuración del Tópico

El tópico se crea automáticamente al iniciar el microservicio Provider con la siguiente configuración:

* **Nombre**: `kafkaprueba.Topic`
* **Particiones**: 2
* **Factor de Réplica**: 1 (configurado para un entorno local de un solo nodo)

---

### Flujo de Datos

* **Producer**: Publica un mensaje de prueba al arrancar la aplicación utilizando un `CommandLineRunner`.
* **Consumer**: Escucha el tópico mediante la anotación `@KafkaListener` y muestra los mensajes recibidos en la consola del IDE.

---

## Objetivo del Proyecto

Este proyecto sirve como base para:

* Comprender la integración de Kafka con Spring Boot.
* Introducir conceptos de mensajería asíncrona en arquitecturas de microservicios.
* Practicar el uso de Docker para levantar infraestructura local.

Puede extenderse fácilmente para incluir serialización avanzada, manejo de errores, múltiples consumidores o integración con bases de datos.

---

## Notas Finales

Este entorno está pensado exclusivamente para desarrollo y pruebas locales. Para un entorno productivo se deben considerar configuraciones adicionales como seguridad, replicación avanzada, monitoreo y gestión de offsets.

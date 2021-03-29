# TW4J Twitter's Custom API

Este proyecto esta basado en las librerias de Twitter4j para el consumo la API publica de Twitter y de esta manera crear una API REST personalizada.

## Prerequisitos
Es necesario tener instaladas las siguientes herramientas:

* [Java 11](https://www.oracle.com/co/java/technologies/javase-jdk11-downloads.html)
* [Apache Maven 3.6.3](https://maven.apache.org/download.cgi)

## Configuración
Para poder usar las librerias de Twitter4j se deben obtener los tokens de autorización. Estos son otorgados en la plataforma para desarrolladores de Twitter [developer.twitter.com](https://developer.twitter.com/en)

Una vez se disponga de dichos tokens vamos al archivo de configuración que se encuentra en la siguiente ruta:

```shell script
tw4j\src\main\resources\application.properties
```

Una vez en el archivo vamos a colocar los token en cada una de las siguientes propiedades: 

```shell script
twitter.config.oauth.consumer.key=***********************
twitter.config.oauth.consumer.secret=***********************
twitter.config.oauth.access.token=***********************
twitter.config.oauth.access.token.secret=***********************
```

y con esto finaliza la configuración.

## Ejecutando la aplicación en modo desarrollo

Para ejecutar la aplicación usamos el siguiente comando:
```shell script
mvn compile quarkus:dev
```
## Usando la aplicación

La aplicación se ejecuta en la siguiente URL: [http://localhost:8080](http://localhost:8080)
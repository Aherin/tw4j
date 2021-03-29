# TW4J Twitter's Custom API

Este proyecto esta basado en las librerias de Twitter4j para el consumo la API publica de Twitter y de esta manera crear una API REST personalizada.

## Prerequisitos
Es necesario tener instalados las siguientes herramientas:

* [Java 11](https://www.oracle.com/co/java/technologies/javase-jdk11-downloads.html)
* [Apache Maven 3.6.3](https://maven.apache.org/download.cgi)

## Configuración
Para poder usar las librerias de Twitter4j se debn obtener los tokens de autorización. Estos son otorgados en la plataforma para desarrolladores de Twitter [developer.twitter.com](https://developer.twitter.com/en)

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

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/tw4j-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Related guides

- RESTEasy JAX-RS ([guide](https://quarkus.io/guides/rest-json)): REST endpoint framework implementing JAX-RS and more

## Provided examples

### RESTEasy JAX-RS example

REST is easy peasy with this Hello World RESTEasy resource.

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

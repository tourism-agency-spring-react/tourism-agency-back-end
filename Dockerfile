#Definir la imagen base que se usara para la construccion del proyecto
FROM maven:3.6-openjdk-17 AS builder

#Definir el directorio raiz de nuestro contenedor
WORKDIR /tourism-app

#se copian los archivos en el directorio del contenedor
COPY pom.xml /tourism-app

#se descargan las dependencias
RUN mvn dependency:go-offline -B

#se copia el codigo fuente dentro el contenedor
COPY ./src /tourism-app/src

#se construye el ejecutable .jar
RUN mvn clean install -DskipTests

#definimos la imagen base para nuestra aplicacion
FROM openjdk:17-alpine

#definimos el directorio raiz
WORKDIR /tourism-app

#exponemos el puerto que usara el contenedor docker (solo es informativo)
EXPOSE 8080

#copiamos nuestro ejecutable
COPY --from=builder /tourism-app/target/*.jar /tourism-app/app.jar

#definimos comandos que se ejecutaran cuando el contenedor se levante
ENTRYPOINT ["java", "-jar", "/tourism-app/app.jar"]




#Definir la imagen base que se usara para la construccion del proyecto
FROM openjdk:17-alpine AS builder

#Definir el directorio raiz de nuestro contenedor
WORKDIR /tourism-app

#se copian los archivos en el directorio del contenedor
COPY ./.mvn /tourism-app/.mvn
COPY ./pom.xml /tourism-app
COPY ./mvnw /tourism-app

#se descargan las dependencias
RUN dos2unix mvnw
RUN ./mvnw dependency:go-offline

#se copia el codigo fuente dentro el contenedor
COPY ./src /tourism-app/src

#se construye el ejecutable .jar
RUN ./mvnw clean install

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



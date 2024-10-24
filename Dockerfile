FROM eclipse-temurin:17-jdk-focal

ARG JAR_FILE=target/tourism-agency-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]


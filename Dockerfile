FROM eclipse-temurin:17.0.4_8-jdk-focal
ARG JAR_FILE=build/libs/customerservice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
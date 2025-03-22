FROM openjdk:21-jdk
LABEL authors="martimsantos"

WORKDIR /app
COPY target/lab3_3-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
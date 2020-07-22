FROM openjdk:14-alpine
COPY target/spring-boot-data-jpa-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java" , "-jar", "/app.jar"]
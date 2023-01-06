FROM openjdk:18-ea-11-jdk-alpine3.15
COPY target/todo-api-1.0-SNAPSHOT.jar todo-api-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/todo-api-1.0-SNAPSHOT.jar"]
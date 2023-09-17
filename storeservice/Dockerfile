
FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/storeservice-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} grossstore.jar

ENTRYPOINT ["java","-jar","grossstore.jar"]
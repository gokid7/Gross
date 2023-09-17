
FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/courierservice-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} grosscourier.jar

ENTRYPOINT ["java","-jar","grosscourier.jar"]
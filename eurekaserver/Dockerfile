
FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/eurekaserver-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} grosseureka.jar

ENTRYPOINT ["java","-jar","grosseureka.jar"]
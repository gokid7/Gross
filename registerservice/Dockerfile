
FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/registerservice-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} grossregister.jar

ENTRYPOINT ["java","-jar","grossregister.jar"]
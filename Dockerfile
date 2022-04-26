FROM openjdk:14-jdk-alpine

ARG JAR_FILE=build/libs/message-ms.jar

COPY ${JAR_FILE} message-ms.jar

ENTRYPOINT ["java","-jar","/message-ms.jar"]

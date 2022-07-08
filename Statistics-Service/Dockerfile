FROM openjdk:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Statistics-Service.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/Statistics-Service.jar"]
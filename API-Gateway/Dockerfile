FROM openjdk:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} API-Gateway.jar
ENTRYPOINT ["java","-jar","/API-Gateway.jar"]
EXPOSE 9191
FROM openjdk:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Investment-Service.jar
ENTRYPOINT ["java","-jar","/Investment-Service.jar"]
EXPOSE 8080
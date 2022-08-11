FROM openjdk:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Calculator-Service.jar
ENTRYPOINT ["java","-jar","/Calculator-Service.jar"]
EXPOSE 80
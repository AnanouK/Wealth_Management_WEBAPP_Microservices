FROM openjdk:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Investment-Service.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/Investment-Service.jar"]

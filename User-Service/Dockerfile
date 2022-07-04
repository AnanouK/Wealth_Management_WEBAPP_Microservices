FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} User-Service.jar
ENTRYPOINT ["java","-jar","/User-Service.jar"]
EXPOSE 9001
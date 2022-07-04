FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Registry-Service.jar
ENTRYPOINT ["java","-jar","/Registry-Service.jar"]
EXPOSE 8761
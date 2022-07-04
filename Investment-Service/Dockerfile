FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Investment-Service.jar
ENTRYPOINT ["java","-jar","/Investment-Service.jar"]
EXPOSE 9002
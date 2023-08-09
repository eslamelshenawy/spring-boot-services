FROM  openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} superhero-service-1.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/superhero-service-1.1.jar"]

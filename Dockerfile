FROM  openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY target/superhero-service-1.1.jar superhero-service-1.1.jar
EXPOSE 9443
ENTRYPOINT ["java","-jar","/superhero-service-1.1.jar"]

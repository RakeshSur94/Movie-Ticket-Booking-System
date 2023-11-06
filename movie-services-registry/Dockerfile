FROM openjdk:17-alpine
ARG JAR_FILE=target/movie-services-registry-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} movieregestry.jar
ENTRYPOINT ["java","-jar","/movieregestry.jar"]
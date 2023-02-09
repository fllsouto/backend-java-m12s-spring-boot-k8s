FROM openjdk:20-ea-17-jdk
VOLUME /tmp
ARG JAR_FILE=target/user-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]

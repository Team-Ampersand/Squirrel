FROM openjdk:17
ARG JAR_FILE=build/libs/squirrel-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","app.jar"]
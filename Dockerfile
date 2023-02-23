FROM openjdk:11

WORKDIR /

ARG JAR_FILE_PATH=./build/libs/*SNAPSHOT.jar

COPY ${JAR_FILE_PATH} /app.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app.jar", "2>&1"]

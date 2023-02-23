FROM openjdk:11

WORKDIR /

RUN touch /var/log/nohup.out

ARG JAR_FILE_PATH=./build/libs/*SNAPSHOT.jar

COPY ${JAR_FILE_PATH} /app.jar

ENTRYPOINT ["nohup", "java", "-jar",\ 
"-Dspring.profiles.active=prod", "/app.jar", ">>", "/var/log/nohup.out", "2>&1", "&"]

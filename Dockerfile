FROM maven:3.6.0-jdk-11-slim AS mysqldoc

ENV HOME=/app
WORKDIR $HOME
VOLUME /tmp

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

COPY ./src ./src
COPY ./pom.xml ./pom.xml


RUN chmod 755 /app/mvnw

RUN mvn package -DskipTests

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","target/tlib-0.0.1-SNAPSHOT.jar"]

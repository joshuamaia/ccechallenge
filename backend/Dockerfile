FROM openjdk:17-alpine

RUN apk add --no-cache bash
RUN apk update && apk add maven

WORKDIR /app

COPY . /app

RUN mvn clean install

ENTRYPOINT ["java", "-jar", "/app/target/cce-challenge-0.0.1-SNAPSHOT.jar"]

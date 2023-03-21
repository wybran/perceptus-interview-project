FROM openjdk:17-jdk-slim-buster AS builder

RUN apt-get update -y
RUN apt-get install -y binutils

WORKDIR /app

COPY . .

RUN ./gradlew build -x test

FROM openjdk:17-jdk-slim-buster

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar .

EXPOSE 8080

CMD ["java", "-jar", "perceptus-interview-project-0.0.1-SNAPSHOT.jar"]



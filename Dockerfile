FROM maven:3.8.4-openjdk-17-slim AS builder

WORKDIR /build
COPY . /build

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine

RUN mkdir /app
WORKDIR /app

COPY --from=builder /build/target/*.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]

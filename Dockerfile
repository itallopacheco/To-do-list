FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./target/demo.jar ./rinha.jar

EXPOSE 8000

ENTRYPOINT [ "java", "-jar", "./rinha.jar" ]
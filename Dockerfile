FROM openjdk:17-jdk-alpine
RUN mkdir /app
WORKDIR /app
RUN ./mvnw clean package -DskipTests
COPY target/*.jar /app/app.jar
CMD ["java","-jar","/app/app.jar"]

# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY ./ExcelApplication /app
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
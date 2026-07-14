## Stage 1: Build the application
#FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
#WORKDIR /app
## Copy the pom.xml and source code
#COPY pom.xml .
#COPY src ./src
## Build the package and skip tests to speed up the deploy
#RUN mvn clean package -DskipTests
#
## Stage 2: Run the application
#FROM eclipse-temurin:17-jre-alpine
#WORKDIR /app
## Copy the built jar from the build stage
#COPY --from=build /app/target/*.jar app.jar
## Render dynamically assigns a port, so we read the PORT env variable
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]

# Stage 1: Build the application using JDK 25
FROM maven:3.9-eclipse-temurin-25-alpine AS build
WORKDIR /app
# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src
# Build the package and skip tests to speed up the deploy
RUN mvn clean package -DskipTests

# Stage 2: Run the application using JRE 25
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar
# Render dynamically assigns a port, so we read the PORT env variable
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
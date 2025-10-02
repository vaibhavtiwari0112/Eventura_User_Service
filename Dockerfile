# ---- Build Stage ----
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ---- Runtime Stage ----
FROM openjdk:17-jdk-slim
WORKDIR /app

# Build arguments (from GitHub Actions)
ARG JDBC_DATABASE_URL
ARG JDBC_DATABASE_USERNAME
ARG JDBC_DATABASE_PASSWORD
ARG JWT_SECRET
ARG JWT_ISSUER
ARG JWT_EXPIRATION

# Set as environment variables (Spring Boot will resolve them)
ENV JDBC_DATABASE_URL=${JDBC_DATABASE_URL}
ENV JDBC_DATABASE_USERNAME=${JDBC_DATABASE_USERNAME}
ENV JDBC_DATABASE_PASSWORD=${JDBC_DATABASE_PASSWORD}
ENV JWT_SECRET=${JWT_SECRET}
ENV JWT_ISSUER=${JWT_ISSUER}
ENV JWT_EXPIRATION=${JWT_EXPIRATION}

# Copy the JAR from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]

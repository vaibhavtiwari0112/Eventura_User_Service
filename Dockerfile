# ---- Build Stage ----
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ---- Runtime Stage ----
FROM openjdk:17-jdk-slim
WORKDIR /app

# Build arguments from GitHub Actions
ARG JDBC_DATABASE_URL
ARG JDBC_DATABASE_USERNAME
ARG JDBC_DATABASE_PASSWORD
ARG EVENTURA_SECURITY_JWT_SECRET
ARG EVENTURA_SECURITY_JWT_ISSUER
ARG EVENTURA_SECURITY_JWT_EXPIRATION

# Set as environment variables with nested property names
ENV spring.datasource.url=${JDBC_DATABASE_URL}
ENV spring.datasource.username=${JDBC_DATABASE_USERNAME}
ENV spring.datasource.password=${JDBC_DATABASE_PASSWORD}

ENV eventura.security.jwt.secret=${EVENTURA_SECURITY_JWT_SECRET}
ENV eventura.security.jwt.issuer=${EVENTURA_SECURITY_JWT_ISSUER}
ENV eventura.security.jwt.expiration=${EVENTURA_SECURITY_JWT_EXPIRATION}

# Copy the JAR from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]

# Stage 1: Build the application using Maven Wrapper
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Copy maven wrapper configurations
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Give execution permission to Maven wrapper script
RUN chmod +x mvnw

# Resolve dependencies to build a cache layer
RUN ./mvnw dependency:go-offline -B

# Copy source code and build package without tests
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Small JRE runtime stage
FROM eclipse-temurin:21-jre-jammy AS runtime
WORKDIR /app

# Dynamically copy the built jar to a standard location without hardcoding its name
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 for Render Web Service compatibility
EXPOSE 8080

# Define PORT env var (Render default binding)
ENV PORT=8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

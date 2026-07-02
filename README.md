# BMS (Booking Management System)

This is a production-ready Spring Boot application configured for deployment on Render.

## Features & Tech Stack
- **Backend Framework**: Spring Boot
- **Database**: MySQL (configurable via environment variables)
- **Build Tool**: Maven with Maven Wrapper
- **Deployment Platform**: Render (via Docker multi-stage builds)

## Prerequisites
- Java 21 or higher (if running locally without Docker)
- Docker (optional, for containerized run)
- MySQL Database (local or managed)

## Local Development

### 1. Configure the Database
By default, the application connects to a MySQL database at `jdbc:mysql://localhost:3306/BMS` with credentials `root` / `Subodh2004@`. You can override these by setting environment variables:
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

### 2. Build the Application
To compile and package the project into a runnable JAR, run:
```bash
./mvnw clean package -DskipTests
```
For Windows CMD:
```cmd
mvnw.cmd clean package -DskipTests
```

### 3. Run the JAR
Once packaged, you can run the JAR file:
```bash
java -jar target/BMS-0.0.1-SNAPSHOT.jar
```
*(Or use `java -jar target/*.jar` to automatically run the generated JAR).*

## Containerized Local Run

### 1. Build the Docker Image
```bash
docker build -t bms-app .
```

### 2. Run the Container
```bash
docker run -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mysql://your-db-host:3306/BMS -e SPRING_DATASOURCE_USERNAME=user -e SPRING_DATASOURCE_PASSWORD=pass bms-app
```

## Render Deployment

This project contains a `render.yaml` blueprint file for easy deployment on Render as a **Docker Web Service**.

### Deployment Steps:
1. Push this repository to GitHub or GitLab.
2. Log into your **Render Dashboard** (https://dashboard.render.com).
3. Click **New** -> **Blueprint**.
4. Connect your GitHub/GitLab repository.
5. Render will detect the `render.yaml` file. Configure your service environment variables in the dashboard:
   - `SPRING_DATASOURCE_URL`: Connection string to your production MySQL database (e.g., Aiven or Clever Cloud MySQL).
   - `SPRING_DATASOURCE_USERNAME`: Database user name.
   - `SPRING_DATASOURCE_PASSWORD`: Database password.
6. Click **Approve** to build and deploy.

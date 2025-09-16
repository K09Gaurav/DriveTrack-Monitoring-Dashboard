# Base image: lightweight JDK 17 runtime
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the built jar into the container
COPY target/DriveTrack-0.0.1-SNAPSHOT.jar /app/DriveTrack-0.0.1-SNAPSHOT.jar

# Expose port 8081 for the app
EXPOSE 8081

# Run the Spring Boot jar
ENTRYPOINT [ "java", "-jar", "DriveTrack-0.0.1-SNAPSHOT.jar" ]


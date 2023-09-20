# Use an official OpenJDK 8 runtime as a parent image
FROM openjdk:8-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/Simple-Maven-Test-0.0.1-SNAPSHOT.jar Simple-Maven-Test-0.0.1-SNAPSHOT.jar

# Copy your application.properties into the container at /app
COPY src/main/resources/application.properties application.properties

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Define environment variable
ENV JAVA_OPTS=""

# Run the application when the container launches
CMD ["java", "-jar", "simple-maven-test.jar"]



# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory inside the image
WORKDIR /app

# Expose port 8080 to the outside world
EXPOSE 8080

# Copy the executable jar file into the Docker image
COPY build/libs/transformer-0.0.1.jar app.jar

# Define the command to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]
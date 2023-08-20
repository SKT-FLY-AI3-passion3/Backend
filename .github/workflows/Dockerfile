# Use the official Amazon Corretto JDK 17 image as the base image
FROM amazoncorretto:17-alpine-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file from the build/libs directory
COPY build/libs/passion3.jar app.jar

# Expose the port that your Spring Boot app runs on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]

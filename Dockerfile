# Use the official Amazon Corretto JDK 17 image as the base image
FROM amazoncorretto:17-alpine-jdk

# Install Nginx
RUN apk --no-cache add nginx

# Set working directory
WORKDIR /app

# Copy the JAR file
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} passion3.jar

# Copy the keystore file
COPY .keystore .keystore

# Copy the JSON credentials file
COPY vaulted-fort-358506-3f5080aabb57.json vaulted-fort-358506-3f5080aabb57.json

# Copy the Nginx config file
COPY nginx.conf /etc/nginx/nginx.conf

# Start Nginx and the Spring Boot application
CMD ["sh", "-c", "nginx && java -jar /app/passion3.jar"]
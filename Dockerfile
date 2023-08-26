# Use the official Amazon Corretto JDK 17 image as the base image
FROM amazoncorretto:17-alpine-jdk

# Set working directory
WORKDIR /app

# Copy the JAR file
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} passion3.jar

# Copy the keystore file
COPY keystore.p12 keystore.p12

# Copy the JSON credentials file
COPY key.json key.json
COPY vaulted-fort-358506-3f5080aabb57.json vaulted-fort-358506-3f5080aabb57.json
# Set the entry point
ENTRYPOINT ["java","-jar","/app/passion3.jar"]

# Use the official Amazon Corretto JDK 17 image as the base image
FROM amazoncorretto:17-alpine-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} passion3.jar
ENTRYPOINT ["java","-jar","/passion3.jar"]

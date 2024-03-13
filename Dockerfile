FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

ARG JAR_FILE=*.jar
# Copy the packaged JAR file into the container
COPY target/${JAR_FILE} /app/chat-app.jar


ENTRYPOINT ["java", "-jar", "chat-app.jar"]
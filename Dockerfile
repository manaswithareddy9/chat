FROM techiescamp/jdk-17:1.0.0 AS build

# Copy the Java Application source code
COPY . /usr/chat/

# Build Java Application
RUN mvn -f /usr/chat/pom.xml package -DskipTests

FROM techiescamp/jdk-17:1.0.0 

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage (/app)
COPY --from=build /usr/chat/target/*.jar ./chat.jar

CMD ["java", "-jar", "chat.jar"]
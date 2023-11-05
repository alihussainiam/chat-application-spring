# Use a base image with the desired JDK
FROM eclipse-temurin:17-jdk-focal

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper files to the container
COPY server/gradle/ gradle/
COPY server/gradlew server/build.gradle server/settings.gradle ./

# Copy the Gradle wrapper script and make it executable
COPY server/gradlew ./
RUN chmod +x ./gradlew

# Copy the source code to the container
COPY server/src/ src/

# Build the project using the Gradle wrapper
RUN ./gradlew build

# Define the command to run the Spring Boot application
CMD ["java", "-jar", "build/libs/server-0.0.1-SNAPSHOT.jar"]
# Use an official JDK base image
FROM amazoncorretto:17-alpine

# Create app directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first for caching
COPY ./mvnw /app/
COPY ./pom.xml /app/
COPY ./.mvn /app/.mvn

# Give execute permission to the Maven wrapper
RUN chmod +x /app/mvnw

# Download dependencies (cached if no changes in pom.xml)
RUN ./mvnw dependency:go-offline -B

# Now copy the rest of your source code
COPY . /app

# Build the JAR, skipping tests
RUN ./mvnw clean package -DskipTests

# Expose port 8080 (or whichever port your Spring Boot app uses)
EXPOSE 8080

# Set the entrypoint to run the jar
CMD ["java", "-jar", "target/Salesforelikeapp-0.0.1-SNAPSHOT.jar"]

# Use an official JDK base image
FROM amazoncorretto:17-alpine

# Create app directory
WORKDIR /app

# Copy Maven wrapper & pom first for caching
COPY ./mvnw /app/
COPY ./pom.xml /app/
COPY ./.mvn /app/.mvn

# Give execute permission to mvnw (if needed)
RUN chmod +x mvnw

# Download dependencies (cached if no changes in pom.xml)
RUN ./mvnw dependency:go-offline -B

# Now copy the rest of your source code
COPY . /app

# Build the JAR
RUN ./mvnw clean package -DskipTests

# Expose port 8080 (or whichever port your Spring Boot app uses)
EXPOSE 8080

# Set the entrypoint to run the jar
CMD ["java", "-jar", "target/Salesforelikeapp-0.0.1-SNAPSHOT.jar"]

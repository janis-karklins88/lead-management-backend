FROM amazoncorretto:17-alpine

WORKDIR /app

# 1. Copy only pom.xml, mvnw, and .mvn folder first (for caching)
COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .

# 2. Make the mvnw script executable
RUN chmod +x mvnw

# 3. Pre-download dependencies (takes advantage of above copy for caching)
RUN ./mvnw dependency:go-offline -B

# 4. Copy the rest of your source code (src, etc.)
COPY src/ src/

# 5. Build
RUN ./mvnw clean package -DskipTests

# 6. Expose port and run
EXPOSE 8080
CMD ["java", "-jar", "target/Salesforelikeapp-0.0.1-SNAPSHOT.jar"]

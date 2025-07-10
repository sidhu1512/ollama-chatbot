# Use a Java 17 environment
FROM eclipse-temurin:17-jdk

# Set working directory inside container
WORKDIR /app

# Copy everything to container
COPY . .

# Build your app
RUN ./mvnw clean package -DskipTests

# Run the built JAR file (change this if needed)
CMD ["java", "-jar", "ollama-chatbot-0.0.1-SNAPSHOT.jar"]

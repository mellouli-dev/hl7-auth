# Use the Java 8 base image
FROM eclipse-temurin:8-jdk

# Accept build-time arguments
ARG MONGO_INITDB_DATABASE


# Set environment variables using build-time arguments
ENV MONGO_INITDB_DATABASE=$MONGO_INITDB_DATABASE

# Create application directory
RUN mkdir -p /usr/local/hl7-auth

# Copy the generated JAR file into the container
COPY hl7-auth.jar /usr/local/hl7-igamt/hl7-auth.jar


# Define the default command to run the application
CMD ["java", "-Xmx900m", "-Xss256k", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=${PROFILE}", "-Ddb.name=${MONGO_INITDB_DATABASE}", "-Ddb.host=hl7-auth-db", "-Ddb.port=27017",  "-jar", "/usr/local/hl7-auth/hl7-auth.jar"]

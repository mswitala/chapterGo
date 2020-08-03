FROM openjdk:8-jdk-alpine

# We create an /app directory within our
# image that will hold our application source files
RUN mkdir /app

COPY target/spring-boot-docker-0.1.0.jar /app/
#COPY target/spring-boot-docker-0.1.0.jar spring-boot-docker-0.1.0.jar

# We specify that we now wish to execute
# any further commands inside our /app
# directory
WORKDIR /app

ENTRYPOINT ["java","-jar","spring-boot-docker-0.1.0.jar"]
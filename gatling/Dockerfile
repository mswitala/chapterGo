FROM openjdk:8-jdk-alpine

# We create an /app directory within our
# image that will hold our application source files
RUN mkdir /app

#RUN apk add --update \
 #   curl \
  #  && rm -rf /var/cache/apk/*

COPY target/gatlingTest.jar /app/gatlingTest.jar
#COPY target/spring-boot-docker-0.1.0.jar spring-boot-docker-0.1.0.jar

# We specify that we now wish to execute
# any further commands inside our /app
# directory
WORKDIR /app

ENTRYPOINT exec java ${JAVA_OPTS} -cp gatlingTest.jar io.gatling.app.Gatling -s gatling.test.simulation.ExampleGetSimulation

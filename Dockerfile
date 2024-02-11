FROM openjdk:latest
WORKDIR /app/zipkin-server
COPY zipkin-server-2.24.3-exec.jar /zipkin-server.jar
CMD [ "java", "-jar", "/zipkin-server.jar"]
FROM gradle:7.4.2-jdk17-alpine AS build
COPY . /build
WORKDIR /build
RUN gradle build --no-daemon

FROM openjdk:17-alpine
COPY --from=build /build/build/libs/*.jar /app/app.jar
WORKDIR /app
CMD ["java",  "-jar", "-Dspring.profiles.active=docker", "app.jar"]

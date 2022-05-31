FROM gradle:7.4.2-jdk17-alpine AS build
COPY . /build
WORKDIR /build
RUN gradle war --no-daemon

FROM tomcat:9.0.63-jre11-openjdk-slim
COPY --from=build /build/aiocars-impl/build/libs/*.war  /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]

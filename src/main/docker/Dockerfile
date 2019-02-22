FROM maven:3.6.0-jdk-11-slim as build

LABEL maintainer "Julius Krah <juliuskrah@gmail.com>"

WORKDIR /tmp
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:11-jre-slim

COPY --from=build /tmp/target/*.jar ./paging.jar
EXPOSE 8080
ENTRYPOINT ["/usr/bin/java", "-jar", "paging.jar"]
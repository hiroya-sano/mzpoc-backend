FROM maven:3.8.5-openjdk-11 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# RUN mkdir -p /usr/local/newrelic
# ADD ./newrelic/newrelic.jar /usr/local/newrelic/newrelic.jar
# ADD ./newrelic/newrelic.yml /usr/local/newrelic/newrelic.yml

EXPOSE 8080
# ENTRYPOINT ["java", "-javaagent:/usr/local/newrelic/newrelic.jar", "-jar", "app.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]

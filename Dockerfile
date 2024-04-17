FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# FROM adoptopenjdk/openjdk11:alpine-jr
FROM openjdk:11-jre-slim
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV MYSQL_HOST pidb
ENV MYSQL_NAME primera_infancia
ENV MYSQL_USER priminf
ENV MYSQL_PASSWORD admin

COPY start.sh start.sh
CMD ["sh", "start.sh"]
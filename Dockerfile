FROM adoptopenjdk/openjdk11:alpine-jre

ENV MYSQL_HOST pidb
ENV MYSQL_NAME primera_infancia
ENV MYSQL_USER priminf
ENV MYSQL_PASSWORD admin

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar
COPY start.sh start.sh

CMD ["sh", "start.sh"]
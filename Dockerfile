FROM maven:latest

WORKDIR /test

COPY ./src ./src
COPY ./pom.xml .

RUN mvn test-compile

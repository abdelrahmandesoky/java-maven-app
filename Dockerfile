FROM openjdk:19-jdk-alpine3.16

EXPOSE 8080

WORKDIR /app

COPY . .

CMD java -jar java-maven-app-*.jar
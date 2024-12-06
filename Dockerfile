FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/register-0.0.1-SNAPSHOT.jar /app/register.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "register.jar"]

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/starships-0.0.1-SNAPSHOT.jar /app/starships.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "starships.jar"]
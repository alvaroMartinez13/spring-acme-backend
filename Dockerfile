FROM openjdk:17-jdk
WORKDIR /app
COPY target/spring-acme-backend-0.0.1-SNAPSHOT.jar app.jar
ENV DB_URL=jdbc:postgresql://dpg-cven9annoe9s73eq4cs0-a.oregon-postgres.render.com:5432/spring_acme
ENV DB_USER=spring_acme_user
ENV DB_PASSWORD=ST7lJ2b6wasY5X2q6ul23LqsvYLHdtnY
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

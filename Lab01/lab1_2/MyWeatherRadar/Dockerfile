FROM openjdk:21-jdk-alpine
COPY target/MyWeatherRadar-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-cp","/app.jar", "pt.ua.deti.ies.weather.WeatherStarter"]
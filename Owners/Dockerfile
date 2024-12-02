FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/Owners-1.0-SNAPSHOT.jar app.jar

CMD ["java","-jar","/app/app.jar"]
#ENTRYPOINT ["java","-jar","/app/app.jar"]
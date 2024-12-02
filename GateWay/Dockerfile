FROM openjdk:17-jdk-slim

WORKDIR /app

#COPY build/libs/GateWay-1.0-SNAPSHOT.jar app.jar
#
#CMD ["java","-jar","/app/app.jar"]

COPY build/libs/GateWay-1.0-SNAPSHOT.jar app.jar
COPY build/libs/lib /app/lib

ENV MAIN_CLASS = "org.example.SpringMainGateWay"

CMD ["java","-jar","/app/app.jar"]

#ENTRYPOINT ["java","-jar","/app/app.jar"]

#FROM openjdk:17-jdk-slim
#
#WORKDIR /app
#
#COPY build/libs/Cats-1.0-SNAPSHOT.jar app.jar
##COPY build/libs/lib /app/lib
#
#ENV MAIN_CLASS = "org.example.SpringMain"
#
#CMD ["java","-jar","/app/app.jar"]

#ENTRYPOINT ["java","-jar","/app/app.jar"]

#C:\Users\sega9\.jdks\corretto-17.0.12

FROM openjdk:17
VOLUME /tmp
ARG JAR_FILE=build/libs/Cats-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
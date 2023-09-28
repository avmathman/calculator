FROM gradle:8.3.0-jdk8 as builder

COPY src /usr/app/src
COPY build.gradle /usr/app

RUN gradle -b /usr/app/build.gradle build

FROM openjdk:8u102-jdk

COPY --from=builder /usr/app/build/libs/*.jar /usr/app/calculator.jar

ENTRYPOINT ["java","-jar","/usr/app/calculator.jar"]
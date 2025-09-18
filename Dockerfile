FROM openjdk:21
COPY ./target/simProj-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "simProj-1.0-SNAPSHOT-jar-with-dependencies.jar"]


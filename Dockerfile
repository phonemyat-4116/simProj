FROM openjdk:21
COPY ./target/simProj-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "simProj-0.1.0.2-jar-with-dependencies.jar"]


FROM openjdk:20
COPY ./target/classes/com /tmp/com
WORKDIR /tmp    doc
ENTRYPOINT ["java", "com.napier.sem.App"]
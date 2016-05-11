FROM java:8
COPY target/kafka-1.0-SNAPSHOT-jar-with-dependencies.jar tmp/

ARG HOST_IP=localhost
ENV HOST_IP ${HOST_IP}

RUN java -jar tmp/kafka-1.0-SNAPSHOT-jar-with-dependencies.jar ${HOST_IP}
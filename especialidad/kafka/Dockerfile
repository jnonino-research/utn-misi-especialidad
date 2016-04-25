FROM spotify/kafka

ADD build/lib/kafka-1.0-SNAPSHOT.jar ~/

RUN cd ~ && \
    java -jar kafka-1.0-SNAPSHOT.jar

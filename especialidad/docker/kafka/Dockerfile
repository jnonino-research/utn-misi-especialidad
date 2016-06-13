#Basar esta imagen en Ubuntu 14.04 actualizada
FROM jnonino/ubuntu-base

#Install Java 7
#Limpiar la imagen
#Obtener Apache Kafka 0.9.0.1
RUN apt-get install -y tar openjdk-7-jre-headless wget && \
    apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* && \
    wget -q http://artfiles.org/apache.org/kafka/0.9.0.1/kafka_2.11-0.9.0.1.tgz -O /tmp/kafka_2.11-0.9.0.1.tgz && \
    tar xfz /tmp/kafka_2.11-0.9.0.1.tgz -C /opt

VOLUME ["/kafka"]

ENV KAFKA_HOME /opt/kafka_2.11-0.9.0.1
COPY start-kafka.sh /usr/local/bin/start-kafka.sh
CMD ["/usr/local/bin/start-kafka.sh"]
# Imagen basada en una imagen de Ubuntu 14.04
FROM ubuntu:trusty
MAINTAINER Julian Nonino <noninojulian@outlook.com>

# Se utiliza la version de Kafka 0.9.0.1 con Scala 2.11 
ENV SCALA_VERSION 2.11
ENV KAFKA_VERSION 0.9.0.1

# Obtener Apache Kafka 0.9.0.1
RUN apt-get update -y && \
    apt-get install -y tar wget openjdk-7-jre-headless && \
    apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* && \
    wget -q http://artfiles.org/apache.org/kafka/$KAFKA_VERSION/kafka_$SCALA_VERSION-$KAFKA_VERSION.tgz -O /tmp/kafka_$SCALA_VERSION-$KAFKA_VERSION.tgz && \
    tar xfz /tmp/kafka_$SCALA_VERSION-$KAFKA_VERSION.tgz -C /opt && \
    rm -rf /tmp/kafka_$SCALA_VERSION-$KAFKA_VERSION.tgz

# Copiar el script de inicio de Kafka e Iniciar Kafka
ENV KAFKA_HOME /opt/kafka_$SCALA_VERSION-$KAFKA_VERSION
COPY start.sh /usr/local/bin
CMD ["/usr/local/bin/start.sh"]

# Basar esta imagen en Ubuntu 14.04 actualizada
FROM jnonino/ubuntu-base

ENV KAFKA_VERSION 2.11-0.9.0.1

# Install Java 7
# Limpiar la imagen
# Obtener Apache Kafka 0.9.0.1
RUN apt-get install -y tar openjdk-7-jre-headless wget && \
    apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* && \
    wget -q http://artfiles.org/apache.org/kafka/0.9.0.1/kafka_$KAFKA_VERSION.tgz -O /tmp/kafka_$KAFKA_VERSION.tgz && \
    tar xfz /tmp/kafka_$KAFKA_VERSION.tgz -C /opt

# Volumen de Datos
VOLUME ["/kafka"]

# Copiar el script de inicio de Kafka
COPY start-kafka.sh /usr/local/bin/start-kafka.sh

# Iniciar Kafka
ENV KAFKA_HOME /opt/kafka_$KAFKA_VERSION
CMD ["/usr/local/bin/start-kafka.sh"]
# Imagen basada en una imagen de Ubuntu 14.04
FROM ubuntu:trusty
MAINTAINER Julian Nonino <noninojulian@outlook.com>

ENV STORM_VERSION 0.9.7

# Instalar dependencias de Python para Storm y SupervisorD
# Limpiar la imagen luego de las instalaciones y obtener Storm
RUN apt-get update -y && \
    apt-get install -y tar wget openjdk-7-jre-headless && \
    apt-get install -y python-setuptools python-virtualenv supervisor --fix-missing && \
    apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* && \
    wget -q -O - http://apache.mirror.digionline.de/storm/apache-storm-$STORM_VERSION/apache-storm-$STORM_VERSION.tar.gz | tar -xzf - -C /opt && \

ENV STORM_HOME /opt/apache-storm-$STORM_VERSION

RUN groupadd storm; useradd --gid storm --home-dir /home/storm --create-home --shell /bin/bash storm; chown -R storm:storm $STORM_HOME; mkdir /var/log/storm ; chown -R storm:storm /var/log/storm && \
    ln -s $STORM_HOME/bin/storm /usr/bin/storm

COPY storm.yaml $STORM_HOME/conf
COPY cluster.xml $STORM_HOME/logback
COPY config-supervisord.sh /usr/bin
COPY start-supervisor.sh /usr/bin

RUN echo [supervisord] | tee -a /etc/supervisor/supervisord.conf ; echo nodaemon=true | tee -a /etc/supervisor/supervisord.conf

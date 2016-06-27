#Basar esta imagen en Ubuntu 14.04 actualizada
FROM jnonino/ubuntu-base:14.04
MAINTAINER Julian Nonino <noninojulian@outlook.com>

ENV STORM_VERSION 0.9.6

# Install Java 7
# Instalar dependencias de Python para las topologias y SupervisorD
# Limpiar la imagen
# Obtener Storm
RUN apt-get install -y tar openjdk-7-jre-headless wget && \
    apt-get install -y python-setuptools python-virtualenv supervisor && \
    apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* && \
    wget -q -O - http://apache.mirror.digionline.de/storm/apache-storm-$STORM_VERSION/apache-storm-$STORM_VERSION.tar.gz | tar -xzf - -C /opt

ENV STORM_HOME /opt/apache-storm-$STORM_VERSION

RUN groupadd storm; useradd --gid storm --home-dir /home/storm --create-home --shell /bin/bash storm; chown -R storm:storm $STORM_HOME; mkdir /var/log/storm ; chown -R storm:storm /var/log/storm && \
    ln -s $STORM_HOME/bin/storm /usr/bin/storm

COPY storm.yaml $STORM_HOME/conf/storm.yaml
COPY cluster.xml $STORM_HOME/logback/cluster.xml
COPY config-supervisord.sh /usr/bin/config-supervisord.sh
COPY start-supervisor.sh /usr/bin/start-supervisor.sh

RUN echo [supervisord] | tee -a /etc/supervisor/supervisord.conf ; echo nodaemon=true | tee -a /etc/supervisor/supervisord.conf
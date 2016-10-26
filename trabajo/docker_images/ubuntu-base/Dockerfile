# Imagen basada en una imagen de Ubuntu 14.04
FROM ubuntu:trusty
MAINTAINER Julian Nonino <noninojulian@outlook.com>

# Actualizar el sistema
# Instalar herramientas por defecto (wget, tar, OpenJDK 7 JRE Headless)
RUN apt-get update -y && \
    apt-get dist-upgrade -y && \
    apt-get upgrade -y && \
    apt-get install -y tar wget openjdk-7-jre-headless && \
    apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

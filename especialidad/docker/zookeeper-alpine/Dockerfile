# Basar esta imagen en una imagen de Alpine con Java 8
FROM java:openjdk-8-jre-alpine
MAINTAINER Julian Nonino <noninojulian@outlook.com>

ARG VERSION=3.4.8

# Instalar los paquetes necesarios
RUN apk add --no-cache wget bash && \
    rm -rf /var/cache/apk/*

# Descargar e Instalar Zookeeper
RUN mkdir /opt && \
    wget -O - http://mirrors.nxnethosting.com/apache/zookeeper/zookeeper-$VERSION/zookeeper-$VERSION.tar.gz | tar -xzf - -C /opt && \
    mv /opt/zookeeper-$VERSION /opt/zookeeper && \
    cp /opt/zookeeper/conf/zoo_sample.cfg /opt/zookeeper/conf/zoo.cfg && \
    mkdir -p /tmp/zookeeper

WORKDIR /opt/zookeeper

VOLUME ["/opt/zookeeper/conf", "/tmp/zookeeper"]

# Copiar el script de inicio de Zookeeper
# El script de inicio de Zookeeper va a estar en un archivo separado para facilitar los cambios de configuracion
COPY start.sh /usr/local/bin/

# Exponer puerto del cliente de Zookeeper
# Exponer el puerto de emparejamiento de Zookeeper
# Exponer el puerto para el lider de Zookeeper
EXPOSE 2181 2888 3888

# Lo recomendado es que Zookeeper corrar en un cluster de al menos 3 nodos
CMD ["/usr/local/bin/start.sh"]
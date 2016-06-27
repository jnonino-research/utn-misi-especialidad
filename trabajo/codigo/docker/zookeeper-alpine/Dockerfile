# Basar esta imagen en una imagen de Alpine con Java 8
FROM java:openjdk-8-jre-alpine
MAINTAINER Julian Nonino <noninojulian@outlook.com>

ARG VERSION=3.4.8

COPY zookeeper-3.4.8.tar.gz .

# Instalar los paquetes necesarios
# Descargar e Instalar Zookeeper
RUN apk add --update bash && rm -rf /var/cache/apk/* \
    && mkdir /opt \
    && tar -xzf zookeeper-3.4.8.tar.gz && mv zookeeper-3.4.8 /opt/zookeeper \
    && cp /opt/zookeeper/conf/zoo_sample.cfg /opt/zookeeper/conf/zoo.cfg \
    && mkdir /opt/zookeeper/data \
    && sed -i "s|/tmp/zookeeper|/opt/zookeeper/data|g" /opt/zookeeper/conf/zoo.cfg
#    && mkdir -p /tmp/zookeeper \

# Exponer puerto del cliente de Zookeeper
# Exponer el puerto de emparejamiento de Zookeeper
# Exponer el puerto para el lider de Zookeeper
EXPOSE 2181 2888 3888

VOLUME ["/opt/zookeeper/conf", "/opt/zookeeper/data"]

# Copiar el script de inicio de Zookeeper
# El script de inicio de Zookeeper va a estar en un archivo separado para facilitar los cambios de configuracion
# Lo recomendado es que Zookeeper corrar en un cluster de al menos 3 nodos
COPY start.sh /usr/local/bin
CMD ["/usr/local/bin/start.sh"]
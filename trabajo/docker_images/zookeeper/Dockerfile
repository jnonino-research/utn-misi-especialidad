# Imagen basada en una imagen de Ubuntu 14.04 actualizada
FROM jnonino/ubuntu-base:14.04
MAINTAINER Julian Nonino <noninojulian@outlook.com>

# Instalar Zookeeper y limpiar la imagen luego de la instalacion
# Hacer que la consola sea el destino por defecto del log de Zookeeper
RUN apt-get install -y zookeeper && \
    apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Exponer puerto del cliente de Zookeeper (2181)
# Exponer el puerto de emparejamiento de Zookeeper (2888)
# Exponer el puerto para el lider de Zookeeper (3888)
EXPOSE 2181 2888 3888

# Copiar el script de inicio de Zookeeper
# El script de inicio de Zookeeper va a estar en un archivo separado para 
# facilitar los cambios de configuracion.
# Lo recomendado es que Zookeeper corrar en un cluster de al menos 3 nodos
COPY start.sh /usr/local/bin
CMD ["/usr/local/bin/start.sh"]

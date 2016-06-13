#Basar esta imagen en Ubuntu 14.04 actualizada
FROM jnonino/ubuntu-base

#Actualizar el SO
#Instalar Zookeeper
#Limpiar la imagen
#Hacer que la consola sea el destino por defecto del log de Zookeeper
RUN apt-get install -y zookeeper && \
    apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* && \
    sed -i 's/ROLLINGFILE/CONSOLE/' /etc/zookeeper/conf/environment

#Copiar el script de inicio de ZOokeeper
#El script de inicio de Zookeeper va a estar en un archivo separado para facilitar los cambios de configuracion
COPY start.sh /usr/local/bin

#Volumen de datos
VOLUME /var/lib/zookeeper

# Exponer puerto del cliente de Zookeeper
EXPOSE 2181
# Exponer el puerto de emparejamiento de Zookeeper
EXPOSE 2888
# Exponer el puerto para el lider de Zookeeper
EXPOSE 3888

#Iniciar Zookeeper en modo de nodo unico, requiere ejecutar la siguiente linea
#CMD ["/usr/share/zookeeper/bin/zkServer.sh", "start-foreground"]

#Lo recomendado es que Zookeeper corrar en un cluster de al menos 3 nodos
CMD ["/usr/local/bin/start.sh"]
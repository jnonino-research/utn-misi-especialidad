#Basar esta imagen en Ubuntu 14.04
FROM ubuntu:trusty

#Actualizar el SO
RUN apt-get update && apt-get dist-upgrade -y

#Instalar Zookeeper
RUN apt-get install -y zookeeper

#Hacer que la consola sea el destino por defecto del log de Zookeeper
#Originalment es un archivo
RUN sed -i 's/ROLLINGFILE/CONSOLE/' /etc/zookeeper/conf/environment

#Iniciar Zookeeper en modo de nodo unico, requiere ejecutar la siguiente linea
#CMD ["/usr/share/zookeeper/bin/zkServer.sh", "start-foreground"]

#Lo recomendado es que Zookeeper corrar en un cluster de al menos 3 nodos
#El script de inicio de Zookeeper va a estar en un archivo separado para facilitar los cambios de configuracion
ADD start.sh /usr/local/bin/
CMD ["/usr/local/bin/start.sh"]

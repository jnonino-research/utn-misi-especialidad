# Imagenes de Docker #

## Construir imágenes ##

### Apache Zookeeper ###
    
    docker build -t jnonino/zookeeper zookeeper
    
### Apache Kafka ###
    
    docker build -t jnonino/kafka kafka

### Apache Storm ###
    
    docker build -t jnonino/storm-base storm-base
    docker build -t jnonino/storm-nimbus storm-nimbus
    docker build -t jnonino/storm-supervisor storm-supervisor
    docker build -t jnonino/storm-ui storm-ui
   
## Iniciar el clúster ##

Se deben reemplazar los siguientes valores para que las diferentes
partes del sistema se vean entre sí.
- En el archivo zookeeper/start_zookeeper.yml  
Reemplazar los valores de la variable "<ZOOKEEPER_NODE_IP>" por
la dirección IP de la máquina donde correrán los containers de Docker
de Zookeeper.
- En el archivo kafka/start_kafka.yml  
Reemplazar los valores de la variable "<ZOOKEEPER_NODE_IP>" por
la dirección IP de la máquina donde están corriendo los containers de
Docker de Zookeeper.  
Reemplazar los valores de la variable "<KAFKA_NODE_IP>" por
la dirección IP de la máquina donde correrán los containers de Docker
de Kafka.
- En el archivo storm/start_storm.yml  
Reemplazar los valores de la variable "<ZOOKEEPER_NODE_IP>" por
la dirección IP de la máquina donde están corriendo los containers de
Docker de Zookeeper.  
Reemplazar las variables "<NIMBUS_HOST_IP>", "<UI_NODE_IP>" y
"<SUPERVISOR_NODE_IP>" por las direcciones IP de las máquinas donde
correrán los servicios Nimbus, UI y Supervisor de APache Storm
respectivamente.

Luego de generar todas las imágenes de Docker del sistema y 
reemplazar las variables por las direcciones correctas, se
deben correr los siguientes comandos para poner el sistema en
funcionamiento.

    docker-compose -f zookeeper/start_zookeeper.yml up -d
    docker-compose -f kafka/start_kafka.yml up -d
    docker-compose -f storm/start_storm.yml up -d

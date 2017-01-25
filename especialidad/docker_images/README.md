
#### Construir la imagen de Docker de Apache Zookeeper

docker build -t jnonino/zookeeper zookeeper

#### Construir la imagen de Apache Kafka

docker build -t jnonino/kafka kafka

#### Construir la imagen base de Apache Storm

docker build -t jnonino/storm-base storm/storm-base
docker build -t jnonino/storm-nimbus storm/storm-nimbus
docker build -t jnonino/storm-supervisor storm/storm-supervisor
docker build -t jnonino/storm-ui storm/storm-ui

#### Para inciar el cluster

docker-compose -f zookeeper/start_zookeeper.yml up -d
docker-compose -f kafka/start_kafka.yml up -d
docker-compose -f storm/start_storm.yml up -d

#### Verificar que el cluster de Zookeeper esta funcionando
Ejecutar:
for i in {2181..2183}; do echo mntr | nc <DOCKER_HOST_IP> $i | grep zk_followers ; done
Resultado esperado:
zk_followers 2

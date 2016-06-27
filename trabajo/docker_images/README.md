#### Construir la imagen de Docker de Ubuntu 14.04 actualizada

docker build -t jnonino/ubuntu-base ubuntu-base

#### Construir la imagen de Docker de Zookeeper

docker build -t jnonino/zookeeper zookeeper

#### Construir la imagen de kafka

docker build -t jnonino/kafka kafka

#### Construir la imagen base de Storm

docker build -t jnonino/storm-base storm-base

#### Para inciar el cluster

docker-compose -f cluster.yml up
docker-compose -f clusterV2.yml up

#### Verificar que el cluster de Zookeeper esta funcionando
Ejecutar:
for i in {2181..2183}; do echo mntr | nc <DOCKER_HOST_IP> $i | grep zk_followers ; done
Resultado esperado:
zk_followers 2

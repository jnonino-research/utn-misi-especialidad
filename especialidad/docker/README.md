#### Construir la imagen de Docker de Zookeeper

docker build -t jnonino/zookeeper zookeeper

#### Para iniciar el cluster de Zookeeper en containers de Docker, se deben correr los siguientes comandos:

docker run --rm -ti -e ZK_SERVERS="server.1=<DOCKER_HOST_IP>:2888:3888 server.2=<DOCKER_HOST_IP>:2889:3889 server.3=<DOCKER_HOST_IP>:2890:3890" -e ZK_ID=1 --publish 2181:2181 --publish 2888:2888 --publish 3888:3888 jnonino/zookeeper

docker run --rm -ti -e ZK_SERVERS="server.1=<DOCKER_HOST_IP>:2888:3888 server.2=<DOCKER_HOST_IP>:2889:3889 server.3=<DOCKER_HOST_IP>:2890:3890" -e ZK_ID=2 --publish 2182:2181 --publish 2889:2889 --publish 3889:3889 jnonino/zookeeper

docker run --rm -ti -e ZK_SERVERS="server.1=<DOCKER_HOST_IP>:2888:3888 server.2=<DOCKER_HOST_IP>:2889:3889 server.3=<DOCKER_HOST_IP>:2890:3890" -e ZK_ID=3 --publish 2183:2181 --publish 2890:2890 --publish 3890:3890 jnonino/zookeeper

#### Verificar que el cluster de Zookeeper esta funcionando

Ejecutar:
for i in {2181..2183}; do echo mntr | nc <DOCKER_HOST_IP> $i | grep zk_followers ; done
Resultado esperado:
zk_followers 2

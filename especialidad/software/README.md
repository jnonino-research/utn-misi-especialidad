# Software #

## Construir el proyecto ##

Correr el comando:

    mvn clean install

## Cargar al Cluster de Storm la Topología Desarrollada ##

Descargar el ejecutable de Storm para poder realizar acciones sobre el cluster:

    http://apache.dattatec.com/storm/apache-storm-0.9.7/apache-storm-0.9.7.tar.gz

Descomprimir en el lugar donde se desee instalar Apache Storm y agregar la carpeta bin a la variable de entorno PATH.

Indicarle a Storm la dirección del cluster.

    echo "nimbus.seeds: [\"<DIRECCION_STORM>\"]" > ~/.storm/storm.yaml

Cargar la topología con el siguiente comando:

    storm jar <PATH_TOPOLOGIA> ar.edu.utn.frc.posgrado.jnonino.storm.Main <LISTA_BROKERS_KAFKA> <TOPICO_A_LEER>

    storm jar storm-1.0-SNAPSHOT-jar-with-dependencies.jar ar.edu.utn.frc.posgrado.jnonino.storm.Main cluster 192.168.0.103:2181

    
## Ejecutar el software Java que produce datos y los envía al servidor de Kafka

	java -jar <PATH_PROGRAMA> <KAFKA_BROKER_LIST> <ENABLE_PRODUCER> <MESSAGE_PRODUCTION_RATE> <ENABLE_TEST_CONSUMER>
	
	java -jar kafka-1.0-SNAPSHOT-jar-with-dependencies.jar 192.168.0.103:9092,192.168.0.103:9093,192.168.0.103:9094 yes 3000 no

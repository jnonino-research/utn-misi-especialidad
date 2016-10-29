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
    echo "nimbus.seeds: [\"192.168.0.103\"]" > ~/.storm/storm.yaml

Cargar la topología con el siguiente comando:

    storm jar <PATH_TOPOLOGIA> ar.edu.utn.frc.posgrado.jnonino.Main <LISTA_BROKERS_KAFKA> <TOPICO_A_LEER>

    apache-storm-0.9.7/bin/storm jar especialidad-utn/trabajo/software/storm/target/storm-1.0-SNAPSHOT-jar-with-dependencies.jar ar.edu.utn.frc.posgrado.jnonino.Main  192.168.0.103:9092,192.168.0.103:9093,192.168.0.103:9094 metrics
    



ENtrar al container nimbus

    ps aux|grep nimbus

    root        16  0.9  2.9 1503476 120176 ?      Sl   14:46   0:13 java -server -Dstorm.options= -Dstorm.home=/opt/apache-storm-0.9.7 -Dstorm.log.dir=/opt/apache-storm-0.9.7/logs -Djava.library.path=/usr/local/lib:/opt/local/lib:/usr/lib -Dstorm.conf.file= -cp /opt/apache-storm-0.9.7/lib/tools.cli-0.2.4.jar:/opt/apache-storm-0.9.7/lib/snakeyaml-1.11.jar:/opt/apache-storm-0.9.7/lib/reflectasm-1.07-shaded.jar:/opt/apache-storm-0.9.7/lib/jline-2.11.jar:/opt/apache-storm-0.9.7/lib/servlet-api-2.5.jar:/opt/apache-storm-0.9.7/lib/commons-logging-1.1.3.jar:/opt/apache-storm-0.9.7/lib/minlog-1.2.jar:/opt/apache-storm-0.9.7/lib/logback-classic-1.0.13.jar:/opt/apache-storm-0.9.7/lib/slf4j-api-1.7.5.jar:/opt/apache-storm-0.9.7/lib/core.incubator-0.1.0.jar:/opt/apache-storm-0.9.7/lib/clojure-1.5.1.jar:/opt/apache-storm-0.9.7/lib/hiccup-0.3.6.jar:/opt/apache-storm-0.9.7/lib/storm-core-0.9.7.jar:/opt/apache-storm-0.9.7/lib/objenesis-1.2.jar:/opt/apache-storm-0.9.7/lib/ring-core-1.1.5.jar:/opt/apache-storm-0.9.7/lib/commons-io-2.4.jar:/opt/apache-storm-0.9.7/lib/clout-1.0.1.jar:/opt/apache-storm-0.9.7/lib/commons-lang-2.5.jar:/opt/apache-storm-0.9.7/lib/carbonite-1.4.0.jar:/opt/apache-storm-0.9.7/lib/tools.logging-0.2.3.jar:/opt/apache-storm-0.9.7/lib/commons-codec-1.6.jar:/opt/apache-storm-0.9.7/lib/math.numeric-tower-0.0.1.jar:/opt/apache-storm-0.9.7/lib/commons-fileupload-1.2.1.jar:/opt/apache-storm-0.9.7/lib/jetty-6.1.26.jar:/opt/apache-storm-0.9.7/lib/logback-core-1.0.13.jar:/opt/apache-storm-0.9.7/lib/ring-servlet-0.3.11.jar:/opt/apache-storm-0.9.7/lib/chill-java-0.3.5.jar:/opt/apache-storm-0.9.7/lib/jetty-util-6.1.26.jar:/opt/apache-storm-0.9.7/lib/jgrapht-core-0.9.0.jar:/opt/apache-storm-0.9.7/lib/ring-devel-0.3.11.jar:/opt/apache-storm-0.9.7/lib/ring-jetty-adapter-0.3.11.jar:/opt/apache-storm-0.9.7/lib/compojure-1.1.3.jar:/opt/apache-storm-0.9.7/lib/json-simple-1.1.jar:/opt/apache-storm-0.9.7/lib/kryo-2.21.jar:/opt/apache-storm-0.9.7/lib/clj-stacktrace-0.2.2.jar:/opt/apache-storm-0.9.7/lib/log4j-over-slf4j-1.6.6.jar:/opt/apache-storm-0.9.7/lib/tools.macro-0.1.0.jar:/opt/apache-storm-0.9.7/lib/disruptor-2.10.4.jar:/opt/apache-storm-0.9.7/lib/joda-time-2.0.jar:/opt/apache-storm-0.9.7/lib/commons-exec-1.1.jar:/opt/apache-storm-0.9.7/lib/clj-time-0.4.1.jar:/opt/apache-storm-0.9.7/lib/asm-4.0.jar:/opt/apache-storm-0.9.7/conf -Xmx64m -Djava.net.preferIPv4Stack=true -Dlogfile.name=nimbus.log -Dlogback.configurationFile=/opt/apache-storm-0.9.7/logback/cluster.xml backtype.storm.daemon.nimbus
    root       207  0.0  0.0   8868   904 ?        R+   15:11   0:00 grep --color=auto nimbus

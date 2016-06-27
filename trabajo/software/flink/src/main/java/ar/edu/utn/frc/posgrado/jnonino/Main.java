package ar.edu.utn.frc.posgrado.jnonino;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * Created by Julián on 21/3/2016.
 *
 * The following parameters should be set
 *  - "bootstrap.servers" (comma separated list of kafka brokers)
 *  - "zookeeper.connect" (comma separated list of zookeeper servers)
 *  - "group.id" the id of the consumer group
 *  - "topic" the name of the topic to read data from.
 *
 * You can pass these required parameters using "--bootstrap.servers host:port,host1:port1 --zookeeper.connect host:port --topic testTopic"
 *
 * This is a valid input example:
 * 		--topic test --bootstrap.servers localhost:9092 --zookeeper.connect localhost:2181 --group.id myGroup
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String [ ] args){
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        String kafkaServerIP = "";
        String topic = "";
        if (args.length == 2) {
            kafkaServerIP = args[0];
            topic = args[1];
        } else {
            logger.error("Should run with two arguments");
            String usage = "Usage: java -jar flink-1.0-SNAPSHOT-jar-with-dependencies.jar <KAFKA_SERVER_IP> <TOPIC>";
            logger.error(usage);
            System.exit(1);
        }

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", kafkaServerIP + ":9092");
        properties.setProperty("zookeeper.connect", kafkaServerIP + ":2181");
        properties.setProperty("group.id", "test");

        FlinkKafkaConsumer09<String> kafkaConsumer = new FlinkKafkaConsumer09<String>(
                topic,
                new SimpleStringSchema(),
                properties);

        DataStream<String> messageStream = env.addSource(kafkaConsumer);
        messageStream.rebalance().map((MapFunction<String, MetricRecord>) s -> {
            return new MetricRecord(s);
        }).print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

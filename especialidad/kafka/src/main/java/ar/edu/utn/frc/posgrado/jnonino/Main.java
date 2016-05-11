package ar.edu.utn.frc.posgrado.jnonino;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.connect.json.JsonSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

/**
 * Created by jnonino on 30/03/2016.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String [ ] args) {

        String kafkaHostIP = null;

        if (args.length == 1) {
            kafkaHostIP = args[0];
            logger.info("Looking for Kafka Broker at host: " + kafkaHostIP);
        } else {
            logger.error("Not enough param" +
                    "s");
            logger.error("Usage java -jar jarfile.jar <KAFKA_HOST_IP_ADDRESS>");
            System.exit(1);
        }

        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHostIP + ":9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("client.id", "DemoProducer");

        DataProducer producer = new DataProducer(new KafkaProducer<>(props));
        producer.start();
    }

}

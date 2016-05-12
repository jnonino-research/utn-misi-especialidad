package ar.edu.utn.frc.posgrado.jnonino;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by Julián on 12/5/2016.
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String [ ] args) {

        String kafkaServerIP = null;

        if (args.length == 1) {
            kafkaServerIP = args[0];
            logger.info("Kafka Server Address: " + kafkaServerIP);
        } else {
            logger.error("Should run with one argument");
            logger.error("Usage: java -jar kafka-test-consumer-1.0-SNAPSHOT-jar-with-dependencies.jar <KAFKA_SERVER_IP>");
            System.exit(1);
        }

        KafkaConsumer<Integer, String> consumer;

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerIP + ":9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10000);
        properties.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 50000);
        properties.put(ConsumerConfig.RECEIVE_BUFFER_CONFIG, 262144);
        properties.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, 2097152);

        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("metrics"));
        while (true) {
            ConsumerRecords<Integer, String> records = consumer.poll(200);
            for (ConsumerRecord<Integer, String> record : records) {
                String message = "Message: " + record.key() + " - Value: " + record.value();
                logger.info(message);
            }
        }
    }
}

package ar.edu.utn.frc.posgrado.jnonino.kafka;

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
public class DataConsumer extends Thread {

    private static final Logger logger = LogManager.getLogger(DataConsumer.class);

    private final KafkaConsumer<Integer, String> consumer;

    public DataConsumer(String kafkaBrokerList) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerList);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10000);
        properties.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 50000);
        properties.put(ConsumerConfig.RECEIVE_BUFFER_CONFIG, 262144);
        properties.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, 2097152);
        this.consumer = new KafkaConsumer<>(properties);
        this.consumer.subscribe(Arrays.asList("metrics"));
    }

    public void run() {
        while (true) {
            ConsumerRecords<Integer, String> records = this.consumer.poll(200);
            for (ConsumerRecord<Integer, String> record : records) {
                String message = "Message: " + record.key() + " - Value: " + record.value();
                logger.info(message);
            }
        }
    }
}

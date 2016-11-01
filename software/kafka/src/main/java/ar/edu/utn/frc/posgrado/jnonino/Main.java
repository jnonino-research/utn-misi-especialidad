package ar.edu.utn.frc.posgrado.jnonino;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by jnonino on 30/03/2016.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        String kafkaBrokerList = null;

        long producerRateTime = 1000;

        if (args.length != 4) {
            logger.error("Should run with four arguments");
            logger.error("Uso: java -jar kafka-<VERSION>-jar-with-dependencies.jar <KAFKA_BROKER_LIST> <ENABLE_PRODUCER> <MESSAGE_PRODUCTION_RATE> <ENABLE_TEST_CONSUMER>");
            logger.error("<ENABLE_PRODUCER>: <yes|no>");
            logger.error("<MESSAGE_PRODUCTION_RATE>: <long value>");
            logger.error("<ENABLE_TEST_CONSUMER>: <yes|no>");
            System.exit(1);
        } else {
            kafkaBrokerList = args[0];
            String enableProducer = args[1];
            producerRateTime = Long.parseLong(args[2]);
            String enableConsumer = args[3];

            logger.info("Kafka Broker List: " + kafkaBrokerList);

            switch (enableProducer) {
                case "yes":
                    logger.info("Starting Kafka Producer");
                    KafkaProducer<Integer, String> producer;
                    Properties properties = new Properties();
                    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerList);
                    properties.put(ProducerConfig.ACKS_CONFIG, "all");
                    properties.put(ProducerConfig.RETRIES_CONFIG, 2);
                    properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
                    properties.put(ProducerConfig.LINGER_MS_CONFIG, 0);
                    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
                    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
                    producer = new KafkaProducer<>(properties);
                    DataProducer producerThread = new DataProducer(producer, producerRateTime);
                    producerThread.start();
                    break;
                case "no":
                    logger.info("The producer will not run");
                    break;
                default:
                    logger.error("No valid value for ENABLE_PRODUCER - Use yes or no");
                    System.exit(1);
                    break;
            }

            switch (enableConsumer) {
                case "yes":
                    logger.info("Starting Kafka Test Consumer");
                    KafkaConsumer<Integer, String> consumer;
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
                    consumer = new KafkaConsumer<>(properties);
                    consumer.subscribe(Arrays.asList("metrics"));
                    DataConsumer consumerThread = new DataConsumer(consumer);
                    consumerThread.start();
                    break;
                case "no":
                    logger.info("The test consumer will not run");
                    break;
                default:
                    logger.error("No valid value for ENABLE_TEST_CONSUMER - Use yes or no");
                    System.exit(1);
                    break;
            }
        }
    }
}

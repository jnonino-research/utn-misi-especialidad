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

    public static void main(String [ ] args) {

        String kafkaServerIP = null;

        boolean runProducer = false;
        boolean runConsumer = false;

        if (args.length == 3) {
            kafkaServerIP = args[0];
            String enableProducer = args[1];
            String enableConsumer = args[2];

            logger.info("Kafka Server Address: " + kafkaServerIP);

            if (enableProducer.equals("yes")) {
                runProducer = true;
                logger.info("The producer will run");
            } else if (enableProducer.equals("no")) {
                runProducer = false;
                logger.info("The producer will not run");
            } else {
                logger.error("No valid value for ENABLE_PRODUCER - Use yes or no");
                System.exit(1);
            }

            if (enableConsumer.equals("yes")) {
                runConsumer = true;
                logger.info("The test consumer will run");
            } else if (enableConsumer.equals("no")) {
                runConsumer = false;
                logger.info("The test consumer will not run");
            } else {
                logger.error("No valid value for ENABLE_TEST_CONSUMER - Use yes or no");
                System.exit(1);
            }
        } else {
            logger.error("Should run with three arguments");
            String usage = "Usage: java -jar kafka-1.0-SNAPSHOT-jar-with-dependencies.jar <KAFKA_SERVER_IP> <ENABLE_PRODUCER> <ENABLE_TEST_CONSUMER>";
            logger.error(usage);
            logger.error("<ENABLE_PRODUCER>: <yes|no>");
            logger.error("<ENABLE_TEST_CONSUMER>: <yes|no>");
            System.exit(1);
        }

        if(runConsumer) {
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

            DataConsumer consumerThread = new DataConsumer(consumer);
            consumerThread.start();
        }

        if (runProducer){
            KafkaProducer<Integer, String> producer;

            Properties properties = new Properties();
            properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerIP + ":9092");
            properties.put(ProducerConfig.ACKS_CONFIG, "all");
            properties.put(ProducerConfig.RETRIES_CONFIG, 2);
            properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
            properties.put(ProducerConfig.LINGER_MS_CONFIG, 0);
            properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
            properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

            producer = new KafkaProducer<>(properties);

            DataProducer producerThread = new DataProducer(producer);
            producerThread.start();
        }

    }
}

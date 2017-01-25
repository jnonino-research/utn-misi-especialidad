package ar.edu.utn.frc.posgrado.jnonino.kafka;

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
                    DataProducer producerThread = new DataProducer(kafkaBrokerList, producerRateTime);
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
                    DataConsumer consumerThread = new DataConsumer(kafkaBrokerList);
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

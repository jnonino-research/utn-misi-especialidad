package ar.edu.utn.frc.posgrado.jnonino;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Julián on 12/5/2016.
 */
public class DataConsumer extends Thread {

    private static final Logger logger = LogManager.getLogger(DataConsumer.class);

    private final KafkaConsumer<Integer, String> consumer;

    public DataConsumer(KafkaConsumer<Integer, String> cons) {
        this.consumer = cons;
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

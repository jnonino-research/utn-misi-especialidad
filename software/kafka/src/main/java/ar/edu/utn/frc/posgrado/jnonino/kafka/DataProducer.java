package ar.edu.utn.frc.posgrado.jnonino.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * Created by Julián on 2/4/2016.
 */
public class DataProducer extends Thread {

    private static final Logger logger = LogManager.getLogger(DataProducer.class);

    private final KafkaProducer<Integer, String> producer;
    private long producingRateMillis = 1000;

    public DataProducer(String kafkaBrokerList, long producingRateInMillis) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerList);
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, 2);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 0);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.producingRateMillis = producingRateInMillis;
        this.producer = new KafkaProducer<>(properties);
    }

    public void run() {
        try {
            InputStream in = getClass().getResourceAsStream("/data.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    sleep(this.producingRateMillis);
                } catch (InterruptedException e) {
                    logger.error("Interrupted Exception while waiting producing rate time");
                }
                count += 1;
                ProducerRecord<Integer, String> record = new ProducerRecord<>("metrics", count, line);
                logger.info("Sending message: " + line);
                this.producer.send(record);
            }
            bufferedReader.close();
            logger.info("SUCCESS - " + count + " metric records were published.");
        } catch (FileNotFoundException ex) {
            logger.error("Unable to open file data.txt");
            ex.printStackTrace();
        } catch (IOException ex) {
            logger.error("Error reading file data.txt");
            ex.printStackTrace();
        } finally {
            producer.close();
        }
    }
}

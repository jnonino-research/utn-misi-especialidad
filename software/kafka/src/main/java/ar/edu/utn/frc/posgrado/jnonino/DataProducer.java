package ar.edu.utn.frc.posgrado.jnonino;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * Created by Julián on 2/4/2016.
 */
public class DataProducer extends Thread {

    private static final Logger logger = LogManager.getLogger(DataProducer.class);

    private final KafkaProducer<Integer, String> producer;
    private long producingRateMillis = 1000;

    public DataProducer(KafkaProducer<Integer, String> prod, long producingRateInMillis) {
        this.producer = prod;
        this.producingRateMillis = producingRateInMillis;
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
                    e.printStackTrace();
                }
                count += 1;
                ProducerRecord<Integer, String> record = new ProducerRecord<>("metrics", count, line);
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

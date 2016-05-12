package ar.edu.utn.frc.posgrado.jnonino;

import com.google.common.io.Resources;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by Julián on 12/5/2016.
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String [ ] args) {
        KafkaConsumer<Integer, String> consumer;

        try (InputStream props = Resources.getResource("consumer.properties").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            consumer = new KafkaConsumer<>(properties);
            consumer.subscribe(Arrays.asList("metrics"));
            while (true) {
                ConsumerRecords<Integer, String> records = consumer.poll(200);
                for (ConsumerRecord<Integer, String> record : records) {
                    String message = "Message: " + record.key() + " - Value: " + record.value();
                    System.out.println(message);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

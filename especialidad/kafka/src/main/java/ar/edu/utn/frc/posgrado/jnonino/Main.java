package ar.edu.utn.frc.posgrado.jnonino;

import com.google.common.io.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jnonino on 30/03/2016.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String [ ] args) {

        KafkaProducer<Integer, String> producer;

        try {
            try (InputStream props = Resources.getResource("producer.properties").openStream()) {
                Properties properties = new Properties();
                properties.load(props);
                producer = new KafkaProducer<>(properties);

                DataProducer producerThread = new DataProducer(producer);
                producerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


//        Properties props = new Properties();
//
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHostIP + ":9092");
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("client.id", "DemoProducer");



    }

}

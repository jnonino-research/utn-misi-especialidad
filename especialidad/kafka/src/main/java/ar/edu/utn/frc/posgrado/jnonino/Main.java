package ar.edu.utn.frc.posgrado.jnonino;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

/**
 * Created by jnonino on 30/03/2016.
 */
public class Main {

    public static void main(String [ ] args) {
        Properties props = new Properties();
        props.put("zk.connect", "127.0.0.1:2181");
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.connect.json.JsonSerializer");

//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("client.id", "DemoProducer");

        DataProducer producer = new DataProducer(new KafkaProducer<>(props));
        producer.start();
    }

}

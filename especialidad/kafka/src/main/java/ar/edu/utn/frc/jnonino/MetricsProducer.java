package ar.edu.utn.frc.jnonino;

import com.google.gson.JsonObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Created by jnonino on 30/03/2016.
 */
public class MetricsProducer extends Thread {

    private final KafkaProducer<Integer, JsonObject> producer;
    private final String topic;
    private String producerId;
    private final long startDateInMillis;
    private final long endDateInMillis;
    private long currentTime;
    private long messageIntervalInMillis;

    public MetricsProducer(String topic, String producerId, long startDateInMillis, long endDateInMillis, long messageIntervalInMillis) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("client.id", "DemoProducer");
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.connect.json.JsonSerializer");
        producer = new KafkaProducer<>(props);

        this.topic = topic;
        this.producerId = producerId;

        this.startDateInMillis = startDateInMillis;
        this.endDateInMillis = endDateInMillis;
        this.messageIntervalInMillis = messageIntervalInMillis;
    }

    public void run() {
        int messageNo = 1;

        this.currentTime = this.startDateInMillis;
        while (this.currentTime > this.endDateInMillis) {




            // Increment times
            this.currentTime += this.messageIntervalInMillis;
        }


        while (true) {

            long timeStamp = System.currentTimeMillis();

            JsonObject message = new JsonObject();
            message.addProperty("timestamp", timeStamp);
            message.addProperty("producer", producerId);


            try {
                producer.send(new ProducerRecord<>(topic, messageNo, message)).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            ++messageNo;
        }
    }
}

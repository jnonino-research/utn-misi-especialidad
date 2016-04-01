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
    private final RandomGenerator metricsGenerator;
    private final long startDateInMillis;
    private final long endDateInMillis;

    private int messageNumber;
    private String producerId;
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

        this.metricsGenerator = RandomGenerator.getInstance();

        this.messageNumber = 1;
    }

    public void run() {
        this.currentTime = this.startDateInMillis;
        while (this.currentTime > this.endDateInMillis) {
            JsonObject metricsMessage = new JsonObject();

            long timeStamp = System.currentTimeMillis();
            metricsMessage.addProperty("timestamp", timeStamp);

            metricsMessage.addProperty("producer", producerId);

            metricsMessage.addProperty("temperature", this.metricsGenerator.getTemperatureValue());
            metricsMessage.addProperty("humidity", this.metricsGenerator.getHumidity());
            metricsMessage.addProperty("wind_speed", this.metricsGenerator.getWindSpeed());
            metricsMessage.addProperty("win_direction", this.metricsGenerator.getWindDirection());

            ProducerRecord<Integer, JsonObject> producerRecord = new ProducerRecord<>(this.topic, this.messageNumber, metricsMessage);

            try {
                producer.send(producerRecord).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            // Increment times
            this.currentTime += this.messageIntervalInMillis;
            this.messageNumber += 1;
        }
    }
}

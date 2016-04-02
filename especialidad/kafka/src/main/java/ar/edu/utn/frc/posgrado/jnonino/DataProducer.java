package ar.edu.utn.frc.posgrado.jnonino;

import com.google.gson.JsonObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Created by Julián on 2/4/2016.
 */
public class DataProducer extends Thread {

    private final KafkaProducer<Integer, JsonObject> producer;

    public DataProducer(KafkaProducer<Integer, JsonObject> prod) {
        this.producer = prod;
    }

    public void run() {
        try {
            InputStream in = getClass().getResourceAsStream("/data.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            JsonObject metricRecord;
            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                metricRecord = createMetricRecord(line);
                if (metricRecord != null) {
                    count += 1;
                    String topic = metricRecord.get("country").getAsString();
                    topic.concat(metricRecord.get("state").getAsString());
                    topic.concat(metricRecord.get("city").getAsString());
                    ProducerRecord<Integer, JsonObject> producerRecord = new ProducerRecord<>(topic, count, metricRecord);
                    try {
                        producer.send(producerRecord).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(metricRecord.toString());
                }
            }
            bufferedReader.close();
            System.out.println("SUCCESS - " + count + " metric records were published.");
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file data.txt");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error reading file data.txt");
            ex.printStackTrace();
        }
    }

    private JsonObject createMetricRecord(String line) {
        List<String> values = Arrays.asList(line.split("\\s*,\\s*"));
        String country = values.get(0);
        String state = values.get(1);
        String city = values.get(2);
        long timestamp = Long.parseLong(values.get(3));
        float temperature = Float.parseFloat(values.get(4));
        float humidity = Float.parseFloat(values.get(5));
        float pressure = Float.parseFloat(values.get(6));
        float windSpeed = Float.parseFloat(values.get(7));
        String windDirection = values.get(8);

        boolean invalidMetric = country.equalsIgnoreCase("")
                || state.equalsIgnoreCase("")
                || city.equalsIgnoreCase("");

        if (invalidMetric) {
            return null;
        } else {
            JsonObject metricsMessage = new JsonObject();
            metricsMessage.addProperty("country", country);
            metricsMessage.addProperty("state", state);
            metricsMessage.addProperty("city", city);
            metricsMessage.addProperty("timestamp", timestamp);
            metricsMessage.addProperty("temperature", temperature);
            metricsMessage.addProperty("humidity", humidity);
            metricsMessage.addProperty("pressure", pressure);
            metricsMessage.addProperty("wind_speed", windSpeed);
            metricsMessage.addProperty("win_direction", windDirection);
//        metricsMessage.addProperty("producer", producerId);
            return metricsMessage;
        }
    }
}

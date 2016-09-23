package ar.edu.utn.frc.posgrado.jnonino;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

import java.util.Properties;

/**
 * Created by jnonino on 08/04/2016.
 */
public class MetricsConsumer extends Thread {

    private final StreamExecutionEnvironment env;
    private final FlinkKafkaConsumer09 flinkKafkaConsumer;


    public MetricsConsumer(String topic, Properties properties) {
        env = StreamExecutionEnvironment.getExecutionEnvironment();
        
        this.flinkKafkaConsumer = new FlinkKafkaConsumer09<>(topic, new SimpleStringSchema(), properties);
    }

    public void run() {
        DataStreamSource messageStream = this.env.addSource(this.flinkKafkaConsumer);
    }
}

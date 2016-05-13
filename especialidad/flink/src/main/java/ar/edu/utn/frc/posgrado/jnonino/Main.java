package ar.edu.utn.frc.posgrado.jnonino;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer082;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

/**
 * Created by Julián on 21/3/2016.
 *
 * The following parameters should be set
 *  - "bootstrap.servers" (comma separated list of kafka brokers)
 *  - "zookeeper.connect" (comma separated list of zookeeper servers)
 *  - "group.id" the id of the consumer group
 *  - "topic" the name of the topic to read data from.
 *
 * You can pass these required parameters using "--bootstrap.servers host:port,host1:port1 --zookeeper.connect host:port --topic testTopic"
 *
 * This is a valid input example:
 * 		--topic test --bootstrap.servers localhost:9092 --zookeeper.connect localhost:2181 --group.id myGroup
 */
public class Main {

    public static void main(String [ ] args){
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        ParameterTool parameterTool = ParameterTool.fromArgs(args);

        FlinkKafkaConsumer082<String> flinkKafkaConsumer = new FlinkKafkaConsumer082<String>(
                parameterTool.getRequired("topic"),
                new SimpleStringSchema(),
                parameterTool.getProperties());

        DataStream<String> messageStream = env.addSource(flinkKafkaConsumer);

        messageStream.rebalance().map((MapFunction<String, MetricRecord>) s -> {
            MetricRecord metricRecord = new MetricRecord(s);
            return metricRecord;
        }).print();


//        messageStream.rebalance().map(new MapFunction<String, String>() {
//            private static final long serialVersionUID = -6867736771747690202L;
//
//            @Override
//            public String map(String value) throws Exception {
//                return "Kafka and Flink says: " + value;
//            }
//        }).print();


        // Process numbers.txt file
//        String name = ClassLoader.getSystemResource("numbers.txt").getFile();
//        DataStream<String> text = env.readTextFile(name);
//        DataStream<String> parsed = text.map(new MapFunction<String, String>() {
//            public String map(String value) {
//                return "Number: " + value;
//            }
//        });
//        parsed.print();
//
//
//        try {
//            env.execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }





    }


}

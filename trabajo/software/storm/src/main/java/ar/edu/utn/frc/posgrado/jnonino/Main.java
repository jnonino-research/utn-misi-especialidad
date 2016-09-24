package ar.edu.utn.frc.posgrado.jnonino;

import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import storm.kafka.*;
import storm.kafka.trident.GlobalPartitionInformation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by jnonino on 12/04/2016.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String [] args) {
        Properties props = new Properties();
        String propsFileName = "config.properties";
        InputStream inStream = Main.class.getClassLoader().getResourceAsStream(propsFileName);
        try {
            props.load(inStream);
        } catch (IOException e) {
            logger.error("No se han cargado las propiedades del sistema");
            e.printStackTrace();
        }

        String brokersList = props.getProperty("brokers");
        GlobalPartitionInformation partitionInfo = new GlobalPartitionInformation();
        List<String> brokers = Arrays.asList(brokersList.split(","));
        for(int index=0 ; index<brokers.size() ; index++) {
            Broker broker = new Broker(brokers.get(index));
            partitionInfo.addPartition(index, broker);
        }
        StaticHosts hosts = new StaticHosts(partitionInfo);

        String topicName = props.getProperty("topic");
        SpoutConfig spoutConfig = new SpoutConfig(hosts, topicName, "/" + topicName, UUID.randomUUID().toString());
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafkaSpout", kafkaSpout);
        builder.setBolt("processBolt", new DataProcessBolt()).shuffleGrouping("kafkaSpout");
    }
}

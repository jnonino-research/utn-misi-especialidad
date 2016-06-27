package ar.edu.utn.frc.posgrado.jnonino;

import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import storm.kafka.*;
import storm.kafka.trident.GlobalPartitionInformation;

import java.util.UUID;

/**
 * Created by jnonino on 12/04/2016.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String [ ] args) {

        String zookeeperServerIP = "";
        String topicName = "";
        if (args.length == 2) {
            zookeeperServerIP = args[0];
            topicName = args[1];
        } else {
            logger.error("Should run with two arguments");
            String usage = "Usage: java -jar storm-1.0-SNAPSHOT-jar-with-dependencies.jar <ZOOKEEPER_SERVER_IP> <TOPIC>";
            logger.error(usage);
            System.exit(1);
        }

        Broker broker = new Broker(zookeeperServerIP, 9092);
        GlobalPartitionInformation partitionInfo = new GlobalPartitionInformation();
        partitionInfo.addPartition(0, broker);
        StaticHosts hosts = new StaticHosts(partitionInfo);

        SpoutConfig spoutConfig = new SpoutConfig(hosts, topicName, "/" + topicName, UUID.randomUUID().toString());
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafkaSpout", kafkaSpout);

    }
}

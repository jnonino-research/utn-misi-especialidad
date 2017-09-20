package ar.edu.utn.frc.posgrado.jnonino.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import storm.kafka.*;

import java.util.UUID;

/**
 * Created by jnonino on 12/04/2016.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final String localModeOption = "local";
    private static final String clusterModeOption = "cluster";

    public static void main(String[] args) {

        String mode = null;
        String zookeeperConnStr = null;

        if (args.length == 2) {
            mode = args[0];
            zookeeperConnStr = args[1];
        } else {
            logger.error("Should run with two arguments");
            logger.error("Usage: storm jar storm-<VERSION>-jar-with-dependencies.jar <MODE> <ZOOKEEPER_IP:ZOOKEEPER_PORT>");
            logger.error("<MODE>: valid choices are: " + localModeOption + " or " + clusterModeOption);
            System.exit(1);
        }
        ZkHosts hosts = new ZkHosts(zookeeperConnStr);
        String topicToRead = "metrics";
        SpoutConfig spoutConfig = new SpoutConfig(hosts, topicToRead, "/" + topicToRead, UUID.randomUUID().toString());
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafkaSpout", kafkaSpout);
        builder.setBolt("processBolt", new DataProcessBolt()).shuffleGrouping("kafkaSpout");

        Config conf = new Config();
        conf.setNumWorkers(1);
        conf.setNumAckers(1);
        conf.setMaxSpoutPending(2000);
        conf.setMessageTimeoutSecs(60);

        if (mode.equalsIgnoreCase(localModeOption)) {
            logger.info("Deploying topology in local mode");
            conf.setDebug(true);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("test", conf, builder.createTopology());
        } else if (mode.equalsIgnoreCase(clusterModeOption)) {
            logger.info("Deploying topology in cluster mode");
            try {
                StormSubmitter.submitTopology("especialidad", conf, builder.createTopology());
            } catch (AlreadyAliveException e) {
                logger.error("Topology already running");
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                logger.error("Invalid Topology");
                e.printStackTrace();
            }
        } else {
            logger.error("The selected deploy mode is not valid");
            System.exit(1);
        }
    }
}

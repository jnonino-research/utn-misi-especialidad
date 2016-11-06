package ar.edu.utn.frc.posgrado.jnonino;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by Julián on 24/9/2016.
 */
public class DataProcessBolt extends BaseRichBolt {

    private static final Logger logger = LogManager.getLogger(Main.class);

    private OutputCollector collector = null;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
    }

    @Override
    public void execute(Tuple tupleInput) {
        String message = tupleInput.getString(0);
        logger.info("LOGGER-Procesando el mensaje: " + message);
        System.out.println("SYSTEM OUT-Procesando el mensaje: " + message);
        MetricRecord metric = new MetricRecord(message);
        this.collector.ack(tupleInput);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}

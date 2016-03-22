import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.io.File;
import java.net.URL;

/**
 * Created by Julián on 21/3/2016.
 */

public class Main {

    public static void main(String [ ] args){
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        String name = ClassLoader.getSystemResource("numbers.txt").getFile();

        DataStream<String> text = env.readTextFile(name);

        DataStream<String> parsed = text.map(new MapFunction<String, String>() {
            public String map(String value) {
                return "Number: " + value;
            }
        });

        parsed.print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

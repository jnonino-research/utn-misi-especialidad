package ar.edu.utn.frc.posgrado.jnonino;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jnonino on 30/03/2016.
 */
public class Main {

    private static final long ONE_MINUTE = 60000;
    private static final long TEN_MINUTES = 600000;
    private static final long ONE_HOUR = 3600000;
    private static final long SIX_HOURS = 21600000;
    private static final long ONE_DAY = 86400000;


    public static void main(String [ ] args){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

        String startDateInString = "01-01-2016 00:00:00";
        Date startDate = null;
        String endDateInString = "30-06-2016 23:59:59";
        Date endDate = null;
        try {
            startDate = sdf.parse(startDateInString);
            endDate = sdf.parse(endDateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (startDate != null && endDate != null) {

            String topic_one = null;
            String id_one = null;
            MetricsProducer producer_one = new MetricsProducer(topic_one, id_one, startDate.getTime(), endDate.getTime(), ONE_MINUTE);
            producer_one.start();

            String topic_two = null;
            String id_two = null;
            MetricsProducer producer_two = new MetricsProducer(topic_two, id_two, startDate.getTime(), endDate.getTime(), TEN_MINUTES);
            producer_two.start();

            String topic_three = null;
            String id_three = null;
            MetricsProducer producer_three = new MetricsProducer(topic_three, id_three, startDate.getTime(), endDate.getTime(), ONE_HOUR);
            producer_three.start();

            String topic_four = null;
            String id_four = null;
            MetricsProducer producer_four = new MetricsProducer(topic_four, id_four, startDate.getTime(), endDate.getTime(), SIX_HOURS);
            producer_four.start();

            String topic_five = null;
            String id_five = null;
            MetricsProducer producer_five = new MetricsProducer(topic_five, id_five, startDate.getTime(), endDate.getTime(), ONE_DAY);
            producer_five.start();
        }
    }

}

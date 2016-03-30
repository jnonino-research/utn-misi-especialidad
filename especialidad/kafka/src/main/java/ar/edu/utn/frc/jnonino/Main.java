package ar.edu.utn.frc.jnonino;

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
        String endDateInString = "31-12-2016 23:59:59";
        Date endDate = null;
        try {
            startDate = sdf.parse(startDateInString);
            endDate = sdf.parse(endDateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (startDate != null && endDate != null){
            String topic = null;
            String id = null;
            MetricsProducer producer = new MetricsProducer(topic, id, startDate.getTime(), endDate.getTime(), SIX_HOURS);
        }




    }

}

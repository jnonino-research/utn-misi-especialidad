package ar.edu.utn.frc.posgrado.jnonino;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Julián on 13/5/2016.
 */
public class MetricRecord {

    private final String country;
    private final String state;
    private final String city;
    private final long timestamp;
    private final float temperature;
    private final float humidity;
    private final float pressure;
    private final float windSpeed;
    private final String windDirection;
    private final String owner;

    public MetricRecord(String line) {
        List<String> values = Arrays.asList(line.split("\\s*,\\s*"));
        this.country = values.get(0);
        this.state = values.get(1);
        this.city = values.get(2);
        this.timestamp = Long.parseLong(values.get(3));
        this.temperature = Float.parseFloat(values.get(4));
        this.humidity = Float.parseFloat(values.get(5));
        this.pressure = Float.parseFloat(values.get(6));
        this.windSpeed = Float.parseFloat(values.get(7));
        this.windDirection = values.get(8);
        this.owner = values.get(9);
    }

    public boolean isValidMetric() {
        boolean invalidMetric = this.country.equalsIgnoreCase("")
                || this.state.equalsIgnoreCase("")
                || this.city.equalsIgnoreCase("");

        return !invalidMetric;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return this.country + "-" + this.city + "-" + this.timestamp;
    }
}

package ar.edu.utn.frc.jnonino;

import java.util.*;

/**
 * Created by jnonino on 01/04/2016.
 */
public class RandomGenerator {

    private static final int MINUMUM_TEMPERATURE_VALUE = -15;
    private static final int MAXIMUM_TEMPERATURE_VALUE = 45;
    private static final int MINUMUM_HUMIDITY_VALUE = 0;
    private static final int MAXIMUM_HUMIDITY_VALUE = 100;
    private static final int MINUMUM_WIND_SPEED_VALUE = 0;
    private static final int MAXIMUM_WIND_SPEED_VALUE = 400;

    private static final ArrayList<String> WIND_DIRECTIONS = new ArrayList<String>() {{
        add("North");
        add("North East");
        add("East");
        add("South East");
        add("South");
        add("South West");
        add("West");
        add("North West");
    }};

    private static RandomGenerator instance = null;
    private final Random random = new Random();

    private RandomGenerator(){}

    public static RandomGenerator getInstance() {
        if (instance == null) {
            instance = new RandomGenerator();
        }
        return instance;
    }

    public int getTemperatureValue() {
        return this.random.nextInt(MAXIMUM_TEMPERATURE_VALUE - MINUMUM_TEMPERATURE_VALUE + 1) + MINUMUM_TEMPERATURE_VALUE;
    }

    public int getHumidity() {
        return this.random.nextInt(MAXIMUM_HUMIDITY_VALUE - MINUMUM_HUMIDITY_VALUE + 1) + MINUMUM_HUMIDITY_VALUE;
    }

    public int getWindSpeed() {
        return this.random.nextInt(MAXIMUM_WIND_SPEED_VALUE - MINUMUM_WIND_SPEED_VALUE + 1) + MINUMUM_WIND_SPEED_VALUE;
    }

    public String getWindDirection() {
        int index = this.random.nextInt(WIND_DIRECTIONS.size());
        return WIND_DIRECTIONS.get(index);
    }
}

package ar.edu.utn.frc.posgrado.jnonino;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jnonino on 01/04/2016.
 */
public class RandomGeneratorTest {

    public static final int AMOUNT_OF_CALLS_TO_RANDOM_METHOD = 1000000;
    private RandomGenerator randomGenerator;

    @Before
    public void setUp() throws Exception {
        this.randomGenerator = RandomGenerator.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        this.randomGenerator = null;
    }

    @Test
    public void getTemperatureValue() throws Exception {
        boolean greaterThanMaximum = false;
        boolean lowerThanMinimum = false;

        for(int i = 0; i < AMOUNT_OF_CALLS_TO_RANDOM_METHOD; i++) {
            int temperature = this.randomGenerator.getTemperatureValue();
            if (temperature > this.randomGenerator.MAXIMUM_TEMPERATURE_VALUE) {
                greaterThanMaximum = true;
                break;
            } else if (temperature < this.randomGenerator.MINUMUM_TEMPERATURE_VALUE) {
                lowerThanMinimum = true;
                break;
            }
        }

        processRandomTestsResult("temperature", greaterThanMaximum, lowerThanMinimum);
    }

    @Test
    public void getHumidity() throws Exception {
        boolean greaterThanMaximum = false;
        boolean lowerThanMinimum = false;

        for(int i = 0; i < AMOUNT_OF_CALLS_TO_RANDOM_METHOD; i++) {
            int humidity = this.randomGenerator.getHumidity();
            if (humidity > this.randomGenerator.MAXIMUM_HUMIDITY_VALUE) {
                greaterThanMaximum = true;
                break;
            } else if (humidity < this.randomGenerator.MINUMUM_HUMIDITY_VALUE) {
                lowerThanMinimum = true;
                break;
            }
        }

        processRandomTestsResult("Humidity", greaterThanMaximum, lowerThanMinimum);
    }

    @Test
    public void getWindSpeed() throws Exception {
        boolean greaterThanMaximum = false;
        boolean lowerThanMinimum = false;

        for(int i = 0; i < AMOUNT_OF_CALLS_TO_RANDOM_METHOD; i++) {
            int windSpeed = this.randomGenerator.getWindSpeed();
            if (windSpeed > this.randomGenerator.MAXIMUM_WIND_SPEED_VALUE) {
                greaterThanMaximum = true;
                break;
            } else if (windSpeed < this.randomGenerator.MINUMUM_WIND_SPEED_VALUE) {
                lowerThanMinimum = true;
                break;
            }
        }
        processRandomTestsResult("Wind Speed", greaterThanMaximum, lowerThanMinimum);
    }

    @Test
    public void getWindDirection() throws Exception {

    }

    private void processRandomTestsResult(String metricType, boolean greaterThanMaximum, boolean lowerThanMinimum) {
        if (greaterThanMaximum) {
            String message = "Random " + metricType + " generator return a value greater that maximum";
            assertTrue(message, false);
        } else if (lowerThanMinimum) {
            String message = "Random " + metricType + " generator return a value lower than minimum";
            assertTrue(message, false);
        } else {
            assertTrue(true);
        }
    }
}
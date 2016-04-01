package ar.edu.utn.frc.posgrado.jnonino;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.After;
import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.junit.Assert.*;

/**
 * Created by jnonino on 01/04/2016.
 */
public class RandomGeneratorTest {

    private RandomGenerator randomGenerator;

    @Before
    public void setUp() throws Exception {
        this.randomGenerator = RandomGenerator.getInstance();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getTemperatureValue() throws Exception {

    }

    @Test
    public void getHumidity() throws Exception {

    }

    @Test
    public void getWindSpeed() throws Exception {

    }

    @Test
    public void getWindDirection() throws Exception {

    }

}
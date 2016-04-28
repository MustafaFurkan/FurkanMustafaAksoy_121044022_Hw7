import static org.junit.Assert.*;

/**
 * Created by Furkan on 28.04.2016.
 */
public class SimulationTest {

    @org.junit.Test
    public void testRunSimulation() throws Exception {

        Simulation sim = new Simulation();
        sim.enterData("data1.txt");

        sim.runSimulation();

        String expected = "CUSTOMER 1:\nQueue:\n\nCUSTOMER 2:\nQueue:\n\nCUSTOMER 3:\nQueue:\n\n";

        assertEquals(sim.toString(),expected);
    }
}
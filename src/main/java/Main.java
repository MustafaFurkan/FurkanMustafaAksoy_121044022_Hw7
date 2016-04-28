import java.io.IOException;

/**
 * Created by Furkan on 24.04.2016.
 */
public class Main {


    public static void main(String [] argv) throws IOException {

        Simulation sim = new Simulation();
        sim.enterData("data1.txt");

        sim.runSimulation();

        System.out.println(sim.toString());

    }

}

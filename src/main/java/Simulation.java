/**
 * Created by Furkan on 25.04.2016.
 */
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/** Simulate the check-in process of an airline.
 *   @author Koffman & Wolfgang
 */

public class Simulation {

    // Data Fields
    /** Queue which has more priority. */
    private CustomerQueue customer1Queue =
            new CustomerQueue("Customer 1" );

    /** Queue which has regular priority. */
    private CustomerQueue customer2Queue =
            new CustomerQueue("Customer 2");

    /** Queue which has less priority. */
    private CustomerQueue customer3Queue =
            new CustomerQueue("Customer 3");

    /** ArrayList keeps initial data from txt*/
    private ArrayList<Customer> allData =
            new ArrayList<Customer>();

    /** Maximum number of frequent flyers to be served
     before a regular Customer gets served. */
    private int frequentFlyerMax;

    /** Maximum time to service a Customer. */
    private int maxProcessingTime;

    /** Total simulated time. */
    private static int totalTime = 1440; // 1 day , 24 hour to minute *60

    /** If set true, print additional output. */
    private boolean showAll=true;

    /** Simulated clock. */
    private int clock = 0;

    /** Time that the agent will be done with the current Customer.*/
    private int timeDone = 0; // initialize

    /** Number of frequent flyers served since the
     last regular Customer was served. */
    private int frequentFlyersSinceRP;

    public void runSimulation() {
        for (clock = 0; clock < totalTime; clock++) {
            customer1Queue.checkNewArrival(clock, showAll,allData);
            customer2Queue.checkNewArrival(clock, showAll,allData);
            customer3Queue.checkNewArrival(clock, showAll,allData);
            if (clock >= timeDone) {
                startServe();
            }
        }
    }

    private void startServe() {
        if (!customer1Queue.isEmpty()){
            // Serve the next more priority Customer.
            timeDone = customer1Queue.update(clock, showAll);
        }
        else if ((customer1Queue.isEmpty())
                && (!customer2Queue.isEmpty())) {
            // Serve the next regular priority Customer.
            timeDone = customer2Queue.update(clock, showAll);
        }
        else if ((customer1Queue.isEmpty())
                && (customer2Queue.isEmpty()) && (!customer3Queue.isEmpty())){
            // Serve the next less priority Customer.
            timeDone = customer3Queue.update(clock, showAll);
        }
        else if (clock/60 < 10) {
            System.out.println("Time is " + clock/60 + ":" + clock%60
                    + " server is idle");
        }
    }
/*
private void startServe() {
        if (!customer1Queue.isEmpty()
                && ( (frequentFlyersSinceRP <= frequentFlyerMax)
                || customer2Queue.isEmpty())) {
            // Serve the next frequent flyer.
            frequentFlyersSinceRP++;
            timeDone = customer1Queue.update(clock, showAll);
        }
        else if (!customer2Queue.isEmpty()) {
            // Serve the next regular Customer.
            frequentFlyersSinceRP = 0;
            timeDone = customer2Queue.update(clock, showAll);
        }
        else if (showAll) {
            System.out.println("Time is " + clock
                    + " server is idle");
        }
    }
* */
    /** Method to show the statistics. */
    private void showStats() {
        System.out.println
                ("\nThe number of regular Customers served was "
                        + customer2Queue.getNumServed());
        double averageWaitingTime =
                (double) customer2Queue.getTotalWait()
                        / (double) customer2Queue.getNumServed();
        System.out.println(" with an average waiting time of "
                + averageWaitingTime);
        System.out.println("The number of frequent flyers served was "
                + customer1Queue.getNumServed());
        averageWaitingTime =
                (double) customer1Queue.getTotalWait()
                        / (double) customer1Queue.getNumServed();
        System.out.println(" with an average waiting time of "
                + averageWaitingTime);
        System.out.println("Customers in frequent flyer queue: "
                + customer1Queue.size());
        System.out.println("Customers in regular Customer queue: "
                + customer2Queue.size());
    }
    public void enterData(String txtName) throws IOException {
        File file = new File(txtName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        int min = 0, hour = 0, arrivalTime, serviceTime = 1,customerType = 1;

        // read info first
        line = bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
            stringBuffer.append("\n");

            StringTokenizer destroy = new StringTokenizer(line);
/*
            System.out.println(line);

            System.out.println(Integer.valueOf(line.substring(0,2)));

            System.out.println(Integer.valueOf(line.substring(3,5)));
*/
            // hour data from each line
            hour = Integer.valueOf(line.substring(0,2));
            // minute data from each line
            min = Integer.valueOf(line.substring(3,5));
            // customer type from each line
            customerType = Integer.valueOf(line.substring(line.length()-1,line.length()));

            // parsing each line
            destroy.hasMoreElements();

            destroy.nextToken();

            serviceTime = Integer.parseInt(destroy.nextToken());

            arrivalTime = hour * 60 + min;

            allData.add(new Customer(arrivalTime, serviceTime ,customerType));
//System.out.println(" ----> " + hour + min + " ----> " + serviceTime + " ----> " + customerType);

        }
//        System.out.println("data size" + allData.size());

        fileReader.close();
//        System.out.println("Contents of file:");
//        System.out.println(stringBuffer.toString());

    }

}

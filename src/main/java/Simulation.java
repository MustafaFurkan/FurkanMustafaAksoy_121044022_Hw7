/**
 * Created by Furkan on 25.04.2016.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/** Simulate the priority of customer.
 *   @author Furkan Mustafa Aksoy
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
    private static int totalTime = 1440; // 1 day , 24 hour to minute *60 1440

    /** If set true, print additional output. */
    private boolean showAll;

    /** Simulated clock. */
    private int clock = 0;

    /** Time that the agent will be done with the current Customer.*/
    private int timeDone = 0; // initialize

    /** Number of frequent flyers served since the
     last regular Customer was served. */
    private int frequentFlyersSinceRP;

    private int countCustomer1 = 0;
    private int countCustomer2 = 0;
    private int countCustomer3 = 0;
    /**
     * Start simulation
     */
    public void runSimulation() {
        for (clock = 0; clock < totalTime; clock++) {
            for(int next=0; next<allData.size(); next++){
                if (allData.get(next).getCustomerType() == 1){
                    customer1Queue.checkNewArrival(clock, showAll,allData.get(next),1);
                }else if (allData.get(next).getCustomerType() == 2){
                    customer2Queue.checkNewArrival(clock, showAll,allData.get(next),2);
                }else if (allData.get(next).getCustomerType() == 3){
                    customer3Queue.checkNewArrival(clock, showAll,allData.get(next),3);
                }else{
                    throw new ExceptionInInitializerError("Wrong type of Customer");
                }
            }
            if (clock >= timeDone) {
                startServe();
            }
        }
    }

    /**
     * Start to serve
     */
    private void startServe() {

        System.out.println("Time: " + clock/60 + ":" + clock%60);
        System.out.println("CUSTOMER 1:");
        System.out.println(customer1Queue.toString());
        System.out.println("CUSTOMER 2:");
        System.out.println(customer2Queue.toString());
        System.out.println("CUSTOMER 3:");
        System.out.println(customer3Queue.toString());
        System.out.println("--------------------------------");

        if (!customer1Queue.isEmpty()){
            // Serve the next more priority Customer.
            timeDone = customer1Queue.update(clock, showAll);
            ++countCustomer1;
        }
        else if ((customer1Queue.isEmpty())
                && (!customer2Queue.isEmpty())) {
            // Serve the next regular priority Customer.
            timeDone = customer2Queue.update(clock, showAll);
            ++countCustomer2;
        }
        else if ((customer1Queue.isEmpty())
                && (customer2Queue.isEmpty()) && (!customer3Queue.isEmpty())){
            // Serve the next less priority Customer.
            timeDone = customer3Queue.update(clock, showAll);
            ++countCustomer3;
        }
        // Show silver, gold and bronz customers
        if (clock%1200 == 1 && clock != 1) {
            System.out.println("Time is " + clock/60 + ":" + clock%60);
            if ((countCustomer1 > countCustomer2)&&(countCustomer1 > countCustomer3))
            {
                System.out.println("Gold Customer: " + "Customer 1 process " + countCustomer1 + " time.");
                if (countCustomer2 > countCustomer3){
                    System.out.println("Silver Customer: " + "Customer 2 process " + countCustomer2 + " time.");
                    System.out.println("Bronz Customer: " + "Customer 3 process " + countCustomer3 + " time.");
                }
                else{
                    System.out.println("Silver Customer: " + "Customer 3 process " + countCustomer3 + " time.");
                    System.out.println("Bronz Customer: " + "Customer 2 process " + countCustomer2 + " time.");
                }
            }else if ((countCustomer2 > countCustomer1)&&(countCustomer2 > countCustomer3))
            {
                System.out.println("Gold Customer: " + "Customer 2 process " + countCustomer2 + " time.");
                if (countCustomer1 > countCustomer3){
                    System.out.println("Silver Customer: " + "Customer 1 process " + countCustomer1 + " time.");
                    System.out.println("Bronz Customer: " + "Customer 3 process " + countCustomer3 + " time.");

                }else {
                    System.out.println("Silver Customer: " + "Customer 3 process " + countCustomer3 + " time.");
                    System.out.println("Bronz Customer: " + "Customer 1 process " + countCustomer1 + " time.");
                }
            }else{
                System.out.println("Gold Customer: " + "Customer 3 process " + countCustomer3 + " time.");
                if (countCustomer1 > countCustomer2){
                    System.out.println("Silver Customer: " + "Customer 1 process " + countCustomer1 + " time.");
                    System.out.println("Bronz Customer: " + "Customer 2 process " + countCustomer2 + " time.");
                }else {
                    System.out.println("Silver Customer: " + "Customer 2 process " + countCustomer2 + " time.");
                    System.out.println("Bronz Customer: " + "Customer 1 process " + countCustomer1 + " time.");
                }
            }
        }
    }

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

    /**
     * Input data from txt file
     * @param txtName name of txt file
     * @throws IOException wrong type check
     */
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
        }
        fileReader.close();
    }

    /**
     * To string method shows data and controls for test methods
     * @return Information about current data
     */
    public String toString(){
        StringBuilder show = new StringBuilder();

        show.append("CUSTOMER 1:\n");
        show.append(customer1Queue.toString());
        show.append("CUSTOMER 2:\n");
        show.append(customer2Queue.toString());
        show.append("CUSTOMER 3:\n");
        show.append(customer3Queue.toString());

        return(show.toString());
    }
}
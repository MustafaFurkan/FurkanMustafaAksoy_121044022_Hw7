/**
 * Created by Furkan on 24.04.2016.
 */
import java.util.*;

/** Class to simulate a queue of customers.
 *  @author Furkan Mustafa Aksoy
 * */

public class CustomerQueue {
    // Data Fields
    /**  The queue of customers. */
    private Queue < Customer > theQueue;

    /** The number of customers served. */
    private int numServed;

    /** The total time customers were waiting. */
    private int totalWait;

    /** The name of this queue. */
    private String queueName;

    /** The average arrival rate. */
    private double arrivalRate;

    /** Service time */
    private int serviceTime;

    /**
     * Get Service time
      * @return Service time
     */
    public int getServiceTime(){
        return serviceTime;
    }
    // Constructor
    /** Construct a customerQueue with the given name.
     @param queueName The name of this queue
     */
    public CustomerQueue(String queueName) {
        numServed = 0;
        totalWait = 0;
        this.queueName = queueName;
//        serviceTime = tService;
        theQueue = new LinkedList < Customer > ();
    }

    /** Return the number of customers served
     @return The number of customers served
     */
    public int getNumServed() {
        return numServed;
    }

    /** Return the total wait time
     @return The total wait time
     */
    public int getTotalWait() {
        return totalWait;
    }

    /** Return the queue name
     @return - The queu name
     */
    public String getQueueName() {
        return queueName;
    }

    /** Set the arrival rate
     @param arrivalRate the value to set
     */
    public void setArrivalRate(double arrivalRate) {
        this.arrivalRate = arrivalRate;
    }

    /** Determine if the customer queue is empty
     @return true if the customer queue is empty
     */
    public boolean isEmpty() {
        return theQueue.isEmpty();
    }

    /** Determine the size of the customer queue
     @return the size of the customer queue
     */
    public int size() {
        return theQueue.size();
    }

    /**
     *  Check if a new arrival has occurred.
     * @param clock The current simulated time
     * @param showAll Flag to indicate that detailed
     * @param data Current customer
     * @param type Type of customer for priority
     */
    public void checkNewArrival(int clock, boolean showAll, Customer data,int type) {
        if ((data.getArrivalTime() <= clock) && (data.inQueue == false)){
            theQueue.add(new Customer(clock, data.getserviceTime(),type));
            // set customer in queue
            data.inQueue = true;
        }
    }

    /** Update statistics.
     pre: The queue is not empty.
     @param clock The current simulated time
     @param showAll Flag to indicate whether to show detail
     @return Time customer is done being served
     */
    public int update(int clock, boolean showAll) {
        Customer nextcustomer = theQueue.remove();
        int timeStamp = nextcustomer.getArrivalTime();
        int wait = clock - timeStamp;
        totalWait += wait;
        numServed++;
        if (showAll) {
            System.out.println("Time is " + clock/60 + ":" + clock%60
                    + ": Serving "
                    + queueName
                    // Show Social Security Number each customer
                    //+ "( SSN = " + nextcustomer.getId() + " )"
                    + " with time stamp "
                    + timeStamp/60 + ":" + timeStamp%60);
        }
        return clock + nextcustomer.getserviceTime();
    }

    /**
     * To string method show data
     * @return Information about current data
     */
    public String toString(){
        StringBuilder show = new StringBuilder();

        show.append("Queue:\n");

        for (Customer customer : theQueue)
            show.append(customer + " ");
        show.append("\n");
        return(show.toString());
    }
}
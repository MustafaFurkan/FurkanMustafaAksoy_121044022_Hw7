/**
 * Created by Furkan on 24.04.2016.
 */
import java.util.*;

/** Class to simulate a queue of customers.
 *  @author Koffman & Wolfgang
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

    // Constructor
    /** Construct a customerQueue with the given name.
     @param queueName The name of this queue
     */
    public CustomerQueue(String queueName) {
        numServed = 0;
        totalWait = 0;
        this.queueName = queueName;
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

    /** Check if a new arrival has occurred.
     @param clock The current simulated time
     @param showAll Flag to indicate that detailed
     data should be output
     */
    public void checkNewArrival(int clock, boolean showAll) {
        if (Math.random() < arrivalRate) {
            theQueue.add(new Customer(clock));
            if (showAll) {
                System.out.println("Time is "
                        + clock + ": "
                        + queueName
                        + " arrival, new queue size is "
                        + theQueue.size());
            }
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
            System.out.println("Time is " + clock
                    + ": Serving "
                    + queueName
                    + " with time stamp "
                    + timeStamp);
        }
        return clock + nextcustomer.getProcessingTime();
    }

}
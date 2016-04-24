/**
 * Created by Furkan on 24.04.2016.
 */
import java.util.*;

/** A class to represent a customer.
 *  @author Koffman & Wolfgang
 * */

public class Customer {

    // Data Fields
    /** The ID number for this customer. */
    private int customerId;

    /** The time needed to process this customer. */
    private int processingTime;

    /** The time this customer arrives. */
    private int arrivalTime;

    /** The maximum time to process a customer. */
    private static int maxProcessingTime;

    /** The sequence number for customers. */
    private static int idNum = 0;

    /** Create a new customer.
     @param arrivalTime The time this customer arrives */
    public Customer(int arrivalTime) {
        this.arrivalTime = arrivalTime;
        processingTime = 1 + (new Random()).nextInt(maxProcessingTime);
        customerId = idNum++;
    }

    /** Get the arrival time.
     @return The arrival time */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /** Get the processing time.
     @return The processing time */
    public int getProcessingTime() {
        return processingTime;
    }

    /** Get the customer ID.
     @return The customer ID */
    public int getId() {
        return customerId;
    }

    /** Set the maximum processing time
     @param maxProcessTime The new value */
    public static void setMaxProcessingTime(int maxProcessTime) {
        maxProcessingTime = maxProcessTime;
    }
}


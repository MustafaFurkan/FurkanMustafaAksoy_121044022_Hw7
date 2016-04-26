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
    private int serviceTime;

    /** The time this customer arrives. */
    private int arrivalTime;

    /** The type of customer **/
    private int customerType;

    /** The maximum time to process a customer. */
    private static int maxserviceTime;

    /** The sequence number for customers. */
    private static int idNum = 0;

    public boolean inQueue;
    /**
     * Create a new customer.
     * @param arrivalTime The time this customer arrives
     * @param tService The time serve to this customer
     * @param type customer priority type
     */
    public Customer(int arrivalTime, int tService ,int type) {
        this.arrivalTime = arrivalTime;
//        serviceTime = 1 + (new Random()).nextInt(maxserviceTime);
        serviceTime = tService;
        customerId = idNum++;
        customerType = type;
        inQueue = false;
    }

    /** Get the arrival time.
     @return The arrival time */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /** Get the processing time.
     @return The processing time */
    public int getserviceTime() {
        return serviceTime;
    }

    /** Get the customer ID.
     @return The customer ID */
    public int getId() {
        return customerId;
    }

    /**
     * Get the customer Type
     * @return The customer type
     */
    public int getCustomerType(){
        return customerType;
    }

    /** Set the maximum processing time
     @param maxProcessTime The new value */
    public static void setMaxserviceTime(int maxProcessTime) {
        maxserviceTime = maxProcessTime;
    }
}


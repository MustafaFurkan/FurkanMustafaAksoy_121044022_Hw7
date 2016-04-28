/**
 * Created by Furkan on 24.04.2016.
 */
/** A class to represent a customer.
 *  @author Furkan Mustafa Aksoy
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

    /**
     * To string method show data
     * @return Information about current data
     */
    public String toString(){
        StringBuilder show = new StringBuilder();

        show.append("Customer " + getCustomerType());
        show.append("\nCustomer SSN = ");
        show.append(getId());
        show.append("\nArrival Time = ");
        show.append(getArrivalTime()/60 + ":" + getArrivalTime()%60);
        show.append("");
        return(show.toString());
    }
}

package com.federico;
import java.util.Date;

public class Ticket {

    private int priority;
    private String reporter; //Stores person or department who reported issue
    private String description;
    private Date dateReported;

    //STATIC Counter - accessible to all Ticket objects.
    //If any Ticket object modifies this counter, all Ticket objects will have the modified value
    //Make it private - only Ticket objects should have access
    private static int staticTicketIDCounter = 1;
    //The ID for each ticket - instance variable. Each Ticket will have it's own ticketID variable
    protected int ticketID;

    public Ticket(String desc, int p, String rep, Date date) {
        this.description = desc;
        this.priority = p;
        this.reporter = rep;
        this.dateReported = date;
        this.ticketID = staticTicketIDCounter;
        staticTicketIDCounter++;
    }

    protected int getTicketID() {
        return ticketID;
    }

    protected String getDescription() {
        return description;
    }
    protected int getPriority() {
        return priority;
    }

    protected boolean contains( String toDelete ) {
        String chain = toDelete;
        String issue = getDescription();

        // Works, but is not the best.
        //return haystack.toLowerCase().indexOf( needle.toLowerCase() ) > -1

        return issue.toLowerCase().contains(chain.toLowerCase());
    }

    public String toString(){
        return("ID= " + this.ticketID + " Issued: " + this.description + " Priority: " +               this.priority + " Reported by: "
                + this.reporter + " Reported on: " + this.dateReported);
    }

}

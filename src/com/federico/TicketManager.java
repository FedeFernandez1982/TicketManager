package com.federico;
import java.util.*;

public class TicketManager {

    public static void main(String[] args) {

        LinkedList<Ticket> ticketQueue = new LinkedList<Ticket>();

        Scanner scantask = new Scanner(System.in);

        while(true){

            System.out.println("1. Enter Ticket\n2. Delete Ticket by ID\n3. Delete Ticket by Issue" +
                    "\n4. Search by Name\n5. Display All Tickets\n6. Quit");



            int task = scantask.nextInt();

            if (task == 1) {
                //Call addTickets, which will let us enter any number of new tickets
                addTickets(ticketQueue);

            }else if (task == 2) {
                //delete a ticket
                deleteTicketbyID(ticketQueue);

            }else if (task == 3) {
                //delete a ticket
                deleteTicketbyIssue(ticketQueue);

            }else if (task == 4 ) {
                System.out.println("Under Construction");
                break;

            }else if (task == 5) {
                //printing ticket list
                printAllTickets(ticketQueue);

            }else if (task == 6) {
                //Quit. Future prototype may want to save all tickets to a file
                System.out.println("Quitting program");
                break;

            }else {

                System.out.println("The input is invalid. Closing program");
                break;

            }

        }

        scantask.close();
    }




    protected static void deleteTicketbyIssue(LinkedList<Ticket> ticketQueue) {
        printAllTickets(ticketQueue);   //display list for user

        if (ticketQueue.size() == 0) {    //no tickets!
            System.out.println("No tickets to delete!\n");
            return;
        }

        Scanner deleteScanner = new Scanner(System.in);
        System.out.println("Enter the string of text contained in the problem of the ticket to delete");
        String deleteIssue = deleteScanner.nextLine();

        //Loop over all tickets. Delete the one with this ticket issue
        boolean found = false;
        for (Ticket ticket : ticketQueue) {
            if (ticket.contains(deleteIssue)) {
                found = true;
                ticketQueue.remove(ticket);
                System.out.println(String.format("Ticket ID%d deleted", ticket.ticketID));
                break; //don't need loop any more.
            }
        }
        if (found == false) {
            System.out.println("Ticket Issue not found, no ticket deleted");

        }
        printAllTickets(ticketQueue);  //print updated list

    }

    //Move the adding ticket code to a method
    protected static void deleteTicketbyID(LinkedList<Ticket> ticketQueue) {
        printAllTickets(ticketQueue);   //display list for user

        if (ticketQueue.size() == 0) {    //no tickets!
            System.out.println("No tickets to delete!\n");
            return;
        }

        Scanner deleteScanner = new Scanner(System.in);
        boolean validate;
        int deleteID = deleteScanner.nextInt();

        do {
            System.out.println("Enter ID of ticket to delete");
            if (deleteScanner.hasNextInt()) {
                deleteID = deleteScanner.nextInt();
                validate = true;
            } else {
                System.out.println(("Please intoduce a valid number"));
                validate = false;
                deleteScanner.next();
            }
        } while (!(validate));//validate can only be true, thus breaking the loop if an int is entered

        //Loop over all tickets. Delete the one with this ticket ID
        boolean found = false;
        for (Ticket ticket : ticketQueue) {
            if (ticket.getTicketID() == deleteID) {
                found = true;
                ticketQueue.remove(ticket);
                System.out.println(String.format("Ticket %d deleted", deleteID));
                break; //don't need loop any more.
            }
        }
        if (found == false) {
            System.out.println("Ticket ID not found, no ticket deleted");
            //TODO – re-write this method to ask for ID again if not found
        }
        printAllTickets(ticketQueue);  //print updated list

    }

    //Move the adding ticket code to a method
    protected static void addTickets(LinkedList<Ticket> ticketQueue) {
        Scanner sc = new Scanner(System.in);
        boolean moreProblems = true;
        String description, reporter;
        Date dateReported = new Date(); //Default constructor creates date with current date/time
        int priority;

        while (moreProblems){
            System.out.println("Enter problem");
            description = sc.nextLine();
            System.out.println("Who reported this issue?");
            reporter = sc.nextLine();
            System.out.println("Enter priority of " + description);
            priority = Integer.parseInt(sc.nextLine());

            Ticket t = new Ticket(description, priority, reporter, dateReported);
            //ticketQueue.add(t);
            addTicketInPriorityOrder(ticketQueue, t);

            printAllTickets(ticketQueue);

            System.out.println("More tickets to add?");
            String more = sc.nextLine();
            if (more.equalsIgnoreCase("N")) {
                moreProblems = false;
            }
        }

    }
    protected static void addTicketInPriorityOrder(LinkedList<Ticket> tickets, Ticket newTicket){

        //Logic: assume the list is either empty or sorted

        if (tickets.size() == 0 ) {//Special case - if list is empty, add ticket and return
            tickets.add(newTicket);
            return;
        }

        //Tickets with the HIGHEST priority number go at the front of the list. (e.g. 5=server on fire)
        //Tickets with the LOWEST value of their priority number (so the lowest priority) go at the end

        int newTicketPriority = newTicket.getPriority();

        for (int x = 0; x < tickets.size() ; x++) {    //use a regular for loop so we know which element we are looking at

            //if newTicket is higher or equal priority than the this element, add it in front of this one, and return
            if (newTicketPriority >= tickets.get(x).getPriority()) {
                tickets.add(x, newTicket);
                return;
            }
        }

        //Will only get here if the ticket is not added in the loop
        //If that happens, it must be lower priority than all other tickets. So, add to the end.
        tickets.addLast(newTicket);
    }
    protected static void printAllTickets(LinkedList<Ticket> tickets) {
        System.out.println(" ------- All open tickets ----------");

        for (Ticket t : tickets ) {
            System.out.println(t); //Write a toString method in Ticket class
            //println will try to call toString on its argument
        }
        System.out.println(" ------- End of ticket list ----------");

    }
}

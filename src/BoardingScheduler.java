////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           p09 Boarding Scheduler
//Files:           none
//Course:          CS 300 spring 2018
//
//Author:          Omjaa Rai
//Email:           orai@wisc.edu
//Lecturer's Name: Mouna Ayari Ben Hadj Kacem
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:
//Partner Email:
//Lecturer's Name:
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//__ Write-up states that pair programming is allowed for this assignment.
//__ We have both read and understand the course Pair Programming Policy.
//__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully
//acknowledge and credit those sources of help here.  Instructors and TAs do
//not need to be credited here, but tutors, friends, relatives, room mates
//strangers, etc do.  If you received no outside help from either type of
//source, then please explicitly indicate NONE.
//
//Persons:         none
//Online Sources:  none
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *The boardingScheduler class can scan a file of incoming passengers and assign
 *them in an efficient order to board a plane
 */
public class BoardingScheduler {
    private int time;
    private static Passenger temp;
    private static Passenger temp2;
    private static boolean lastDequeued;
    private ArrayList<Passenger> ps;
    private BoardingHeap heap;
    
    public BoardingScheduler() {
        ps = new ArrayList<Passenger>();
        time = 0;
        heap = new BoardingHeap();
    }
    

    /**
     * schedules and prints schedule of passengers boarding plane
     * @param passengers an iterator to supply passengers as they arrive
     * @param startTime time signiture when passengers start boarding
     */
    public static void boardFlight(Iterator<Passenger> passengers, int startTime) {
        BoardingScheduler s = new BoardingScheduler();
        lastDequeued = false;
        System.out.println("" + startTime + " Boarding begins");
        if(passengers.hasNext()) {
            temp = passengers.next();
        }
        while ((passengers.hasNext() || !s.heap.isEmpty() || !lastDequeued) && s.time<40) {
            if (!(!passengers.hasNext() && temp.isQueued())) {
                boolean correctTime = true;
                if (temp.getT() > s.time) {
                    correctTime = false;
                }
                while (correctTime) {
        
                //tries to enqueue any new passengers that have arrived at current time
                    if (temp.getT() > s.time) {
                        correctTime = false;
                        break;
                    } else {
                        temp.setDoneTimeEstimate(s.calculateDoneTimeEstimate(temp));
            
            
                        s.heap.enqueue(temp);
                        temp.queue();
                    }
                    if (passengers.hasNext()) {
                        temp = passengers.next();
                    }
                    else {
                        correctTime = false;
                        break;
                    }
                }
            }
            // dequeues one passenger if possible
            if ((!s.heap.isEmpty()) && (s.time >= startTime)) {
                temp2 = s.heap.dequeue();
                temp2.setBDT(s.calculateDoneTimeEstimate(temp2));
                s.ps.add(temp2);
                
                           }
            if(!passengers.hasNext() && s.heap.isEmpty() && s.ps.contains(temp)) {
                lastDequeued = true;
            }
            s.time++;
        }
        //prints time when all passengers are seated
        int maxTime = 0;
        for(int i = 0; i < s.ps.size(); i++) {
            if(s.ps.get(i).getBDT()>maxTime) {
                maxTime = s.ps.get(i).getBDT();
            }
        }
        maxTime++;
        System.out.println("\n" + maxTime + " All passengers have boarded!");
    }
    
    /**
     * Reads in a file containing a list of flight passengers in the order they
     * check in and runs the boardFlight() method with those passengers.
     * @author Tina, Alexi
     * @param flight is the name of the input file in the project directory
     */
    public static void checkIn(String flight) {
        File f = new File(flight);
        try {
            Scanner s = new Scanner(f);
            List<Passenger> passengers = new ArrayList<Passenger>();
            while(s.hasNextLine()) {
                //Data are separated by commas and possibly also whitespace.
                String[] line = s.nextLine().split("\\s*,\\s*");
                if (line.length == 3) //Default preferredBoarding 0 constructor
                    passengers.add(new Passenger(line[0],
                            Integer.parseInt(line[1]),
                            line[2]));
                else //Use the preferredBoarding number if given
                    passengers.add(new Passenger(line[0],
                            Integer.parseInt(line[1]),
                            line[2],
                            Integer.parseInt(line[3])));
            }
            s.close(); 
            boardFlight(passengers.iterator(), 0);
        } catch (IOException e) {
            System.out.println("Error: Unable to load file " + flight);
        }
    }
    

    /**
     * calculates the time that a passenger c   ould be seated by if they tried now
     * @param p passenger to estimate for
     * @return time signature
     */
    public int calculateDoneTimeEstimate(Passenger p) {
        int timeEst = time + 5;
        for(int i = 0; i < ps.size(); i++) {
            if(ps.get(i).getSN() <= p.getSN()) {
                if(ps.get(i).getBDT() > timeEst-5) {
                    timeEst = ps.get(i).getBDT() + 5;
                }
            }
        }
        return timeEst;
    }
    
    public static void main(String[] args) {
        checkIn("flight.txt");
    }
}

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
/**
 *The Passenger class represents a single passenger, including all information about that passenger
 *
 */

public class Passenger implements Comparable<Passenger> {
    private String name;
    private int time;
    private String seat;
    private int seatNum;
    private int preferredBoarding;
    private int doneTimeEstimate;
    private int boardingDoneTime;
    private boolean queued;
    
    public Passenger(String name, int time, String seat) {
        this.name = name;
        this.time = time;
        this.seat = seat;
        this.seatNum = Integer.parseInt(seat.substring(0, seat.length()-1));
        preferredBoarding = 0;
        queued = false;
    }
    public Passenger(String name, int time, String seat, int preferredBoarding) {
        this.name = name;
        this.time = time;
        this.seat = seat;
        this.seatNum = Integer.parseInt(seat.substring(0, seat.length()-1));
        this.preferredBoarding = preferredBoarding;
        queued = false;
    }
    //gets arrival time
    public int getT() {
        return time;
    }
    //gets name
    public String getN() {
        return name;
    }
    //gets seat number
    public int getSN() {
        return seatNum;
    }
   
    //gets seat string
    public String getS() {
        return seat;
    }
   
    //gets preferredBoarding number
    public int getPB() {
        return preferredBoarding;
    }
    
    //gets doneTimeEstimate
    public int getDoneTimeEstimate() {
        return doneTimeEstimate;
    }
    
  //sets estimate of doneTime
    public void setDoneTimeEstimate(int estimate) {
        doneTimeEstimate = estimate;
    }
    //sets done time
    public void setBDT(int dt) {
        boardingDoneTime = dt;
    }
    //gets done time
    public int getBDT() {
        return boardingDoneTime;
    }
    //gets wheather passenger has been queued
    public boolean isQueued() {
        return queued;
    }
    //marks passenger as queued
    public void queue() {
        queued = true;
    }
    

    /**
     * compares two passengers and determines their relative priority
     * @param other second passenger to compare to
     * @return positive # if main passenger is higher priority, 0 if 
     * they are the same, and negative # if main is lower
     */
    public int compareTo(Passenger other) {
        if(this.preferredBoarding != other.getPB()) {
            return this.preferredBoarding - other.getPB();
        }
        else {
            return other.getDoneTimeEstimate() - this.doneTimeEstimate;
        }
    }
    
    public String toString() {
        return "" + name + time + seat;
    }
}

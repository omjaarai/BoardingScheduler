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
 *The BoardingHeap class has a linear priority heap of passengers that is
 *sorted as they are enqueued
 */
public class BoardingHeap {
    private Passenger[] heap; //array of passengers currently in the heap
    private int size; //amount of passengers in the heap

    public BoardingHeap() {
        heap = new Passenger[10];
        size = 0;
    }
    

    /**
     * increases size of heap array when necessary
     */
    private Passenger[] expandArr() {
        Passenger[] nHeap = new Passenger[heap.length * 2];
        for(int i = 1; i <= size; i++) {
            nHeap[i]=heap[i];
        }
        return nHeap;
    }
    
    

    /**
     * inserts passenger into the heap according to their priority
     * @param p passenger to insert
     */
    public void enqueue(Passenger p) {
        boolean foundPlace = false;
        int place = size;
        while(!foundPlace) {    //finds place to insert p
            if(place == 0) {
                place = 1;
                foundPlace = true;
            }
            else if(p.compareTo(heap[place])<=0) {
                place++;
                foundPlace = true;
            }
            else{
                place--;
            }
        }
        //once place is found, moves back any necessary other nodes, and inserts p
        if(size + 2 >= heap.length) {
            heap = expandArr();
        }
    //  System.out.println("Found place " + place + " for " + p.toString());
        for(int i = size + 1; i > place; i--) {
            heap[i] = heap[i-1];
        }
        heap[place] = p;
        size++;
        
    }
    
    

    /**
     * removes the highest priority passenger out and moves all others up
     * @return the passenger that was taken out
     */
    public Passenger dequeue() {
        Passenger temp = heap[1];
        for(int i = 1; i <= size; i++) {
            heap[i] = heap[i+1];
        }
        size--;
        return temp;
    }
    
    //returns true if heap is empty
    public boolean isEmpty() {
        return (size==0);
    }
}

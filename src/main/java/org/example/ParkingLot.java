package org.example;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ParkingLot {
    private static final int TotalSpots = 4;
    private static final Semaphore parkingSpots = new Semaphore(TotalSpots);
    private static int totalCarsServed = 0;
    private static final int[] gateCarsServed = {0, 0, 0};
    private static final Lock logLock = new ReentrantLock();
    private static int currentParked = 0;

    /////////////////////(Getters)//////////////////////////
    public static Semaphore getParkingSpots() {//Getter for available parking spots
        return parkingSpots;
    }

    public static int getTotalCarsServed() {//Getter for served cars
        return totalCarsServed;
    }

    public static int getCurrentParked() {//Getter for the current parked cars
        return currentParked;
    }

    public static Lock getLogLock() {
        return logLock;
    }

    public static int[] getGateCarsServed() {//Getter for the array that carries the gates servings
        return gateCarsServed;
    }

    ///////////////////////////////(Incrementing and Decrementing functions) ///////////////////////////////////
    public static synchronized void incrementTotalServed() {//incrementing function to increment when a car gets served
        totalCarsServed++;
    }

    public static synchronized void incrementCurrentParked() {//incrementing the current parking cars in the parking lot
        currentParked++;
    }

    public static synchronized void decrementCurrentParked() {//decrementing the current parking cars in the parking lot
        currentParked--;
    }

    public static synchronized void incrementGateCarsServed(int gateId) {//incrementing the specific gate that served a specific car
        gateCarsServed[gateId - 1]++;
    }


}

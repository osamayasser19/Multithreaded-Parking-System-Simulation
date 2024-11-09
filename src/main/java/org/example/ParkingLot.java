package org.example;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ParkingLot {
    private static final int TotalSpots = 4;
    private static final Semaphore parkingSpots = new Semaphore(TotalSpots);
    private static int totalCarsServed = 0;
    private static int[] gateCarsServed = {0, 0, 0};
    private static Lock logLock = new ReentrantLock();
    private static int currentParked = 0;

    public static int getTotalSpots() {
        return TotalSpots;
    }

    public static Semaphore getParkingSpots() {
        return parkingSpots;
    }

    public static int getTotalCarsServed() {
        return totalCarsServed;
    }

    public static int getCurrentParked() {
        return currentParked;
    }

    public static synchronized void incrementTotalServed() {
        totalCarsServed++;
    }

    public static synchronized void incrementCurrentParked() {
        currentParked++;
    }

    public static synchronized void decrementCurrentParked() {
        currentParked--;
    }

    public static synchronized void incrementGateCarsServed(int gateId) {
        gateCarsServed[gateId - 1]++;
    }

    public static Lock getLogLock() {
        return logLock;
    }

    public static int[] getGateCarsServed() {
        return gateCarsServed;
    }

}

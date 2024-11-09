package org.example;

import java.util.concurrent.Semaphore;


public class ParkingLot {
    private static final int TotalSpots = 4;
    private static final Semaphore parkingSpots = new Semaphore(TotalSpots);
    private static int totalCarsServed = 0;

    public static int getTotalSpots() {
        return TotalSpots;
    }

    public static Semaphore getParkingSpots() {
        return parkingSpots;
    }

    public static int getTotalCarsServed() {
        return totalCarsServed;
    }

    public static synchronized void incrementTotalCarsServed() {
        totalCarsServed++;
    }

}

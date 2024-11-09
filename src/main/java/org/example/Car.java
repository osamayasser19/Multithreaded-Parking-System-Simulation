package org.example;

import java.util.concurrent.TimeUnit;


public class Car implements Runnable {
    private final String carID;
    private final int parkDuration;

    public Car(String carID, int parkDuration) {
        this.carID = carID;
        this.parkDuration = parkDuration;
    }

    @Override
    public void run() {
        try {
            System.out.println(carID + "  arrived at time.");

            // Acquire a parking spot
            ParkingLot.getParkingSpots().acquire();
            ParkingLot.incrementTotalCarsServed();

            System.out.println(carID + " parked. (Parking Status: " + (ParkingLot.getTotalSpots() - ParkingLot.getParkingSpots().availablePermits()) + " spots occupied)");

            // Simulate staying in the parking lot
            TimeUnit.SECONDS.sleep(parkDuration);

            // Release the parking spot and log exit
            System.out.println(carID + " is left after " + parkDuration + " units of time. (Parking Status: " + (ParkingLot.getTotalSpots()- ParkingLot.getParkingSpots().availablePermits() + 1) + " spots occupied)");
            ParkingLot.getParkingSpots().release();

        } catch (InterruptedException e) {
            System.out.println(carID + " was interrupted.");
        }
    }
}


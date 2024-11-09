package org.example;

import java.util.concurrent.TimeUnit;


public class Car implements Runnable {
    private final String carID;
    private final int parkDuration;
    private final int arrivalTime;
    private final int gateId;

    public Car(String carID, int arrivalTime, int parkDuration, int gateId) {
        this.carID = carID;
        this.parkDuration = parkDuration;
        this.arrivalTime = arrivalTime;
        this.gateId = gateId;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(arrivalTime);


            ParkingLot.getLogLock().lock();
            System.out.println(carID + " from Gate " + gateId + " arrived at time " + arrivalTime);
            ParkingLot.getLogLock().unlock();

            boolean spotAvailable = ParkingLot.getParkingSpots().tryAcquire();

            if (!spotAvailable) {

                ParkingLot.getLogLock().lock();
                System.out.println(carID + " from Gate " + gateId + " waiting for a spot.");
                ParkingLot.getLogLock().unlock();

                long waitStartTime = System.currentTimeMillis();// Start waiting here when the car is about to block
                ParkingLot.getParkingSpots().acquire(); // Block until a spot becomes available
                long waitTime = (System.currentTimeMillis() - waitStartTime) / 1000; // Calculate actual wait time
                System.out.println(waitTime);
                ParkingLot.incrementCurrentParked();// Increment current parked cars
                ParkingLot.incrementTotalServed();    // Increment total served cars
                ParkingLot.incrementGateCarsServed(gateId); // Increment cars served by the gate

                ParkingLot.getLogLock().lock();
                System.out.println(carID + " from Gate " + gateId + " parked after waiting for " + waitTime + " units of time. (Parking Status: " + ParkingLot.getCurrentParked() + " spots occupied)");
                ParkingLot.getLogLock().unlock();

            } else {

                ParkingLot.incrementCurrentParked();  // Increment current parked cars
                ParkingLot.incrementTotalServed();    // Increment total served cars
                ParkingLot.incrementGateCarsServed(gateId); // Increment cars served by the gate

                ParkingLot.getLogLock().lock();
                System.out.println(carID + " from Gate " + gateId + " parked. (Parking Status: " + ParkingLot.getCurrentParked() + " spots occupied)");
                ParkingLot.getLogLock().unlock();

            }


            // Simulate staying in the parking lot
            TimeUnit.SECONDS.sleep(parkDuration);

            // Release the parking spot and log exit
            ParkingLot.getParkingSpots().release();
            ParkingLot.decrementCurrentParked();  // Decrement current parked cars

            ParkingLot.getLogLock().lock();
            System.out.println(carID + " from Gate " + gateId
                    + " left after " + parkDuration
                    + " units of time. (Parking Status: " + ParkingLot.getCurrentParked() + " spots occupied)");
            ParkingLot.getLogLock().unlock();

        } catch (InterruptedException e) {
            System.out.println(carID + " from Gate " + gateId + " was interrupted.");
        }
    }
}


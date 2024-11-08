// package org.example;
// //hello
// public class Main {
//     public static void main(String[] args) {

//     }
// }
package org.os;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ParkingLot {
    private static final int TotalSpots = 4;
    private static final Semaphore parkingSpots = new Semaphore(TotalSpots);
    private static int totalCarsServed = 0;

    static class Car implements Runnable {
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
                parkingSpots.acquire();
                totalCarsServed++;

                System.out.println(carID + " parked. (Parking Status: " + (TotalSpots - parkingSpots.availablePermits()) + " spots occupied)");

                // Simulate staying in the parking lot
                TimeUnit.SECONDS.sleep(parkDuration);

                // Release the parking spot and log exit
                System.out.println(carID + " is left after " + parkDuration + " units of time. (Parking Status: " + (TotalSpots - parkingSpots.availablePermits() + 1) + " spots occupied)");
                parkingSpots.release();

            } catch (InterruptedException e) {
                System.out.println(carID + " was interrupted.");
            }
        }
    }

    static class Gate {
        private final String gateName;

        public Gate(String gateName) {
            this.gateName = gateName;
        }

        public void handleCarArrival(String carID, int parkDuration) {
            Car car = new Car(carID, parkDuration);
            new Thread(car).start();
        }
    }

    public static void main(String[] args) {
        // Create gates
        Gate gate1 = new Gate("Gate 1");
        Gate gate2 = new Gate("Gate 2");
        Gate gate3 = new Gate("Gate 3");

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String gateName = parts[0];
                String carId = parts[1];
                int arriveTime = Integer.parseInt(parts[2].split(" ")[1]);
                int parkDuration = Integer.parseInt(parts[3].split(" ")[1]);

                // Simulate arrival time using sleep
                TimeUnit.SECONDS.sleep(arriveTime);

                // Handle car arrival based on the gate
                switch (gateName) {
                    case "Gate 1":
                        gate1.handleCarArrival(carId + " from " + gateName, parkDuration);
                        break;
                    case "Gate 2":
                        gate2.handleCarArrival(carId + " from " + gateName, parkDuration);
                        break;
                    case "Gate 3":
                        gate3.handleCarArrival(carId + " from " + gateName, parkDuration);
                        break;
                    default:
                        System.out.println("Unknown gate: " + gateName);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(30000); // Wait for 30 seconds for all threads to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Total Cars Served: " + totalCarsServed);
    }
}

package org.example;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Create gates
        Gate gate1 = new Gate("Gate 1");
        Gate gate2 = new Gate("Gate 2");
        Gate gate3 = new Gate("Gate 3");

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
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

        System.out.println("Total Cars Served: " + ParkingLot.getTotalCarsServed());
    }
}

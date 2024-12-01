package org.example;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        Gate gate1 = new Gate(1);
        Gate gate2 = new Gate(2);
        Gate gate3 = new Gate(3);

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                String[] parts = line.split(", ");//parts[0]=Gate #, parts[1]=Car #, parts[2]=Arrive #, parts[3]=Parks #
                int gateID = Integer.parseInt(parts[0].split(" ")[1]);//{Gate,#}
                String carId = parts[1];//Car #
                int arriveTime = Integer.parseInt(parts[2].split(" ")[1]);//{Arrive,#}
                int parkDuration = Integer.parseInt(parts[3].split(" ")[1]);//{Parks,#}

                // Handle car arrival based on the gate
                switch (gateID) {
                    case 1:
                        gate1.handleCarArrival(carId, arriveTime, parkDuration, gateID);
                        break;
                    case 2:
                        gate2.handleCarArrival(carId, arriveTime, parkDuration, gateID);
                        break;
                    case 3:
                        gate3.handleCarArrival(carId, arriveTime, parkDuration, gateID);
                        break;
                    default:
                        System.out.println("Unknown gate: " + gateID);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(30000); // Wait for 30 seconds for all threads to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ParkingLot.getLogLock().lock();
        try {
            System.out.println("\nTotal Cars Served: " + ParkingLot.getTotalCarsServed());
            System.out.println("Current Cars in Parking: " + ParkingLot.getCurrentParked());
            System.out.println("Details:");
            for (int i = 0; i < ParkingLot.getGateCarsServed().length; i++) {
                System.out.println("- Gate " + (i + 1) + " served " + ParkingLot.getGateCarsServed()[i] + " cars.")
                ;
            }
        } finally {
            ParkingLot.getLogLock().unlock();
        }

    }
}


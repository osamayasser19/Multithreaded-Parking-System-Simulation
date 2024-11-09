package org.example;

public class Gate {
    private final String gateName;

    public Gate(String gateName) {
        this.gateName = gateName;
    }

    public void handleCarArrival(String carID, int parkDuration) {
        Car car = new Car(carID, parkDuration);
        new Thread(car).start();
    }
}

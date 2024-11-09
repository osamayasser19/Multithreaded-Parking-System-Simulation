package org.example;

public class Gate {
    private final int gateId;

    public Gate(int gateName) {
        this.gateId = gateName;
    }

    public int getGateId() {
        return this.gateId;
    }

    public void handleCarArrival(String carId, int arriveTime, int parkDuration, int gateId) {
        Car car = new Car(carId, arriveTime, parkDuration, gateId);
        new Thread(car).start();
    }
}

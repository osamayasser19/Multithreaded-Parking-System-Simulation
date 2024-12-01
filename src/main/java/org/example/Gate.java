package org.example;

public class Gate {
    private final int gateId;

    public Gate(int gateId) {
        this.gateId = gateId;
    }

    //Function that handles the threads
    public void handleCarArrival(String carId, int arriveTime, int parkDuration, int gateId) {
        Car car = new Car(carId, arriveTime, parkDuration, gateId);//making a new thread of type car
        new Thread(car).start();//running the thread
    }
}

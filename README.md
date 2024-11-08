Multithreaded Parking System Simulation
This project is a Java-based simulation of a multithreaded parking system, designed to model real-world parking lot dynamics with concurrent car arrivals, limited parking spots, and multiple entry gates. The system leverages semaphores and threads to synchronize access to the parking spots, managing multiple cars arriving at different gates and handling concurrent processes efficiently.

Project Overview
The parking system operates with:

Parking Capacity: A fixed number of parking spots available.
Multiple Gates: Three entry gates that handle car arrivals independently.
Concurrency Management: Each car is represented by a thread that attempts to park upon arrival and waits if no spots are available.
Status Reporting: Logs car activities and provides a final summary of cars served, including statistics per gate.
Key Features
Thread Synchronization: Uses threading and semaphores to control parking spot access, preventing conflicts when multiple cars arrive simultaneously.
Realistic Simulation: Simulates cars arriving at specified times, parking for a set duration, and leaving when their time is up. If all spots are occupied, cars wait until a spot becomes free.
Detailed Logging: Tracks and reports each car's entry, parking status, and departure, along with any wait times when spots are full.
End-of-Simulation Summary: Provides a complete report of the total number of cars served, the number still parked (if any), and a breakdown by gate.
System Requirements
Programming Language: Java
Concurrency Control: Managed with Java threading and semaphore constructs to handle spot allocation and prevent race conditions.
Input and Output
The system reads a car schedule from an input file specifying car arrivals, parking durations, and gate assignments. Each car is associated with a specific gate, arrival time, and parking duration, allowing the simulation to model realistic staggered arrivals and departures.

The program outputs:

Real-Time Status Updates: Logs each car's arrival, parking, waiting (if applicable), and departure.
Summary Statistics: Provides the total number of cars served, including per-gate details and final occupancy status.
Usage Instructions
Prepare Input Data: Create a text file listing car arrivals, durations, and gates as per the specified format.
Run the Simulation: Execute the Java program, specifying the input file path if required.
Review Logs and Summary: Observe console logs for real-time updates and review the summary output for the simulation's final report.
This simulation serves as a practical demonstration of multithreading, synchronization, and concurrency in an operating system context, effectively showcasing Java’s threading capabilities in a controlled environment.


1. Parking Lot Setup and Semaphore Management

Tasks:

Set up a parking lot with 4 parking spots.

Implement a semaphore to manage these spots and prevent race conditions.

Ensure that only 4 cars are parked simultaneously.


Easy Part: Setting up the initial parking lot structure and creating the semaphore.

Challenging Part: Managing the semaphore effectively so that it prevents race conditions in a multithreaded environment.


2. Gate and Car Threads Implementation

Tasks:

Create separate threads to simulate the car arrivals for each of the three gates.

Ensure each car is represented by a thread that attempts to park, stays for a duration, and then exits.


Easy Part: Creating the threads and assigning them to each gate.

Challenging Part: Synchronizing thread activities, especially when multiple cars try to enter the limited spots simultaneously.


3. Timing and Simulation of Car Arrivals and Parking Duration

Tasks:

Use sleep functions to simulate arrival times and parking durations as defined in the input file.

Read and parse the car arrival schedule from an input text file.


Easy Part: Using sleep() for timing.

Challenging Part: Parsing the input file accurately and handling any edge cases where the timing may create conflicts.


4. Logging, Reporting, and Final Status Output

Tasks:

Log each car’s actions (arrival, parking, waiting, departure).

Report the number of cars currently in the parking lot and the total number served at the end of the simulation.


Easy Part: Creating the basic log messages and setting up counters.

Challenging Part: Ensuring logs are accurate in a concurrent environment and managing the final status report to reflect accurate counts.


By dividing these parts, each team member can focus on a specific functionality, making it manageable.

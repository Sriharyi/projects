package airtraffic_management;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AirController {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Flight> flights = new ArrayList<>();
    private ArrayList<Runway> runways = new ArrayList<>();

    public AirController() {
        Flight f1 = new Flight(1, "ATR", 12, 30);
        Flight f2 = new Flight(2, "Airbus", 20, 40);
        Flight f3 = new Flight(3, "Boeing", 40, 50);
        Flight f4 = new Flight(4, "Cargo", 100, 60);
        flights.addAll(Arrays.asList(f1, f2, f3, f4));

        Runway r1 = new Runway(1, "r1", true, 40);
        Runway r2 = new Runway(2, "r2", true, 60);
        Runway r3 = new Runway(3, "r3", true, 80);
        Runway r4 = new Runway(4, "r4", true, 90);
        runways.addAll(Arrays.asList(r1, r2, r3, r4));
    }

    public void start() {
        getChoice();
    }

    private void getDetails() {
        System.out.println("Enter your Flight:");
        for (Flight f : flights) {
            System.out.println(f.getfId() + "." + f.getFlightname());
        }

        int choice = sc.nextInt();
        while (choice > flights.size()) {
            System.out.println("Invalid selection...");
            System.out.println("Choose only valid flights...");
            choice = sc.nextInt();
        }

        System.out.println("Enter the weight:");
        int weight = sc.nextInt();
        int computeTime = 0;

        for (Flight f : flights) {
            if (f.getfId() == choice) {
                System.out.println(f.getfId() + "." + f.getFlightname() + "." + f.getHalttime());
                computeTime = f.compute(weight);
                break;
            }
        }

        computeTime += 10;
        System.out.println("compute time:" + computeTime);

        synchronized (runways) {
            Runway availableRunway = null;

            for (Runway r : runways) {
                if (computeTime <= r.getRunwaytime() && r.isFree()) {
                    availableRunway = r;
                    break;
                }
            }

            if (availableRunway != null) {
                availableRunway.setFree(false);
                System.out.println("Runway " + availableRunway.getRunwayid() + " is Allocated");
                Request req = new Request(availableRunway, computeTime);
                req.start();
            } else {
                System.out.println("No runway is available at the moment, please try again later.");
            }
        }
    }

    private void getChoice() {
        while (true) {
            System.out.println("1. Take off");
            System.out.println("2. Landing");
            System.out.println("3. Emergency Landing");
            System.out.println("4. Show Runways");
            System.out.println("5. Exit");
            System.out.println("Enter your choice:");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                case 2:
                case 3:
                    getDetails();
                    break;
                case 4:
                    getStatus();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    private void getStatus() {
		for(Runway r:runways) {
			if(r.isFree())
			{
				System.out.println("the Runway "+r.getRunwayid()+" is free.");
			}
			else
			{
				System.out.println("the Runway "+r.getRunwayid()+" is not free.");
			}
		}
	}
}

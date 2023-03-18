package taxiUtil;

import java.util.*;

public class taxibook extends Thread {
	static Scanner sc = new Scanner(System.in);
	static int bookid = 1;
	static ArrayList<taxi> taxies = new ArrayList<taxi>();
	taxibook(){
		for(int i=1;i<=4;i++){
			taxi t = new taxi(i);
			taxies.add(t);
		}
		getDetails();	
	}
	private static void getDetails(){
		System.out.println("------Taxi Booking------");
		System.out.println("1.Book Taxi  ");
		System.out.println("2.Taxi Details  ");
		System.out.println("3.Taxies Status ");
		System.out.println("4.Exit ");
		System.out.println("Enter your choice : ");
		int ch = sc.nextInt();
		switch(ch){
			case 1:{
				bookTaxi();
				getDetails();
				break;
			}
			case 2:{
				taxiDetails();
				getDetails();
				break;
			}
			case 3:{
				taxiStatus();
				getDetails();
				break;
			}
			case 4:{
				return;
			}
			default:{
				System.out.println("Enter valid choice :");
				getDetails();
				break;
			}
		}
	}
	private static void bookTaxi() {
		System.out.println("Enter Customer Id:");
		int cutomerid = sc.nextInt();
		System.out.println("Enter Pickup Point:");
		char pickup =sc.next().charAt(0);
		System.out.println("Enter Drop Point:");
		char drop = sc.next().charAt(0);
		System.out.println("Enter Pickup Time:");
		int pickuptime = sc.nextInt();
		boolean reject = true;
		for(taxi t : taxies)
		{
			if(t.isFree && t.currentPoint==pickup)
			{
				t.assign(bookid, cutomerid, pickup, drop, pickuptime);
				bookid++;
				taxiHold th = new taxiHold(t);
				th.start();
				try {
					Thread.sleep(1000);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return;
			}
			if(reject && t.isFree)
			{
				reject = false;
			}
		}
		if(reject) {
			System.out.println(" sorry No taxi is Available Now :-( ");
			System.out.println("sorry your booking is rejected ");
			return;
		}
		
		PriorityQueue<taxi> nearesttaxies = new PriorityQueue<>(new Comparator<taxi>() {

			@Override
			public int compare(taxi o1, taxi o2) {
				if(Math.abs(o1.currentPoint-pickup)==Math.abs(o2.currentPoint-pickup))
					return (int) (o1.totalEarn - o2.totalEarn);
				return Math.abs(o1.currentPoint-pickup)-Math.abs(o2.currentPoint-pickup);
			}
		});
		
		for(taxi t : taxies) {
			if(t.isFree)
			{
				nearesttaxies.add(t);
			}
		}
		if(nearesttaxies.isEmpty()) {
			if(nearesttaxies.peek().isFree)
			{
				nearesttaxies.peek().assign(bookid, cutomerid, pickup, drop, pickuptime);
				bookid++;
				taxiHold th = new taxiHold(nearesttaxies.peek());
				th.start();
				try {
					Thread.sleep(1000);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return;
			}
		}
		
	}
	private static void taxiDetails() {
		for(taxi t:taxies){
			System.out.println(" ");
			System.out.print("Taxi id "+t.id);
			System.out.print("\t\t Total Earnings "+t.totalEarn);
			System.out.println(" ");
			System.out.println("BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
			for(int i=0;i<t.bookingId.size();i++){
				System.out.print(t.bookingId.get(i));
				System.out.print("            "+t.customerId.get(i));
				System.out.print("             "+t.pickupPoint.get(i));
				System.out.print("       "+t.dropPoint.get(i));
				System.out.print("     "+t.pickUpTime.get(i));
				System.out.print("             "+t.dropTime.get(i));
				System.out.println("           "+t.amount.get(i));
			}
			System.out.println(" ");
		}
		
	}
	private static void taxiStatus() {
		for(taxi t:taxies){
			if(t.isFree==true)
				System.out.println("Taxi id:"+t.id+" is free( Available Now )");
			else
				System.out.println("Taxi id:"+t.id+" is busy ( Not Available Now ) ");
		}
	}
	
}

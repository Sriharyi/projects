package taxiUtil;
import java.util.*;
public class taxi extends Thread{
	int id;
	char currentPoint='A';
	double totalEarn=0.0;
	LinkedList<Integer> bookingId = new LinkedList<Integer>();
	LinkedList<Integer> customerId = new LinkedList<Integer>();
	LinkedList<Character>  pickupPoint = new LinkedList<Character>();
	LinkedList<Character> dropPoint = new LinkedList<Character>();
	LinkedList<Integer> pickUpTime = new LinkedList<Integer>();
	LinkedList<Integer> dropTime = new LinkedList<Integer>();
	LinkedList<Double> amount = new LinkedList<Double>();
	boolean isFree=true;
	taxi(int id){
		this.id=id;
	}
	void assign(int bookingid,int customerid,char pickup,char drop,int pickuptime)
	{
		this.bookingId.add(bookingid);
		this.customerId.add(customerid);
		this.pickupPoint.add(pickup);
		this.dropPoint.add(drop);
		this.pickUpTime.add(pickuptime);
		this.dropTime.add(pickuptime+Math.abs(pickup-drop));
		this.amount.add(100.0+((Math.abs(pickup-drop)*15)-5)*10);
		this.totalEarn+=this.amount.peekLast();
		System.out.println("Amount to pay:"+this.amount.peekLast());
		this.currentPoint=drop;
	}
}

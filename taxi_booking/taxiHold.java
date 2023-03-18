package taxiUtil;

public class taxiHold extends Thread {
	private taxi t;
	taxiHold(taxi t)
	{
		this.t=t;
	}
	public void run()
	{
		t.isFree = false;
		try {
			System.out.println("taxi id:"+t.id+" is Assigned");
//			Thread.sleep(Math.abs(t.pickupPoint.getLast()-t.dropPoint.getLast())*(60*(60*1000(1SEC))(60SEC OR 1MIN))(1HOUR OR 60 MIN));(60MIN FOR 15KM THE MINIMUM DISTANCE BETWEEEN ONE TO ANOTHER)
			Thread.sleep(60000);
			t.isFree =true;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

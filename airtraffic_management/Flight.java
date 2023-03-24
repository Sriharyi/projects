package airtraffic_management;

public class Flight extends Thread {
	private int flightid;
	private String flightname;
	private int maxweight;
	private int halttime;
	public Flight(int flightid, String fname,int maxw,int ht)
	{
		this.flightid = flightid;
		this.setFlightname(fname);
		this.maxweight = maxw;
		this.halttime = ht;
	}
	
	public int getfId() {
		return flightid;
	}
	public void setfId(int fid) {
		this.flightid = fid;
	}
	public String getFlightname() {
		return flightname;
	}
	public void setFlightname(String flightname) {
		this.flightname = flightname;
	}
	public int getMaxweight() {
		return maxweight;
	}
	public void setMaxweight(int maxweight) {
		this.maxweight = maxweight;
	}
	public int getHalttime() {
		return halttime;
	}
	public void setHalttime(int halttime) {
		this.halttime = halttime;
	}
	
	public int compute(int weight) {
		int weightperc =  (int) ((weight/(double)maxweight)*100);
		System.out.println(weightperc);
		if(weightperc>75)
		{
			return halttime;
		}
		else if(weightperc>50 && weightperc<=75)
		{
			return halttime -(int)(halttime*(10/100.0));
		}
		else {
			return halttime - (int)(halttime*(20/100.0));
		}
	}

}

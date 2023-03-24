package airtraffic_management;
public class Runway {
    private int runwayid;
    private String runwayname;
    private boolean isFree;
    private int runwaytime;
    private int runwaydistance;

    public Runway(int runwayid, String runwayname, boolean isFree, int runwaytime) {
        this.runwayid = runwayid;
        this.runwayname = runwayname;
        this.isFree = isFree;
        this.runwaytime = runwaytime;
    }

    public synchronized int getRunwayid() {
        return runwayid;
    }

    public synchronized void setRunwayid(int runwayid) {
        this.runwayid = runwayid;
    }

    public synchronized String getRunwayname() {
        return runwayname;
    }

    public synchronized void setRunwayname(String runwayname) {
        this.runwayname = runwayname;
    }

    public synchronized boolean isFree() {
        return isFree;
    }

    public synchronized void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    public synchronized int getRunwaytime() {
        return runwaytime;
    }

    public synchronized void setRunwaytime(int runwaytime) {
        this.runwaytime = runwaytime;
    }

    public synchronized int getRunwaydistance() {
        return runwaydistance;
    }

    public synchronized void setRunwaydistance(int runwaydistance) {
        this.runwaydistance = runwaydistance;
    }
}

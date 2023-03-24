package airtraffic_management;

public class Request extends Thread {
    private final Runway runway;
    private final int computeTime;

    public Request(Runway runway, int computeTime) {
        this.runway = runway;
        this.computeTime = computeTime;
        this.runway.setFree(false);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(computeTime * 1000);
            synchronized (runway) {
                runway.setFree(true);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


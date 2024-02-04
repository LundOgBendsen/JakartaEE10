package dk.lundogbendsen.concurrency;

public class PeriodicTask extends Task {
    private int counter;

    public PeriodicTask(String n) {
        super(n, "PERIODIC");
    }

    @Override
    public void run() {
        sendToWebService("started run #" + counter);
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
        }
        sendToWebService("finished run #" + (counter++));
    }

}


package dk.lundogbendsen.concurrency;

public class ImmediateTask extends Task {

    private int counter;

    public ImmediateTask(String n) {
        super(n, "IMMEDIATE");

    }

    @Override
    public void run() {
        sendToWebService("started");
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
        }

        sendToWebService("finished");
    }


}

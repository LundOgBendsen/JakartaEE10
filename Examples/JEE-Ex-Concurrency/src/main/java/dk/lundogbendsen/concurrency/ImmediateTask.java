
package dk.lundogbendsen.concurrency;

public class ImmediateTask extends Task {

    private int counter;

    public ImmediateTask(String n) {
        super(n, "IMMEDIATE");
        sendToWebService("started");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
        }

        sendToWebService("finished");
    }


}

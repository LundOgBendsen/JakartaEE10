package dk.lundogbendsen.concurrency;


/* The tasks just notify the JAX-RS web service in the EJB */
public class DelayedTask extends Task {
    public DelayedTask(String n) {
        super(n, "DELAYED");
        sendToWebService("submitted");
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

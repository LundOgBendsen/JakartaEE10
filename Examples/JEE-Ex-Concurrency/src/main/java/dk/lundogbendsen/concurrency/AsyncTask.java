
package dk.lundogbendsen.concurrency;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/* The tasks just notify the JAX-RS web service in the EJB */
public class AsyncTask extends Task {
    @Resource(name = "java:comp/DefaultManagedExecutorService")
    ManagedExecutorService mExecService;
    /* Inject the default managed scheduled executor from the app server */

    public AsyncTask(String n) {
        super(n, "ASYNC");
        sendToWebService("Started asynchronous computation");
    }

    @Override
    public void run() {
        Supplier<String> step1 = () -> {  // Supplier interface is a function with a result
            return "step1";
        };
        CompletableFuture.supplyAsync(step1, mExecService)
                .thenApply((r) -> {
                    return ", step 2";
                })
                .thenApplyAsync((r) -> {
                    return r + ", step 3";
                }, mExecService) // explicitly specify exec service
                .thenApply((r) -> {
                    if (new Random().nextBoolean()) // generate error with 50% probability
                        return r;
                    else throw new RuntimeException("Bad luck!");
                })
                .thenApply((r) -> {  // last step - delay
                    try {
                        Thread.sleep(1500);
                    } catch (Exception e) {
                    }
                    sendToWebService("Executed asynchronously: " + r); // send result of async computation
                    return null;
                })
                .exceptionally((e) -> { // handle exception
                    sendToWebService("Error in asynchronous execution: " + e.getMessage()); // send result of async computation
                    return null;
                });
    }

 }

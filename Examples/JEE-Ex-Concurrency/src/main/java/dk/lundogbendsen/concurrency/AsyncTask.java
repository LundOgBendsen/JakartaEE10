
package dk.lundogbendsen.concurrency;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/* The tasks just notify the JAX-RS web service in the EJB */
public class AsyncTask extends Task {
    public AsyncTask(String n) {
        super(n, "ASYNC");
    }

    @Override
    public void run() {
        Supplier<String> step1 = () -> {  // Supplier interface is a function with a result
            sendToWebService("Step1 executed in thread:" + Thread.currentThread().getName());
            return "step1";

        };
        sendToWebService("Before async computation supplied in thread:" + Thread.currentThread().getName());
        CompletableFuture.supplyAsync(step1)
                .thenApply((r) -> {  // step 2 is a lambda expression
                    sendToWebService("Step2 executed in thread:" + Thread.currentThread().getName());
                    return r + ", step 2";
                })
                .thenApply((r) -> {
                    if (new Random().nextBoolean()) // generate error with 50% probability
                        return r;
                    else throw new RuntimeException("Bad luck!");
                })
                .thenApply((r) -> {  // last step - delay
                    sendToWebService("Final result:" + r + " from thread:" + Thread.currentThread().getName()); // send final result of async computation
                    return null;
                })
                .exceptionally((e) -> { // handle exception
                    sendToWebService("Error in asynchronous execution: " + e.getMessage()); // send result of async computation
                    return null;
                });
        sendToWebService("After Async computation supplied");
    }
}



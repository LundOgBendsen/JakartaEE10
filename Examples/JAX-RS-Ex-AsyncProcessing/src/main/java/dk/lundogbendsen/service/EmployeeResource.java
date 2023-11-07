package dk.lundogbendsen.service;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import jakarta.enterprise.concurrent.ManagedThreadFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.CompletionCallback;
import jakarta.ws.rs.container.ConnectionCallback;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.container.TimeoutHandler;
import jakarta.ws.rs.core.MediaType;

@Path("/employees")
public class EmployeeResource {


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public void getEmployeeEntity(@Suspended final AsyncResponse ar,
                                  @PathParam("id") String id) throws NamingException {
        ar.setTimeoutHandler(new TimeoutHandler() {

            @Override
            public void handleTimeout(AsyncResponse ar) {
                ar.resume("Operation timed out");
            }
        });
        ar.setTimeout(4000, TimeUnit.MILLISECONDS);

        ar.register(new MyCompletionCallback());
        ar.register(new MyConnectionCallback());

        //This is a nifty java ee 7 feature
        ManagedThreadFactory threadFactory = (ManagedThreadFactory) new InitialContext()
                .lookup("java:comp/DefaultManagedThreadFactory");

        Executors.newSingleThreadExecutor(threadFactory).submit(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("Going to sleep");
                    Thread.sleep(3000);
                    System.out.println("Waking up");
                    Employee employee = new Employee();
                    employee.setId("42");
                    employee.setName("Roy Fielding");
                    ar.resume(employee);
                } catch (InterruptedException ex) {

                }
            }

        });
    }
}

class MyCompletionCallback implements CompletionCallback {

    @Override
    public void onComplete(Throwable t) {
        System.out.println("onComplete: " + t);
    }

}

class MyConnectionCallback implements ConnectionCallback {

    @Override
    public void onDisconnect(AsyncResponse ar) {
        System.out.println("onDisconnect");
    }

}

package dk.lundogbendsen.client;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.SseEventSource;



/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class SseClient {

    public static WebTarget target;

    public static void main(String[] args) {
    	System.out.println("start1");
        target = ClientBuilder.newClient().target("http://localhost:8080/JEE-Ex-JAXRS21-SSE-1.0-SNAPSHOT/resources/sse/subscribe");
        System.out.println("start2 ");
        consumeEventsViaSubscription();
        System.out.println("start3");
        



        WebTarget target = ClientBuilder.newClient()
                .target("http://localhost:8080/JEE-Ex-JAXRS21-SSE-1.0-SNAPSHOT/resources/sse/subscribe");
        System.out.println("start4");
        
        try (SseEventSource source = SseEventSource
                .target(target).build()) {
            source.register(System.out::println);
            System.out.println("start5");
            
            source.open();
        }


    }

    private static void consumeEventsViaSubscription() {
        
    	target = ClientBuilder.newClient().target("http://localhost:8080/JEE-Ex-JAXRS21-SSE-1.0-SNAPSHOT/resources/sse/subscribe");
       
        try (final SseEventSource eventSource =
                     SseEventSource.target(target)
                             .build()) {
        	System.out.println("start-1");
            
            eventSource.register(System.out::println);
        	System.out.println("start-2");

            eventSource.open();

        	System.out.println("start-3");

        	WebTarget target2 = ClientBuilder.newClient().target("http://localhost:8080/JEE-Ex-JAXRS21-SSE-1.0-SNAPSHOT/resources/sse/broadcast");

            for (int counter = 0; counter < 5; counter++) {
            	System.out.println("Posting message");
            	Form form = new Form();
            	form.param("message", "hello foo");

            	target2.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
            	System.out.println("start-4");

            }

            Thread.sleep(1000); // make sure all the events have time to arrive
        	System.out.println("start-5");

        } catch (InterruptedException e) {
            e.printStackTrace();
        	System.out.println("start-6");

        }
    }
}

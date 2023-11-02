package dk.lundogbendsen.service;

//source: https://github.com/readlearncode/Java-EE-8-Only-Whats-New

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseBroadcaster;
import jakarta.ws.rs.sse.SseEventSink;


@Path("/sse")
@Singleton
public class SseResource {

    @Context
    private Sse sse;

    private SseBroadcaster broadcaster;

    @PostConstruct
    public void initialise() {
        this.broadcaster = sse.newBroadcaster();
    }

    @GET
    @Path("subscribe")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void subscribe(@Context SseEventSink eventSink) {
    	System.out.println("Subscribing client");
        eventSink.send(sse.newEvent("You are subscribed"));
        broadcaster.register(eventSink);
    }

    @POST
    @Path("broadcast")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void broadcast(@FormParam("message") String message) {
    	System.out.println("Broadcasting : " + message);
        broadcaster.broadcast(sse.newEvent(message));
    }
}
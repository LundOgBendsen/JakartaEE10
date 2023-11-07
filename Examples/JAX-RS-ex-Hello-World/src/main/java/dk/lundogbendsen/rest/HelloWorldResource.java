package dk.lundogbendsen.rest;

import jakarta.ws.rs.*;

@Path("/hello")
public class HelloWorldResource {

    @GET
    @Path("/{id}")
    @Produces("text/html")
    public String helloWorld(@PathParam("id") String name) {
        return "Hello, " + name;
    }
}


package org.acme;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/greeting")
public class GreetingResource {
    @ConfigProperty(name = "greetings")
    List<String> greetings = new ArrayList<String>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Howdy RESTEasy";
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> listGreetings() {
        return greetings;
    }

    @GET
    @Path("/list/random")
    @Produces(MediaType.TEXT_PLAIN)
    public String randomGreeting() {
        Random r = new Random();
        return greetings.get(r.nextInt(listGreetings().size())); // <1>
    }
}
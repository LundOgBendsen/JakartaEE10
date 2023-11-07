package dk.lundogbendsen.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

import dk.lundogbendsen.service.Employee;

public class RestClient {

    public static void main(String[] args) throws InterruptedException {

        ClientBuilder cb = ClientBuilder.newBuilder();
        Client client = cb.build();

        System.out.println("Async processing example client--------------");

        // a valid request
        Employee emp = client
                .target("http://localhost:8080/JAX-RS-Ex-AsyncProcessing-1.0-SNAPSHOT/resources/employees/42")
                .request(MediaType.APPLICATION_XML).get(Employee.class);
        System.out.println("1. read employee: " + emp.getId() + ", "
                + emp.getName());

    }
}

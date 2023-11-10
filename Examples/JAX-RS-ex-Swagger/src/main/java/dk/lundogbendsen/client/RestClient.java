package dk.lundogbendsen.client;

import dk.lundogbendsen.rest.model.Product;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.logging.Logger;

public class RestClient {
    private static final String BASE_URL = "http://localhost:8080/JAX-RS-ex-Swagger-1.0-SNAPSHOT/resources";
    static Logger log = Logger.getLogger(RestClient.class.getName());

    public static void main(String[] args) {
        ClientBuilder cb = ClientBuilder.newBuilder();
        Client client = cb.build();


        Response res = client
                .target(BASE_URL + "/products")
                .request(MediaType.APPLICATION_JSON_TYPE).get();
        log.info(res.readEntity(String.class));

        String s = client
                .target(BASE_URL + "/products/1")
                .request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
        log.info(s);


        client
                .target(BASE_URL + "/products/1")
                .request(MediaType.APPLICATION_JSON_TYPE).delete();
        log.info(s);


    }
}

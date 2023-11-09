package dk.lundogbendsen.rest;

import dk.lundogbendsen.rest.model.Product;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonGenerator;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;

import java.io.*;

@Path("/products")
public class ProductResource {

    @GET
    @Path("/1")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct1() {
        Product product = new Product();
        product.setId(1);
        product.setName("keyboard");
        product.setPrice(100.0);
        return Response.ok(product).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct2() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Writer writer = new BufferedWriter(new OutputStreamWriter(baos));
        final JsonGenerator gen = Json.createGenerator(writer);
        gen.writeStartObject()
                .write("id", 2)
                .write("name", "Mouse")
                .write("price", 50.0)
                .writeEnd();
        gen.close();

        StreamingOutput stream = new StreamingOutput() {
            public void write(OutputStream os)  {
                Writer writer = new BufferedWriter(new OutputStreamWriter(os));
                try {
                    writer.write(new String(baos.toByteArray()));
                    writer.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        };
        return Response.ok(stream).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/3")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct3() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Writer writer = new BufferedWriter(new OutputStreamWriter(baos));

        final JsonObject model = Json.createObjectBuilder()
                .add("id", 3)
                .add("name", "Keyboard")
                .add("price", 180.0)
                .build();

        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                Writer writer = new BufferedWriter(new OutputStreamWriter(os));
                writer.write(model.toString());
                writer.flush();
            }
        };
        return Response.ok(stream).type(MediaType.APPLICATION_JSON).build();
    }


}

package dk.lundogbendsen.rest;

import dk.lundogbendsen.rest.model.Product;
import dk.lundogbendsen.services.ProductDao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.inject.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Path("/products")
@Produces({"application/json"})
public class ProductResource {
    Logger log = Logger.getLogger(ProductResource.class.getName());

    @Inject
    ProductDao dao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all products",
            tags = {"products"},
            description = "Returns an array of Products",
            responses = {
                    @ApiResponse(description = "The product", content = @Content(
                            schema = @Schema(implementation = Product.class)
                    ))
            })
    public Response getAll(@PathParam("id") int id) {
        log.info("getProduct: " + id);
        List<Product> all = dao.getAll();
        return Response.ok(all).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get a product by id",
            tags = {"products"},
            description = "Returns a product",
            responses = {
                    @ApiResponse(description = "The product", content = @Content(
                            schema = @Schema(implementation = Product.class)
                    )),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            })
    public Response getProduct(@PathParam("id") int id) {
        log.info("getProduct: " + id);
        Optional<Product> p = dao.findById(id);
        if (p.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(p.get()).type(MediaType.APPLICATION_JSON).build();
        }

    }

    @POST
    // @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new product",
            tags = {"products"},
            description = "Create a new product. The product submitted must not have a product id",
            responses = {
                    @ApiResponse(description = "The product", content = @Content(
                            schema = @Schema(implementation = Product.class)
                    )),
                    @ApiResponse(responseCode = "406", description = "Not acceptable if product has an id.")
            })
    public Response create(Product product) {
        log.info("create: " + product);
        if (product.getId() == null) {
            dao.add(product);
            return Response.ok(product).type(MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a product",
            tags = {"products"},
            description = "Delete a product from id.The product must exist.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Deleted succesfully."),
                    @ApiResponse(responseCode = "404", description = "Product with submitted id does not exist")
            })
    public Response remove(@PathParam("id") int id) {
        log.info("remove: " + id);
        Optional<Product> p = dao.findById(id);
        if (p.isPresent()) {
            dao.remove(p.get());
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
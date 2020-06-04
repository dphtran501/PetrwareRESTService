package edu.uci.inf124.petrwarerestservice;

import edu.uci.inf124.petrwarerestservice.response.ProductListResponse;
import edu.uci.inf124.petrwarerestservice.service.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
public class ProductResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllProducts() {
        ProductListResponse response = ProductService.getAllProducts();

        if (response == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(response).build();
    }

}

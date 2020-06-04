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

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProduct(@PathParam("id") int id) {
        ProductListResponse response = ProductService.getProduct(id);

        if(response == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else if (response.getProductCPUList().size() > 0) {
            return Response.ok(response.getProductCPUList().get(0)).build();
        } else if (response.getProductRAMList().size() > 0) {
            return Response.ok(response.getProductRAMList().get(0)).build();
        } else if (response.getProductVCList().size() > 0) {
            return Response.ok(response.getProductVCList().get(0)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/search")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getSearchedProducts(@QueryParam("query") String query) {
        ProductListResponse response = ProductService.getSearchedProducts(query);

        if (response == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(response).build();
    }

}

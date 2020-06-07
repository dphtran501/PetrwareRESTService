package edu.uci.inf124.petrwarerestservice.resource;

import edu.uci.inf124.petrwarerestservice.response.ZipcodeResponse;
import edu.uci.inf124.petrwarerestservice.service.CheckoutService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/checkout")
public class CheckoutResource {

    @Path("/zipcode")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getZipcodeData(@QueryParam("zipcode") String zipcode) {
        ZipcodeResponse response = CheckoutService.getZipcodeData(zipcode);

        if (response == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(response).build();
    }
}

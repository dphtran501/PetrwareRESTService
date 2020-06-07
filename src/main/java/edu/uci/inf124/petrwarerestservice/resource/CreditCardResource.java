package edu.uci.inf124.petrwarerestservice.resource;

import edu.uci.inf124.petrwarerestservice.model.CreditCard;
import edu.uci.inf124.petrwarerestservice.service.CreditCardService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/creditcards")
public class CreditCardResource {

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCreditCard(@PathParam("id") int id) {
        CreditCard card = CreditCardService.getCreditCard(id);

        if (card == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(card).build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response postCreditCard(CreditCard card) {
        if (CreditCardService.addCreditCard(card)) {
            return Response.ok().entity("Credit card added successfully").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}

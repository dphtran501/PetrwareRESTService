package edu.uci.inf124.petrwarerestservice.resource;

import edu.uci.inf124.petrwarerestservice.model.Customer;
import edu.uci.inf124.petrwarerestservice.service.CustomerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
public class CustomerResource {

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCustomer(@PathParam("id") int id) {
        Customer customer = CustomerService.getCustomer(id);

        if(customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(customer).build();
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response postCustomer() {
        int customerID = CustomerService.addCustomer();
        if (customerID == -1) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } else {
            Customer customer = new Customer(customerID);
            return Response.ok(customer).build();
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response putCustomer(Customer customer) {
        if (CustomerService.updateCustomer(customer)) {
            return Response.ok().entity("Customer updated successfully").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}

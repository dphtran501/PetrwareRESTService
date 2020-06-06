package edu.uci.inf124.petrwarerestservice.resource;

import edu.uci.inf124.petrwarerestservice.model.Customer;
import edu.uci.inf124.petrwarerestservice.service.CustomerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
public class CustomerResource {

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
}

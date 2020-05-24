package example;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//Endpunkt für Client-Requests
@Path("initial")
public class TenantEndpoint {
	

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tenant> getTenants() {
        return TenantModel.getTenants();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tenant getTenant(@PathParam("id") int id) {
        return TenantModel.getTenant(id);   
    }


    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response postTenant(Tenant t) {
        TenantModel.postTenant(t);
        return Response.status(200).entity(t).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deleteTenant(@PathParam("id") int id){
    	TenantModel.deleteTenant(id);

        return Response.status(200).build();
    }
    
    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response update(Tenant t) {
        TenantModel.updateTenant(t);

        return Response.status(200).entity(t).build();
    }
}


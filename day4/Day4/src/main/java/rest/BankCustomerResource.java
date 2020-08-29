package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.BankCustomer;
import facades.CustomerFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("bankcustomer")
public class BankCustomerResource {
    
    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    CustomerFacade facade =  CustomerFacade.getFacadeExample(emf);
    
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(BankCustomer entity) {
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getCustomerById(@PathParam("id") Long id) {
     
        return Response.ok().entity(gson.toJson(facade.getCustomerById(id))).build();
     
    }
    
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBankCustomers() {
    EntityManager em = emf.createEntityManager();
    try{
    
        return Response.ok().entity(gson.toJson(facade.getAllBankCustomers())).build();
        
     } finally {
            em.close();
     }
  }
}

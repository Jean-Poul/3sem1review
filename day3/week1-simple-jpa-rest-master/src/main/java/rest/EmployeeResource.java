package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Employee;
import facades.EmployeeFacade;
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


@Path("Employee")
public class EmployeeResource {
    
    //NOTE: Change Persistence unit name according to your setup
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    private EmployeeFacade facade =  EmployeeFacade.getFacadeExample(emf);
    
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }
    
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees() {
    EntityManager em = emf.createEntityManager();
    try{
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> employee = query.getResultList();
        return Response.ok().entity(gson.toJson(facade.getAllEmployees())).build();
     } finally {
            em.close();
     }
  }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getEmployeeId(@PathParam("id") Long id) {
     
        return Response.ok().entity(gson.toJson(facade.getEmployeeById(id))).build();
     
    }
    
    @GET
    @Path("/highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getEmployeesWithHighestSalary() {
     
        return Response.ok().entity(gson.toJson(facade.getEmployeesWithHighestSalary())).build();
     
    }
    
     @GET
    @Path("/name{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getEmployeesByName(@PathParam("name") String name) {
     
        return Response.ok().entity(gson.toJson(facade.getEmployeesByName(name))).build();
     
    }
 
}

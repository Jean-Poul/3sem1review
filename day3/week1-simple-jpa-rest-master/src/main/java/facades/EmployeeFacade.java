package facades;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EmployeeDTO getEmployeeById(Long id) {
        EntityManager em = getEntityManager();
        try {
            Employee employee = em.find(Employee.class, id);
            EmployeeDTO e1 = new EmployeeDTO(employee);
            return e1;
        } finally {
            em.close();
        }
    }

    public EmployeeDTO getEmployeesByName(String name) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery("Select e FROM Employee e WHERE e.name = :NAME");
            query.setParameter("NAME", name);
            query.setMaxResults(1);
            Employee result2 = (Employee) query.getSingleResult();
            EmployeeDTO e2 = new EmployeeDTO(result2);
            return e2;
        } finally {
            em.close();
        }
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> pdtolist = new ArrayList();
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("Select e from Employee e", Employee.class);
            List<Employee> employee = query.getResultList();
            employee.stream().forEach(p -> {
                pdtolist.add(new EmployeeDTO(p));
            });
            return pdtolist;
        } finally {
            em.close();
        }
    }

    public EmployeeDTO getEmployeesWithHighestSalary() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("Select e FROM Employee e WHERE e.salary = (Select MAX(e.salary) FROM Employee e)", Employee.class);
            Employee result3 = (Employee) query.getSingleResult();
            EmployeeDTO e3 = new EmployeeDTO(result3);
            return e3;
        } finally {
            em.close();
        }
    }

    public Employee createEmployee(String name, String address, int salary) {
        Employee employee = new Employee(name, address, salary);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        } finally {
            em.close();
        }
    }

}

package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //Find a customer by id
    public CustomerDTO getCustomerById(Long id) {
        EntityManager em = getEntityManager();
        try {
            BankCustomer bc = em.find(BankCustomer.class, id);
            CustomerDTO c1 = new CustomerDTO(bc);
            return c1;
        } finally {
            em.close();
        }
    }
    
    //Find a customer by name
    public List<CustomerDTO> getCustomerByName(String name) {
        EntityManager em = getEntityManager();
        List<CustomerDTO> pdtolist = new ArrayList();
        try {
            TypedQuery<BankCustomer> query = em.createQuery("Select e from BankCustomer e", BankCustomer.class);
            List<BankCustomer> customer = query.getResultList();
            customer.stream().forEach(p->{pdtolist.add(new CustomerDTO(p));});
            return pdtolist;
        } finally {
            em.close();
        }
    }
    
    //Add a new customer
    public BankCustomer addCustomer(BankCustomer cust) {
        cust = new BankCustomer();
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }
    
    //Find all customers
    public List<CustomerDTO> getAllBankCustomers() {
        EntityManager em = getEntityManager();
        List<CustomerDTO> pdtolist = new ArrayList();
        try {
            TypedQuery<BankCustomer> query = em.createQuery("Select e from BankCustomer e", BankCustomer.class);
            List<BankCustomer> customer = query.getResultList();
            customer.stream().forEach(p->{pdtolist.add(new CustomerDTO(p));});
            return pdtolist;
        } finally {
            em.close();
        }
    }
    
}

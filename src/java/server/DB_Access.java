package server;

import data.Country;
import data.Customer;
import data.DataHolder;
import data.Representative;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DB_Access {
    
    private static DB_Access theInstance;
    
    private XML_Access xml;
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    private int repCount;
    
    private DB_Access() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        
        em = emf.createEntityManager();
        
        xml = XML_Access.getInstance();
        
        repCount = 1;
    }

    public static DB_Access getInstance() {
        
        if(theInstance == null) {
            theInstance = new DB_Access();
        }
        
        return theInstance;
    }

    public void persistDataHolder() {
        
        em.getTransaction().begin();
        
        DataHolder holder = xml.loadLocations();
        
        holder.getCountries()
              .stream()
              .forEach(em::persist);
        
        em.getTransaction().commit();
    }

    public void connect() {}

    public void disconnect() {}
    
    public List<Country> getAllCountries() {
        
        TypedQuery query = em.createNamedQuery("Country.getAll", Country.class);
        
        return query.getResultList();
    }

    public List<Representative> getRepresentativesOfCountry(String countryName) {
        TypedQuery query = em.createNamedQuery("Representative.getByCountry", Representative.class);
        
        query.setParameter("countryName", countryName);
        
        return query.getResultList();
    }
    
    public List<Customer> getCustomersOfRepresentative(String representativeName) {
        TypedQuery query = em.createNamedQuery("Customer.getByRepresentative", Customer.class);
        
        query.setParameter("representativeName", representativeName);
        
        return query.getResultList();
    }
    
    public Country getCountryWithName(String countryName) {
        TypedQuery query = em.createNamedQuery("Country.getWithName", Country.class);
        
        query.setParameter("countryName", countryName);
        
        List<Country> countries = query.getResultList();
        
        return countries.get(0);
    }
    
    public void addRepresentative(String countryName) {
        Country c = getCountryWithName(countryName);
        
        Representative rep = new Representative("Representative" + repCount);
        rep.setCountry(c);
        
        repCount++;
        
        
        em.getTransaction().begin();
        em.persist(rep);
        em.getTransaction().commit();
        
    }


    
    public static void main(String[] args) {
        System.out.println("Starting to persist Dataholder");
        
        DB_Access db = DB_Access.getInstance();
        
        db.persistDataHolder();
    }
    
}

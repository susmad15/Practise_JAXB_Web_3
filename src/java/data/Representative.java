package data;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@NamedQuery(name = "Representative.getByCountry", query = "SELECT r FROM Representative r WHERE r.country.name = :countryName")
public class Representative {

    @Id
    @GeneratedValue
    @Expose
    private Long representativeId;

    @Expose
    private String name;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "representative", cascade = CascadeType.PERSIST)
    private List<Customer> customers = new ArrayList<>();

    public Representative() {
    }

    public Representative(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @XmlElementWrapper(name = "Customers")
    @XmlElement(name = "Customer")
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Long getRepresentativeId() {
        return representativeId;
    }

    public void setRepresentativeId(Long representativeId) {
        this.representativeId = representativeId;
    }

    @Override
    public String toString() {
        return name;
    }

}

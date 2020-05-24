package data;

import adapters.DateAdapter;
import com.google.gson.annotations.Expose;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@NamedQuery(name = "Customer.getByRepresentative", query = "SELECT c FROM Customer c WHERE c.representative.name = :representativeName")
public class Customer {

    @Id
    @GeneratedValue
    @Expose
    private Long customerId;

    @Expose
    private String name;

    private Gender gender;

    private LocalDate dateOfBirth;

    @ManyToOne
    private Representative representative;

    public Customer() {
    }

    public Customer(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Representative getRepresentative() {
        return representative;
    }

    public void setRepresentative(Representative representative) {
        this.representative = representative;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}

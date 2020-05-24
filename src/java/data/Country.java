package data;

import adapters.DateAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity

@NamedQueries({
    @NamedQuery(name = "Country.getAll", query = "SELECT DISTINCT c FROM Country c ORDER BY c.name"),
    
    @NamedQuery(name = "Country.getWithName", query = "SELECT DISTINCT c FROM Country c WHERE c.name = :countryName")
})
@NamedQuery(name = "Country.getAll", query = "SELECT DISTINCT c FROM Country c ORDER BY c.name")
public class Country {

    @Id
    @GeneratedValue
    private Long countryId;

    private int population;
    private String name;
    private String capital;
    private String continent;
    private int importance;

    private LocalDate foundation;

    public Country() {
    }

    @OneToMany(mappedBy = "country", cascade = CascadeType.PERSIST)
    private List<Representative> representatives = new ArrayList<>();

    public void addRepresentative(Representative r) {
        representatives.add(r);
    }

    @XmlElementWrapper(name = "Representatives")
    @XmlElement(name = "Representative")
    public List<Representative> getRepresentatives() {
        return representatives;
    }

    public void setRepresentatives(List<Representative> representatives) {
        this.representatives = representatives;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public LocalDate getFoundation() {
        return foundation;
    }

    public void setFoundation(LocalDate foundation) {
        this.foundation = foundation;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return name;
    }

}

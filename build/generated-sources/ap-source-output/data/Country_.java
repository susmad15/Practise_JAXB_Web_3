package data;

import data.Representative;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-23T19:30:47")
@StaticMetamodel(Country.class)
public class Country_ { 

    public static volatile SingularAttribute<Country, String> continent;
    public static volatile SingularAttribute<Country, String> capital;
    public static volatile SingularAttribute<Country, Integer> importance;
    public static volatile SingularAttribute<Country, String> name;
    public static volatile SingularAttribute<Country, LocalDate> foundation;
    public static volatile ListAttribute<Country, Representative> representatives;
    public static volatile SingularAttribute<Country, Long> countryId;
    public static volatile SingularAttribute<Country, Integer> population;

}
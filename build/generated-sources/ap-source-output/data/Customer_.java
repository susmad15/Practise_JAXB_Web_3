package data;

import data.Gender;
import data.Representative;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-23T19:30:47")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, Gender> gender;
    public static volatile SingularAttribute<Customer, Long> customerId;
    public static volatile SingularAttribute<Customer, String> name;
    public static volatile SingularAttribute<Customer, LocalDate> dateOfBirth;
    public static volatile SingularAttribute<Customer, Representative> representative;

}
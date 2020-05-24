package data;

import data.Country;
import data.Customer;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-23T19:30:47")
@StaticMetamodel(Representative.class)
public class Representative_ { 

    public static volatile SingularAttribute<Representative, Country> country;
    public static volatile SingularAttribute<Representative, Long> representativeId;
    public static volatile SingularAttribute<Representative, String> name;
    public static volatile ListAttribute<Representative, Customer> customers;

}
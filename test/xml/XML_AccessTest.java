package xml;

import data.Country;
import data.Customer;
import data.DataHolder;
import data.Representative;
import java.lang.reflect.Modifier;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server.XML_Access;

public class XML_AccessTest {

    public XML_AccessTest () {
    }

    @BeforeClass
    public static void setUpClass () {
        System.out.println ( ">>> XML_Access.class tests:" );
    }

    @AfterClass
    public static void tearDownClass () {
    }

    @Before
    public void setUp () {
    }

    @After
    public void tearDown () {
    }

    private XML_Access xmla;

    @Test
    public void XML_AccessTestSingleton () {
        System.out.print ( "  XML_Access->Singleton" );
        try {
            xmla = XML_Access.getInstance ();
            Boolean expResult = true;
            Boolean result = xmla.getClass ().getDeclaredField ( "theInstance" ).getModifiers () == (Modifier.PRIVATE + Modifier.STATIC);
            if ( result ) {
                result = result && xmla.getClass ().getDeclaredMethod ( "getInstance" ).getModifiers () == (Modifier.PUBLIC + Modifier.STATIC);
            }
            if ( result ) {
                result = result && (xmla.getClass ().getDeclaredConstructor ().getModifiers () == Modifier.PRIVATE);
            }
            System.out.println ( expResult == result ? " ok" : " failed <<<<<" );
            assertEquals ( expResult, result );
        }
        catch ( Exception e ) {
            System.out.println ( e.toString () );
            fail ();
        }
    }

    @Test
    public void XML_AccessTestDataRead () {
        System.out.print ( "  XML_Access->DataRead" );
        try {
            xmla = XML_Access.getInstance ();
            Boolean expResult = true;
            DataHolder holder = xmla.loadLocations ();
            Country country = holder.getCountries ().get ( 1 );
            String countryName = country.getName ();
            Representative representative = country.getRepresentatives ().get ( 0 );
            String representativeName = representative.getName ();
            Customer customer = representative.getCustomers ().get ( 0 );
            String customerName = customer.getName ();

            System.out.println ( countryName );
            System.out.println ( representativeName );
            System.out.println ( customerName );

            Boolean result = countryName.equals ( "Niger" ) 
                    && representativeName.equals ( "Brunner" ) 
                    && customerName.equals ( "Shorty" );
            System.out.println ( expResult == result ? " ok" : " failed <<<<<" );
            assertEquals ( expResult, result );
        }
        catch ( Exception e ) {
            System.out.println ( e.toString () );
            fail ();
        }
    }

    @Test
    public void XML_AccessTestDataLinked () {
        System.out.print ( "  XML_Access->All Links established" );
        try {
            xmla = XML_Access.getInstance ();
            System.out.println (1);
            Boolean expResult = true;
            DataHolder holder = xmla.loadLocations ();
            Customer cust = holder.getCountries ().get ( 1 ).getRepresentatives ().get ( 0 ).getCustomers ().get ( 0 );
            String customerName = cust.getName ();
            System.out.println (2);
            String representativeName = cust.getRepresentative ().getName ();
            String countryName = cust.getRepresentative ().getCountry ().getName ();
            
                        System.out.println (3);

            System.out.println ( countryName );
            System.out.println ( representativeName );
            System.out.println ( customerName );
            
            Boolean result = countryName.equals ( "Niger" ) && representativeName.equals ( "Brunner" ) && customerName.equals ( "Shorty" );
            System.out.println ( expResult == result ? " ok" : " failed <<<<<" );
            assertEquals ( expResult, result );
        }
        catch ( Exception e ) {
            System.out.println ( e.toString () );
            fail ();
        }
    }
}

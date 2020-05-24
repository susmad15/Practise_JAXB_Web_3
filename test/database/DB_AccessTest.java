package database;

import data.Country;
import data.Representative;
import java.lang.reflect.Modifier;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import server.DB_Access;

@FixMethodOrder(MethodSorters.DEFAULT)
public class DB_AccessTest {

    public DB_AccessTest () {
        
        /* We assume persistence.xml to be drop-and-create, so we must
           load the database after we start our program. 
           drop-and-create means the database is cleared on the first emf/em 
           call
        */
        dba = DB_Access.getInstance ();
        dba.persistDataHolder ();
    }

    @BeforeClass
    public static void setUpClass () {
        System.out.println ( ">>> DB_Access.class tests:" );
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

    private DB_Access dba;

    @Test
    public void DB_AccessTestSingleton () {
        System.out.print ( "  DB_Access->Singleton" );
        try {
            dba = DB_Access.getInstance ();
            Boolean expResult = true;
            Boolean result = dba.getClass ().getDeclaredField ( "theInstance" ).getModifiers () == (Modifier.PRIVATE + Modifier.STATIC);
            if ( result ) {
                result = result && dba.getClass ().getDeclaredMethod ( "getInstance" ).getModifiers () == (Modifier.PUBLIC + Modifier.STATIC);
            }
            if ( result ) {
                result = result && (dba.getClass ().getDeclaredConstructor ().getModifiers () == Modifier.PRIVATE);
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
    public void DB_AccessTestConnect () {
        System.out.print ( "  DB_Access->connect+disconnect" );
        try {
            DB_Access.getInstance ().connect ();
            DB_Access.getInstance ().disconnect ();
            assertEquals ( true, true );
        }
        catch ( Exception e ) {
            System.out.println ( e.toString () );
            fail ();
        }
    }

    @Test
    public void DB_AccessTestPersistDH () {
        System.out.print ( "  DB_Access->persist DataHolder" );
        try {
            DB_Access dba = DB_Access.getInstance ();
            dba.connect ();
            //dba.resetDatabase();
            dba.persistDataHolder ();
            dba.disconnect ();
            assertEquals ( true, true );
        }
        catch ( Exception e ) {
            System.out.println ( e.toString () );
            fail ();
        }
    }

    @Test
    public void DB_AccessTestGetAllCountries () {
        System.out.print ( "  DB_Access->GetAllCountries" );
        try {
            DB_Access dba = DB_Access.getInstance ();
            dba.connect ();
            List<Country> countries = dba.getAllCountries ();
            //dba.disconnect();
            String start = "[Niger";
            String end = "Spain]";
            String result = countries.toString ();
            System.out.println ( "DB_AccessTestGetAllCountries: result=\n" + result );
            System.out.println ( "DB_AccessTestGetAllCountries(): result=" + result );
            Boolean expected = result.startsWith ( start ) && result.endsWith ( end );
            assertEquals ( true, expected );
        }
        catch ( Exception e ) {
            System.out.println ( e.toString () );
            fail ();
        }
    }

    @Test
    public void DB_AccessTestGetRepresentativesOfCountry () {
        System.out.print ( "  DB_Access->GetRepresentativesOfCountry" );
        try {
            DB_Access dba = DB_Access.getInstance ();
            //dba.connect ();

            List<Country> countries = dba.getAllCountries ();
            System.out.println ( countries );
            String countryName = "Niger";
            List<Representative> representatives = dba.getRepresentativesOfCountry ( countryName );
            //dba.disconnect ();
            String result = representatives.toString ();
            System.out.println ();
            System.out.println ( "DB_AccessTestGetRepresentativesOfCountry: " + result );
            System.out.println ();
            String expected = "[Brunner]";
            assertEquals ( expected, result );
        }
        catch ( Exception e ) {
            System.out.println ( e.toString () );
            fail ();
        }
    }

}

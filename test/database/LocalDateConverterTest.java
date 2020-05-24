package database;

import adapters.LocalDateConverter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocalDateConverterTest {

    public LocalDateConverterTest () {
    }

    @BeforeClass
    public static void setUpClass () {
        System.out.println ( ">>> LocalDateConverter.class tests:" );
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

    private LocalDateConverter ldc = new LocalDateConverter ();

    @Test
    public void LocalDateAdapterTestExtends () {
        System.out.print ( "  LocalDateConverter->Implments" );
        Boolean expResult = true;
        Boolean result = ldc.getClass ().getGenericInterfaces ()[0].getTypeName ()
                .equals ( "javax.persistence.AttributeConverter<java.time.LocalDate, java.sql.Date>" );
        System.out.println ( expResult == result ? " ok" : " failed <<<<<" );
        assertEquals ( expResult, result );
    }

    @Test
    public void LocalDateAdapterTestToSQL () {
        try {
            System.out.print ( "  LocalDateConverter->ToSQL" );
            Date expResult = new Date ( 120, 1, 29 );
            Date sqlDate = ldc.convertToDatabaseColumn ( LocalDate.of ( 2020, Month.FEBRUARY, 29 ) );
            Boolean result = sqlDate.equals ( expResult );
            System.out.println ( result ? " ok" : " failed <<<<<" );
            assertEquals ( expResult, sqlDate );
        }
        catch ( Exception e ) {
            System.out.println ( e.toString () );
            fail ();
        }
    }

    @Test
    public void LocalDateAdapterTestToLocalDate () {
        try {
            System.out.print ( "  LocalDateConverter->ToLocalDate" );
            LocalDate expResult = LocalDate.of ( 2020, Month.FEBRUARY, 29 );
            LocalDate ld = ldc.convertToEntityAttribute ( new Date ( 120, 1, 29 ) );
            Boolean result = ld.equals ( expResult );
            System.out.println ( result ? " ok" : " failed <<<<<" );
            assertEquals ( expResult, ld );
        }
        catch ( Exception e ) {
            System.out.println ( e.toString () );
            fail ();
        }
    }
}

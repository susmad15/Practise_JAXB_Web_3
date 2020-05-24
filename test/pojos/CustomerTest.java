package pojos;

import data.Customer;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 */
public class CustomerTest {

  public CustomerTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    System.out.println(">>> Customer.class tests:");
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  private Customer customer = new Customer();

  /**
   * Test of JPA annotations
   */
  @Test
  public void customerTestClassAnnotation() {
    System.out.print("  Customer->Class-Annotation-Entity");
    Boolean expResult = true;
    Boolean result = customer.getClass().isAnnotationPresent(Entity.class);
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void customerTestPKAnnotationId() throws NoSuchFieldException {
    System.out.print("  Customer->PK-Annotation-Id");
    Boolean expResult = true;
    Boolean result = customer.getClass().getDeclaredField("customerId").isAnnotationPresent(Id.class);
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void customerTestPKAnnotationGenVal() throws NoSuchFieldException {
    System.out.print("  Customer->PK-Annotation-GenVal");
    Boolean expResult = true;
    Boolean result = customer.getClass().getDeclaredField("customerId").isAnnotationPresent(GeneratedValue.class);
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void customerTestAnnotationNto1() throws NoSuchFieldException {
    System.out.print("  Customer->n:1-Annotation");
    Boolean expResult = true;
    Boolean result = customer.getClass().getDeclaredField("representative").isAnnotationPresent(ManyToOne.class);
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  /**
   * Test of JAXB annotations
   */
  @Test
  public void customerTestJAXBAnnotationAdapter() throws NoSuchMethodException {
    System.out.print("  Customer->1st JAXB-Annotation for list");
    Boolean expResult = true;
    Boolean result = customer.getClass().getDeclaredMethod("getDateOfBirth")
            .isAnnotationPresent(XmlJavaTypeAdapter.class);
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

   @Test
  public void customerTestJAXBAnnotationCountry() throws NoSuchMethodException {
    System.out.print("  Customer->JAXB-Annotation for representative");
    Boolean expResult = true;
    Boolean result = customer.getClass().getDeclaredMethod("getRepresentative")
            .isAnnotationPresent(XmlTransient.class);
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

}

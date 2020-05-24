package pojos;

import data.Country;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CountryTest {

  public CountryTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    System.out.println(">>> Country.class tests:");
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

  private Country country = new Country();

  /**
   * Test of JPA annotations
   */
  @Test
  public void countryTestNamedQueryAnnotation1() throws NoSuchFieldException {
    System.out.print("  Country->NQ-Annotation");
    Boolean expResult = true;
    Boolean result = country.getClass().getAnnotation(NamedQuery.class).name().equals("Country.getAll");
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void countryTestClassAnnotation() {
    System.out.print("  Country->Class-Annotation-Entity");
    Boolean expResult = true;
    Boolean result = country.getClass().isAnnotationPresent(Entity.class);
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void countryTestPKAnnotationId() throws NoSuchFieldException {
    System.out.print("  Country->PK-Annotation-Id");
    Boolean expResult = true;
    Boolean result = country.getClass().getDeclaredField("countryId").isAnnotationPresent(Id.class);
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void countryTestPKAnnotationGenVal() throws NoSuchFieldException {
    System.out.print("  Country->PK-Annotation-GenVal");
    Boolean expResult = true;
    Boolean result = country.getClass().getDeclaredField("countryId").isAnnotationPresent(GeneratedValue.class);
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void countryTestAnnotation1toN() throws NoSuchFieldException {
    System.out.print("  Country->1:n-Annotation");
    Boolean expResult = true;
    Boolean result = country.getClass().getDeclaredField("representatives").isAnnotationPresent(OneToMany.class);
    if (result) {
      result = result && country.getClass().getDeclaredField("representatives")
              .getAnnotation(OneToMany.class).mappedBy().equals("country");
    }
    if (result) {
      result = result && country.getClass().getDeclaredField("representatives")
              .getAnnotation(OneToMany.class).cascade()[0].equals(CascadeType.PERSIST);
    }
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  /**
   * Test of JAXB annotations
   */
  @Test
  public void countryTestJAXBAnnotationElem() throws NoSuchMethodException {
    System.out.print("  Country->1st JAXB-Annotation for list");
    Boolean expResult = true;
    Boolean result = country.getClass().getDeclaredMethod("getRepresentatives").isAnnotationPresent(XmlElement.class);
    if (result) {
      result = result && country.getClass().getDeclaredMethod("getRepresentatives").getAnnotation(XmlElement.class).name().equals("Representative");
    }
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void countryTestJAXBAnnotationWrapper() throws NoSuchMethodException {
    System.out.print("  Country->2nd JAXB-Annotation for list");
    Boolean expResult = true;
    Boolean result = country.getClass().getDeclaredMethod("getRepresentatives").isAnnotationPresent(XmlElementWrapper.class);
    if (result) {
      result = result && country.getClass().getDeclaredMethod("getRepresentatives").getAnnotation(XmlElementWrapper.class).name().equals("Representatives");
    }
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

}

package pojos;

import data.Representative;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RepresentativeTest {

  public RepresentativeTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    System.out.println(">>> Representative.class tests:");
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

  private Representative representative = new Representative();

  /**
   * Test of JPA annotations
   */
  @Test
  public void representativeTestNamedQueryAnnotation1() throws NoSuchFieldException {
    System.out.print("  Representative->NQ-Annotation");
    Boolean expResult = true;
    Boolean result = representative.getClass().getAnnotation(NamedQuery.class).name().equals("Representative.getByCountry");
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void representativeTestClassAnnotation() {
    System.out.print("  Representative->Class-Annotation-Entity");
    Boolean expResult = true;
    Boolean result = representative.getClass().isAnnotationPresent(Entity.class);
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void representativeTestPKAnnotationId() throws NoSuchFieldException {
    System.out.print("  Representative->PK-Annotation-Id");
    Boolean expResult = true;
    Boolean result = representative.getClass().getDeclaredField("representativeId").isAnnotationPresent(Id.class);
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void representativeTestPKAnnotationGenVal() throws NoSuchFieldException {
    System.out.print("  Representative->PK-Annotation-GenVal");
    Boolean expResult = true;
    Boolean result = representative.getClass().getDeclaredField("representativeId").isAnnotationPresent(GeneratedValue.class);
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void representativeTestAnnotation1toN() throws NoSuchFieldException {
    System.out.print("  Representative->1:n-Annotation");
    Boolean expResult = true;
    Boolean result = representative.getClass().getDeclaredField("customers").isAnnotationPresent(OneToMany.class);
    if (result) {
      result = result && representative.getClass().getDeclaredField("customers").getAnnotation(OneToMany.class).mappedBy().equals("representative");
    }
    if (result) {
      result = result && representative.getClass().getDeclaredField("customers").getAnnotation(OneToMany.class).cascade()[0].equals(CascadeType.PERSIST);
    }
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void representativeTestAnnotationNto1() throws NoSuchFieldException {
    System.out.print("  Representative->n:1-Annotation");
    Boolean expResult = true;
    Boolean result = representative.getClass().getDeclaredField("country").isAnnotationPresent(ManyToOne.class);
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  /**
   * Test of JAXB annotations
   */
  @Test
  public void representativeTestJAXBAnnotationElem() throws NoSuchMethodException {
    System.out.print("  Representative->1st JAXB-Annotation for list");
    Boolean expResult = true;
    Boolean result = representative.getClass().getDeclaredMethod("getCustomers").isAnnotationPresent(XmlElement.class);
    if (result) {
      result = result && representative.getClass().getDeclaredMethod("getCustomers").getAnnotation(XmlElement.class).name().equals("Customer");
    }
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void representativeTestJAXBAnnotationWrapper() throws NoSuchMethodException {
    System.out.print("  Representative->2nd JAXB-Annotation for list");
    Boolean expResult = true;
    Boolean result = representative.getClass().getDeclaredMethod("getCustomers")
            .isAnnotationPresent(XmlElementWrapper.class);
    if (result) {
      result = result && representative.getClass().getDeclaredMethod("getCustomers")
              .getAnnotation(XmlElementWrapper.class).name().equals("Customers");
    }
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void representativeTestJAXBAnnotationCountry() throws NoSuchMethodException {
    System.out.print("  Representative->JAXB-Annotation for country");
    Boolean expResult = true;
    Boolean result = representative.getClass().getDeclaredMethod("getCountry").isAnnotationPresent(XmlTransient.class);
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

}

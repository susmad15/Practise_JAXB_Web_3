package pojos;

import data.DataHolder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DataHolderTest {

  public DataHolderTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    System.out.println(">>> DataHolder.class tests:");
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

  private DataHolder dh = new DataHolder();

  /**
   * Test of JAXB annotations
   */
  @Test
  public void dataHolderTestClassAnnotation() {
    System.out.print("  DataHolder->Class-Annotation-JPA");
    Boolean expResult = true;
    Boolean result = dh.getClass().isAnnotationPresent(XmlRootElement.class);
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void dataHolderTestJAXBAnnotationElem() throws NoSuchMethodException {
    System.out.print("  City->1st JAXB-Annotation for list");
    Boolean expResult = true;
    Boolean result = dh.getClass().getDeclaredMethod("getCountries").isAnnotationPresent(XmlElement.class);
    if (result) {
      result = result && dh.getClass().getDeclaredMethod("getCountries").getAnnotation(XmlElement.class).name().equals("Country");
    }
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void dataHolderTestJAXBAnnotationWrapper() throws NoSuchMethodException {
    System.out.print("  City->2nd JAXB-Annotation for list");
    Boolean expResult = true;
    Boolean result = dh.getClass().getDeclaredMethod("getCountries").isAnnotationPresent(XmlElementWrapper.class);
    if (result) {
      result = result && dh.getClass().getDeclaredMethod("getCountries")
              .getAnnotation(XmlElementWrapper.class).name().equals("Countries");
    }
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }


}

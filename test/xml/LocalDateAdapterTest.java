/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import adapters.DateAdapter;
import java.time.LocalDate;
import java.time.Month;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocalDateAdapterTest {

  public LocalDateAdapterTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    System.out.println(">>> LocalDateAdapter.class tests:");
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

  private DateAdapter lda = new DateAdapter();

  @Test
  public void LocalDateAdapterTestExtends() {
    System.out.print("  LocalDateAdapter->Extends");
    Boolean expResult = true;
    Boolean result = lda.getClass().getGenericSuperclass().getTypeName()
            .equals("javax.xml.bind.annotation.adapters.XmlAdapter<java.lang.String, java.time.LocalDate>");
    System.out.println(expResult == result ? " ok" : " failed <<<<<");
    assertEquals(expResult, result);
  }

  @Test
  public void LocalDateAdapterTestMarshall() {
    try {
      System.out.print("  LocalDateAdapter->Marshall");
      String expResult = "2020-02-29";
      String xmlString = lda.marshal(LocalDate.of(2020, Month.FEBRUARY, 29));
      Boolean result = xmlString.equals(expResult);
      System.out.println(result ? " ok" : " failed <<<<<");
      assertEquals(expResult, xmlString);
    } catch (Exception e) {
      System.out.println(e.toString());
      fail();
    }
  }

    @Test
  public void LocalDateAdapterTestUnmarshall() {
    try {
      System.out.print("  LocalDateAdapter->Unmarshall");
      LocalDate expResult = LocalDate.of(2020, Month.FEBRUARY, 29);
      LocalDate ld = lda.unmarshal("2020-02-29");
      Boolean result = ld.equals(expResult);
      System.out.println(result ? " ok" : " failed <<<<<");
      assertEquals(expResult,ld);
    } catch (Exception e) {
      System.out.println(e.toString());
      fail();
    }
  }

}

////////////////////////////////////////////////////////////////////
// [Andrea] [Longo] [1174957]
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import static org.junit.Assert.assertEquals;
import org.junit.rules.ExpectedException;
import org.junit.Test;
import org.junit.Rule;

import java.util.List;
import java.util.ArrayList;

import it.unipd.tos.business.TakeAwayBillImpl;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.itemType;

public class PaninotecaTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void MenuItemGetItemTypeTest() {
    MenuItem fritti = new MenuItem(itemType.FRITTI, "Arancini", 3);
    assertEquals(itemType.FRITTI, fritti.getItemType());
  }

  @Test
  public void MenuItemGetNameTest() {
    MenuItem primavera = new MenuItem(itemType.PANINI, "Primavera", 5);
    assertEquals("Primavera", primavera.getName());
  }

  @Test
  public void MenuItemGetPriceTest() {
    MenuItem cola = new MenuItem(itemType.BEVANDE, "Cola", 2);
    assertEquals(2, cola.getPrice(), 0.0);
  }  

  @Test
  public void TakeAwayBillSimpleSumTest() {
    List<MenuItem> orderedItems = new ArrayList<MenuItem>();
    TakeAwayBillImpl order = new TakeAwayBillImpl();

    orderedItems.add(new MenuItem(itemType.PANINI, "Primavera", 3));
    orderedItems.add(new MenuItem(itemType.PANINI, "Vegetariano", 3));
    orderedItems.add(new MenuItem(itemType.FRITTI, "Arancini", 2));
    orderedItems.add(new MenuItem(itemType.BEVANDE, "Cola", 2));
    orderedItems.add(new MenuItem(itemType.BEVANDE, "Fanta", 2));

    try {
      assertEquals(12.0, order.getOrderPrice(orderedItems), 0.0);
    } catch (TakeAwayBillException tabe) {
      tabe.getMessage();
    }
  }

  @Test
  public void HalfPerCentDiscountToCheaperPaninoForOrderBiggerThan5Test() {
    List<MenuItem> orderedItems = new ArrayList<MenuItem>();
    TakeAwayBillImpl order = new TakeAwayBillImpl();

    orderedItems.add(new MenuItem(itemType.PANINI, "Primavera", 3));
    orderedItems.add(new MenuItem(itemType.PANINI, "Vegetariano", 4.50));
    orderedItems.add(new MenuItem(itemType.FRITTI, "Arancini", 2));
    orderedItems.add(new MenuItem(itemType.BEVANDE, "Cola", 2));
    orderedItems.add(new MenuItem(itemType.PANINI, "Indonesiano", 2));
    orderedItems.add(new MenuItem(itemType.PANINI, "Kebap", 4.50));
    orderedItems.add(new MenuItem(itemType.PANINI, "Pugliese", 6));
    orderedItems.add(new MenuItem(itemType.PANINI, "Arabo", 4));

    try {
      assertEquals(27, order.getOrderPrice(orderedItems), 0.0);
    } catch (TakeAwayBillException tabe) {
      tabe.getMessage();
    }

  }

  @Test
  public void TenPerCentDiscountForOrderBillOver50EuroTest() {
    List<MenuItem> orderedItems = new ArrayList<MenuItem>();
    TakeAwayBillImpl order = new TakeAwayBillImpl();
    
    for (int i = 0; i < 15; i++) {
      orderedItems.add(new MenuItem(itemType.FRITTI, "Olive Ascolane", 5));
    }

    orderedItems.add(new MenuItem(itemType.PANINI, "Primavera", 3));
    orderedItems.add(new MenuItem(itemType.BEVANDE, "Cola", 2));
    orderedItems.add(new MenuItem(itemType.BEVANDE, "Fanta", 2));

    try {
      assertEquals(73.8, order.getOrderPrice(orderedItems), 0.0);
    } catch (TakeAwayBillException tabe) {
      tabe.getMessage();
    }
  }

  @Test
  public void OrderItemWithMoreThan5PaninoAndMoreExpensiveThan50EuroTest() {
    List<MenuItem> orderedItems = new ArrayList<MenuItem>();
    TakeAwayBillImpl order = new TakeAwayBillImpl();

    for (int i = 0; i < 10; i++) {
      orderedItems.add(new MenuItem(itemType.PANINI, "Primavera", 5));
    }

    orderedItems.add(new MenuItem(itemType.PANINI, "Vegetariano", 4));
    orderedItems.add(new MenuItem(itemType.BEVANDE, "Cola", 2));
    orderedItems.add(new MenuItem(itemType.BEVANDE, "Fanta", 2));

    try {
      assertEquals(50.4, order.getOrderPrice(orderedItems), 0.0);
    } catch (TakeAwayBillException tabe) {
      tabe.getMessage();
    }
  }  

  @Test
  public void MaxThirtyItemsPerOredTest() throws TakeAwayBillException{
    List<MenuItem> orderedItems = new ArrayList<MenuItem>();
    TakeAwayBillImpl order = new TakeAwayBillImpl();

    for (int i = 0; i < 40; i++)
      orderedItems.add(new MenuItem(itemType.FRITTI, "Arancini", 3));    
      
    thrown.expect(TakeAwayBillException.class);
    thrown.expectMessage("Non si possono acquistare più di 30 elementi in un solo ordine.");   
    
    order.getOrderPrice(orderedItems);
  } 

  @Test
  public void TenEuroAdditionForOrderBillLessThan10Test() {
    List<MenuItem> orderedItems = new ArrayList<MenuItem>();
    TakeAwayBillImpl order = new TakeAwayBillImpl();

    orderedItems.add(new MenuItem(itemType.FRITTI, "Arancini", 3));
    orderedItems.add(new MenuItem(itemType.BEVANDE, "Cola", 2));

    try {
      assertEquals(5.50, order.getOrderPrice(orderedItems), 0.0);
    } catch(TakeAwayBillException tabe) {
      tabe.getMessage();
    }
  }  
}

////////////////////////////////////////////////////////////////////
// [Andrea] [Longo] [1174957]
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import static org.junit.Assert.assertEquals;
import org.junit.rules.ExpectedException;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.itemType;

public class PaninotecaTest {

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
 
}

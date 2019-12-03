////////////////////////////////////////////////////////////////////
// [Andrea] [Longo] [1174957]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.itemType; 

public class TakeAwayBillImpl implements TakeAwayBill {

  public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
    double totalPrice = 0; 
    int nPanini = 0;
    double cheaperPanino = 1000.0;
    double totalPanini = 0;
    double totalFritti = 0;
    double totalBevande = 0;

    if(itemsOrdered.size() > 30) {
      throw new TakeAwayBillException("Non si possono acquistare più di 30 elementi in un solo ordine.");
    }

    for(MenuItem item : itemsOrdered) {
      if(item.getItemType() == itemType.PANINI) {
        nPanini += 1;
        cheaperPanino = cheaperPanino > item.getPrice() ? item.getPrice() : cheaperPanino;

        totalPanini += item.getPrice();
      }
      else if(item.getItemType() == itemType.FRITTI) {
        totalFritti += item.getPrice();
      }
      else if(item.getItemType() == itemType.BEVANDE) {
        totalBevande += item.getPrice();
      }

    }

    totalPrice = totalPanini + totalFritti + totalBevande;

    if(nPanini >= 5) {
      totalPrice -= cheaperPanino/2;  
    }
    
    if(totalPanini + totalFritti >= 50) {
      totalPrice -= totalPrice/10;
    }

    if(totalPrice <= 10) {
      totalPrice += 0.50;
    }

    return totalPrice;
  }

}
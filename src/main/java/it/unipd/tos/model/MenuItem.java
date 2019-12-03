////////////////////////////////////////////////////////////////////
// [Andrea] [Longo] [1174957]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class MenuItem {

  private itemType type;
  private String name;
  private double price;

  public MenuItem(itemType type, String name, double price) {
    super();
    this.type = type;
    this.name = name;
    this.price = price;
  }  
  
  public itemType getItemType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

}
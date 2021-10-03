// --== CS400 File Header Information ==--
// Name: Xintong Li
// Email: xli2224@wisc.edu
// Team: DB
// Role: BackEnd Developer
// TA: Yelun Bao
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * This restaurant class create a new restaurant object include the restaurant's name and its menu
 * 
 * @author Xintong Li
 */
public class Restaurant {
  private String name;
  private Menu curMenu;

  /**
   * Constructor for the Restaurant object
   * 
   * @param name    the name of the restaurant
   * @param curMenu the menu of the restaurant
   */
  public Restaurant(String name, Menu curMenu) {
    this.name = name;
    this.curMenu = curMenu;
  }

  /**
   * Get the name of the restaurant
   * 
   * @return String the name for the restaurant
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the restaurant
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the String list of the restaurant Menu
   * 
   * @return String the menu for the restaurant
   */
  public Menu getMenu() {
    return curMenu;
  }


  /**
   * Return a string representation of the restaurant
   * 
   * @return string name + ": " + menu
   */
  public String toString() {
    if (curMenu.entry() != 0) {
      return name + ": " + curMenu.toString();
    } else {
      return name;
    }
  }

}

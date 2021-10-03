// --== CS400 File Header Information ==--
// Name: Xintong Li
// Email: xli2224@wisc.edu
// Team: DB
// Role: BackEnd Developer
// TA: Yelun Bao
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
/**
 * A class grouping together the dish and the price
 * 
 * @author Xintong Li
 *
 */
public class Dish implements Comparable<Dish> {
  private String name;
  private double price;

  /**
   * Constructor for the dish object
   * 
   * @param name  the name of the dish
   * @param score the price of the dish
   */
  public Dish(String name, double price) {
    this.name = name;
    this.price = price;
  }

  /**
   * Get the name of the dish
   * 
   * @return String the name for the dish
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the dish
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the score of the dish
   * 
   * @return double the price for the dish
   */
  public double getPrice() {
    return price;
  }

  @Override
  /**
   * Compare dishes with price
   * 
   * @param The other dish object to compare to
   * @return -1 if this dish has a price that's smaller than or equal to the other dish; 1 if
   *         this dish has a higher price
   */
  public int compareTo(Dish o) {
    if (this.price <= o.price)
      return -1;
    return 1;
  }

  /**
   * Return a string representation of the dish
   * 
   * @return string name + ": " + price
   */
  public String toString() {
    return name + ": " + price;
  }

}

// --== CS400 File Header Information ==--
// Name: Xintong Li
// Email: xli2224@wisc.edu
// Team: DB
// Role: BackEnd Developer
// TA: Yelun Bao
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.ArrayList;

/**
 * Menu implementation Note: 1) When two dishes have the same price, the later added one is put
 * in the left subtree. 2) Dishes with duplicate names are not allowed
 * 
 * @author Xintong Li
 */
public class Menu extends RedBlackTree<Dish> {

  private double total = 0; // total value of the price
  private int entry = 0; // size of the dataset

  /**
   * Add a dish into the tree
   * 
   * @param name  the name of the dish
   * @param price the price of the dish
   * @throws IllegalArgumentException throws an IllegalArgumentException with message "Please enter
   *                                  a positive number for price." price entered is non - positive
   *                                  or "No such dish recorded." if the dish is recorded
   */
  public void insert(String name, double price) throws IllegalArgumentException {
    if (containsDish(name))
      throw new IllegalArgumentException("The dish already exists.");
    if (price <= 0.0)
      throw new IllegalArgumentException("Please enter a positive number for price.");
    insert(new Dish(name, price));
    total = total + price;
    entry++;
  }

  /**
   * A private helper method that adds Dish objects to an ArrayList according to the price range
   * 
   * @param lower   the lower bound of the range
   * @param upper   the upper bound of the range
   * @param current the current Node
   * @param s       the list of Dish objects
   */
  private void rangeHelper(double lower, double upper, Node<Dish> current,
      ArrayList<Dish> s) {
    if (current != null) {
      rangeHelper(lower, upper, current.leftChild, s);
      rangeHelper(lower, upper, current.rightChild, s);
      if ((current.data.getPrice() <= upper) && (current.data.getPrice() >= lower)) {
        s.add(current.data);
      }
    }
  }

  /**
   * A method that returns an ArrayList of Dish objects according to according to the price range
   * (inclusive)
   * 
   * @param lower the lower bound of the range
   * @param upper the upper bound of the range
   * @return an ArrayList containing Dish objects with scores in the range; an empty ArrayList if
   *         no dish is within the range
   * @throws IllegalArgumentException throws an IllegalArgumentException with message "The lower
   *                                  bound should be smaller than or equal to the higher bound." if
   *                                  the min is larger than the max. Note: the min and max can be
   *                                  the same. In this case, the method is detecting dishes with
   *                                  the specific price.
   */
  public ArrayList<Dish> searchPriceRange(double lower, double upper)
      throws IllegalArgumentException {
    if (lower > upper)
      throw new IllegalArgumentException(
          "The lower bound should be smaller than or equal to the higher bound.");
    ArrayList<Dish> s = new ArrayList<Dish>();
    rangeHelper(lower, upper, root, s);
    return s;
  }

  /**
   * A private helper method searching for a dish's price
   * 
   * @param name    the dish's name
   * @param current the current node being examined
   * @return 0.0 if none found
   */
  private double priceHelper(String name, Node<Dish> current) {
    if (current != null) {
      if (current.data.getName().compareTo(name) == 0) {
        return current.data.getPrice();
      } else {
        return priceHelper(name, current.leftChild) + priceHelper(name, current.rightChild);
      }
    } else
      return 0.0;
  }

  /**
   * Check if a given dish is recorded
   * 
   * @param name the name of the dish
   * @return true if recorded; false if not
   */
  public boolean containsDish(String name) {
    if (priceHelper(name, root) < 0.0001)
      return false;
    return true;
  }

  /**
   * Search the price of a dish
   * 
   * @param name the name of the dish
   * @return Double the price of the dish
   * @throws IllegalArgumentException if the dish is not recorded
   */
  public double searchADish(String name) throws IllegalArgumentException {
    if (!containsDish(name))
      throw new IllegalArgumentException("No such dish recorded.");
    return priceHelper(name, root);
  }

  /**
   * Show the min price; 0.0 if no record in the tree
   * 
   * @return double the min
   */
  public double showMin() {
    if (root == null)
      return 0.0;
    Node<Dish> current = root;
    while (current.leftChild != null)
      current = current.leftChild;
    return current.data.getPrice();
  }

  /**
   * Show the max price; 0.0 if no record in the tree
   * 
   * @return double the max
   */
  public double showMax() {
    if (root == null)
      return 0.0;
    Node<Dish> current = root;
    while (current.rightChild != null)
      current = current.rightChild;
    return current.data.getPrice();
  }

  /**
   * Show the average price
   * 
   * @return double the average
   */
  public double showAverage() {
    return total / entry;
  }

  /**
   * Show the number of entries recorded
   * 
   * @return int entry number
   */
  public int entry() {
    return entry;
  }

  /**
   * Change the name of a dish
   * 
   * @param oldName the original name of the dish
   * @param newName the new name of the dish
   * @throws IllegalArgumentException if the dish is not recorded
   */
  public void changeName(String oldName, String newName) throws IllegalArgumentException {
    if (!containsDish(oldName))
      throw new IllegalArgumentException("No such dish recored.");
    nameHelper(oldName, newName, root);
  }

  /**
   * A private helper method changing a dish's name
   * 
   * @param oldName the original name of the dish
   * @param newName the new name of the dish
   * @param current the current node being examined
   */
  private void nameHelper(String oldName, String newName, Node<Dish> current) {
    if (current != null) {
      if (current.data.getName().compareTo(oldName) == 0) {
        current.data.setName(newName);
      } else {
        nameHelper(oldName, newName, current.leftChild);
        nameHelper(oldName, newName, current.rightChild);
      }
    }
  }
  
  /**
   * A private helper method that adds Dish objects to an ArrayList according to the star price
   * 
   * @param starPrice  given star rating price
   * @param current    the current Node
   * @param s          the list of Dish objects
   */
  private void starHelper(double starPrice, Node<Dish> current, ArrayList<Dish> s) {
    if (current != null) {
      starHelper(starPrice, current.leftChild, s);
      starHelper(starPrice, current.rightChild, s);
      if (current.data.getPrice() >= starPrice ) {
        s.add(current.data);
      }
    }
  }
  
}


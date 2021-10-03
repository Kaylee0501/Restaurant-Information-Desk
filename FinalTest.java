// --== CS400 File Header Information ==--
// Name: Lillian Li
// Email: yli2244@wisc.edu
// Team: DB
// TA: Yelun
// Lecturer: Professor Gary Dahl

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * @author lillian Tests the functionalities of the program
 */
class FinalTest {

  BackEnd back = new BackEnd();
  Menu chipotleMenu;
  Menu popeyesMenu;
  Menu chickFilAMenu;
  Menu senchaMenu;
  Menu chePopMenu;
  Menu cheTimeMenu;

  Restaurant Chipotle;
  Restaurant popeyes;
  Restaurant chickFilA;
  Restaurant sencha;
  Restaurant chePop;
  Restaurant cheTime;

  @BeforeEach
  /**
   * Initialize the graph before each test method
   */
  public void initialGraph() {

    chipotleMenu = new Menu();
    chipotleMenu.insert("Burrito", 7.90);
    chipotleMenu.insert("Burrito Bowl", 7.90);
    chipotleMenu.insert("Chips", 1.60);
    chipotleMenu.insert("Chips&Queso Blanco", 4.10);
    chipotleMenu.insert("Chips&Guacamole", 4.10);
    chipotleMenu.insert("Three Tacos", 7.90);
    Chipotle = new Restaurant("Chipotle", chipotleMenu);

    popeyesMenu = new Menu();
    popeyesMenu.insert("Chicken Dinner (3 Pcs)", 7.70);
    popeyesMenu.insert("Mixed Chicken (8 Pcs)", 19.80);
    popeyesMenu.insert("Handcrafted Tenders Dinner (3 Pcs)", 7.10);
    popeyesMenu.insert("Handcrafted Tenders Dinner (5 Pcs)", 8.20);
    popeyesMenu.insert("Classic Chicken Sandwich Combo", 7.69);
    popeyesMenu.insert("Handcrafted Tender Combo (5 Pcs)", 9.30);
    popeyes = new Restaurant("Popeyes", popeyesMenu);

    chickFilAMenu = new Menu();
    chickFilAMenu.insert("Chick-fil-A Nuggets", 5.40);
    chickFilAMenu.insert("Chick-fil-A Chicken Sandwich", 5.30);
    chickFilAMenu.insert("Spicy Chicken Sandwich", 5.70);
    chickFilAMenu.insert("Spicy Deluxe Sandwich", 6.60);
    chickFilAMenu.insert("Chick-fil-A Deluxe Sandwich", 6.20);
    chickFilAMenu.insert("Chick-n-Strips", 5.85);
    chickFilA = new Restaurant("Chick-fil-A", chickFilAMenu);

    senchaMenu = new Menu();
    senchaMenu.insert("Royal Tea Latte", 4.99);
    senchaMenu.insert("Thai Tea Latte", 4.99);
    senchaMenu.insert("Iced Tea", 4.79);
    senchaMenu.insert("Tea Latte", 4.89);
    senchaMenu.insert("Cooler", 4.69);
    senchaMenu.insert("The Campfire Latte", 5.99);
    sencha = new Restaurant("Sencha Tea Bar", senchaMenu);

    chePopMenu = new Menu();
    chePopMenu.insert("Oreo® Dream Extreme Cheesecake", 8.95);
    chePopMenu.insert("Fresh Strawberry Cheesecake", 8.95);
    chePopMenu.insert("Cheeseburger Spring Rolls", 8.50);
    chePopMenu.insert("Avocado Eggrolls", 12.95);
    chePopMenu.insert("Adam's Peanut Butter Cup Fudge Ripple Cheesecake", 8.50);
    chePopMenu.insert("Fried Macaroni and Cheese", 13.95);
    chePop = new Restaurant("The Cheesecake Factory - Popular Items", chePopMenu);

    cheTimeMenu = new Menu();
    cheTimeMenu.insert("\"Nashville Hot\" Chicken Nuggets", 6.50);
    cheTimeMenu.insert("Crispy Crab Wontons", 7.95);
    cheTimeMenu.insert("Bruschetta", 8.50);
    cheTimeMenu.insert("Avocado Eggrolls", 6.50);
    cheTimeMenu.insert("Tons of Fun Burger*", 11.95);
    cheTimeMenu.insert("Fried Macaroni and Cheese", 13.95);
    cheTime = new Restaurant("The Cheesecake Factory - Timeless Classics", cheTimeMenu);

    back.insertVertex(Chipotle);
    back.insertVertex(popeyes);
    back.insertVertex(chickFilA);
    back.insertVertex(sencha);
    back.insertVertex(chePop);
    back.insertVertex(cheTime);

    // back.insertEdge(Chipotle, popeyes, 1);
    // back.insertEdge(Chipotle, chickFilA, 2);
    // back.insertEdge(Chipotle, sencha, 3);
    back.insertEdge(Chipotle, chePop, 4);
    // back.insertEdge(Chipotle, cheTime, 5);

    // back.insertEdge(popeyes, Chipotle, 1);
    // back.insertEdge(popeyes, chickFilA, 2);
    back.insertEdge(popeyes, sencha, 4);
    // back.insertEdge(popeyes, chePop, 6);
    back.insertEdge(popeyes, cheTime, 8);

    // back.insertEdge(chickFilA, Chipotle, 2);
    // back.insertEdge(chickFilA, popeyes, 2);
    back.insertEdge(chickFilA, sencha, 3);
    // back.insertEdge(chickFilA, chePop, 5);
    back.insertEdge(chickFilA, cheTime, 7);

    // back.insertEdge(sencha, Chipotle, 3);
    back.insertEdge(sencha, popeyes, 4);
    back.insertEdge(sencha, chickFilA, 3);
    back.insertEdge(sencha, chePop, 2);
    // back.insertEdge(sencha, cheTime, 4);

    back.insertEdge(chePop, Chipotle, 4);
    // back.insertEdge(chePop, popeyes, 6);
    // back.insertEdge(chePop, chickFilA, 5);
    back.insertEdge(chePop, sencha, 2);
    // back.insertEdge(chePop, cheTime, 1);

    // back.insertEdge(cheTime, Chipotle, 5);
    back.insertEdge(cheTime, popeyes, 8);
    back.insertEdge(cheTime, chickFilA, 7);
    // back.insertEdge(cheTime, sencha, 4);
    // back.insertEdge(cheTime, chePop, 1);


  }

  /**
   * Test changing the name of a restaurant
   */
  @Test
  public void testChangeName() {

    if (!back.changeName("Sencha Tea Bar", "Sencha")) {
      fail("The changeName() method did not correctly find the original name");
    }

    if (sencha.getName().equals("Sencha Tea Bar")) {
      fail("The changeName() method did not change the original name");
    }

    if (!sencha.getName().equals("Sencha")) {
      fail("The changeName() method did not change the original name into the "
          + "new namecorrectly");
    }

  }

  /**
   * Tests adding a restaurant
   */
  @Test
  public void testAdd() {
    Menu paneraMena = new Menu();
    paneraMena.insert("Avocado, Egg White & Spinach,", 5.39);
    paneraMena.insert("Bacon, Scrambled Egg & Cheese", 5.39);
    paneraMena.insert("Half Dozen Bagels", 7.09);
    paneraMena.insert("Bacon, Egg & Cheese on Brioche", 5.39);
    paneraMena.insert("Green Passion Smoothie", 5.39);
    paneraMena.insert("Plain Cream Cheese Spread Tub", 3.19);
    Restaurant panera = new Restaurant("Panera Bread", paneraMena);

    back.insertVertex(panera);
    back.insertEdge(panera, Chipotle, 5);
    back.insertEdge(panera, popeyes, 8);
    back.insertEdge(panera, chickFilA, 7);
    back.insertEdge(panera, sencha, 4);
    back.insertEdge(panera, chePop, 1);
    back.insertEdge(panera, cheTime, 1);

    if (!back.containsVertex(panera)) {
      fail("Fail to add a new restaurant");
    }

  }

  /**
   * Tests removing a restaurant
   */
  @Test
  public void testRemove() {
    Menu paneraMena = new Menu();
    paneraMena.insert("Avocado, Egg White & Spinach,", 5.39);
    paneraMena.insert("Bacon, Scrambled Egg & Cheese", 5.39);
    paneraMena.insert("Half Dozen Bagels", 7.09);
    paneraMena.insert("Bacon, Egg & Cheese on Brioche", 5.39);
    paneraMena.insert("Green Passion Smoothie", 5.39);
    paneraMena.insert("Plain Cream Cheese Spread Tub", 3.19);
    Restaurant panera = new Restaurant("Panera Bread", paneraMena);

    back.insertVertex(panera);
    back.insertEdge(panera, Chipotle, 5);
    back.insertEdge(panera, popeyes, 8);
    back.insertEdge(panera, chickFilA, 7);
    back.insertEdge(panera, sencha, 4);
    back.insertEdge(panera, chePop, 1);
    back.insertEdge(panera, cheTime, 1);

    if (!back.removeVertex(panera)) {
      fail("The remove method did not find the restaurant to remove.");
    }

    if (back.containsVertex(panera)) {
      fail("The remove method did not remove the restaurant successfully.");
    }

    if (back.containsEdge(panera, Chipotle) || back.containsEdge(panera, popeyes)
        || back.containsEdge(panera, chickFilA) || back.containsEdge(panera, sencha)
        || back.containsEdge(panera, chePop) || back.containsEdge(panera, cheTime)) {
      fail("The remove method did not remove the path centered around the "
          + "target restaurant successfully.");
    }
  }

  /**
   * Checks the data sequence of the shortest paths for the restaurants
   */
  @Test
  public void testPathSequence() {

    List<Restaurant> result = back.shortestPath(Chipotle, chickFilA);

    if (!result.get(0).getName().equals("Chipotle")
        || !result.get(1).getName().equals("The Cheesecake Factory - Popular Items")
        || !result.get(2).getName().equals("Sencha Tea Bar")
        || !result.get(3).getName().equals("Chick-fil-A")) {
      fail("The shortest path between Chipotle and Chick-fil-A does"
          + "not contain the expected restaurants.");
    }
  }

  /**
   * Check the distance between two restaurants
   */
  @Test
  public void testPathDsitance() {
    if (back.getPathCost(Chipotle, chickFilA) != 9) {
      fail("The shortest path between Chipotle and Chick-fil-A does"
          + "not have the expected distance.");
    }
  }

  /**
   * Creates a complicated scenario and check the validity of the path
   */
  @Test
  public void testComplexPath1() {
    List<Restaurant> result = back.shortestPath(cheTime, chePop);

    if (!result.get(0).getName().equals("The Cheesecake Factory - Timeless Classics")
        || !result.get(1).getName().equals("Chick-fil-A")
        || !result.get(2).getName().equals("Sencha Tea Bar")
        || !result.get(3).getName().equals("The Cheesecake Factory - Popular Items")) {
      System.out.println(back.shortestPath(cheTime, chePop).toString());
      fail("The shortest path between The Cheesecake Factory - Timeless "
          + "Classics and The Cheesecake Factory - Popular Items does"
          + "not contain the expected restaurants.");
    }

    if (back.getPathCost(cheTime, chePop) != 12) {
      fail("The shortest path between The Cheesecake Factory - Timeless "
          + "Classics and The Cheesecake Factory - Popular Items does"
          + "not have the expected distance.");
    }
  }

  /**
   * Creates a second complicated scenario and check the validity of the path
   */
  @Test
  public void testComplexPath2() {
    List<Restaurant> result = back.shortestPath(Chipotle, cheTime);

    if (!result.get(0).getName().equals("Chipotle")
        || !result.get(1).getName().equals("The Cheesecake Factory - Popular Items")
        || !result.get(2).getName().equals("Sencha Tea Bar")
        || !result.get(3).getName().equals("Chick-fil-A")
        || !result.get(4).getName().equals("The Cheesecake Factory - Timeless Classics")) {
      fail("The shortest path between Chipotle "
          + "and The Cheesecake Factory - Timeless Classics does"
          + "not contain the expected restaurants.");
    }

    if (back.getPathCost(cheTime, chePop) != 12) {
      fail("The shortest path between Chipotle "
          + "and The Cheesecake Factory - Timeless Classics does"
          + "not have the expected distance.");
    }
  }

}

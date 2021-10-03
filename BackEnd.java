// --== CS400 File Header Information ==--
// Name: Xintong Li
// Email: xli2224@wisc.edu
// Team: DB
// Role: BackEnd Developer
// TA: Yelun Bao
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Iterator;
import java.util.Hashtable;

/**
 * This BackEnd class help user find the shortest path between target restaurants and also add
 * some methods to change the restaurant's name and find the neighbor of the restaurant.
 * 
 * @author Xintong Li
 */
public class BackEnd extends CS400Graph<Restaurant>{
    
  /**
   * This method changes the name of the restaurant in the map
   * 
   * @param oldName original name of the restaurant
   * @param newName new name of the restaurant
   * @return true if old name exists in the map and false otherwise.
   */
  public boolean changeName(String oldName, String newName) {
    Set<Restaurant> keys = vertices.keySet();
    Iterator<Restaurant> itr = keys.iterator();
    while (itr.hasNext()) {
      Restaurant cafe = itr.next();
      if (cafe.getName().equals(oldName)) {
        Vertex tempV = vertices.get(cafe);
        LinkedList<Edge> tempEdgesLea = tempV.edgesLeaving;
        LinkedList<Edge> edgeTarget = new LinkedList<Edge>();
        LinkedList<Restaurant> sourceV = new LinkedList<Restaurant>();
        for (Vertex v : vertices.values()) { // goes through the whole table
          for (Edge e : v.edgesLeaving) // goes through all edges
            if (e.target == tempV) { // if target matches to our temp nodes
              edgeTarget.add(e);
            }
          sourceV.add(v.data);
        }
        removeVertex(cafe);
        cafe.setName(newName);
        insertVertex(cafe); // insert only vertex
        for (Edge e: tempEdgesLea) { // inserts all edges from given node
          insertEdge(cafe, e.target.data, e.weight);
        }
        for (int i = 0 ; i < edgeTarget.size(); i++) {
          // inserts all edges to given node
          
          insertEdge(sourceV.get(i), edgeTarget.get(i).target.data,
              edgeTarget.get(i).weight);
        }
        return true;
      }
    }
    return false;
  }
  
  /**
   * This method finds the immediate neighbour of a restaurant with its name
   * 
   * @param name the name of the restaurant
   * @return a string of all the neighbour restaurants include each restaurant's name and menu
   */
  public String findNeighbours(String name) {
    String s = "";
    LinkedList <Restaurant> neighbours = new LinkedList<Restaurant>();
    Set<Restaurant> keys = vertices.keySet();
    Iterator<Restaurant> itr = keys.iterator();
    while (itr.hasNext()) {
      Restaurant cafe = itr.next();
      if (cafe.getName().equals(name)) {
        for (int i = 0 ; i < vertices.get(cafe).edgesLeaving.size(); i++) {
          Restaurant newCafe = vertices.get(cafe).edgesLeaving.get(i).target.data;
          neighbours.add(newCafe);
          s += newCafe.toString() + "/n";
        }
        return s;
      }
    }
    return null;
  }
  

}

// --== CS400 File Header Information ==--
// Name: Xintong Li
// Email: xli2224@wisc.edu
// Team: DB
// Role: BackEnd Developer
// TA: Yelun Bao
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.LinkedList;

/**
 * Binary Search Tree implementation with a Node inner class for representing the nodes within a
 * binary search tree. You can use this class' insert method to build a binary search tree, and its
 * toString method to display the level order (breadth first) traversal of values in that tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

  /**
   * This class represents a node holding a single value within a binary tree the parent, left, and
   * right child references are always be maintained.
   */
  protected static class Node<T> {
    public T data; // the value add to the RedBlackTree
    public Node<T> parent; // null for root node
    public Node<T> leftChild;
    public Node<T> rightChild;
    public boolean isBlack = false;// the color of the node

    /**
     * This constructor create set a new data variable to the Node object
     * 
     * @param data - the data value store in the node
     */
    public Node(T data) {
      this.data = data;
    }

    /**
     * This method test whether the node has a parent and is the left child of that parent
     * 
     * @return true when this node has a parent and is the left child of that parent, otherwise
     *         return false
     */
    public boolean isLeftChild() {
      return parent != null && parent.leftChild == this;
    }

    /**
     * This method performs a level order traversal of the tree rooted at the current node. The
     * string representations of each data value within this tree are assembled into a comma
     * separated string within brackets (similar to many implementations of java.util.Collection).
     * 
     * @return string containing the values of this tree in level order
     */
    @Override
    public String toString() { // display subtree in order traversal
      String output = "[";
      // create a new linkedList stores node object
      LinkedList<Node<T>> q = new LinkedList<>();
      q.add(this);
      // print the values of this tree in level order
      while (!q.isEmpty()) {
        Node<T> next = q.removeFirst();
        if (next.leftChild != null)
          q.add(next.leftChild);
        if (next.rightChild != null)
          q.add(next.rightChild);
        output += next.data.toString();
        if (!q.isEmpty())
          output += ", ";
      }
      return output + "]";
    }
  }

  protected Node<T> root; // reference to root node of tree, null when empty

  /**
   * Performs a naive insertion into a binary search tree: adding the input data value to a new node
   * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
   * balance the tree. This tree will not hold null references, nor duplicate data values.
   * 
   * @param data to be added into this binary search tree
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when the tree already contains data
   */
  public void insert(T data) throws NullPointerException, IllegalArgumentException {
    // null references cannot be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");

    Node<T> newNode = new Node<>(data);
    if (root == null) {
      root = newNode;
    } // add first node to an empty tree
    else {
      insertHelper(newNode, root);
      System.out.println(root);// recursively insert into subtree
    }
    root.isBlack = true;
  }

  /**
   * This method handles all the cases when added a new node to the RedBlackTree and do changes to
   * reorganized the tree to make it fit all the specialties.
   * 
   * @param newRedNode - the new node added to the RedBlackTree
   */
  private void enforceRBTreePropertiesAfterInsert(Node<T> newRedNode) {
    // case1: change the node color to black if the inserted node is the root node
    if (newRedNode.parent == null) {
      newRedNode.isBlack = true;
    } else if (newRedNode.parent.isBlack == true) {
      // case2: parent node is black
    } else if (newRedNode.parent.isBlack == false) {
      // create a parent and grantParent node
      Node<T> parent = newRedNode.parent;
      Node<T> grandParent = parent.parent;
      // parent node is in the left position
      if (parent.isLeftChild()) {
        Node<T> uncle = grandParent.rightChild;
        // case3(1): when uncle equals null, consider uncle as a black node
        if (uncle == null) {
          if (!newRedNode.isLeftChild()) {
            Node<T> current = parent;
            grandParent.leftChild = leftRotate(current);
            // recursively call the method
            enforceRBTreePropertiesAfterInsert(current);
          } else {
            // case5(1): parent is red, uncle is black,and current node is the left child of parent
            parent.isBlack = true;
            // change color
            grandParent.isBlack = false;
            Node<T> current = grandParent;
            if (grandParent.parent != null) {
              if (grandParent.parent.leftChild == grandParent) {
                grandParent.parent.leftChild = rightRotate(current);
              } else {
                grandParent.parent.rightChild = rightRotate(current);
              }
            } else {
              root = rightRotate(current);
            }
          }
          // when uncle isn't equal to null
        } else {
          // case3(2): both uncle and parent are red node
          if (uncle.isBlack == false) {
            parent.isBlack = true;
            uncle.isBlack = true;
            grandParent.isBlack = false;
            enforceRBTreePropertiesAfterInsert(grandParent);
          } else {
            // case4: parent is red, uncle is black, and current node is the right child of parent
            if (!newRedNode.isLeftChild()) {
              Node<T> current = parent;
              grandParent.leftChild = leftRotate(current);
              enforceRBTreePropertiesAfterInsert(parent);
            } else {
              // case5(2): parent is red, uncle is black,and current node is the left child of
              // parent
              parent.isBlack = true;
              // change color
              grandParent.isBlack = false;
              Node<T> current = grandParent;
              if (grandParent.parent != null) {
                if (grandParent.parent.leftChild == grandParent) {
                  grandParent.parent.leftChild = rightRotate(current);
                } else {
                  grandParent.parent.rightChild = rightRotate(current);
                }
              } else {
                root = rightRotate(current);
              }
            }
          }
        }
        // parent is the right child of grandparent
      } else {
        Node<T> uncle = grandParent.leftChild;
        // case6(1): when uncle equals null, consider uncle as a black node
        if (uncle == null) {
          if (newRedNode.isLeftChild()) {
            Node<T> current = parent;
            grandParent.rightChild = rightRotate(current);
            // recursively call the method
            enforceRBTreePropertiesAfterInsert(parent);
          } else {
            // case8(1): parent is red, uncle is black, and current node is the right child of
            // parent
            parent.isBlack = true;
            grandParent.isBlack = false;
            Node<T> current = grandParent;
            if (grandParent.parent != null) {
              if (grandParent.parent.leftChild == grandParent) {
                grandParent.parent.leftChild = leftRotate(current);
              } else {
                grandParent.parent.rightChild = leftRotate(current);
              }
            } else {
              root = leftRotate(current);
            }
          }
          // uncle node isn't equal to null
        } else {
          // case6(2): both parent and uncle node is red
          if (uncle.isBlack == false) {
            parent.isBlack = true;
            uncle.isBlack = true;
            grandParent.isBlack = false;
            enforceRBTreePropertiesAfterInsert(grandParent);
          } else {
            // case7(2): parent is red, uncle is black, and current node is the left child of parent
            if (newRedNode.isLeftChild()) {
              Node<T> current = parent;
              grandParent.rightChild = rightRotate(current);
              enforceRBTreePropertiesAfterInsert(parent);
            } else {
              // case8(2): parent is red, uncle is black,and current node is the right child of
              // parent
              parent.isBlack = true;
              grandParent.isBlack = false;
              Node<T> current = grandParent;
              if (grandParent.parent != null) {
                if (grandParent.parent.leftChild == grandParent) {
                  grandParent.parent.leftChild = leftRotate(current);
                } else {
                  grandParent.parent.rightChild = leftRotate(current);
                }
              } else {
                root = leftRotate(current);
              }
            }
          }
        }
      }
    }
  }

  /**
   * Recursive helper method to find the subtree with a null reference in the position that the
   * newNode should be inserted, and then extend this tree by the newNode in that position.
   * 
   * @param newNode is the new node that is being added to this tree
   * @param subtree is the reference to a node within this tree which the newNode should be inserted
   *                as a descenedent beneath
   * @throws IllegalArgumentException when the newNode and subtree contain equal data references (as
   *                                  defined by Comparable.compareTo())
   */
  private void insertHelper(Node<T> newNode, Node<T> subtree) {
    int compare = newNode.data.compareTo(subtree.data);
    // do not allow duplicate values to be stored within this tree
    if (compare == 0)
      throw new IllegalArgumentException("This RedBlackTree already contains that value.");

    // store newNode within left subtree of subtree
    else if (compare < 0) {
      if (subtree.leftChild == null) { // left subtree empty, add here
        subtree.leftChild = newNode;
        newNode.parent = subtree;
        enforceRBTreePropertiesAfterInsert(newNode);
        // otherwise continue recursive search for location to insert
      } else
        insertHelper(newNode, subtree.leftChild);
    }

    // store newNode within the right subtree of subtree
    else {
      if (subtree.rightChild == null) { // right subtree empty, add here
        subtree.rightChild = newNode;
        newNode.parent = subtree;
        enforceRBTreePropertiesAfterInsert(newNode);
        // otherwise continue recursive search for location to insert
      } else
        insertHelper(newNode, subtree.rightChild);
    }
  }

  /**
   * This method performs a level order traversal of the tree. The string representations of each
   * data value within this tree are assembled into a comma separated string within brackets
   * (similar to many implementations of java.util.Collection, like java.util.ArrayList, LinkedList,
   * etc).
   * 
   * @return string containing the values of this tree in level order
   */
  @Override
  public String toString() {
    return root.toString();
  }

  /**
   * Performs the rotation operation on the provided nodes within this BST. When the provided child
   * is a leftChild of the provided parent, this method will perform a right rotation (sometimes
   * called a left-right rotation). When the provided child is a rightChild of the provided parent,
   * this method will perform a left rotation (sometimes called a right-left rotation). When the
   * provided nodes are not related in one of these ways, this method will throw an
   * IllegalArgumentException.
   * 
   * @param child  is the node being rotated from child to parent position (between these two node
   *               arguments)
   * @param parent is the node being rotated from parent to child position (between these two node
   *               arguments)
   * @throws IllegalArgumentException when the provided child and parent node references are not
   *                                  initially (pre-rotation) related that way
   */
  private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
    // throw IllegalArgumentException if the provided nodes are not related
    if (parent.leftChild != child && parent.rightChild != child) {
      throw new IllegalArgumentException("the provided child and parent "
          + "node references are not initially (pre-rotation) related that way");
    }
    // check if parent equal to root
    if (parent == root) {
      if (child.isLeftChild()) {
        Node<T> newRightNode = child.rightChild;
        // change child's right node to be parent's node
        parent.leftChild = null;
        child.rightChild = parent;
        parent.parent = child;
        // set parent's leftChild to be child's rightChild
        parent.leftChild = newRightNode;
        root = child;
      } else {
        Node<T> newLeftNode = child.leftChild;
        parent.rightChild = null;
        // change child's left node to be parent's node
        child.leftChild = parent;
        parent.parent = child;
        // set parent;s rightChild to be child's leftChild
        parent.rightChild = newLeftNode;
        root = child;
      }
    } else {
      // if parent are not the root, store parent's parent to a new node
      Node<T> grandNode = parent.parent;
      Node<T> oriParent = parent;
      if (child.isLeftChild()) {
        Node<T> newRightNode = child.rightChild;
        parent.leftChild = null;
        // change child's right node to be parent's node
        child.rightChild = parent;
        parent.parent = child;
        // set parent;s leftChild to be child's rightChild
        parent.leftChild = newRightNode;
      } else {
        Node<T> newLeftNode = child.leftChild;
        parent.rightChild = null;
        // change child's left node to be parent's node
        child.leftChild = parent;
        parent.parent = child;
        // set parent;s rightChild to be child's leftChild
        parent.rightChild = newLeftNode;
      }
      // set parent's parent's leftChild to be child's node
      if (grandNode.leftChild == oriParent) {
        grandNode.leftChild = child;
        child.parent = grandNode;
      }
      // set parent's parent;s rightChild to be child's node
      if (grandNode.rightChild == oriParent) {
        grandNode.rightChild = child;
        child.parent = grandNode;
      }
    }
  }

  /**
   * private helper method to right rotate the current node
   * 
   * @param newNode is the new node that is being added to this tree
   * 
   * @return the new child node
   */
  private Node<T> rightRotate(Node<T> redBlackNode) {
    // change child's right node to be parent's node
    Node<T> left = redBlackNode.leftChild;
    // change the child's rightChild to parent's leftChild
    redBlackNode.leftChild = left.rightChild;
    left.rightChild = redBlackNode;
    left.parent = redBlackNode.parent;
    redBlackNode.parent = left;
    // check if the leftChild is null
    if (redBlackNode.leftChild != null) {
      redBlackNode.leftChild.parent = redBlackNode;
    }
    return left;
  }

  /**
   * private helper method to left rotate the current node
   * 
   * @param newNode is the new node that is being added to this tree
   * 
   * @return the new child node
   */
  private Node<T> leftRotate(Node<T> redBlackNode) {
    // change child's right node to be parent's node
    Node<T> rightChild = redBlackNode.rightChild;
    // change the child's rightChild to parent's leftChild
    redBlackNode.rightChild = rightChild.leftChild;
    rightChild.leftChild = redBlackNode;
    rightChild.parent = redBlackNode.parent;
    redBlackNode.parent = rightChild;
    // check if the rightChild is null
    if (redBlackNode.rightChild != null) {
      redBlackNode.rightChild.parent = redBlackNode;
    }
    return rightChild;
  }

}

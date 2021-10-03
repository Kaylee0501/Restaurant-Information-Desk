
CS400 Project Four Proposal  
TEAM EMAILS: 1. xli2224@wisc.edu 2. slee986@wisc.edu 3. yli2244@wisc.edu 4. vasquezgarci@wisc.edu 5. bhuffar@wisc.edu 6. shallhoub@wisc.edu 7. rwasserman2@wisc.edu 8. agrawal28@wisc.edu



Project Title: Restaurant Information Desk

Brief Project Description:  
Main Function:  
It will show the shortest path to a given restaurant to other restaurants  
It will show the general information about each restaurant, menus, reviews(stars if we choose to rank them), for example.  
Expected users:  
People who would like to decide which restaurant to eat before leaving their houses,  
As the application will provide helpful information to do so.  

Four Chosen Requirements that this Project Fulfills:

Red-Black Tree data structure: It will be used to store the menus of each restaurant. 

Dijkstra’s shortest path algorithm: It will be used to calculate the shortest path from restaurant to restaurant.

HTML and CSS: It will be used to visualize the app with its functionalities. It will display the option list and output sent from the backend.

JavaFX: It will be used to interact with users to utilize the app more efficiently. It will receive the information from users as input and manipulate those to generate essential information users need to have.



Data Wranglers: Neyanel Vasquez-Garcia Shourya Agrawal 

Application Data:  
<brief description of what the data being loaded by this program represents>  
We will write the code that is able to extract the data from a .txt and .csv  
Data Format:  
<define the Java type(s) that this data will be store in after it is loaded>  
We will have a text file with the names of the restaurants and the Michelin stars. 



Back End Developer: Xintong Li Brock Huffar 

Data Processing:  
Use Dijkstra's shortest path algorithm to find the shortest path from different restaurants, and then we can put each restaurants' menu in a red-black tree.

Front End Interface:  
<define the Java interface by listing the specific method signatures that exposes this functionality to the front end of your application>

public void insert(String food, double price) throws IllegalArgumentException {}  
public boolean insertVertex(Restaurant name)  
public boolean removeVertex(Restaurant name)  
public boolean insertEdge(Restaurant source, Restaurant target, int weight)  
public boolean removeEdge(Restaurant source, Restaurant target)  
public boolean containsVertex(Restaurant name)  
public boolean containsEdge(Restaurant source, Restaurant target)  
public int getWeight(Restaurant source, Restaurant target)  
public boolean isEmpty()  
public List<T> shortestPath(Restaurant start, Restaurant end)  
public int getPathCost(Restaurant start, Restaurant end)  
public boolean changeName(String oldName, String newName)  

Restaurant class:  
Restaurant<String name, linkedList<Menu> menu>  
public Restaurant(String name, Menu menu)  
public String getName()  
public void setName(String name)  
public Menu getMenu()  

Menu class:  
Menu<String food, double price>  
public Menu(String food, double price)  
public String getFood()  
public void setName(String name)  
public Double getprice()  


public void insert(String food, double price) throws IllegalArgumentException {}  
public boolean containsFood(String food){}  
public Double searchFoodPrice(String food) throws IllegalArgumentException) {}  
public void changeName(String oldName, String newName)  






Front End Developer: John Lee Khalid Shallhoub 

User Commands:  
UserInput() - Take input from the user either through text box or JS  
FindPath() - This will find the path from one location to another and update the map  
ClearAll() - This command will clear all locations and paths  
DeleteLocation() - Delete a pre-existing location from the map  

Error Messages:  
An error message in the case that no path exists  
An error message in the case of having a valid input (ex. Too long of a string or a pre-existing location)  



Test Engineer: Lillian Li Rex Wasserman  
Engineers will communicate with each other.

Test Descriptions:  
<list brief (one line) summaries for each of the key tests that you will implement for this application>  
Public void testAdd() // tests adding a restaurant  
Public void remove() //test removing a restaurant  
Public void testPathSequence() // check the data sequence of the shortest paths for the restaurants  
Public void testPathDsitance() // check the distance between two restaurants  
Public void testComplexPath1() // create a complicated scenario and check the validity of the path  
Public void testComplexPath1() // create a second complicated scenario and check the validity of the path



Additional Responsibilities and Notes:  
<list by role, any additional responsibilities that are expected of team members to help balance the workload for this project… if you are concerned about your project being too simple or too involved, this is also a good place to suggest plans for expanding or contracting your main idea>


Schedule:

Due 11/24  
Data Wranglers  
We will provide the .txt and .csv data files  
Back End Developers  
Finish the menu and Restaurant class
Front End Developers  
HTML Body with template  
Test Engineers  
Initial JUnit test case template  


Due 12/01  
Data Wranglers  
We will write the code that is able to extract the data from a .txt and .csv on github.  
Back End Developers  
Use Dijkstra's shortest path algorithm to find the shortest path  
Front End Developers  
HTML Template implementing CSS Selectors  
Test Engineers  
Preliminary tests based on the proposal  



Due 12/08  
Data Wranglers  
Be available for trouble shooting our code if need be  
Back End Developers  
Use the red Black tree to create a menu list, and check the whole backEnd  
Front End Developers  
Further additions to HTML template to use other’s JS implementation  
Test Engineers  
Tests that thoroughly check the functionality of the program  

Signatures  
Lillian Li 11/17/2020  
Xintong Li 11/17/2020  
Brock Huffar 11/17/2020  
Rex Wasserman 11/17/2020  
Khalid Shallhoub 11/17/2020  
Shourya Agrawal 11/18/2020  
John Lee 11/18/2020  
Brock Huffar 11/18/2020


End of Proposal


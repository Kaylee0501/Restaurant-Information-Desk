// --== CS400 File Header Information ==--
// Name: Khalid Shallhoub
// Email: shallhoub@wisc.edu
// Team: DB
// TA: Yelun
// Lecturer: Professor Gary Dahl

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import java.util.Set;
import java.util.Iterator;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/**
 * @author Khalid Shallhoub, project 4 JavaFX database
 */
public class project4FX extends Application {
	BackEnd backEnd = new BackEnd();
	AlertBox alertBox = new AlertBox();
	TextField textFieldEdge;
	TextField textFieldFName;
	TextField textFieldFPrice;
	TextField textFieldRName;
	TextField textFieldRName1;
	TextField textFieldRName2;
	TextField textFieldRName3;
	TextField textFieldRName4;
	TextField textFieldVertex1;
	TextField textFieldVertex2;
	TextField textFieldRem1;
	TextField textFieldRem2;
	TextField textFieldFromFile;
	TextField textOieldOldName;
	TextField textFieldNewName;
	TextField textFieldFind1;
	TextField textFieldFind2;
	TextField textFieldFindNeigh;
	TextField textFieldChangeNew;
	TextField textFieldChangeOld;
	TextField textFieldFindFName;
	TextField textFieldFindFRName;
	TextField textFieldFChgRName;
	TextField textFieldOldFName;
	TextField textFieldNewFName;
	TextField textFieldRangeRName;
	TextField textFieldRangeLow;
	TextField textFieldRangeHigh;

	/**
	 * Private helper method to retrieve restaurant objects using their String name.
	 * 
	 * @param txtName Restaurant's name
	 * @return Corresponding restaurant object
	 */
	private Restaurant helperSearch(String txtName) {
		Set<Restaurant> setToIterate = backEnd.vertices.keySet();
		Iterator<Restaurant> setIritator = setToIterate.iterator();

		while (setIritator.hasNext()) {
			Restaurant test = setIritator.next();
			if (test.getName().equals(txtName)) {
				return test;
			}
		}
		return null;
	}

	/**
	 * Private helper method that resets all text field after a button has been
	 * called to have the default text.
	 * 
	 * @return void
	 */
	private void resetAllFields() {
		textFieldRName.setText("Enter restaurant Name"); // TextField to insert restaurant name**
		textFieldRName1.setText("Enter restaurant Name"); // Textfield to insert restaurant name 1 (for adding menu)**
		textFieldRName2.setText("Enter restaurant Name"); // Textfield to insert restaurant name 2 (for deletion)**
		textFieldRName3.setText("Enter restaurant Name"); // Textfield to insert restaurant name 3 (for finding)**
		textFieldRName4.setText("Enter restaurant Name"); // Textfield to find restaurant
		textFieldFName.setText("Enter Food to add to menu");// Textfield to insert food name**
		textFieldFPrice.setText("Enter food price");// Textfield to insert food price**
		textFieldVertex1.setText("Enter first restaurant");// Textfield for first vertex**
		textFieldVertex2.setText("Enter second restaurant");// Textfied for second vertex**
		textFieldEdge.setText("Enter distance between two restaurants");// Textfield for distance entry**
		textFieldRem1.setText("Enter first restaurant");
		textFieldRem2.setText("Enter second restaurant");
		textFieldFromFile.setText("Enter file path");
		textFieldFind1.setText("Enter first restaurant");
		textFieldFind2.setText("Enter second restaurant");
		textFieldFindNeigh.setText("Enter restaurant name");
		textFieldChangeOld.setText("Enter current restaurant name");
		textFieldChangeNew.setText("Enter new restaurant name");
		textFieldFindFRName.setText("Enter restaurant name");
		textFieldFindFName.setText("Enter food name");
		textFieldFChgRName.setText("Enter restaurant name");
		textFieldOldFName.setText("Enter current food's name");
		textFieldNewFName.setText("Enter new food's name");
		textFieldRangeRName.setText("Enter restaurant name");
		textFieldRangeLow.setText("Enter lower price range");
		textFieldRangeHigh.setText("Enter higher price range");
	}

	/**
	 * @author Shourya Agrawal
	 */
	public void processCommand(String s) {

		try {
			if (s.charAt(0) == 'A') {
				String strArr[] = s.substring(2).trim().split(",");

				String resName = strArr[0];
				String dishName = strArr[1];

				double price = Double.parseDouble(strArr[2]); // Parse
				helperSearch(resName).getMenu().insert(dishName, price);
			} else if (s.charAt(0) == 'C') {

				String resName = s.substring(2).trim();

				Restaurant newRes = new Restaurant(resName, new Menu());
				backEnd.insertVertex(newRes); // adds restaurant
			} else if (s.charAt(0) == 'D') {

				String strArr[] = s.substring(2).trim().split(",");

				String res1 = strArr[0];
				String res2 = strArr[1];
				int dist = Integer.parseInt(strArr[2]);

				backEnd.insertEdge(helperSearch(res1), helperSearch(res2), dist);
			} else if (s.charAt(0) == 'Q') {
				System.out.println("Thanks for using our program");
			}

		} catch (Exception e) {
			System.out.println("Exception caught");
		}

	}

	/**
	 * @author Shourya Agrawal
	 */
	public void processFile(File f) {

		Scanner sc = null;
		try {

			sc = new Scanner(f); // can throw null error

			while (sc.hasNextLine()) {

				String nextLine = sc.nextLine().trim();

				if (nextLine.length() > 0) {
					processCommand(nextLine);
				}
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Unable to read " + f);
		} finally {
			// if its already not closed
			if (sc != null)
				sc.close();
		}
	}

	@Override
	public void start(final Stage stage) {
		Label label = new Label("Welcome to our project 4"); // first label to introduce user
		Label labelNl = new Label("\n\n\n"); // label to create space in HBox or VBox
		Label labelNl2 = new Label("\n\n\n"); // label to create space in HBox or VBox
		Label labelNl3 = new Label("\n\n\n"); // label to create space in HBox or VBox
		Label labelNl4 = new Label("\n\n\n"); // label to create space in HBox or VBox
		Label labelEnterNew = new Label("\nEnter a new restaurant"); // Label to show section where to add a restaurant.
		Label labelEnterEdge = new Label("\nEstablish a path between two restaurant"); // Label to show section where to
																						// establish path
		Label labelEnterFood = new Label("Enter a food to an existing restaurant"); // Label to show section where to
																					// add food
		Label labelDelete = new Label("Delete a restaurant"); // Label to delete a restaurant from database
		Label labelFindRest = new Label("Find a restaurant and its menu");
		Label labelRemEdge = new Label("Remove a path");
		Label labelFromFile = new Label("Get data from a file");
		Label labelFindPath = new Label("\nFind shortest path between 2 restaurants");
		Label labelFindNeigh = new Label("\nFind neighbors of restaurant");
		Label labelChangeName = new Label("\nChange name of a restaurant");
		Label labelFindFood = new Label("\nFind price of food");
		Label labelFindRange = new Label("\nFind foods within a price range");
		Label labelChangeFoodName = new Label("\nChange name of a food");

		labelEnterNew.setStyle("-fx-font-weight: bold;"); // Style label to be in bold
		labelEnterEdge.setStyle("-fx-font-weight: bold;"); // Style label to be in bold
		labelEnterFood.setStyle("-fx-font-weight: bold;"); // Style label to be in bold
		labelDelete.setStyle("-fx-font-weight: bold;"); // Style label to be in bold
		labelFindRest.setStyle("-fx-font-weight: bold;"); // Style label to be in bold
		label.setStyle("-fx-font-weight: bold;"); // Style label to be in bold
		labelRemEdge.setStyle("-fx-font-weight: bold;"); // Style label to be in bold
		labelFromFile.setStyle("-fx-font-weight: bold;"); // Style label to be in bold
		labelFindPath.setStyle("-fx-font-weight: bold;"); // Style label to be in bold
		labelFindNeigh.setStyle("-fx-font-weight: bold;"); // Style label to be in bold
		labelChangeName.setStyle("-fx-font-weight: bold;"); // Style label to be in bold
		labelFindFood.setStyle("-fx-font-weight: bold;"); // Style label to be in bold
		labelFindRange.setStyle("-fx-font-weight: bold;"); // Style label to be in bold
		labelChangeFoodName.setStyle("-fx-font-weight: bold;"); // Style label to be in bold

		Button button = new Button("Submit a new restaurant"); // Button to submit new restaurant**
		Button buttonClear = new Button("Clear all entries"); // Button to clear all entries**
		Button buttonAddEdge = new Button("Add a path between two restaurants"); // Button to add a path between 2
																					// places
		Button buttonDelete = new Button("Delete a restaurant"); // Button to delete a restaurant
		Button buttonMenu = new Button("Add a food to a menu"); // Button to add a food to a restaurant menu
		Button buttonFindRes = new Button("Find Resaurant"); // Button to find restaurant
		Button buttonExit = new Button("Click here to exit"); // Button to exit app
		Button buttonRemoveEdge = new Button("Remove path"); // Button to remove an edge/path
		Button buttonFromFile = new Button("Get from file"); // Button to receive from file
		Button buttonFindPath = new Button("Find path"); // Button to find path
		Button buttonResetFields = new Button("Reset all text fields"); // Button to reset all text fields
		Button buttonFindNeigh = new Button("Find neigbours"); // Button to find neighbors
		Button buttonChangeName = new Button("Change name"); // Button to change name of a restaurant
		Button buttonFindFood = new Button("Find price of food"); // Button to find price of a food stored in a
																	// restaurant
		Button buttonChangeFoodName = new Button("Change food name");
		Button buttonFindRange = new Button("Find foods");
		textFieldRName = new TextField("Enter restaurant Name"); // TextField to insert restaurant name**
		textFieldRName1 = new TextField("Enter restaurant Name"); // Textfield to insert restaurant name 1 (for adding
																	// menu)**
		textFieldRName2 = new TextField("Enter restaurant Name"); // Textfield to insert restaurant name 2 (for
																	// deletion)**
		textFieldRName3 = new TextField("Enter restaurant Name"); // Textfield to insert restaurant name 3 (for
																	// finding)**
		textFieldRName4 = new TextField("Enter restaurant Name"); // Textfield to find restaurant
		textFieldFName = new TextField("Enter Food to add to menu");// Textfield to insert food name**
		textFieldFPrice = new TextField("Enter food price");// Textfield to insert food price**
		textFieldVertex1 = new TextField("Enter first restaurant");// Textfield for first vertex**
		textFieldVertex2 = new TextField("Enter second restaurant");// Textfied for second vertex**
		textFieldEdge = new TextField("Enter distance between two restaurants");// Textfield for distance entry**
		textFieldRem1 = new TextField("Enter first restaurant");// Textfield of first restaurant to remove edge
		textFieldRem2 = new TextField("Enter second restaurant");// Textfield of second restaurant to remove edge
		textFieldFromFile = new TextField("Enter file path");// Textfield to receieve file path to load data from (data
																// wrangler)
		textFieldFind1 = new TextField("Enter first restaurant");
		textFieldFind2 = new TextField("Enter second restaurant");
		textFieldFindNeigh = new TextField("Enter restaurant name");
		textFieldChangeOld = new TextField("Enter current restaurant name");
		textFieldChangeNew = new TextField("Enter new restaurant name");
		textFieldFindFRName = new TextField("Enter restaurant name");
		textFieldFindFName = new TextField("Enter food name");
		textFieldFChgRName = new TextField("Enter restaurant name");
		textFieldOldFName = new TextField("Enter current food's name");
		textFieldNewFName = new TextField("Enter new food's name");
		textFieldRangeRName = new TextField("Enter restaurant name");
		textFieldRangeLow = new TextField("Enter lower price range");
		textFieldRangeHigh = new TextField("Enter higher price range");
		TextArea displayText = new TextArea("Program output will be displayed here");

		button.setOnAction(event -> {
			try {
				Restaurant newR = new Restaurant(textFieldRName.getText(), new Menu());
				if (backEnd.insertVertex(newR)) {
					displayText.setText(textFieldRName.getText() + " has been inserted successfully");
				} else {
					displayText.setText("There was an error in serting your stated restaurant");
				}
				resetAllFields();
			} catch (Exception e) {
				alertBox.display("Error", e.getClass().getName() + "\n" + e.getMessage());
			}
		});
		buttonClear.setOnAction(event -> {
			backEnd = new BackEnd();
		});

		buttonMenu.setOnAction(event -> {
			try {
				helperSearch(textFieldRName1.getText()).getMenu().insert(textFieldFName.getText(),
						Double.parseDouble(textFieldFPrice.getText()));
				displayText.setText("The food " + textFieldFName.getText()
						+ " has been inserted successfully to the restaurant: " + textFieldRName1.getText());
				resetAllFields();
			} catch (Exception e) {
				alertBox.display("Error", e.getClass().getName() + "\n" + e.getMessage());
			}
		});

		buttonExit.setOnAction(event -> {
			stage.close();
		});

		buttonFindRes.setOnAction(event -> {
			try {
				displayText.setText(helperSearch(textFieldRName3.getText()).toString());
				resetAllFields();
			} catch (Exception e) {
				alertBox.display("Error", e.getClass().getName() + "\n" + e.getMessage());
			}
		});

		buttonAddEdge.setOnAction(event -> {
			try {
				if (backEnd.insertEdge(helperSearch(textFieldVertex1.getText()),
						helperSearch(textFieldVertex2.getText()), Integer.parseInt(textFieldEdge.getText()))) {
					displayText.setText("An edge has been established between " + textFieldVertex1.getText() + " and "
							+ textFieldVertex2.getText() + " with the weight of " + textFieldEdge.getText());
				} else {
					displayText.setText("An error eccorued while establishing the edge between the stated restaurants");
				}
				resetAllFields();
			} catch (Exception e) {
				alertBox.display("Error", e.getClass().getName() + "\n" + e.getMessage());
			}
		});

		buttonDelete.setOnAction(event -> {
			try {
				if (backEnd.removeVertex(helperSearch(textFieldRName2.getText()))) {
					displayText.setText(textFieldRName2.getText() + " has been successfully removed");
				} else {
					displayText.setText("An error eccorued while attempting to remove the restaurant");
				}
				resetAllFields();
			} catch (Exception e) {
				alertBox.display("Error", e.getClass().getName() + "\n" + e.getMessage());
			}
		});

		buttonRemoveEdge.setOnAction(event -> {
			try {
				if (backEnd.removeEdge(helperSearch(textFieldRem1.getText()), helperSearch(textFieldRem2.getText()))) {
					displayText.setText("Path between " + textFieldRem1.getText() + " and " + textFieldRem2.getText()
							+ " has been removed");
				} else {
					displayText.setText("There was an error in removing the paths between both stated restaurants");
				}
				resetAllFields();
			} catch (Exception e) {
				alertBox.display("Error", e.getClass().getName() + "\n" + e.getMessage());
			}
		});

		buttonFindPath.setOnAction(event -> {
			try {
				displayText.setText(
						"The shortest path between " + textFieldFind1.getText() + " and " + textFieldFind2.getText()
								+ " is ---> " + backEnd.shortestPath(helperSearch(textFieldFind1.getText()),
										helperSearch(textFieldFind2.getText())).toString());
			} catch (Exception e) {
				alertBox.display("Error", e.getClass().getName() + "\n" + e.getMessage());
			}
			resetAllFields();
		});

		buttonResetFields.setOnAction(event -> {
			resetAllFields();
		});

		buttonFindNeigh.setOnAction(event -> {
			try {
				displayText.setText("The neighbors of " + textFieldFindNeigh.getText() + " are: "
						+ backEnd.findNeighbours(textFieldFindNeigh.getText()));
			} catch (Exception e) {
				alertBox.display("Error", e.getClass().getName() + "\n" + e.getMessage());
			}
			resetAllFields();
		});

		buttonChangeName.setOnAction(event -> {
			try {
				backEnd.changeName(textFieldChangeOld.getText(), textFieldChangeOld.getText());
				displayText.setText(
						textFieldChangeOld.getText() + "'s name has been changed to " + textFieldChangeNew.getText());
			} catch (Exception e) {
				alertBox.display("Error", e.getClass().getName() + "\n" + e.getMessage());
			}
			resetAllFields();
		});

		buttonFindFood.setOnAction(event -> {
			try {
				displayText.setText("The price of " + textFieldFindFName.getText() + " in "
						+ textFieldFindFRName.getText() + " is " + helperSearch(textFieldFindFRName.getText()).getMenu()
								.searchADish(textFieldFindFName.getText())
						+ "$");
			} catch (Exception e) {
				alertBox.display("Error", e.getClass().getName() + "\n" + e.getMessage());
			}
			resetAllFields();
		});

		buttonFindRange.setOnAction(event -> {
			try {
				displayText.setText("The foods from the restraunt " + textFieldRangeRName.getText() + " between "
						+ textFieldRangeLow.getText() + "$ and " + textFieldRangeHigh.getText() + "$ are "
						+ helperSearch(textFieldRangeRName.getText()).getMenu()
								.searchPriceRange(Double.parseDouble(textFieldRangeLow.getText()),
										Double.parseDouble(textFieldRangeHigh.getText()))
								.toString());
			} catch (Exception e) {
				alertBox.display("Error", e.getClass().getName() + "\n" + e.getMessage());
			}
			resetAllFields();
		});

		buttonChangeFoodName.setOnAction(event -> {
			try {
				helperSearch(textFieldFChgRName.getText()).getMenu().changeName(textFieldOldFName.getText(),
						textFieldNewFName.getText());
				displayText.setText("The name of " + textFieldOldFName.getText() + " in the restaurant "
						+ textFieldFChgRName.getText() + " has been successfully changed to "
						+ textFieldNewFName.getText());
			} catch (Exception e) {
				alertBox.display("Error", e.getClass().getName() + "\n" + e.getMessage());
			}
			resetAllFields();
		});

		buttonFromFile.setOnAction(event -> {
			try {
				processFile(new File(textFieldFromFile.getText()));
			} catch (Exception e) {
				alertBox.display("Error", e.getClass().getName() + "\n" + e.getMessage());
			}
			resetAllFields();
		});

		HBox hbox = new HBox(buttonClear, buttonExit, displayText, buttonResetFields);
		VBox vbox = new VBox(labelEnterNew, textFieldRName, button, labelNl2, labelEnterFood, textFieldRName1,
				textFieldFName, textFieldFPrice, buttonMenu, labelNl3, labelDelete, textFieldRName2, buttonDelete,
				labelNl, labelFindRest, textFieldRName3, buttonFindRes, labelChangeName, textFieldChangeOld,
				textFieldChangeNew, buttonChangeName, labelFindFood, textFieldFindFRName, textFieldFindFName,
				buttonFindFood, labelChangeFoodName, textFieldFChgRName, textFieldOldFName, textFieldNewFName,
				buttonChangeFoodName, labelFindRange, textFieldRangeRName, textFieldRangeLow, textFieldRangeHigh,
				buttonFindRange);
		VBox vbox1 = new VBox(labelEnterEdge, textFieldVertex1, textFieldVertex2, textFieldEdge, buttonAddEdge, labelNl,
				labelRemEdge, textFieldRem1, textFieldRem2, buttonRemoveEdge, labelNl2, labelFromFile,
				textFieldFromFile, buttonFromFile, labelFindPath, textFieldFind1, textFieldFind2, buttonFindPath,
				labelFindNeigh, textFieldFindNeigh, buttonFindNeigh);
		BorderPane borderpane = new BorderPane();
		borderpane.setTop(label);
		borderpane.setBottom(hbox);
		borderpane.setLeft(vbox);
		borderpane.setRight(vbox1);
		borderpane.setAlignment(label, Pos.CENTER);

		WebView webView = new WebView();
		WebEngine webEngine = webView.getEngine();
		webEngine.load("http://35.187.102.24/frontend1.html");
		borderpane.setCenter(webView);
		Scene scene = new Scene(borderpane, 1200, 1200);
		stage.setScene(scene);
		stage.setTitle("CS400 Project #4 DB");
		stage.show();

	}

	public static void main(String[] args) {
		Application.launch();
	}
}
// --== CS400 File Header Information ==--
// Name: Khalid Shallhoub
// Email: shallhoub@wisc.edu
// Team: DB
// TA: Yelun
// Lecturer: Professor Gary Dahl
//

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {
    /**
     * @author Khalid Shallhoub
     */

     
    /**
     * This function is to display an alert box with relevant details
     * such as the excpetion's message and the exception's type:
     * obtained from any exception thrown within the project4FX class. 
     * @param title Title of the window to be displayed.
     * @param message Message to be displayed to the user.
     */
    public static void display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        Label label = new Label(message);
        Button button = new Button("Understood error");
        button.setOnAction(event -> window.close());
        VBox layout = new VBox();
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}

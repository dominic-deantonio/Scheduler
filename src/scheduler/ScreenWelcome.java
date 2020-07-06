package scheduler;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ScreenWelcome {
    
    ScreenWelcome(Stage primaryStage){
        Log.log("---------------------Application started---------------------");
        primaryStage.setTitle("System Use Notification");
        GridPane grid = new GridPane();
        // Note Position is geometric object for alignment
        grid.setAlignment(Pos.CENTER);
        // Set gap between the components
        // Larger numbers mean bigger spaces
        grid.setHgap(10);
        grid.setVgap(10);

        // Create some text to place in the scene
        Text welcomeMessage = new Text("Welcome to Super Scheduler!");
        welcomeMessage.setWrappingWidth(500);
        welcomeMessage.setTextAlignment(TextAlignment.CENTER);
        grid.add(welcomeMessage, 0, 0, 2, 1);

        // Create acceptance Button
        Button loginButton = new Button("Login");
        // Add button to grid 1,4
        grid.add(loginButton, 1, 4);

        //If this button is clicked run the program
        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
//                Log.log("User accepted system use notification");
//                ScreenLogin.run(primaryStage);
            }
        });

        // Create acceptance Button
        Button signupButton = new Button("Sign up");
        // Add button to grid 1,4
        grid.add(signupButton, 2, 4);

        //If this button is clicked run the program
        signupButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                Log.log("User accepted system use notification");
//                ScreenLogin.run(primaryStage);
            }
        });

        // Set the size of Scene
        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        Log.log("Displayed system use notification");
    }

    public static void run(Stage primaryStage) {
        Log.log("---------------------Application started---------------------");
        primaryStage.setTitle("System Use Notification");
        GridPane grid = new GridPane();
        // Note Position is geometric object for alignment
        grid.setAlignment(Pos.CENTER);
        // Set gap between the components
        // Larger numbers mean bigger spaces
        grid.setHgap(10);
        grid.setVgap(10);

        // Create some text to place in the scene
        Text welcomeMessage = new Text("Welcome to Super Scheduler!");
        welcomeMessage.setWrappingWidth(500);
        welcomeMessage.setTextAlignment(TextAlignment.CENTER);
        grid.add(welcomeMessage, 0, 0, 2, 1);

        // Create acceptance Button
        Button loginButton = new Button("Login");
        // Add button to grid 1,4
        grid.add(loginButton, 1, 4);

        //If this button is clicked run the program
        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
//                Log.log("User accepted system use notification");
//                ScreenLogin.run(primaryStage);
            }
        });

        // Create acceptance Button
        Button signupButton = new Button("Sign up");
        // Add button to grid 1,4
        grid.add(signupButton, 2, 4);

        //If this button is clicked run the program
        signupButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                Log.log("User accepted system use notification");
//                ScreenLogin.run(primaryStage);
            }
        });

        // Set the size of Scene
        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        Log.log("Displayed system use notification");
    }
}

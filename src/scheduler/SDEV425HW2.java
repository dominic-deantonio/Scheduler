//Author: Dominic DeAntonio
//Class: UMUC SDEV 425 6380
//Date: 9 February 2020
package scheduler;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class SDEV425HW2 extends Application {

    @Override
    public void start(Stage primaryStage) {
//        runProgram(primaryStage);
        System.out.println("You are running the wrong start method");
    }

    //Called when the program ends
    @Override
    public void stop() {
//        //Don't save if the data never loaded, will overwrite the data.
//        if(Data.didLoad)
//            Data.saveDB();
//        
        Log.log("---------------------User quit the program---------------------");
    }

    //Run any command line arguments.
//    public static void main(String[] args) {
//        launch(args);
//    }
    //Displays an error message from the received errorcode
    public void displayErrorMessage(Text display, int errorCode, User u) {
        String msg = "";

        switch (errorCode) {
            case 0:
                msg = "This should not display"; //There was no error, no error message will display
                break;
            case 1:
                msg = "Incorrect username or password\nPlease try again";
                break;
            case 2:
                msg = "Too many login attempts\n";
                if (u != null) {
                    msg += "Try again in " + u.checkLockout() + " minutes.";
                }
                break;
            case 3:
                msg = "Incorrect code. Please try again.";
                break;
        }

        //Show the error message only if the errorcode is not 0
        if (errorCode != 0) {
            display.setFill(Color.FIREBRICK);
            display.setText(msg);
        }
    }

    //This runs after the user accepts the notification/disclaimer
    public void runLogin(Stage primaryStage) {
        Log.log("Awaiting user credential input");
        primaryStage.setTitle("SDEV425 Login");
        // Grid Pane divides your window into grids
        GridPane grid = new GridPane();
        // Align to Center
        // Note Position is geometric object for alignment
        grid.setAlignment(Pos.CENTER);
        // Set gap between the components
        // Larger numbers mean bigger spaces
        grid.setHgap(10);
        grid.setVgap(10);

        // Create some text to place in the scene
        Text scenetitle = new Text("Welcome. Login to continue.");
        // Add text to grid 0,0 span 2 columns, 1 row
        grid.add(scenetitle, 0, 0, 2, 1);

        // Create Label
        Label userName = new Label("User Name:");
        // Add label to grid 0,1
        grid.add(userName, 0, 1);

        // Create Textfield
        TextField userTextField = new TextField();
        // Add textfield to grid 1,1
        grid.add(userTextField, 1, 1);

        // Create Label
        Label pw = new Label("Password:");
        // Add label to grid 0,2
        grid.add(pw, 0, 2);

        // Create Passwordfield
        PasswordField pwBox = new PasswordField();
        // Add Password field to grid 1,2
        grid.add(pwBox, 1, 2);

        // Create Login Button
        Button btn = new Button("Login");
        // Add button to grid 1,4
        grid.add(btn, 1, 4);

        // This will serve as the error message display
        final Text errorTextDisplay = new Text("");
        grid.add(errorTextDisplay, 1, 6);

        // Set the Action when button is clicked
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

            }
        });

        // Set the size of Scene
        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //This is the first thing the user sees. User must accept the notification 
    //In order to proceed.
    public void runProgram(Stage primaryStage) {
        Log.log("---------------------Application started---------------------");
        primaryStage.setTitle("System Use Notification");
        GridPane grid3 = new GridPane();
        // Note Position is geometric object for alignment
        grid3.setAlignment(Pos.CENTER);
        // Set gap between the components
        // Larger numbers mean bigger spaces
        grid3.setHgap(10);
        grid3.setVgap(10);

        // Create some text to place in the scene
        Text notif = new Text("* * * * * * * * * * W A R N I N G * * * * * * * * * *\n"
                + "This computer system is the property of University of Maryland Global Campus."
                + "It is for authorized use only.  By using this system, all users acknowledge notice of,"
                + "and agree to comply with, the University’s Acceptable Use of Information Technology Resources Policy (“AUP”)."
                + "  Unauthorized or improper use of this system may result in"
                + "administrative disciplinary action, civil charges/criminal penalties, and/or other sanctions"
                + "as set forth in the University’s AUP. By continuing to use this system you indicate your awareness"
                + "of and consent to these terms and conditions of use."
                + "If you are physically located in the European Union, you may have additional rights per the GDPR.\n"
                + "Visit the web site dataprivacy.utk.edu for more information.\n"
                + "LOG OFF IMMEDIATELY if you do not agree to the conditions stated in this warning.\n\n"
                + "* * * * * * * * * * * * * * * * * * * * * * * *");
        notif.setWrappingWidth(500);
        notif.setTextAlignment(TextAlignment.CENTER);
        grid3.add(notif, 0, 0, 2, 1);

        // Create acceptance Button
        Button acceptBtn = new Button("Accept");
        // Add button to grid 1,4
        grid3.add(acceptBtn, 2, 4);

        //If this button is clicked run the program
        acceptBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                Log.log("User accepted system use notification");
                runLogin(primaryStage);
            }
        });

        // Create decline Button
        Button declineBtn = new Button("Decline");
        // Add button to grid 1,4
        grid3.add(declineBtn, 3, 4);

        //If this button is clicked, close out of the application
        declineBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                Log.log("User declined system use notification");
                Log.log("Quitting application");
                Platform.exit();
            }
        });

        // Set the size of Scene
        Scene scene = new Scene(grid3, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        Log.log("Displayed system use notification");
    }

    //This runs after the user enters a correct password. 
    //An code is sent to the user's email and the user must enter the code received
    public void doMultifactor(Stage primaryStage, User user) {
        primaryStage.setTitle("Multifactor Authentication");
        // Grid Pane divides your window into grids
        GridPane grid = new GridPane();
        // Align to Center
        // Note Position is geometric object for alignment
        grid.setAlignment(Pos.CENTER);
        // Set gap between the components
        // Larger numbers mean bigger spaces
        grid.setHgap(10);
        grid.setVgap(10);

        // Create some text to place in the scene
        Text scenetitle = new Text("We sent you an e-mail with a login code.\nPlease enter it below to log in.");
        // Add text to grid 0,0 span 2 columns, 1 row
        grid.add(scenetitle, 0, 0, 2, 1);

        // Create Label
        Label userName = new Label("Code:");
        // Add label to grid 0,1
        grid.add(userName, 0, 1);

        // Create Textfield
        TextField codeInput = new TextField();
        // Add textfield to grid 1,1
        grid.add(codeInput, 1, 1);

        // Create Login Button
        Button submitBtn = new Button("Submit");
        // Add button to grid 1,4
        grid.add(submitBtn, 1, 4);

        // This will serve as the error message display
        final Text errorTextDisplay = new Text("");
        grid.add(errorTextDisplay, 1, 6);

        Log.log("Starting multifactor authentication");
        String code = /*Multifactor.sendCode(user);*/ "fill this in later";
        Log.log("Sent code " + code + " to user " + user.name + " at " + user.email);

        // Set the Action when button is clicked
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                if (code.equals(codeInput.getText())) {
                    Log.log("User entered correct multifactor code - welcoming user");
                    //Welcome the user
                    grid.setVisible(false);
                    GridPane grid2 = new GridPane();
                    // Align to Center
                    // Note Position is geometric object for alignment
                    grid2.setAlignment(Pos.CENTER);
                    // Set gap between the components
                    // Larger numbers mean bigger spaces
                    grid2.setHgap(10);
                    grid2.setVgap(10);
                    Text sceneTitle = new Text("Welcome " + user.getName() + "!\nLast login was " + user.recordLogin());
                    // Add text to grid 0,0 span 2 columns, 1 row
                    grid2.add(sceneTitle, 0, 0, 2, 1);
                    Scene scene = new Scene(grid2, 500, 400);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } else {
                    Log.log("User entered incorrect code " + codeInput.getText() + " - rejecting multifactor attempt");
                    displayErrorMessage(errorTextDisplay, 3, user);
                }
            }
        });

        // Set the size of Scene
        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

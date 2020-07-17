package scheduler;

import scheduler.utilities.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScreenSignup {

    TextField firstNameTextField = new TextField();
    TextField lastNameTextField = new TextField();
    TextField emailTextField = new TextField();
    PasswordField passwordField = new PasswordField();

    public void run(Stage primaryStage) {
        Log.log("running sign up screen");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        // Set gap between the components
        grid.setHgap(10);
        grid.setVgap(10);

        // Create some instructions
        Text screenMessage = new Text("Sign up. It\'s quick and easy.\n");
        grid.add(screenMessage, 0, 0, 2, 1);

        // Add first name text field and label
        Label firstNameLabel = new Label("First Name");
        grid.add(firstNameLabel, 0, 1);
        firstNameTextField.setMinWidth(Constants.CREDENTIAL_FIELD_WIDTH); //No need to set fields in same column
        grid.add(firstNameTextField, 1, 1);

        // Add last name text field and label
        Label lastNameLabel = new Label("Last Name");
        grid.add(lastNameLabel, 0, 2);
        grid.add(lastNameTextField, 1, 2);

        // Add username text field and label
        Label emailLabel = new Label("Email");
        grid.add(emailLabel, 0, 3);
        grid.add(emailTextField, 1, 3);

        // Add password field and label
        Label passwordLabel = new Label("Password");
        grid.add(passwordLabel, 0, 4);
        grid.add(passwordField, 1, 4);

        // Add submit button - color, allow stretch, set method
        Button signUpButton = new Button("Submit");
        signUpButton.setMaxWidth(Double.MAX_VALUE);
        signUpButton.setStyle(Constants.BUTTON_EMPHASIS_STYLE);
        signUpButton.setOnAction((ActionEvent e) -> {

            //This should probably be turned into a function
            String email = emailTextField.getText();
            String password = passwordField.getText();
            boolean didRegister = Firebase.doSignUp(email, password);
            if (didRegister) {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                System.out.println("Successfully registered" + email + "\nNeed to get the UID, create DB entry using UID, "
                        + "add firstName (" + firstName + ") and last name (" + lastName + ") to the DB entry");
                //After name is submitted to the new DB entry, should fetch the name and welcome the user
                //Then go to the next screen. Dashboard screen maybe?                
            } else {
                System.out.println("Oof. Failed to register " + email + ". Should alert the user here.");
            }

        });
        grid.add(signUpButton, 0, 5, 2, 1);
        GridPane.setFillWidth(signUpButton, true);

        // Add "go back" button - allow stretch, set method
        Button backButton = new Button("I already have an account");
        backButton.setMaxWidth(Double.MAX_VALUE);
        backButton.setOnAction((ActionEvent e) -> {
            ScreenLogin screen = new ScreenLogin();
            screen.run(primaryStage);
        });
        grid.add(backButton, 0, 6, 2, 1);
        GridPane.setFillWidth(backButton, true);

        //This will serve as the error message display - updated on invalid credentials
        final Text errorTextDisplay = new Text("");
        grid.add(errorTextDisplay, 0, 7, 2, 1);

        // Set the size of Scene
        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
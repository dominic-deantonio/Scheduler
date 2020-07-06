package scheduler;

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

public class ScreenLogin {

    TextField emailTextField = new TextField();
    PasswordField passwordField = new PasswordField();
    boolean loginResult = true;

    public void run(Stage primaryStage) {
        Log.log("running sign up screen");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        // Set gap between the components
        grid.setHgap(10);
        grid.setVgap(10);

        // Create some instructions
        Text screenMessage = new Text("Welcome back! Please log in.\n");
        grid.add(screenMessage, 0, 0, 2, 1);

        // Add username text field and label
        Label usernameLabel = new Label("Email");
        grid.add(usernameLabel, 0, 1);
        emailTextField.setMinWidth(Constants.CREDENTIAL_FIELD_WIDTH); //No need to set fields in same column
        grid.add(emailTextField, 1, 1);

        // Add password field and label
        Label passwordLabel = new Label("Password");
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);

        // Add login button - color, allow stretch, set method
        Button loginButton = new Button("Log In");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setStyle(Constants.BUTTON_STYLE);
        loginButton.setOnAction((ActionEvent e) -> {
            System.out.println("Run the backend methods");
            String email = emailTextField.getText();
            String password = passwordField.getText();            
            loginResult = Firebase.doLogin(email, password);
            if(loginResult){
                System.out.println("Success");
            }else{
                System.out.println("Failed to log in");
            }
        });
        grid.add(loginButton, 0, 3, 2, 1);
        GridPane.setFillWidth(loginButton, true);

        // Add sign up button
        Button backButton = new Button("Sign Up");
        backButton.setMaxWidth(Double.MAX_VALUE);
        backButton.setOnAction((ActionEvent e) -> {
            ScreenSignup screen = new ScreenSignup();
            screen.run(primaryStage);
        });
        grid.add(backButton, 0, 4, 2, 1);
        GridPane.setFillWidth(backButton, true);

        //This will serve as the error message display - updated on invalid credentials
        final Text errorTextDisplay = new Text("");
        grid.add(errorTextDisplay, 0, 5, 2, 1);

        // Set the size of Scene
        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

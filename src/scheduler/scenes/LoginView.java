package scheduler.scenes;

import scheduler.utilities.Constants;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import scheduler.*;

public class LoginView extends VBox {

    TextField emailTextField = new TextField();
    PasswordField passwordField = new PasswordField();
    Button loginButton = new Button("Log in");
    Button signupButton = new Button("Sign up");
    Label forgotButton = new Label("Forgot password?");
    boolean loginResult = true;

    public LoginView(Stage mainWindow) {
        super(5);

        //Email field
        emailTextField.setPromptText("Email");
        emailTextField.setPrefWidth(200);

        //Password field
        passwordField.setPromptText("Password");
        passwordField.setPrefWidth(200);

        //Login button
        loginButton.setStyle(Constants.BUTTON_EMPHASIS_STYLE);
        loginButton.setPrefWidth(200);
        loginButton.setOnAction((ActionEvent e) -> {
            mainWindow.setScene(new Scene(new DashboardView(mainWindow), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        });

        //Sign up button
        signupButton.setPrefWidth(200);
        signupButton.setOnAction((ActionEvent e) -> {
            mainWindow.setScene(new Scene(new SignupView(mainWindow), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        });

        //Set the VBox
        this.setFillWidth(false);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(
                new Text("Welcome to Super Scheduler! Please log in.\n"),
                emailTextField,
                passwordField,
                new Label("\n"),
                loginButton,
                signupButton,
                new Label("\n"),
                forgotButton
        );

        /* if gridPane
        *
        //Set up this object
        this.setHgap(10);
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);

        // Create some instructions
        Text screenMessage = new Text("Welcome back! Please log in.\n");
        this.add(screenMessage, 0, 0, 2, 1);

        // Add username text field and label
        Label usernameLabel = new Label("Email");
        this.add(usernameLabel, 0, 1);
        emailTextField.setMinWidth(Constants.CREDENTIAL_FIELD_WIDTH); //No need to set fields in same column
        emailTextField.setPromptText("Email");
        this.add(emailTextField, 1, 1);

        // Add password field and label
        Label passwordLabel = new Label("Password");
        this.add(passwordLabel, 0, 2);
        this.add(passwordField, 1, 2);

        // Add login button - color, allow stretch, set method
        Button loginButton = new Button("Log In");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setStyle(Constants.BUTTON_EMPHASIS_STYLE);
        loginButton.setOnAction((ActionEvent e) -> {
            System.out.println("Run the backend methods");
            String email = emailTextField.getText();
            String password = passwordField.getText();
            loginResult = Firebase.doLogin(email, password);
            if (loginResult) {
                System.out.println("Success");
            } else {
                System.out.println("Failed to log in");
            }
        });
        this.add(loginButton, 0, 3, 2, 1);
        GridPane.setFillWidth(loginButton, true);

        // Add sign up button
        Button backButton = new Button("Sign Up");
        backButton.setMaxWidth(Double.MAX_VALUE);
        backButton.setOnAction((ActionEvent e) -> {

        });
        this.add(backButton, 0, 4, 2, 1);
        GridPane.setFillWidth(backButton, true);

        //This will serve as the error message display - updated on invalid credentials
        final Text errorTextDisplay = new Text("");
        this.add(errorTextDisplay, 0, 5, 2, 1);
         */
    }

}

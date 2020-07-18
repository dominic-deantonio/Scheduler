package scheduler.scenes;

import scheduler.utilities.Constants;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

        //Forgot password button
        forgotButton.setCursor(Cursor.HAND);
        forgotButton.setOnMouseClicked((MouseEvent e) -> {
            mainWindow.setScene(new Scene(new ForgotView(mainWindow), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        });

        //Set the VBox
        this.setFillWidth(false);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(
                new Text("Welcome to Super Scheduler! Please log in.\n"),
                emailTextField,
                passwordField,
                new Text("\n"),
                loginButton,
                signupButton,
                new Text("\n"),
                forgotButton
        );
    }
}

package scheduler.scenes;

import java.io.IOException;
import scheduler.utilities.Constants;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import scheduler.models.Controller;

//This is the view where the user can log in
public class LoginView extends VBox {

    TextField emailTextField = new TextField();
    PasswordField passwordField = new PasswordField();
    Button loginButton = new Button("Log in");
    Button signupButton = new Button("Sign up");
    Text forgotButton = new Text("Forgot password?");
    Text errorMessage = new Text("");
    int WIDTH = 200;

    Button devLoginButton = new Button("Dev Log in");

    public LoginView() {
        super(5);

        //Email field
        emailTextField.setPromptText("Email");
        emailTextField.setPrefWidth(WIDTH);

        //Password field
        passwordField.setPromptText("Password");
        passwordField.setPrefWidth(WIDTH);

        //Login button
        loginButton.setStyle(Constants.BUTTON_EMPHASIS_STYLE);
        loginButton.setPrefWidth(WIDTH);
        loginButton.setOnAction((ActionEvent e) -> {
            errorMessage.setText("");
            deactivateElements();
            try {
                Controller.getInstance().login(emailTextField.getText(), passwordField.getText());
            } catch (IOException ex) {
                errorMessage.setText(ex.getMessage());
            }
            reactivateElements();
        });

        //Sign up button
        signupButton.setPrefWidth(WIDTH);
        signupButton.setOnAction((ActionEvent e) -> {
            Controller.getInstance().goToScene("signup");
        });

        //Forgot password button
        forgotButton.setCursor(Cursor.HAND);
        forgotButton.setFill(Color.BLUE);
        forgotButton.setOnMouseClicked((MouseEvent e) -> {
            Controller.getInstance().goToScene("forgot");
        });

        //Set up the error text
        errorMessage.setFill(Constants.TEXT_ERROR_COLOR);

        //Set the VBox
        this.setFillWidth(false);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(
                new Text("Welcome to Super Scheduler! Please log in.\n"),
                emailTextField,
                passwordField,
                errorMessage,
                loginButton,
                signupButton,
                new Text("\n"),
                forgotButton
        );
    }

    //Don't allow interaction while scene is processing (prevent double login attempts before first finishes)
    private void deactivateElements() {
        emailTextField.setEditable(false);
        passwordField.setEditable(false);
        loginButton.setVisible(false);
        signupButton.setVisible(false);
        forgotButton.setVisible(false);
    }

    private void reactivateElements() {
        emailTextField.setEditable(true);
        passwordField.setEditable(true);
        loginButton.setVisible(true);
        signupButton.setVisible(true);
        forgotButton.setVisible(true);
    }
}

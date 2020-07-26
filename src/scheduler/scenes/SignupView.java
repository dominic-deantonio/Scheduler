package scheduler.scenes;

import java.io.IOException;
import scheduler.utilities.Constants;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import scheduler.models.Controller;
import scheduler.models.User;

public class SignupView extends VBox {

    TextField firstNameField = new TextField();
    TextField lastNameField = new TextField();
    TextField emailTextField = new TextField();
    TextField zipCodeField = new TextField();
    PasswordField passwordField = new PasswordField();
    PasswordField confirmPasswordField = new PasswordField();
    Text errorMessage = new Text("");
    Button submitButton = new Button("Submit");
    Button goLoginButton = new Button("I already have an account");
    int WIDTH = 200;

    public SignupView() {
        super(5);

        //First name field
        firstNameField.setPromptText("First name");
        firstNameField.setPrefWidth(WIDTH);

        //Last name field
        lastNameField.setPromptText("Last name");
        lastNameField.setPrefWidth(WIDTH);

        //ZIP code field
        zipCodeField.setPromptText("ZIP code");
        zipCodeField.setPrefWidth(WIDTH);

        //Email field
        emailTextField.setPromptText("Email");
        emailTextField.setPrefWidth(WIDTH);

        //Password field
        passwordField.setPromptText("Password");
        passwordField.setPrefWidth(WIDTH);

        //Confirm password field
        confirmPasswordField.setPromptText("Re-enter password");
        confirmPasswordField.setPrefWidth(WIDTH);

        //Set error message color
        errorMessage.setFill(Constants.TEXT_ERROR_COLOR);

        //Submit button
        submitButton.setStyle(Constants.MAIN_THEME_COLOR);
        submitButton.setPrefWidth(WIDTH);
        submitButton.setOnAction((ActionEvent e) -> {
            errorMessage.setText("");
//            deactivateElements();
            try {
                Controller.getInstance().signUp(
                        firstNameField.getText(),
                        lastNameField.getText(),
                        zipCodeField.getText(),
                        emailTextField.getText(),
                        passwordField.getText(),
                        confirmPasswordField.getText());
            } catch (IOException ex) {
                errorMessage.setText(ex.getMessage());
            }
//            reactivateElements();

        });

        //Back to login button
        goLoginButton.setPrefWidth(WIDTH);
        goLoginButton.setOnAction((ActionEvent e) -> {
            Controller.getInstance().goToScene("login");
        });

        //Set the VBox and add the children
        this.setFillWidth(false);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(
                new Text("Sign up here. It's quick and easy!\n"),
                firstNameField,
                lastNameField,
                zipCodeField,
                emailTextField,
                passwordField,
                confirmPasswordField,
                errorMessage,
                submitButton,
                goLoginButton
        );
    }
}

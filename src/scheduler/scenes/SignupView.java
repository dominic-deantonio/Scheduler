package scheduler.scenes;

import scheduler.utilities.Constants;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignupView extends VBox {

    TextField firstNameField = new TextField();
    TextField lastNameField = new TextField();
    TextField emailTextField = new TextField();
    TextField zipCodeField = new TextField();
    PasswordField passwordField = new PasswordField();
    Button submitButton = new Button("Submit");
    Button goLoginButton = new Button("I already have an account");

    public SignupView(Scene scene) {
        super(5);

        //First name field
        firstNameField.setPromptText("First name");
        firstNameField.setPrefWidth(200);

        //Last name field
        lastNameField.setPromptText("Last name");
        lastNameField.setPrefWidth(200);

        //ZIP code field
        zipCodeField.setPromptText("ZIP code");
        zipCodeField.setPrefWidth(200);

        //Email field
        emailTextField.setPromptText("Email");
        emailTextField.setPrefWidth(200);

        //Password field
        passwordField.setPromptText("Password");
        passwordField.setPrefWidth(200);

        //Login button
        submitButton.setStyle(Constants.BUTTON_EMPHASIS_STYLE);
        submitButton.setPrefWidth(200);

        //Back to login button
        goLoginButton.setPrefWidth(200);
        goLoginButton.setOnAction((ActionEvent e) -> {
//            scene.setScene(new Scene(new LoginView(mainWindow), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
            scene.setRoot(new LoginView(scene));
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
                new Label("\n"),
                submitButton,
                goLoginButton,
                new Label("\n")
        );

    }

}

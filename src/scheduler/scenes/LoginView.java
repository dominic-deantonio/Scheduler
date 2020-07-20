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
import javafx.stage.Stage;
import scheduler.models.Controller;
import scheduler.models.User;

//This is the view where the user can log in
public class LoginView extends VBox {

    TextField emailTextField = new TextField();
    PasswordField passwordField = new PasswordField();
    Button loginButton = new Button("Log in");
    Button signupButton = new Button("Sign up");
    Text forgotButton = new Text("Forgot password?");
    Text errorMessage = new Text("");

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
            errorMessage.setText("");
            deactivateElements();
            try {
//                long startTime = System.currentTimeMillis();
                User user = Controller.getInstance().login(emailTextField.getText(), passwordField.getText());
//                long endTime = System.currentTimeMillis();
//                long seconds = (endTime - startTime) / 1000;
                mainWindow.setScene(new Scene(new DashboardView(mainWindow, user), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
            } catch (IOException ex) {
                errorMessage.setText(ex.getMessage());
            }
            reactivateElements();

        });

        //Sign up button
        signupButton.setPrefWidth(200);
        signupButton.setOnAction((ActionEvent e) -> {
            mainWindow.setScene(new Scene(new SignupView(mainWindow), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        });

        //Forgot password button
        forgotButton.setCursor(Cursor.HAND);
        forgotButton.setFill(Color.BLUE);
        forgotButton.setOnMouseClicked((MouseEvent e) -> {
            mainWindow.setScene(new Scene(new ForgotView(mainWindow), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        });

        //Set up the error text
        errorMessage.setFill(Constants.TEXT_ERROR_COLOR);
//        errorMessage.setText("Here is an error");

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

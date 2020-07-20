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

public class ForgotView extends VBox {

    TextField emailTextField = new TextField();
    Button submitButton = new Button("Submit");
    Button goLoginButton = new Button("Log in instead");

    public ForgotView(Scene scene) {
        super(5);

        //Email field
        emailTextField.setPromptText("Email");
        emailTextField.setPrefWidth(200);

        //Submit button
        submitButton.setStyle(Constants.BUTTON_EMPHASIS_STYLE);
        submitButton.setPrefWidth(200);
        submitButton.setOnAction((ActionEvent e) -> {
//            mainWindow.setScene(new Scene(new LoginView(mainWindow), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
            System.out.println("Submit request for forgotton password");
        });

        //Back to login button
        goLoginButton.setPrefWidth(200);
        goLoginButton.setOnAction((ActionEvent e) -> {
            scene.setRoot(new LoginView(scene));
        });

        //Set the VBox
        this.setFillWidth(false);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(
                new Text("Enter your email below and we'll send you a link to reset your password.\n"),
                emailTextField,
                new Text("\n"),
                submitButton,
                goLoginButton,
                new Text("\n")
        );
    }

}

package scheduler.scenes;

import scheduler.utilities.Constants;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import scheduler.*;

public class DashboardView extends VBox {

    Button logoutButton = new Button("Log out");

    public DashboardView(Stage mainWindow) {
        super();

        //Sign out button
        logoutButton.setPrefWidth(200);
        logoutButton.setOnAction((ActionEvent e) -> {
            mainWindow.setScene(new Scene(new LoginView(mainWindow), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        });
        
        

        //Set the VBox
        this.setFillWidth(false);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(
                new Text("You have logged in. This is the dashboard\n\n\n"),
                logoutButton
        );
    }
}

package scheduler;

import scheduler.utilities.Constants;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import scheduler.scenes.LoginView;

public class App extends Application {

    @Override
    public void start(Stage mainWindow) {
        Scene scene = new Scene(new AnchorPane());  // Create scene with arbitrary root node
        LoginView loginView = new LoginView(scene); // Create thing to go inside scene
        scene.setRoot(loginView);                   // Set the scene with new root node

        //Set up the main window
        mainWindow.setMinHeight(500);
        mainWindow.setMinWidth(500);
        mainWindow.setTitle("Super Scheduler");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

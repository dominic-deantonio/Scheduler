package scheduler;

import scheduler.utilities.Constants;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scheduler.scenes.LoginView;

public class App extends Application {

    @Override
    public void start(Stage mainWindow) {
        User user = new User();

        LoginView loginView = new LoginView(mainWindow);

        //Set up the main window
        mainWindow.setMinHeight(500);
        mainWindow.setMinWidth(500);
        mainWindow.setTitle("Super Scheduler");
        mainWindow.setScene(new Scene(loginView, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        mainWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

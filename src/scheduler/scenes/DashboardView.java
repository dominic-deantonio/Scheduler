package scheduler.scenes;


import scheduler.utilities.Constants;
import scheduler.widgets.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class DashboardView extends BorderPane {

    Button logoutButton = new Button("Log out");
    Insets insets = new Insets(15);

    public DashboardView(Stage mainWindow) {
        super();

        //Set buttons' attributes
        logoutButton.setOnAction((ActionEvent e) -> {
            mainWindow.setScene(new Scene(new LoginView(mainWindow), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        });

        //Create the center node and children
        VBox center = new VBox();
        center.getChildren().add(new CalendarMonth());

        //Create the left node and children
        VBox left = new VBox(5);
        left.getChildren().addAll(
                new Button("Placeholder 1"),
                new Button("Placeholder 2"),
                new Button("Placeholder 3"),
                new Button("Placeholder 4"),
                new Button("Options"),
                logoutButton
        );

        //Set the attributes of this scene
        DashboardView.setMargin(left, insets);

        //Add the nodes to this object
        this.setTop(new SceneHeader("Dashboard"));
        this.setCenter(center);
        this.setLeft(left);

    }
}

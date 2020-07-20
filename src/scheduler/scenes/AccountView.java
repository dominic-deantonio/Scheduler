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
import scheduler.models.User;

public class AccountView extends BorderPane {

    Button logoutButton = new Button("Log out");
    Insets insets = new Insets(15);

    public AccountView(Scene scene, User user) {
        super();
        //Set buttons' attributes
        logoutButton.setOnAction((ActionEvent e) -> {
            scene.setRoot(new DashboardView(scene, user));
//            mainWindow.setScene(new Scene(new DashboardView(mainWindow, user)));
        });

        //Create the center node and children
        VBox center = new VBox();
        center.getChildren().add(new CalendarMonth());

        //Create the left node and children
        VBox left = new VBox(5);
        left.getChildren().addAll(
                new Text("Should anything go here?")
        );

        //Set the attributes of this scene
        AccountView.setMargin(left, insets);

        //Add the nodes to this object
        this.setTop(new SceneHeader("Account", user));
        this.setCenter(center);
        this.setLeft(left);

    }
}

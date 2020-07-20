package scheduler.scenes;

import scheduler.widgets.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import scheduler.models.Controller;
import scheduler.models.User;

public class AccountView extends BorderPane {

    Button logoutButton = new Button("Log out");
    Insets insets = new Insets(15);

    public AccountView(User user) {
        super();
        //Set buttons' attributes
        logoutButton.setOnAction((ActionEvent e) -> {
            Controller.getInstance().logout();
        });

        //Set up a button that allows you to exit account view
        Button goDashboardButton = new Button("Dashboard");
        goDashboardButton.setOnAction((ActionEvent e) -> {
            Controller.getInstance().goToScene("dashboard");
        });

        //Create the center node and children
        VBox center = new VBox();
        center.getChildren().addAll(
                new Text("Your account information:\n\n"),
                new Text(user.toString() + "\n\n"),
                goDashboardButton
        );

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

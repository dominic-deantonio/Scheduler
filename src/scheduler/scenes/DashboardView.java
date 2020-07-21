package scheduler.scenes;

import scheduler.widgets.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import scheduler.models.Controller;
import scheduler.models.User;

public class DashboardView extends BorderPane {

    Insets insets = new Insets(15);

    public DashboardView(User user) {
        super();

        //Create the center node and children
        VBox center = new VBox();
        center.getChildren().add(new CalendarMonth());

        //Create the left node and children
        VBox left = new VBox();
        left.getChildren().add(new NavigationButtons());

        //Set the attributes of this scene
        DashboardView.setMargin(left, insets);

        //Add the nodes to this object
        this.setTop(new SceneHeader("Dashboard", user));
        this.setCenter(center);
        this.setLeft(left);

    }
}

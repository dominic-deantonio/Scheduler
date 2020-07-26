package scheduler.scenes;

import scheduler.widgets.CalendarWeekGui.CalendarWeek;
import scheduler.widgets.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import scheduler.models.Meeting;
import scheduler.models.User;

public class DashboardView extends BorderPane {

    Insets insets = new Insets(15);

    public DashboardView(User user) {
        super();

        //Create the center node and children
        VBox center = new VBox();
        Meeting[] meetings = Meeting.getMockAppointments();
        center.getChildren().add(new CalendarWeek(meetings));

        //Create the left node and children
        VBox left = new VBox();
        left.getChildren().add(new NavigationButtons());

        VBox right = new VBox();
        right.getChildren().add(new CalendarMonth());

        //Set the attributes of this scene
        DashboardView.setMargin(left, insets);

        //Add the nodes to this object
        this.setTop(new SceneHeader("Dashboard", user));
        this.setCenter(center);
        this.setLeft(left);
        this.setRight(right);

    }
}

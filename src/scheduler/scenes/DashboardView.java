package scheduler.scenes;

import scheduler.widgets.CalendarWeekGui.CalendarWeek;
import scheduler.widgets.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import scheduler.models.Meeting;
import scheduler.models.User;
import scheduler.widgets.CalendarWeekGui.MeetingDetail;

public class DashboardView extends BorderPane {

    Insets insets = new Insets(15);

    public DashboardView(User user) {
        super();

        MeetingDetail meetingDetail = new MeetingDetail();

        //Create the center node and children
        VBox center = new VBox();
        center.getChildren().add(new CalendarWeek(Meeting.getMockAppointments(), meetingDetail));

        //Create the left node and children
        VBox left = new VBox();
        left.getChildren().add(new NavigationButtons());

        VBox right = new VBox();
        right.getChildren().addAll(new CalendarMonth(), meetingDetail);

        //Set the attributes of this scene
        DashboardView.setMargin(left, insets);

        //Add the nodes to this object
        this.setTop(new SceneHeader("Dashboard", user));
        this.setCenter(center);
        this.setLeft(left);
        this.setRight(right);

    }
}

package scheduler.scenes;

import scheduler.widgets.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import scheduler.models.Controller;
import scheduler.models.User;

public class MyScheduleView extends BorderPane {

    Insets insets = new Insets(15);

    public MyScheduleView(User user) {
        super();

        VBox center = new VBox();
        
        // add table here to track current appointments
        NavigationButtons left = new NavigationButtons();

        DashboardView.setMargin(left, Constants.MENU_INSETS);

        this.setTop(new SceneHeader("My Schedule", user));
        this.setCenter(center);
        this.setLeft(left);
    }
}

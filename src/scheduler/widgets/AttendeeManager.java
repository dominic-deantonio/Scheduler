package scheduler.widgets;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import scheduler.models.Controller;
import scheduler.models.User;
import scheduler.scenes.LoginView;
import scheduler.utilities.Constants;
import scheduler.widgets.CalendarWeekGui.MeetingDetail;

public class AttendeeManager extends Stage {

    public AttendeeManager(MeetingDetail meetingPanel) {
        setMinHeight(600);
        setMinWidth(800);
        setTitle("Super Scheduler");
        setScene(new Scene(new AttendeesView(meetingPanel)));
        show();
    }

}

class AttendeesView extends BorderPane {

    Insets insets = new Insets(15);
    TextField searchField = new TextField();
    ArrayList<User> searchResults = new ArrayList();

    public AttendeesView(MeetingDetail meetingPanel) {
        super();

        Text label = new Text("Invite Attendees");
        label.setFont(Constants.SCENE_TITLE_FONT);

        // Top elements        
        HBox top = new HBox(label);
        top.setPadding(insets);

        // Center elements        
        VBox center = new VBox();
        center.setSpacing(5);
        center.setPadding(insets);

        Button doSearchButton = new Button("Search");

        HBox searchElements = new HBox();
        searchElements.getChildren().addAll(searchField, doSearchButton);

        center.getChildren().addAll(
                new Text("Search for others to attend your meeting using first or last name or email"),
                searchElements
        );

        VBox right = new VBox(new Text("Search results"));
        right.setPadding(insets);
        right.setPrefWidth(250);
        right.setSpacing(5);
        setRight(right);
        // Right elements
        doSearchButton.setOnAction((a) -> {
            searchResults = Controller.getInstance().searchAttendees(searchField.getText());
            right.getChildren().clear();
            right.getChildren().add(new Text("Search results"));

            if (searchResults.size() > 0) {
                System.out.println("Found" + searchResults.size());
                ArrayList<Button> foundUsers = buildButtonsFromSearchResults(searchResults);
                right.getChildren().addAll(foundUsers);
            } else {
                right.getChildren().add(new Text("No results found"));
            }
        });

        setTop(top);
        setCenter(center);

    }

    ArrayList<Button> buildButtonsFromSearchResults(ArrayList<User> contacts) {
        ArrayList<Button> found = new ArrayList();

        contacts.forEach((c) -> {
            Button b = new Button(c.getFullName() + "\n" + c.getEmail());
            b.setMinWidth(225);
            b.setAlignment(Pos.BASELINE_LEFT);
            b.setStyle("-fx-base: " + Constants.MAIN_THEME_COLOR);
            found.add(b);
        });

        return found;
    }

}

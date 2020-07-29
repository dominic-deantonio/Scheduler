package scheduler.widgets.CalendarWeekGui;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import scheduler.models.Meeting;

public class MeetingDetailPane extends VBox {

    private final String STYLE2 = "-fx-padding: 10; -fx-border-width: 2; -fx-border-insets: 5; -fx-border-radius: 5; -fx-border-color: grey;";
    Text meetingText = new Text();

    public MeetingDetailPane() {
        displayMeeting(null);
    }

    public MeetingDetailPane(Meeting mtg) {
        displayMeeting(mtg);
    }

    public void displayMeeting(Meeting mtg) {

        setStyle(STYLE2);
        setPadding(new Insets(15));
        setMinHeight(200);

        // Delete the other children and add the new children
        getChildren().clear();
        if (mtg == null) {
            getChildren().add(new Text("No meeting selected"));
        } else {
            meetingText.setText(mtg.getDisplayInfo());
            getChildren().addAll(meetingText);
        }
    }
    
    

}

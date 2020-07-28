package scheduler.widgets.CalendarWeekGui;

import javafx.scene.control.Button;
import scheduler.models.Meeting;
import scheduler.utilities.Constants;

public class MeetingBlock extends Button {

    public MeetingBlock(Meeting meeting) {
        super(meeting.getDisplayInfo());
        setStyle("-fx-base: " + Constants.MAIN_THEME_COLOR);
        this.setOnAction(e -> {
            System.out.println("This meeting is: " + meeting.getDisplayInfo());
        });
    }

}

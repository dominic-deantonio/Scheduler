package scheduler.widgets.CalendarWeekGui;

import java.time.LocalDate;
import java.time.YearMonth;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import scheduler.models.Meeting;
import scheduler.utilities.Constants;

public class CalendarWeek extends VBox {

    private LocalDate dateToDisplay = LocalDate.now();
    private YearMonth yearMonth;
    private Text monthLabel = new Text("Error"); //Ignore NetBeans, this should not be final.

    String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String[] minutes = new String[]{"00", "15", "30", "45"};
    GridPane grid = new GridPane();
    Text headerText = new Text("Week of blah");
    ScrollPane scroll = new ScrollPane();
    int hourHeight = 80;

    public CalendarWeek(Meeting[] meetings) {

        headerText.setFont(Constants.SUB_TITLE_FONT);

        buildTimeLabels();
        buildBasePane();
        buildMeetings(meetings);

        //Set the scrollpane and add the header and scrollpane to this object
        scroll.setFocusTraversable(false);
        scroll.setContent(grid);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color:transparent;");
        getChildren().addAll(headerText, scroll);

    }

    public CalendarWeek() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Add time labels to the base grid
    private void buildTimeLabels() {
        for (int hour = 0; hour < 24; hour++) {
            Text timeText = new Text(hour + ":00");
            GridPane.setValignment(timeText, VPos.TOP);
            int labelRow = hour == 0 ? 1 : hour * 4 + 1;      //The y should be placed every 4 (quarter-hours) or on 0
            grid.add(timeText, 0, labelRow);
        }
    }

    private void buildBasePane() {
        //Add day labels and the quarter-hour buttons
        for (int day = 0; day < 7; day++) {
            grid.add(new Text(days[day]), day + 1, 0);
            int quarterIndex = 0;

            for (int row = 1; row < 97; row++) {
                int hour = (row - 1) / 4;
                int quarter = quarterIndex;
                QuarterHourTimeBlock block = new QuarterHourTimeBlock(hour, quarter);

                grid.add(block, day + 1, row);

                if (quarterIndex < 3) {
                    quarterIndex++;
                } else {
                    quarterIndex = 0;
                }
            }
        }

        //Set width resize ability to each day column
        for (int j = 0; j < 8; j++) {
            ColumnConstraints cc = new ColumnConstraints();
            if (j > 0) {
                cc.setHgrow(Priority.ALWAYS);   // allow column to grow
                cc.setFillWidth(true);          // ask timeblocks to fill space for column
            }
            grid.getColumnConstraints().add(cc);
        }
    }

    private void buildMeetings(Meeting[] meetings) {

        for (Meeting meeting : meetings) {

            Button meetingBtn = new Button(meeting.subject);
            meetingBtn.setPrefWidth(80); //This gets overwritten by the setmaxsize
            meetingBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);  //allow button to grow      
            grid.add(meetingBtn, meeting.day + 1, meeting.getStartingRow(), 1, meeting.getSpan());

        }

//        for (int day = 1; day < 8; day++) {
//            //Create the meeting instance
//
//            //Create the meeting GUI
//            int meetingDurationInQuarterHours = 6;
//            int numberOverlapping = 1;
//            int startingTimeByBlockIndex = 5;
//            for (int i = 0; i < numberOverlapping; i++) {
//
//                Button meeting = new Button("Meeting subject");
//                meeting.setPrefWidth(80); //This gets overwritten by the setmaxsize
//                meeting.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);  //allow button to grow      
//                grid.add(meeting, day, startingTimeByBlockIndex, 1, meetingDurationInQuarterHours);
//
//            }
//        }
    }

}

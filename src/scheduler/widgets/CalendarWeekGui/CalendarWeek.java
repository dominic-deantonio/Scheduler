package scheduler.widgets.CalendarWeekGui;

import java.time.*;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import scheduler.models.Meeting;
import scheduler.utilities.Constants;

public class CalendarWeek extends VBox {

    private LocalDate dateToDisplay = LocalDate.now();
    private YearMonth yearMonth;
    private Text weekLabel = new Text("Error"); //Ignore NetBeans, this should not be final.

    String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    String[] minutes = new String[]{"00", "15", "30", "45"};
    GridPane grid = new GridPane();
    Text headerText = new Text("Error displaying header");
    ScrollPane scroll = new ScrollPane();
    MeetingDetailPane meetingPane = new MeetingDetailPane();

    HBox header = new HBox();
    Meeting[] meetings;

    public CalendarWeek(Meeting[] meetings) {

        headerText.setFont(Constants.SUB_TITLE_FONT);
        this.meetings = meetings;
        setGridAttributes();
        rebuild();
        

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
            LocalDate dateOfDay = getPreviousMonday().plusDays(day);
            String dayText = dateOfDay.getDayOfMonth() + "\n" + days[day];
            grid.add(new Text(dayText), day + 1, 0);
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

        //Set the scrollpane and add the header and scrollpane to this object
        scroll.setFocusTraversable(false);
        scroll.setContent(grid);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color:transparent;");        
        this.getChildren().add(scroll);

    }

    private void buildMeetings() {

        for (Meeting meeting : meetings) {

            LocalDate start = meeting.getStartDate();

            // If start date is equalto or after the previous monday, add it to this grid
            if ((start.isEqual(getPreviousMonday()) || start.isAfter(getPreviousMonday())) && start.isBefore(getNextMonday())) {

                MeetingBlock meetingBtn = new MeetingBlock(meeting, meetingPane);
                meetingBtn.setPrefWidth(80); //This gets overwritten by the setmaxsize
                meetingBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);  //allow button to grow      
                grid.add(meetingBtn, meeting.getDay(), meeting.getStartingRow(), 1, meeting.getSpan());

            }
        }
    }

    private void buildHeader() {
        //Set the "previous week" button
        Button prevWeekButton = new Button("<");
        prevWeekButton.setOnAction((ActionEvent e) -> {
            dateToDisplay = dateToDisplay.minusWeeks(1);
            rebuild();
        });
        header.getChildren().add(prevWeekButton);

        //Set the "next week" button
        Button nextWeekButton = new Button(">");
        nextWeekButton.setOnAction((ActionEvent e) -> {
            dateToDisplay = dateToDisplay.plusWeeks(1);
            rebuild();
        });
        header.getChildren().add(nextWeekButton);

        //Set the month text label
        String month = yearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.US);
        weekLabel.setText("Week of " + getPreviousMonday().getDayOfMonth() + " " + month + " " + yearMonth.getYear());
        weekLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        header.getChildren().add(weekLabel);
        this.getChildren().add(header);

    }

    //Allows this object to rebuild all children 
    private void rebuild() {
        // current yearmonth may have been changed by the button. Update here then rebuild children
        yearMonth = YearMonth.of(dateToDisplay.getYear(), dateToDisplay.getMonth());
        removeAllChildren();
        buildHeader();
        buildTimeLabels();
        buildBasePane();
        buildMeetings();
        getChildren().add(meetingPane);

    }

    private void removeAllChildren() {
        meetingPane.displayMeeting(null);
        this.getChildren().clear();
        header.getChildren().clear();
        grid.getChildren().clear();
        scroll.setContent(null);
    }

    // Should only be called once and not on every rebuild
    private void setGridAttributes() {
        //Set width resize ability to each day column
        for (int j = 0; j < 8; j++) {
            ColumnConstraints cc = new ColumnConstraints();
            if (j > 0) {
                cc.setHgrow(Priority.ALWAYS);   // allow column to grow
                cc.setFillWidth(true);          // ask timeblocks to fill space for column
            }
            grid.getColumnConstraints().add(cc);
        }
        setPadding(new Insets(15));
    }

    private LocalDate getPreviousMonday() {
        LocalDate monday = dateToDisplay.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        return monday;
    }

    private LocalDate getNextMonday() {
        LocalDate monday = dateToDisplay.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        return monday;
    }
}

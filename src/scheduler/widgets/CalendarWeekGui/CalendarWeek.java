package scheduler.widgets.CalendarWeekGui;

import java.time.LocalDate;
import java.time.YearMonth;

import java.time.format.TextStyle;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import static javafx.scene.layout.GridPane.setColumnSpan;
import static javafx.scene.layout.GridPane.setHalignment;
import static javafx.scene.layout.GridPane.setHgrow;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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

    HBox header = new HBox();
    Meeting[] meetings;

    public CalendarWeek(Meeting[] meetings) {

        headerText.setFont(Constants.SUB_TITLE_FONT);
        this.meetings = meetings;
        rebuild();
        setPadding(new Insets(15));

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

        //Set the scrollpane and add the header and scrollpane to this object
        scroll = new ScrollPane();
        scroll.setFocusTraversable(false);
        scroll.setContent(grid);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color:transparent;");
        this.getChildren().add(scroll);

    }

    private void buildMeetings() {

        for (Meeting meeting : meetings) {

            MeetingBlock meetingBtn = new MeetingBlock(meeting);
            meetingBtn.setPrefWidth(80); //This gets overwritten by the setmaxsize
            meetingBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);  //allow button to grow      
            grid.add(meetingBtn, meeting.getDay(), meeting.getStartingRow(), 1, meeting.getSpan());
        }
    }

    private void buildHeader() {
        //Set the "previous week" button
        Button prevMonthButton = new Button("<");
        prevMonthButton.setOnAction((ActionEvent e) -> {
            dateToDisplay = dateToDisplay.minusWeeks(1);
            rebuild();
        });
        header.getChildren().add(prevMonthButton);
//        setHalignment(prevMonthButton, HPos.CENTER); //Centers these objects

        //Set the "next month" button
        Button nextMonthButton = new Button(">");
        nextMonthButton.setOnAction((ActionEvent e) -> {
            dateToDisplay = dateToDisplay.plusWeeks(1);
            rebuild();
        });
        header.getChildren().add(nextMonthButton);
//        setHalignment(nextMonthButton, HPos.CENTER); //Centers these objects

        //Set the month text label
        String month = yearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.US);
        monthLabel.setText("Week of " + dateToDisplay.getDayOfMonth() + " " + month + " " + yearMonth.getYear());
        monthLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        setColumnSpan(monthLabel, 5);
        setHgrow(monthLabel, Priority.ALWAYS);
        setHalignment(monthLabel, HPos.CENTER); //Centers these objects
        header.getChildren().add(monthLabel);
        this.getChildren().add(header);

    }
    //Allows this object to rebuild all children at will

    private void rebuild() {
        this.getChildren().clear();
        header.getChildren().clear();
        grid.getChildren().clear();
        
        yearMonth = YearMonth.of(dateToDisplay.getYear(), dateToDisplay.getMonth());
        buildHeader();
        buildTimeLabels();
        buildBasePane();
        buildMeetings();
        
    }
}

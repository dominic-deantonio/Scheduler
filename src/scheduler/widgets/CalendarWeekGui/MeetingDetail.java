package scheduler.widgets.CalendarWeekGui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import scheduler.models.Meeting;
import scheduler.utilities.Constants;

public class MeetingDetail extends VBox {

    Text titleLabel = new Text();
    Text organizerLabel = new Text();

    //Meeting fields
    TextField subjectField = new TextField();
    DatePicker datePicker = new DatePicker();

    HBox startTime = new HBox();
    ComboBox startHourPicker = new ComboBox();
    ComboBox startMinPicker = new ComboBox();

    HBox endTime = new HBox();
    ComboBox endHourPicker = new ComboBox();
    ComboBox endMinPicker = new ComboBox();

    Button attendeeButton = new Button("View Attendees");
    HBox buttons = new HBox();

    public MeetingDetail() {
        setStyle(Constants.PANEL_STYLE);
        setPadding(new Insets(15));
        setMinHeight(250);
        setMaxWidth(300);
        setSpacing(5);

        buildTimePickers();

        titleLabel.setFont(Constants.SUB_TITLE_FONT);

        subjectField.setPromptText("Meeting with Anne");

    }

    public void displayMeeting(Meeting meeting) {
        titleLabel.setText("Meeting");
        organizerLabel.setText("Organized by " + meeting.getOrganizer());

        subjectField.setText(meeting.subject);

        datePicker.setValue(meeting.getStartDate());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm");

        startHourPicker.setValue(meeting.getStartDateTime().getHour());
        startMinPicker.setValue(meeting.getStartDateTime().format(formatter));

        endHourPicker.setValue(meeting.getEndDateTime().getHour());
        endMinPicker.setValue(meeting.getEndDateTime().format(formatter));

        attendeeButton.setText("Edit attendees (" + meeting.getAttendees().length + ")");

        buttons = getButtons(false);

        display();
    }

    public void createMeeting(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm");

        titleLabel.setText("Create Meeting");
        organizerLabel.setText("");
        subjectField.setText("No subject");
        datePicker.setValue(time.toLocalDate());

        startHourPicker.setValue(time.getHour());
        startMinPicker.setValue(time.format(formatter));

        endHourPicker.setValue(time.plusMinutes(15).getHour());
        endMinPicker.setValue(time.plusMinutes(15).format(formatter));

        attendeeButton.setText("Edit attendees (0)"); // Change this to the actual number invited

        buttons = getButtons(true);

        display();
    }

    public void display() {
        getChildren().clear();

        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);

        // Set and add subject label and field        
        Text subjLabel = new Text("Subject");
        grid.add(subjLabel, 0, 0);
        grid.add(subjectField, 1, 0);

        // Set and add date label and field
        Text dateLabel = new Text("Date");
        grid.add(dateLabel, 0, 1);
        grid.add(datePicker, 1, 1);

        // Set and add time label and field
        Text startTimeLabel = new Text("Start time");
        grid.add(startTimeLabel, 0, 2);
        grid.add(startTime, 1, 2);

        // Set and add time label and field
        Text endTimeLabel = new Text("End time");
        grid.add(endTimeLabel, 0, 3);
        grid.add(endTime, 1, 3);

        // Set and add attendees label and field
        Text attendeesLabel = new Text("Attendees");
        grid.add(attendeesLabel, 0, 4);
        grid.add(attendeeButton, 1, 4);

        grid.add(new Text(""), 0, 5);
        grid.add(buttons, 0, 6, 2, 1);

        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(80);
        getChildren().addAll(titleLabel, organizerLabel, grid);
        grid.getColumnConstraints().addAll(new ColumnConstraints(), cc);
    }

    public void clear() {
        getChildren().clear();
    }

    private HBox getButtons(boolean isCreating) {
        HBox hb = new HBox();

        Button b1 = new Button("Save Changes");
        Button b2 = new Button("Delete");

        if (isCreating) {
            b1.setText("Cancel");
            b1.setOnAction((ActionEvent e) -> {
                clear();
            });

            b2.setText("Save");
            b2.setOnAction((ActionEvent e) -> {
                System.out.println("Pretending to save the meeting");
                // Run function which returns a new meeting to the controller for processing.
                // When the meeting is succesfully added, return it to this object                                
                // Run the display meeting function for the newly created meeting meeting
                // Refresh the weekly calendar view to include the new meeting
            });
        }

        hb.getChildren().addAll(b1, b2);
        hb.setSpacing(5);
        return hb;
    }

    private void buildTimePickers() {

        for (int i = 0; i < 24; i++) {
            startHourPicker.getItems().add(i);
            endHourPicker.getItems().add(i);
        }

        Object[] mins = new String[]{"00", "15", "30", "45"};
        startMinPicker.getItems().addAll(mins);
        endMinPicker.getItems().addAll(mins);

        startTime.getChildren().addAll(startHourPicker, new Text(" : "), startMinPicker);
        endTime.getChildren().addAll(endHourPicker, new Text(" : "), endMinPicker);

    }
}
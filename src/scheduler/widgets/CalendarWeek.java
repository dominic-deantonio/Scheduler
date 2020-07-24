package scheduler.widgets;

import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CalendarWeek extends VBox {

    String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String[] minutes = new String[]{"00", "15", "30", "45"};
    GridPane grid = new GridPane();
    ScrollPane scroll = new ScrollPane();
    int hourHeight = 80;

    public CalendarWeek() {

        //Add time labels to the left
        for (int hour = 0; hour < 24; hour++) {
            Text timeText = new Text(hour + ":00");
            GridPane.setValignment(timeText, VPos.TOP);
            int labelRow = hour == 0 ? 1 : hour * 4 + 1;      //The y should be placed every 4 (quarter-hours) or on 0
            grid.add(timeText, 0, labelRow);
        }

        //Add day labels and the quarter-hour buttons
        for (int day = 0; day < 7; day++) {
            grid.add(new Text(days[day]), day + 1, 0);
            int timeIndex = 0;

            for (int row = 1; row < 97; row++) {
                Button hourButton = new Button((row - 1) / 4 + ":" + minutes[timeIndex]);
                hourButton.setPrefWidth(100);
                hourButton.setMaxSize(Double.MAX_VALUE, 100);  //allow button to grow super wide               
                hourButton.setMinHeight(20);
                grid.add(hourButton, day + 1, row);

                if (timeIndex < 3) {
                    timeIndex++;
                } else {
                    timeIndex = 0;
                }
            }
        }

        //Set width resize ability
        for (int j = 0; j < 8; j++) {
            ColumnConstraints cc = new ColumnConstraints();
            if (j > 0) {
                cc.setHgrow(Priority.ALWAYS); // allow column to grow
                cc.setFillWidth(true); // ask nodes to fill space for column
            }
            grid.getColumnConstraints().add(cc);
        }

//        grid.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        scroll.setFocusTraversable(
                false);
        scroll.setContent(grid);

        scroll.setFitToWidth(
                true);
//        scroll.setMaxHeight(800);
        scroll.setStyle(
                "-fx-background-color:transparent;");

//        this.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren()
                .addAll(new Text("Week of blah"), scroll);

    }
}

package scheduler.widgets;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CalendarWeek extends VBox {

    String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    GridPane grid = new GridPane();
    ScrollPane scroll = new ScrollPane();

    public CalendarWeek() {

        //days are columns
        for (int column = 1; column < 8; column++) {

            grid.add(new Text(days[column - 1]), column, 0);

            //hours are rows
            for (int row = 1; row < 25; row++) {

                //Add the hour labels
                if (column == 1) {
                    grid.add(new Text(row - 1 + ":00"), 0, row);
                }

                Button hour = new Button("Hour");

                if (row - 1 < 6 || row - 1 > 18) {
                    hour.setMinSize(120, 25);
                    hour.setStyle("-fx-base: #C0C0C0;");
                } else {
                    hour.setMinSize(120, 80);
                }

                grid.add(hour, column, row);
            }

        }
        scroll.setFocusTraversable(false);
        scroll.setContent(grid);
        scroll.setStyle("-fx-background-color:transparent;");
        
        getChildren().addAll(new Text("Week of blah"), scroll );

    }
}

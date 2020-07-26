package scheduler.widgets.CalendarWeekGui;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import scheduler.models.Controller;

public class QuarterHourTimeBlock extends Button {

    private final String IDLE_STYLE = "-fx-background-color: #EFF0F1; -fx-border-width: 1 0 0 1; -fx-border-color: #FFFFFF; fx-background-radius: 0;";
    private final String HOVER_STYLE = "-fx-background-color: #FFFFFF; -fx-border-width: 1 0 0 1;-fx-border-color: #FFFFFF; fx-background-radius: 0;";
    private final String SELECTED_STYLE = "-fx-background-color: #D192FF; -fx-border-width: 1 0 0 1; -fx-border-color: #FFFFFF; fx-background-radius: 0;";

    public QuarterHourTimeBlock(int hour, int quarter) {
        super();
        String id = hour + ":" + quarter;

        setPrefWidth(100);
        setMaxSize(Double.MAX_VALUE, 100);  //allow button to grow super wide               
        setMinHeight(10);
        setStyle(IDLE_STYLE);

        setOnMouseEntered((MouseEvent e) -> {
            setStyle(HOVER_STYLE);
        });

        setOnMousePressed((MouseEvent e) -> {
            setStyle(SELECTED_STYLE);
        });

        setOnMouseExited((MouseEvent e) -> {
            setStyle(IDLE_STYLE);
        });

        setOnMouseReleased((MouseEvent e) -> {
            setStyle(IDLE_STYLE);
        });

        setOnAction((ActionEvent e) -> {
            System.out.println("Selected " + id);
        });
    }

}

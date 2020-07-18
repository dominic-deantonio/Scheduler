package scheduler.scenes;

import java.time.LocalDate;
import scheduler.utilities.Constants;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import scheduler.widgets.*;

public class DashboardView extends BorderPane {

    Button logoutButton = new Button("Log out");
    Insets insets = new Insets(15);

    public DashboardView(Stage mainWindow) {
        super();

        //Set buttons' attributes
        logoutButton.setOnAction((ActionEvent e) -> {
            mainWindow.setScene(new Scene(new LoginView(mainWindow), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        });
//        logoutButton.setMinWidth(LEFT_WIDTH);

        //Create the top node and children
        HBox top = new HBox();
        Text title = new Text("Dashboard");
        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        top.getChildren().add(title);
        top.setPadding(insets);

        //Create the center node and children
        VBox center = new VBox();
        center.getChildren().addAll(
                new CalendarMonth()
        );

        //Create the left node and children
        VBox left = new VBox(5);
        left.getChildren().addAll(
                new Button("Placeholder 1"),
                new Button("Placeholder 2"),
                new Button("Placeholder 3"),
                new Button("Placeholder 4"),
                new Button("Placeholder 5"),
                logoutButton
        );

        //Set the attributes of this scene
        DashboardView.setMargin(left, insets);

        //Add the nodes to this object
        this.setTop(top);
        this.setCenter(center);
        this.setLeft(left);

    }
}

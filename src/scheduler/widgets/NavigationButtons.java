package scheduler.widgets;

import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import scheduler.models.Controller;

public class NavigationButtons extends VBox {

    Button dashBoardButton = new Button("My Dashboard");
    Button myScheduleButton = new Button("My Schedule");
    Button createGroupButton = new Button("Create Group");
    Button ViewGroupsButton = new Button("View Groups");
    Button optionsButton = new Button("Options");
    Button logoutButton = new Button("Log out");

    public NavigationButtons() {

        dashBoardButton.setOnAction((ActionEvent e) -> {
            Controller.getInstance().goToScene("dashboard");
        });

        myScheduleButton.setOnAction((ActionEvent e) -> {
            Controller.getInstance().goToScene("schedule");
        });

        createGroupButton.setOnAction((ActionEvent e) -> {
            Controller.getInstance().goToScene("create");
        });

        ViewGroupsButton.setOnAction((ActionEvent e) -> {
            Controller.getInstance().goToScene("view");
        });

        optionsButton.setOnAction((ActionEvent e) -> {
            Controller.getInstance().goToScene("options");
        });

        logoutButton.setOnAction((ActionEvent e) -> {
            Controller.getInstance().logout();
        });
        VBox main = new VBox(5);
        main.getChildren().addAll(
                dashBoardButton,
                myScheduleButton,
                createGroupButton,
                ViewGroupsButton,
                optionsButton,
                logoutButton
        );

        this.getChildren().addAll(
                main
        );

    }
}

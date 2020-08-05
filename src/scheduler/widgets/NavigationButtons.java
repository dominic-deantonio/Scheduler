package scheduler.widgets;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import scheduler.models.Controller;

import java.util.ArrayList;

public class NavigationButtons extends VBox {

    Label dashBoardButton   = new Label("My Dashboard");
    Label myScheduleButton  = new Label("My Schedule");
    Label createGroupButton = new Label("Create Group");
    Label ViewGroupsButton  = new Label("View Groups");
    Label ViewMapButton     = new Label("View Map");
    Label optionsButton     = new Label("Options");
    Label logoutButton      = new Label("Log out");

    public NavigationButtons() {

        ArrayList<Label> labels = new ArrayList<>();
        labels.add(dashBoardButton);
        labels.add(myScheduleButton);
        labels.add(createGroupButton);
        labels.add(ViewGroupsButton);

        labels.add(ViewMapButton);
        labels.add(optionsButton);

        setUpMenu(labels, "dashboard","schedule","create","view", "map","options");

        setStyle("-fx-background-color: cyan");
        setPadding(new Insets(5,2,5,2));
        setMinWidth(200);



        logoutButton.setOnMouseClicked(mouseEvent-> Controller.getInstance().logout());
        logoutButton.setMinHeight(40);
        logoutButton.setStyle("-fx-background-color: yellow; -fx-font-size: 16; -fx-cursor: hand");
        logoutButton.setMinWidth(200);

        setSpacing(5);
        getChildren().addAll(
                dashBoardButton,
                myScheduleButton,
                createGroupButton,
                ViewGroupsButton,
                ViewMapButton,
                optionsButton,
                logoutButton
        );
    }

    private void setUpMenu(ArrayList<Label> labels, String... scenes){

        for (int i = 0; i < labels.size(); i++) {
            int finalI = i;
            labels.get(finalI).setOnMouseClicked(mouseEvent -> Controller.getInstance().goToScene(scenes[finalI]));
            labels.get(finalI).setMinHeight(40);
            labels.get(finalI).setStyle("-fx-background-color: yellow; -fx-font-size: 16; -fx-cursor: hand");
            labels.get(finalI).setMinWidth(200);
        }
    }
}

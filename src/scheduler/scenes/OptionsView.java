package scheduler.scenes;

import scheduler.widgets.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import scheduler.models.Controller;
import scheduler.models.User;

public class OptionsView extends BorderPane {

    Insets insets = new Insets(15);

    public OptionsView(User user) {
        super();

        VBox center = new VBox();

        // Filler for use later
        // Adding general navigation
        VBox left = new VBox();
        left.getChildren().add(new NavigationButtons());

        DashboardView.setMargin(left, insets);

        this.setTop(new SceneHeader("Options", user));
        this.setCenter(center);
        this.setLeft(left);

    }

}

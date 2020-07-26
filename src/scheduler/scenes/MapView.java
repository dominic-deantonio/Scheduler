package scheduler.scenes;

import scheduler.widgets.*;
//import com.google.maps.*;
import javafx.scene.web.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import scheduler.models.Controller;
import scheduler.models.User;

public class MapView extends BorderPane {

    Insets insets = new Insets(15);

    public MapView(User user) {
        super();

        VBox center = new VBox();
        center.getChildren().add(new Browser());
        // Filler for use later

        // Adding general navigation
        VBox left = new VBox();
        left.getChildren().add(new NavigationButtons());

        DashboardView.setMargin(left, insets);

        this.setTop(new SceneHeader("Group Map", user));
        this.setCenter(center);
        this.setLeft(left);

    }

}
class Browser extends Region {

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    public Browser() {
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        webEngine.load(getClass().getResource("/scheduler/resources/map.html").toExternalForm());
        //add the web view to the scene
        getChildren().add(browser);
        

    }
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

    @Override protected double computePrefWidth(double height) {
        return 200;
    }

    @Override protected double computePrefHeight(double width) {
        return 1000;
    }
}

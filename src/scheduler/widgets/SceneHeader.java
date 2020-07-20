package scheduler.widgets;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import scheduler.models.User;
import scheduler.utilities.Constants;

//Persistent header across most screens. Allows user to have easy access to account from anywhere within the app
public class SceneHeader extends HBox {

    Insets insets = new Insets(15);
    Region spacer = new Region();

    public SceneHeader(String title, User user) {

        //Make the spacer take extra space
        HBox.setHgrow(spacer, Priority.ALWAYS);

        //Set label as the passed in value
        Text label = new Text(title);
        label.setFont(Constants.SCENE_TITLE_FONT);

        //Set up the account button and image next to it        
        double btnSize = 45.0;
        Button accountButton = new Button();
        accountButton.setShape(new Circle(btnSize));
        accountButton.setPrefSize(btnSize, btnSize);
        Image image = new Image("/scheduler/resources/icon_account.png", 30, 30, true, true);
        ImageView icon = new ImageView(image);
        accountButton.setGraphic(icon);

        //Set up this object and add the children
        this.setPadding(insets);
        this.getChildren().addAll(
                label,
                spacer,
                accountButton
        );

    }
}

package scheduler.scenes;

import scheduler.widgets.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import scheduler.models.Controller;
import scheduler.models.User;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.event.ActionEvent; 
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage; 

public class AccountView extends BorderPane {

    Button logoutButton = new Button("Log out");
    Button changePasswordButton = new Button("Change Password");
    Button editInfoButton = new Button("Edit Account Information");
    Button deleteAccountButton = new Button("Delete Account");
    Insets insets = new Insets(15);
    final BooleanProperty firstTime = new SimpleBooleanProperty(true);
    int WIDTH = 200;
    
    public AccountView(User user) {
        super();
        //Set buttons' attributes
        logoutButton.setOnAction((ActionEvent e) -> {
            Controller.getInstance().logout();
        });
        
        // --- change password --------------------------------------------
        
        //this removes the default blue highlight of the button. 
        //*note: it removes the highlight forever. it won't highlight blue if you click on it either.
        changePasswordButton.setStyle("-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color; \n" +
            "    -fx-background-insets: 0, 1, 2;\n" +
            "    -fx-background-radius: 5, 4, 3;");
        
        changePasswordButton.setOnAction((ActionEvent e) -> {
            PasswordField passwordField = new PasswordField();
            PasswordField confirmPasswordField = new PasswordField();
            
            passwordField.setPromptText("Password");
            passwordField.setPrefWidth(WIDTH);
            confirmPasswordField.setPromptText("Re-enter password");
            confirmPasswordField.setPrefWidth(WIDTH);
            
            Stage popupwindow = new Stage();
            popupwindow.initModality(Modality.APPLICATION_MODAL);
            popupwindow.setTitle("Password Change Request");
            Button button = new Button("Save");
            button.setOnAction(f -> {
                firstTime.setValue(true);
                popupwindow.close();
            });
            VBox layout = new VBox(10);
            layout.setFillWidth(false);
            layout.getChildren().addAll(
                    new Text("Please enter your new password below.\n"),
                    passwordField,
                    confirmPasswordField,
                    button
            );
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout, 350, 200);
            popupwindow.setScene(scene);
            
            passwordField.focusedProperty().addListener((observable, oldValue, newValue) -> {
               if (newValue && firstTime.get()){
                   layout.requestFocus();
                   firstTime.setValue(false);
               }
            });
                        
            popupwindow.showAndWait();
        });
        
        // --- edit information --------------------------------------------
        
        //this removes the default blue highlight of the button. 
        //*note: it removes the highlight forever. it won't highlight blue if you click on it either.
        editInfoButton.setStyle("-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color; \n" +
            "    -fx-background-insets: 0, 1, 2;\n" +
            "    -fx-background-radius: 5, 4, 3;");
        
        editInfoButton.setOnAction((ActionEvent e) -> {
            TextField firstNameField = new TextField();
            TextField lastNameField = new TextField();
            TextField displayNameField = new TextField();
            TextField emailTextField = new TextField();
            TextField zipCodeField = new TextField();
            
            firstNameField.setPromptText("First name");
            firstNameField.setPrefWidth(WIDTH);
            lastNameField.setPromptText("Last name");
            lastNameField.setPrefWidth(WIDTH);
            displayNameField.setPromptText("Display name");
            displayNameField.setPrefWidth(WIDTH);
            zipCodeField.setPromptText("ZIP code");
            zipCodeField.setPrefWidth(WIDTH);
            emailTextField.setPromptText("Email");
            emailTextField.setPrefWidth(WIDTH);
            
            //creating popup window for editting account info
            Stage popupwindow = new Stage();
            popupwindow.initModality(Modality.APPLICATION_MODAL);
            popupwindow.setTitle("Edit Account Information");
            Button button = new Button("Save");
            button.setOnAction(f -> {
                firstTime.setValue(true);
                popupwindow.close();
            });
            VBox layout = new VBox(10);
            layout.setFillWidth(false);
            layout.getChildren().addAll(
                    new Text("Please edit your information below.\n"),
                    firstNameField,
                    lastNameField,
                    displayNameField,
                    emailTextField,
                    zipCodeField,
                    button
            );
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout, 350, 300);
            popupwindow.setScene(scene);
            

            firstNameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
               if (newValue && firstTime.get()){
                   layout.requestFocus();
                   firstTime.setValue(false);
               }
            });
            popupwindow.showAndWait();
        });
        
        
        // --- delete account --------------------------------------------
        
        //this removes the default blue highlight of the button. 
        //*note: it removes the highlight forever. it won't highlight blue if you click on it either.
        deleteAccountButton.setStyle("-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color; \n" +
            "    -fx-background-insets: 0, 1, 2;\n" +
            "    -fx-background-radius: 5, 4, 3;");
        
        deleteAccountButton.setOnAction((ActionEvent e) -> {
            //creating popup window for editting account info
            Stage popupwindow = new Stage();
            popupwindow.initModality(Modality.APPLICATION_MODAL);
            popupwindow.setTitle("Delete Account");
            Button button = new Button("Delete Account");
            button.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-background-insets: 0;\n" +
                "    -fx-text-fill: white;");
            button.setOnAction(f -> {
                firstTime.setValue(true);
                popupwindow.close();
            });
            VBox layout = new VBox(10);
            layout.setFillWidth(false);
            layout.getChildren().addAll(
                    new Text("Are you sure you'd like to delete your account?"),
                    new Text("This action cannot be undone.\n"),
                    button
            );
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout, 350, 200);
            popupwindow.setScene(scene);
            popupwindow.showAndWait();
        });

        //Set up a button that allows you to exit account view
        Button goDashboardButton = new Button("Return to Dashboard");
        goDashboardButton.setOnAction((ActionEvent e) -> {
            Controller.getInstance().goToScene("dashboard");
        });

        //Create the center node and children
        VBox center = new VBox();
        center.getChildren().addAll(
                new Text("\nName: "+user.getFullName()),
                new Text("Display Name: "+user.getDisplayName()),
                new Text("E-mail: "+user.getEmail()),
                new Text("Zipcode: "+user.getZipCode()+"\n"),
                
                new Text("Would you like to edit your personal information?"),
                editInfoButton, 
                
                new Text("\nWould you like to change your password?"),
                changePasswordButton,
                
                new Text("\nWould you like to delete your account?"),
                deleteAccountButton
        );

        //Create the left node and children
        VBox left = new VBox(5);
        left.getChildren().addAll(
                new Text("Personal Account Information:\t\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"),
                goDashboardButton,
                logoutButton
        );

        //Set the attributes of this scene
        AccountView.setMargin(left, insets);

        //Add the nodes to this object
        this.setTop(new SceneHeader("Account", user));
        this.setCenter(center);
        this.setLeft(left);
    }
}

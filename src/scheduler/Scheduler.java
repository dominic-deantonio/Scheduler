package scheduler;

import javafx.application.Application;
import javafx.stage.Stage;

public class Scheduler extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Super Scheduler");
        ScreenLogin loginScreen = new ScreenLogin();
        loginScreen.run(primaryStage);

//        Button btn = new Button();
//        btn.setText("Test");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
////                String output = Firebase.fetch(Firebase.USERS);
////                System.out.println(output);
//
//                String payload = Firebase.getCredentialsPayload("someobone@icloud.com", "somePassword");
//                Firebase.sendPost("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=AIzaSyDAEG_Ynr-ewIov3Au1YUlR9breoQhXFHQ", payload);
//            }
//        });
//
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//
//        Scene scene = new Scene(root, 300, 250);
//
//        primaryStage.setOnCloseRequest(e -> {
//            System.exit(0);
//        });
//
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

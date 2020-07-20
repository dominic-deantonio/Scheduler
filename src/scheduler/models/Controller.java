package scheduler.models;

import scheduler.services.Firebase;
import com.google.gson.Gson;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scheduler.scenes.*;

// This is the central class to control the flow of the application
public class Controller {

    private static Controller instance; //The singleton, accessed through the getter    
    private Parent[] sceneParents;
    private Scene scene;
    private User user;

    // Private controller prevents new instances
    private Controller() {
    }

    // Access the instance only through this getter
    public static Controller getInstance() {
        instance = instance == null ? new Controller() : instance; //Look up ternary operators if confused
        return instance;
    }

    // Initial entry point into the application
    public void runProgram(Stage mainWindow) {
        buildScenes();
        getInstance(); // Prevents attempted method calls on uninstantiated instance (was happening on first getInstance().login() method because didnt exist)
        scene = new Scene(new LoginView());
        mainWindow.setMinHeight(500);
        mainWindow.setMinWidth(500);
        mainWindow.setTitle("Super Scheduler");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    // Login process. All steps should occur in this method
    public void login(String email, String password) throws IOException {

        if (email.equals("") || password.equals("")) {
            throw new IOException("You must enter an email and password.");
        }
        //Throw more exceptions for security, formatting, bad response from network, etc here
        //This method needs A LOT of work before safely building the user

        String jsonResponse = Firebase.getInstance().sendLoginRequest(email, password);
        user = buildUser(jsonResponse);
        buildScenes(); //New user, rebuild scenes for this user
        goToScene("dashboard");
    }

    // Delete the user object and return to the login screen
    public void logout() {
        user = null;  //is this good enough for deleting?
        goToScene("login");
    }

    // Sign up method
    public void signUp(String email, String password) throws IOException {

        if (email.equals("") || password.equals("")) {
            throw new IOException("You must enter an email and password.");
        }
        //Throw more exceptions for security, formatting, bad response from network, etc here
        //This method needs A LOT of work before safely building the user

        String jsonResponse = Firebase.getInstance().sendLoginRequest(email, password);
        user = buildUser(jsonResponse);
        buildScenes();
        goToScene("dashboard");
    }

    //Create the user from the response and update the user info
    private User buildUser(String loginResponse) {
        Gson gson = new Gson();
        user = gson.fromJson(loginResponse, User.class);
        updateUserInfo(user);
        return user;
    }

    //Update the user information by querying the database and applying response to the user object
    private User updateUserInfo(User user) {
        Gson gson = new Gson();
        String databaseResponse = Firebase.getInstance().getUserInfo(user.getId());
        UserInfo data = gson.fromJson(databaseResponse, UserInfo.class);
        user.updateData(data);
        return user;
    }

    //Create all the scene nodes. Holding them in an object allows persistent data entered
    //for now, null user means user is logged out. Do not build scenes for null user.
    private void buildScenes() {
        if (user == null) {
            sceneParents = new Parent[]{
                new LoginView(),
                new SignupView(),
                new ForgotView()
            };
        } else {
            sceneParents = new Parent[]{
                new LoginView(),
                new SignupView(),
                new ForgotView(),
                new DashboardView(user),
                new AccountView(user)
            };
        }
    }

    //Navigate to specified scene
    public void goToScene(String sceneName) {
        switch (sceneName) {
            case "login":
                scene.setRoot(sceneParents[0]);
                break;
            case "signup":
                scene.setRoot(sceneParents[1]);
                break;
            case "forgot":
                scene.setRoot(sceneParents[2]);
                break;
            case "dashboard":
                scene.setRoot(sceneParents[3]);
                break;
            case "account":
                scene.setRoot(sceneParents[4]);
                break;
            default:
                System.out.println("There was an error going to \'" + sceneName + "\'. Is it spelled correctly?");
                break;
        }
    }
}

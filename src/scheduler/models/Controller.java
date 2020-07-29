package scheduler.models;

import scheduler.services.Firebase;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
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
        mainWindow.setMinWidth(1100);
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
        user = updateUserObjectData();
        buildScenes(); //New user, rebuild scenes for this user
        goToScene("dashboard");
    }

    // Delete the user object and return to the login screen
    public void logout() {
        user = null;  //is this good enough for deleting?
        goToScene("login");
    }

    // Sign up method
    public void signUp(String fName, String lName, String zip, String email, String pWord, String pWord2) throws IOException {

        String[] inputs = new String[]{fName, lName, zip, email, pWord, pWord2};
        ArrayList<String> missingInputs = new ArrayList();
        
        //Security methods should be separated into their own classes
        for (String input : inputs) {
            if (input.equals("")) {
                missingInputs.add(input);
            }
        }
        if (missingInputs.size() > 0) {
            String errorMsg = missingInputs.size() + " missing field(s) - all fields are required";
            throw new IOException(errorMsg);
        }
        if (!pWord.equals(pWord2)) {
            throw new IOException("Passwords don't match");
        }

        //Throw more exceptions for security, formatting, bad response from network, etc here
        //This method needs A LOT of work before safely building the user
        String jsonResponse = Firebase.getInstance().sendSignupRequest(fName, lName, email, zip, pWord);
        user = buildUser(jsonResponse);
        jsonResponse = Firebase.getInstance().putNewUserData(user.getId(), fName, lName, email, zip);
        user = updateUserObjectData();
        System.out.println(jsonResponse);
        buildScenes();
        goToScene("dashboard");
    }

    //Create the user from the response and update the user info
    private User buildUser(String loginResponse) {
        Gson gson = new Gson();
        user = gson.fromJson(loginResponse, User.class);
        return user;
    }

    //Update the user information by querying the database and applying response to the user object
    private User updateUserObjectData() {
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
                new AccountView(user),
                new MyScheduleView(user),
                new CreateGroupView(user),
                new ViewGroupView(user),
                new OptionsView(user),
                new MapView(user),};
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
            case "schedule":
                scene.setRoot(sceneParents[5]);
                break;
            case "create":
                scene.setRoot(sceneParents[6]);
                break;
            case "view":
                scene.setRoot(sceneParents[7]);
                break;
            case "options":
                scene.setRoot(sceneParents[8]);
                break;
            case "map":
                scene.setRoot(sceneParents[9]);
                break;
            default:
                System.out.println("There was an error going to \'" + sceneName + "\'. Is it spelled correctly?");
                break;
        }
    }
}

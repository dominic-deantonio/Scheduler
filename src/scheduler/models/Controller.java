package scheduler.models;

import scheduler.services.Firebase;
import com.google.gson.Gson;
import java.io.IOException;
import java.time.LocalDate;
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
    UserSecurity userSec = new UserSecurity();

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
        mainWindow.setMinHeight(750);
        mainWindow.setMinWidth(1100);
        mainWindow.setTitle("Super Scheduler");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    // Login process. All steps should occur in this method
    public void login(String email, String password) throws IOException {

        UserSecurity userSec = new UserSecurity();
        userSec.loginCheck(email, password);

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

        userSec.emailValidation(email);
        userSec.accountInputs(fName, lName, zip, email, pWord, pWord2);

        //Throw more exceptions for security, formatting, bad response from network, etc here
        //This method needs A LOT of work before safely building the user
        String jsonResponse = Firebase.getInstance().sendSignupRequest(fName, lName, email, zip, pWord);
        user = buildUser(jsonResponse);
        jsonResponse = Firebase.getInstance().putNewUserData(user.getId(), fName, lName, email, zip);
        user = updateUserObjectData();
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

    //Add the meeting ID to the user account, and the actual meeting to the list of meetings
    public String addNewMeeting(LocalDate date, int startHour, int startMin, int endHour, int endMin, String organizerId, String subject) {

        // TODO: ADD SECURITY CHECKING OF USER INPUT HERE !!IMPORTANT!!        
        Meeting mtg = new Meeting(date, startHour, startMin, endHour, endMin, organizerId, subject);
        user = updateUserObjectData(); // Get the latest data from the database in case it changed
        String result = Firebase.getInstance().putNewMeeting(user, mtg);
        DashboardView dbView = new DashboardView(user);     // Build new dashboard using the updated user
        sceneParents[3] = dbView;                           // Replaces old dashboard view
        goToScene("dashboard");
        return result;
    }

    public void changePassword(String tokenId, String newPass) throws IOException {
        String jsonResponse = Firebase.getInstance().changePassRequest(tokenId, newPass);
        System.out.println("Change password result: " + jsonResponse);
    }

    public void editAccountInfo(String fName, String lName, String zip, String email) throws IOException {
        String jsonResponse = Firebase.getInstance().putEditedUserData(user.getId(), fName, lName, email, zip);
        user = updateUserObjectData();
        System.out.println(jsonResponse);
        buildScenes();
        goToScene("dashboard");
    }

	//method to delete account. 
	public void deleteAccount(String tokenId) throws IOException {
        String jsonResponse = Firebase.getInstance().deleteAccountRequest(tokenId);
        System.out.println(jsonResponse);
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

    public User getUser() {
        return user;
    }
}

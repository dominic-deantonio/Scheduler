package scheduler.models;

import scheduler.services.Firebase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;

// This is the central class to control the flow of the application
public class Controller {

    private static Controller instance; //The singleton, accessed through the getter

    //P rivate controller prevents new instances
    private Controller() {
    }

    // Access the instance only through this getter
    public static Controller getInstance() {
        instance = instance == null ? new Controller() : instance; //Look up ternary operators if confused
        return instance;
    }

    //A pp level logic for the login process. All steps should occur in this method
    public User login(String email, String password) throws IOException {

        if (email.equals("") || password.equals("")) {
            throw new IOException("You must enter an email and password.");
        }
        //Throw more exceptions for security, formatting, bad response from network, etc here
        //This method needs A LOT of work before safely building the user

        String jsonResponse = Firebase.getInstance().sendLoginRequest(email, password);
        User user = buildUser(jsonResponse);
        System.out.println(user); //Just here to check that it was successful
        return new User();
    }

    //Create the user from the response and update the user info
    private User buildUser(String loginResponse) {
        Gson gson = new Gson();
        User user = gson.fromJson(loginResponse, User.class);
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

}

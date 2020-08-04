package scheduler.models;

//Simple class to represent a user
import java.util.ArrayList;

public class User {

    //These have to be named exactly like this so GSON can map the JSON response
    //However, you CAN add more fields which aren't needed for GSON
    private String firstName;
    private String lastName;
    private String email;
    private String kind;
    private String localId;                                         //the user id. use this to reference this user in the database
    private String displayName;                                     //this field doesn't need to be used, but does need to exist
    private String idToken;                                         //maybe used for security? not sure - 
    private ArrayList<String> meetingIds = new ArrayList<>();       //list of meeting IDs
    private int expiresIn;                                          //perhaps how long the idToken lasts?
    private int zipCode;

    public String getEmail() {
        return email;
    }

    public String getId() {
        return localId;
    }

    public String getToken() {
        return idToken;
    }

    public String getKind() {
        return kind;
    }

    public int getExpires() {
        return expiresIn;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getDisplayName() {
        if (!"".equals(displayName)) {
            return displayName;
        } else {
            return "You have not specified a display name.";
        }
    }

    public int getZipCode() {
        return zipCode;
    }

    public ArrayList<String> getMeetings() {
        return meetingIds;
    }

    @Override
    public String toString() {
        return "firstName: " + firstName
                + "\nlastName: " + lastName
                + "\nemail: " + email
                + "\nkind: " + kind
                + "\nlocalId: " + localId
                + "\ndisplayName: " + displayName
                //                + "\nidToken: " + idToken         //omitted from toString because so long
                + "\nexpiresIn: " + expiresIn
                + "\nzipCode: " + zipCode;
    }

    //Takes in the userinfo class and maps the relevant fields to this class
    //EXTREMELY IMPORTANT: Update this when the schema of the database changes
    public void updateData(UserInfo info) {
        firstName = info.getFirstName();
        lastName = info.getLastName();
        zipCode = info.getZipCode();
        email = info.getEmail();            //This was already set by the auth JSON response, but no harm being uniform
        meetingIds = info.getMeetings();
        System.out.println("Received " + firstName + "'s account info.");
    }
}

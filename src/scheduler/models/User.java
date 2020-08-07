package scheduler.models;

//Simple class to represent a user
import java.util.ArrayList;

public class User {

    //These have to be named exactly like this so GSON can map the JSON response
    //However, you CAN add more fields which aren't needed for GSON
    private String firstName;
    private String lastName;
    private String email;
    private String localId;                                         //the user id. use this to reference this user in the database
    private String idToken;                                         //maybe used for security? not sure - 
    private ArrayList<Meeting> meetings = new ArrayList<>();        //list of meetings
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public ArrayList<Meeting> getMeetings() {
        return meetings;
    }

    public ArrayList<Meeting> setMeetings(ArrayList<Meeting> newMeetings) {
        this.meetings = newMeetings;
        return meetings;
    }

    // Make sure the values were checked before passing into here
    public User setEditValues(String fName, String lName, String email, String zip) {
        firstName = fName;
        lastName = lName;
        this.email = email;
        zipCode = Integer.parseInt(zip);
        return this;
    }

    @Override
    public String toString() {
        return "firstName: " + firstName
                + "\nlastName: " + lastName
                + "\nemail: " + email
                + "\nlocalId: " + localId
                + "\nzipCode: " + zipCode;
    }

    //Takes in the userinfo class and maps the relevant fields to this class
    //EXTREMELY IMPORTANT: Update this when the schema of the database changes
    public void updateData(UserInfo info) {
        firstName = info.getFirstName();
        lastName = info.getLastName();
        zipCode = info.getZipCode();
        email = info.getEmail();            //This was already set by the auth JSON response, but no harm being uniform
        meetings = info.getMeetings();
        System.out.println("Received " + firstName + "'s account info.");
    }
}

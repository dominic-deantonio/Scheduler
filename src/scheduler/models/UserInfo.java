package scheduler.models;

// This class exists so GSON can map the database response to this object GSON is limited because it can only create 
// new objects from JSON, not update existing objects. Since we have to make a call to the auth 
// and a call to the db, this class will serve as a temporary object for GSON to map to. 
// This object will then be passed directly to the User where it can be consolidated
// As fields in the database change, this must be updated to reflect it or else GSON will fail to map
// The fields must be named exactly the same as in the database (JSON response more specifically)
class UserInfo {

    private String firstName;
    private String lastName;
    private int zipCode;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public int getZipCode(){
        return zipCode;
    }

}

package scheduler.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class UserSecurity {

    public UserSecurity() {

    }

    // Validates all fields are present while logging in
    public void loginCheck(String email, String password) throws IOException {

        if (password.equals("") && email.equals("")) {
            throw new IOException("Please enter a valid email and password");
        }
        if (password.equals("")) {
            throw new IOException("Please enter a password");
        }
        if (email.equals("")) {
            throw new IOException("Please enter an email account");
        }
    }

    // validates all inputs for sign up process
    public void accountInputs(String fName, String lName, String zip, String email,
            String pWord, String pWord2) throws IOException {

        String[] inputs = new String[]{fName, lName, zip, email, pWord, pWord2};
        ArrayList<String> missingInputs = new ArrayList();

        for (String input : inputs) {
            if (input.equals("")) {
                missingInputs.add(input);
            }
        }
        if (missingInputs.size() > 0) {
            String errorMsg = missingInputs.size() + " missing field(s) - all fields are required";
            throw new IOException(errorMsg);
        }

        passwordValidation(pWord, pWord2);

    }

    // validates user password to meet application requirements
    public void passwordValidation(String pWord, String pWord2) throws IOException {

        Pattern specialCharPattern = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePattern = Pattern.compile("[A-Z]");
        Pattern lowerCasePattern = Pattern.compile("[a-z]");
        Pattern digitCasePattern = Pattern.compile("[0-9]");

        boolean flag = true;

        if (!pWord.equals(pWord2)) {
            throw new IOException("Passwords do not match");
        }
        if (pWord.length() < 8) {
            flag = false;
        }
        if (!specialCharPattern.matcher(pWord).find()) {
            flag = false;
        }
        if (!UpperCasePattern.matcher(pWord).find()) {
            flag = false;
        }
        if (!lowerCasePattern.matcher(pWord).find()) {
            flag = false;
        }
        if (!digitCasePattern.matcher(pWord).find()) {
            flag = false;
        }

        if (flag == false) {
            throw new IOException("Password must be 8 characters long\n"
                    + "and contain lower case, upper case,\n"
                    + "numbers, and special characters");
        }
    }
}

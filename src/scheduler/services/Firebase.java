/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.services;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


//There is no need to instantiate new Firebase classes, so this is a singleton
// See https://firebase.google.com/docs/reference/rest/database for db api
public class Firebase {

    //Firebase should be accessed by the getInstance
    private static Firebase instance;

    //Private constructor prevents more instances
    private Firebase() {
    }

    //Access the instance only through this getter
    public static Firebase getInstance() {
        instance = instance == null ? new Firebase() : instance; //Look up ternary operators if confused
        return instance;
    }

    //Class variables - might remove many if not necessary
    //Some of these values like api key should not be saved in the app itself. Major security risk
    private final String API_KEY = "AIzaSyDAEG_Ynr-ewIov3Au1YUlR9breoQhXFHQ";
    private final String AUTH_DOMAIN = "scheduler-cmsc.firebaseapp.com";
    private final String PROJECT_ID = "scheduler-cmsc";
    private final String STORAGE_BUCKET = "scheduler-cmsc.appspot.com";
    private final String MESSAGING_SENDER_ID = "176621522738";
    private final String APP_ID = "1:176621522738:web:ae75cf4dccb07b9a86112b";
    private final String MEASUREMENT_ID = "G-H2P33184SZ";
    private final String DB_ENDPOINT = "https://scheduler-cmsc.firebaseio.com/";
    private final String AUTH_ENDPOINT = "https://identitytoolkit.googleapis.com/v1/accounts:";
    private final String SIGN_UP_ENDPOINT = AUTH_ENDPOINT + "signUp?key=" + API_KEY;
    private final String SIGN_IN_ENDPOINT = AUTH_ENDPOINT + "signInWithPassword?key=" + API_KEY;

    private HttpURLConnection connection;

    // Sends GET http requests and returns a string response
    public String get(String urlString) {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println("Status: " + status);

            if (status > 299) {
                // Failure
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                // Successful
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }

            // Write the stream contents to the reponse
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            connection.disconnect();
        }

        return responseContent.toString();
    }

    // Sends POST or PUT request
    public String sendRequest(String type, String urlString, String payload) {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        int status = 400;
        try {
            URL url = new URL(urlString);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(type);  //Should be "POST" or "PUT" 
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            status = connection.getResponseCode();
            System.out.println("Connection status: " + status);

            if (status > 299) {
                // Failure
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                // Successful
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }

            // Write the response to the string
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            connection.disconnect();
        }

        return responseContent.toString();
    }

    // Attempts a login request
    public String sendLoginRequest(String email, String password) {
        String payload = buildCredentialsPayload(email, password);
        String response = "";
        try {
            response = sendRequest("POST", SIGN_IN_ENDPOINT, payload);
        } catch (Exception e) {

        }
        return response;
    }

    // Creates JSON payloard for signing in and signing up
    private String buildCredentialsPayload(String email, String password) {
        return "{\"email\":\"" + email + "\",\"password\":\"" + password + "\",\"returnSecureToken\":true}";
    }

    // Builds new user data JSON to send to the database
    // this should be updated as new data requirements are realized and the db schema is changed
    private String buildSignUpPayload(String first, String last, String email, String zip) {
        String newUserJson = "{\"firstName\":\"" + first + "\","
                + "\"lastName\":\"" + last + "\","
                + "\"email\":\"" + email + "\","
                + "\"zipCode\": \"" + zip + "\"}";
        return newUserJson;
    }

    // Query a user based on the userId
    public String getUserInfo(String userId) {
        String response = get(DB_ENDPOINT + "users/" + userId + ".json");
        return response;
    }

    // Attempts a signup request
    public String sendSignupRequest(String firstName, String lastName, String email, String zip, String password) {
        String response;
        String payload = buildCredentialsPayload(email, password);
        response = sendRequest("POST", SIGN_UP_ENDPOINT, payload);

        return response;
    }

    //Put new user data 
    public String putNewUserData(String userId, String firstName, String lastName, String email, String zip) {
        String payload = buildSignUpPayload(firstName, lastName, email, zip);
        String response = sendRequest("PUT", DB_ENDPOINT + "users/" + userId + ".json", payload);
        System.out.println(response);
        return response;
    }
   
}

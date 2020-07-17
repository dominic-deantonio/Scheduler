/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.services;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Firebase {

    public static final String API_KEY = "AIzaSyDAEG_Ynr-ewIov3Au1YUlR9breoQhXFHQ";
    public static final String AUTH_DOMAIN = "scheduler-cmsc.firebaseapp.com";
    public static final String DATABASE_URL = "https://scheduler-cmsc.firebaseio.com";
    public static final String PROJECT_ID = "scheduler-cmsc";
    public static final String STORAGE_BUCKET = "scheduler-cmsc.appspot.com";
    public static final String MESSAGING_SENDER_ID = "176621522738";
    public static final String APP_ID = "1:176621522738:web:ae75cf4dccb07b9a86112b";
    public static final String MEASUREMENT_ID = "G-H2P33184SZ";

    public static final String USERS = "https://scheduler-cmsc.firebaseio.com/users.json";
    public static final String SIGN_UP = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY;
    public static final String SIGN_IN = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY;

    private static HttpURLConnection connection;

    public static String fetch(String urlString) {
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
                //Failure
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
            } else {
                //Successful
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
            }
            reader.close();

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            connection.disconnect();
        }

        return responseContent.toString();
    }

    public static int sendPost(String urlString, String payload) {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        int status = 400;
        try {
            URL url = new URL(urlString);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            status = connection.getResponseCode();
            System.out.println("Connection status: " + status);

//            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
//                StringBuilder response = new StringBuilder();
//                String responseLine = null;
//                while ((responseLine = br.readLine()) != null) {
//                    response.append(responseLine.trim());
//                }
//                System.out.println(response.toString());
//            }
            if (status > 299) {
                //Failure
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
            } else {
                //Successful
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
            }
            System.out.println(responseContent.toString());
            reader.close();

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            connection.disconnect();
        }
        return status;
    }

    // Used for login and signup
    public static String buildCredentialsPayload(String email, String password) {
        return "{\"email\":\"" + email + "\",\"password\":\"" + password + "\",\"returnSecureToken\":true}";
    }

//    public static Gson getUsersList() {
//        Gson json = new GsonBuilder().create();
//        String response = getDataFromFirebase();
//        Gson value = json.fromJson(response, String.class);
//        
//        return value;
//    }
    public static boolean doLogin(String email, String password) {
        String payload = buildCredentialsPayload(email, password);
        int result = sendPost(SIGN_IN, payload);
        return result <= 200; //get the bool, return it in one line
    }

    public static boolean doSignUp(String email, String password) {
        String payload = buildCredentialsPayload(email, password);
        int result = sendPost(SIGN_UP, payload);
        return result <= 200; //get the bool, return it in one line
    }

}

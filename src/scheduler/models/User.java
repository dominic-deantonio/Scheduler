//Author: Dominic DeAntonio
//Class: UMUC SDEV 425 6380
//Date: 9 February 2020

package scheduler.models;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

//Simple class to represent a user
public class User {
        
    public String name;
    public String lockoutTime = "0";
    public String email;
    
    public String getName(){
        return name;
    }
    
    ///All of the methods here need to be tailored to this application. These are 
    ///placeholders from a previous assignment. Or implement however you want to make it better
    
    //Incremement the value in the db
    public void incrementFails(){

    }
    
    //Get number of failures from db
    public int getNumFailures(){
        return 0;
    }    
 
    //Set the lockout time to now + 30 minutes
    void lockOut(){
        lockoutTime = getRecordableTime(LocalDateTime.now().plusMinutes(30));
    }
    
    //Clears the lockout variables upon successful login 
    void clearLockout(){

    }

    //Checks if the user is currently locked out. Returns the remaining minutes until the lock expires.
    public int checkLockout(){
        //checks if the user is locked out
        return 0;
    }
    
    //Takes a parameter time and returns it as a string to enable recording in db
    String getRecordableTime(LocalDateTime dt){        
        DecimalFormat f = new DecimalFormat("00");        
        
        String time = "" + 
                dt.getYear() + " " +
                f.format(dt.getMonthValue()) +  " " +
                f.format(dt.getDayOfMonth()) + " " +
                f.format(dt.getHour()) + " " +
                f.format(dt.getMinute());
        
        return time;
    }
    
    //Get recorded time and converts to manageable format
    LocalDateTime getSavedTime(String time){
        
        //Check if the user has any lockout time, if not, return null
        if(!time.equals("0")){
            String[] vals = time.split(" ");        
            int[] v = new int[5];
            
            for (int i = 0; i < 5; i ++)
                v[i] = Integer.parseInt(vals[i]);        

            return LocalDateTime.of(v[0], v[1], v[2], v[3], v[4]);        
        }else{
            return null;
        }
    }
   
}

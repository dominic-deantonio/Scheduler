//Author: Dominic DeAntonio
//Class: UMUC SDEV 425 6380
//Date: 9 February 2020

package scheduler;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

//Simple class to represent a user
public class User {
        
    public String name;
    public String password;
    public int numFails;
    public String lockoutTime = "0";
    public String email;
    public String previousLogin = "0";
    
    public String getName(){
        return name;
    }
    
    //Increments the attempt var and returns it
    public void incrementFails(){
        numFails++;
    }
    
    public int getNumFailures(){
        return numFails;
    }
    
    public boolean authenticate(String pword){
        if(pword.equals(password)){
            return true;
        }else{
            return false;
        }
    }    
    
    //Set the lockout time to now + 30 minutes
    void lockOut(){
        lockoutTime = getRecordableTime(LocalDateTime.now().plusMinutes(30));
    }
    
    //Clears the lockout variables
    void clearLockout(){
        numFails=0;
        lockoutTime = "0";
    }
    
    //Sets users latest login to the newest login and returns the previous login
    public String recordLogin(){        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime previous = getSavedTime(previousLogin);
        previousLogin = getRecordableTime(LocalDateTime.now());        
       
        return previous.format(formatter);
    }

    //Checks if the user is currently locked out. Returns the remaining minutes until the lock expires.
    public int checkLockout(){
        LocalDateTime lockout = getSavedTime(lockoutTime);
        
        //Only proceeds if there lockout time is not null, otherwise the user is not locked out.
        if(lockout != null){
            long remain = ChronoUnit.MINUTES.between(LocalDateTime.now(), lockout);
            if(remain < 0)
                return 0;

            return (int)remain;
        }else{
            return 0;
        }
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

package scheduler.models;

//This class holds the meeting data and has methods to provide GUI representation data
import java.time.LocalDateTime;

public class Meeting1 {

    public String subject = "No subject";
    private String start;     //Storage format format should be YYYYMMDDHHMM
    private String end;       //Storage format format should be YYYYMMDDHHMM

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private int[] position = new int[]{};

    private String[] attendeeIds;

    public Meeting1(String start, String end) {
        this.start = start;
        this.end = end;
        startDateTime = setDateTime(start);
        endDateTime = setDateTime(end);
    }

    public Meeting1(String start, String end, String subject) {
        this.start = start;
        this.end = end;
        this.subject = subject;
        startDateTime = setDateTime(start);
        endDateTime = setDateTime(end);
    }

    private LocalDateTime setDateTime(String date) {
        int year, month, dayOfMonth, hour, minute;

        //Get the year value
        String num = date.substring(0, 4);
        year = Integer.valueOf(num);

        //Get the month value
        num = date.substring(4, 6);
        month = Integer.valueOf(num);

        //Get the day value
        num = date.substring(6, 8);
        dayOfMonth = Integer.valueOf(num);

        //Get the hour value
        num = num.substring(8, 10);
        hour = Integer.valueOf(num);

        //Get the minute value
        num = num.substring(10, 12);
        minute = Integer.valueOf(num);

        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);

    }

    public static Meeting1[] getMockAppointments() {
        return new Meeting1[]{
            new Meeting1("202007260700", "202007260800", "Dental Appointment"),
            new Meeting1("202007261300", "202007261300", "Gym - leg day"),
            new Meeting1("202007261300", "202007261300", "Doctor's office"),
            new Meeting1("202007261300", "202007261300", "Jeff Facetime meeting"),
            new Meeting1("202007261300", "202007261300", "Lunch Meeting"),
            new Meeting1("202007261300", "202007261300", "Movies with Mom"),
            new Meeting1("202007261300", "202007261300", "Dinner at Applebees"),
            new Meeting1("202007261300", "202007261300", "Study Group")
        };
    }
}

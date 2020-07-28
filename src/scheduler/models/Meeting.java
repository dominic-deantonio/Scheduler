package scheduler.models;

import java.time.LocalDateTime;

public class Meeting {

    public String subject = "No subject";
    private String start;       //Storage format format should be YYYYMMDDHHMM
    private String end;         //Storage format format should be YYYYMMDDHHMM

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private int[] position = new int[]{};

    private String[] attendeeIds;

    public Meeting(String start, String end) {
        this.start = start;
        this.end = end;
        startDateTime = parseDateTime(start);
        endDateTime = parseDateTime(end);
    }

    public Meeting(String start, String end, String subject) {
        this.start = start;
        this.end = end;
        this.subject = subject;
        startDateTime = parseDateTime(start);
        endDateTime = parseDateTime(end);
    }

    //NEEDS ERROR CHECKING BADLY
    private LocalDateTime parseDateTime(String date) {
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
        num = date.substring(8, 10);
        hour = Integer.valueOf(num);

        //Get the minute value
        num = date.substring(10, 12);
        minute = Integer.valueOf(num);

        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);

    }

    public static Meeting[] getMockAppointments() {
        return new Meeting[]{
            new Meeting("202007260730", "202007260800", "Dental Appointment"),
            new Meeting("202007260800", "202007260900", "Gym - leg day"),
            new Meeting("202007260915", "202007261030", "Doctor's office"),
            new Meeting("202007261045", "202007261215", "Lunch Meeting"),
            new Meeting("202007261430", "202007261645", "Jeff Facetime meeting"),
            new Meeting("202007261700", "202007261930", "Movies with Mom"),
            new Meeting("202007262015", "202007262100", "Study Group")

        };
    }

    public String getDisplayInfo() {
        return subject;
    }

    public int getRow(LocalDateTime dateTime) {

        LocalDateTime target = LocalDateTime.of(2020, 07, 26, 00, 00);
        int row = 0;
        for (int i = 1; i < 97; i++) {

            if (target.isEqual(dateTime)) {
                row = i;
                break;
            } else {
                target = target.plusMinutes(15);
            }
        }
        return row;
    }

    public int getDay() {
        return 1;
    }

    public int getStartingRow() {
        return getRow(startDateTime);
    }

    public int getSpan() {
        int startRow = getRow(startDateTime);
        int endRow = getRow(endDateTime);
        int span = endRow - startRow;
        return span;
    }
}

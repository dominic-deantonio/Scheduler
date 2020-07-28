package scheduler.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Meeting {

    public String subject = "No subject";
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String organizerId = "";
    private String[] attendeeIds;

    public Meeting(String start, String end) {
        startDateTime = parseDateTime(start);
        endDateTime = parseDateTime(end);
    }

    public Meeting(String start, String end, String subject) {
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
            new Meeting("202007270730", "202007270800", "Dental Appointment"),
            new Meeting("202007280800", "202007280900", "Gym - leg day"),
            new Meeting("202007270915", "202007271030", "Doctor's office"),
            new Meeting("202007281045", "202007281215", "Lunch Meeting"),
            new Meeting("202007291430", "202007291645", "Jeff Facetime meeting"),
            new Meeting("202007281700", "202007281930", "Movies with Mom"),
            new Meeting("202007302015", "202007302100", "Study Group")

        };
    }

    public String getDisplayInfo() {
        return subject;
    }

    public int getRow(boolean start) {

        LocalDateTime dateTimeToUse = start ? startDateTime : endDateTime;
        LocalDateTime target = LocalDateTime.of(dateTimeToUse.getYear(), dateTimeToUse.getMonth(), dateTimeToUse.getDayOfMonth(), 00, 00);
        int row = 0;
        for (int i = 1; i < 97; i++) {

            if (target.isEqual(dateTimeToUse)) {
                row = i;
                break;
            } else {
                target = target.plusMinutes(15);
            }
        }
        return row;
    }

    public int getDay() {
        return startDateTime.getDayOfWeek().getValue();
    }

    public int getStartingRow() {
        return getRow(true);
    }

    public int getSpan() {
        int startRow = getRow(true);
        int endRow = getRow(false);
        int span = endRow - startRow;
        return span;
    }

    public LocalDate getStartDate() {
        return startDateTime.toLocalDate();
    }

}

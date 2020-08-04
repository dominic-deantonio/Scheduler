package scheduler.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Meeting {

    public final String id;     // ID is created on construction, no need for getter?
    public String subject = "No subject";
    private String start;       // Format should be YYYYMMDDhhmm
    private String end;         // Format should be YYYYMMDDhhmm
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String organizerId = "FAKEUSERID";
    private String[] attendeeIds = new String[]{"QA6RQ46QHIVC9QYA6u7q7CkgXje2", "jeb28ERX3sexQ9g6icSDslFIUIZ2"};

    public Meeting() {
        id = UUID.randomUUID().toString();
    }

    public Meeting(String start, String end) {
        this.start = start;
        this.end = end;
        id = UUID.randomUUID().toString();
        startDateTime = parseDateTime(start);
        endDateTime = parseDateTime(end);
    }

    public Meeting(String start, String end, String subject) {
        this.start = start;
        this.end = end;
        id = UUID.randomUUID().toString();
        this.subject = subject;
        startDateTime = parseDateTime(start);
        endDateTime = parseDateTime(end);
    }

    public Meeting(String start, String end, String organizerId, String subject) {
        this.start = start;
        this.end = end;
        this.organizerId = organizerId;
        this.subject = subject;
        id = UUID.randomUUID().toString();
        startDateTime = parseDateTime(start);
        endDateTime = parseDateTime(end);
    }

    public Meeting(LocalDate date, int startHour, int startMin, int endHour, int endMin, String organizerId, String subject) {
        id = UUID.randomUUID().toString();
        this.organizerId = organizerId;
        this.subject = subject;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        startDateTime = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), startHour, startMin);
        endDateTime = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), endHour, endMin);
        this.start = startDateTime.format(formatter);
        this.end = endDateTime.format(formatter);
        
        System.out.println("Start:" + this.start);
        System.out.println("End:" + this.end);
    }

    //NEEDS ERROR CHECKING BADLY or convert to using traditional LocalDateTime parsing method
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        String formattedStart = startDateTime.format(formatter);
        String formattedEnd = endDateTime.format(formatter);

        return subject + "\n"
                + "\nStarts:\t" + formattedStart
                + "\nEnds:\t" + formattedEnd
                + "\nAttendees: " + displayAttendees();
    }

    public String getButtonDisplay() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        String formattedStart = startDateTime.format(formatter);
        String formattedEnd = endDateTime.format(formatter);

        String output = getSpan() > 1 ? subject + "\n" + formattedStart + " to " + formattedEnd : subject;
        return output;
    }

    private String displayAttendees() {
        return "Some people";
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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public LocalDate getEndDate() {
        return endDateTime.toLocalDate();
    }

    public String[] getAttendees() {
        return attendeeIds;
    }

    public String getOrganizer() {
        return organizerId;
    }

    public String getSubject() {
        return subject;
    }

    public String getStartString() {
        return start;
    }

    public String getEndString() {
        return end;
    }

}

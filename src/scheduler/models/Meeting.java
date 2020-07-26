package scheduler.models;

//This class holds the meeting data and has methods to provide GUI representation data
public class Meeting {

    public String subject = "No subject";
    public int startHour;
    public int startMinute;
    public int endHour;
    public int endMinute;
    public int day = 0;
    public int startDate = 20200726;
    public int startTime = 1300;
    public int endDate = 20200726;
    public int endTime = 1345;

    public Meeting(int startHour, int startMinute, int endHour, int endMinute) {
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

    public Meeting(int startHour, int startMinute, int endHour, int endMinute, String subject) {
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.subject = subject;
    }

    // Use this to get the start or stop minute
    // This is terrible and you should use actual time instead.
    public int getMinuteQuarter(int input) {
        switch (input) {
            case 0:
                return 0;
            case 15:
                return 1;
            case 30:
                return 2;
            case 45:
                return 3;
            default:
                System.out.println("Can't parse meeting minute. Should be a multiple of 0, 15, 30, or 45");
                return 0;
        }
    }

    // How many tiles this should stetch over
    public int getSpan() {
        return getEndingRow() - getStartingRow();
    }

    public int getEndingRow() {
        int row = 1;
        if (endHour == 0) {
            row += getMinuteQuarter(endMinute);
        } else {
            row = endHour * 4;
            row += getMinuteQuarter(endMinute) + 1;
        }
        return row;
    }

    public int getStartingRow() {
        int row = 1;
        if (startHour == 0) {
            row += getMinuteQuarter(startMinute);
        } else {
            row = startHour * 4;
            row += getMinuteQuarter(startMinute) + 1;
        }
        return row;
    }

    private void parseDateTime() {

    }

    public static Meeting[] getMockAppointments() {
        return new Meeting[]{
            new Meeting(7, 30, 8, 0, "Dental"),
            new Meeting(8, 15, 9, 0, "Chiro"),
            new Meeting(9, 45, 10, 30, "Doctor"),
            new Meeting(10, 45, 11, 45, "Jeff Facetime"),
            new Meeting(12, 00, 13, 30, "Lunch Meeting"),
            new Meeting(14, 15, 16, 30, "Movies with Mom"),
            new Meeting(18, 45, 19, 30, "Dinner at Applebees"),
            new Meeting(20, 45, 21, 30, "Study Group")
        };
    }
    
    public String getDisplayInfo(){
        return subject;
    }

}

package model;

// Represent a laundry room user having a name and the machine and time slot booked the user
public class User {
    private String userName;
    private int bookedTime;

    //bookedTime is initialized to -1, meaning the user object has no booked time
    public User(String userName) {
        this.userName = userName;
        this.bookedTime = -1;
    }

    // setters
    public void setBookedTime(int time) {
        bookedTime = time;
    }

    // getters
    public int getBookedTime() {
        return bookedTime;
    }

    public String geUserName() {
        return userName;
    }
}


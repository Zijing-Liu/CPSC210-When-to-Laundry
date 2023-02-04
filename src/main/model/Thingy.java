package model;

import org.json.JSONObject;
import persistence.Writable;


//Represents a booking having a username and booked time
public class Thingy implements Writable {
    private String bookedUsername;
    private int bookedTime;
    private User bookedUser;

    // EFFECTS: constructs a thingy with a docUserName and category
    public Thingy(String bookedUsername, int bookedTime) {
        this.bookedUsername = bookedUsername;
        this.bookedTime = bookedTime;

    }

    public String getBookedUsername() {
        return bookedUsername;
    }

    public int getBookedTime() {
        return bookedTime;
    }


    // EFFECTS: returns string representation of this thingy
    public String toString() {
        return bookedUsername + ": " + bookedTime;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("bookedUsername", bookedUsername);
        json.put("bookedTime", bookedTime);
        return json;
    }
}


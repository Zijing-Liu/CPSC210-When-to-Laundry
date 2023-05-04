package ui.windows;

import model.AllBookingRecord;
import model.Booking;
import model.Record;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class DefaultWindow extends JFrame {
    ImageIcon iconImage;

    Booking booking;
    User currentUser;
    int timeSlotChosen;
    String nameInput;
    LocalTime startTime;
    LocalTime endTime;

    static final String JSON_STORE = "./data/bookingRecord.json.";
    protected String filePath;
    protected AllBookingRecord bookingRecord;
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;
    //userBookings stores all the bookings added when the application is running, this is not to be confused with
    //booking, which is an array list storing 36 number of User instantiated as null.
    protected List<User> userBookings;


    public DefaultWindow() {
        this.setVisible(true);
        this.setSize(375, 812);
        this.setTitle("When to laundry");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        iconImage = new ImageIcon("src/main/ui/laundrylogo.jpg");
        this.setIconImage(iconImage.getImage());

        booking = new Booking();
        nameInput = "";
        timeSlotChosen = -1;
        bookingRecord = new AllBookingRecord("admin's booking record");
        jsonWriter = new JsonWriter(JSON_STORE);
        userBookings = new ArrayList<>();

    }

}

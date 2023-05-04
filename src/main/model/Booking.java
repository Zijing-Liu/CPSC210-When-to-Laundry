package model;

import java.time.LocalTime;
import java.util.ArrayList;

// Represents the booking information for the laundry machines.
// Contains an array list with the length of 30, each item in the list represent a slot.
// Methods like makeANewBooking and changeABooking can be called on this class
public class Booking {
    private static int NUMBER_OF_SLOTS_PER_MACHINE = 36;

    public static int getNumberOfSlotsPerMachine() {
        return NUMBER_OF_SLOTS_PER_MACHINE;
    }

    //bookings is the list of time slot for each 24hours, there is 36 slots for each laundry machine
    private ArrayList<User> bookingSlots;
    LocalTime startTime;
    LocalTime endTime;

    // The Booking instructor is contributed by the HairSalon project provided at Edx
    // Effect: Create empty booking list
    public Booking() {
        bookingSlots = new ArrayList<>();
        for (int i = 0; i < getNumberOfSlotsPerMachine(); i++) {
            bookingSlots.add(i, null);
        }
    }

    // REQUIRES: 0 =< i <= bookings.size()
    // EFFECTS: To convert the time slot index (0 -35) of booking array to LocalTime (00:00 - 23:59)
    public LocalTime getStartTime(int i) {
        int startHrs = (i * 40) / 60;
        int startMins = (i * 40) % 60;
        startTime = LocalTime.of(startHrs, startMins);
        return startTime;
    }

    // REQUIRES: 0 =< i <= bookings.size()
    // EFFECTS: Produce the ending time of each slot in LocalTime (00:00 - 23:59)
    public LocalTime getEndTime(int i) {
        int endHrs = ((i + 1) * 40) / 60;
        int endMins = ((i + 1) * 40) % 60;
        if (endHrs < 24) {
            endTime = LocalTime.of(endHrs, endMins);
        } else {
            endTime = LocalTime.of(0, endMins);
        }
        return endTime;
    }


    //MODIFIES: this and User
    //EFFECTS: make a booking if the slot is valid and empty, and return true, and inform the user of their bookings
    // otherwise return false and provide the error message.
    // assign User the bookedTime
    public boolean makeANewBooking(int time, User user) {
        if (time <= this.bookingSlots.size()) {
            if ((this.bookingSlots.get(time) == null)) {
                this.bookingSlots.set(time, user);
                user.setBookedTime(time);
                Event addEvent = new Event("Successfully booked " + user.geUserName() + " at " + time);
                EventLog.getInstance().logEvent(addEvent);
                return true;
            }
            EventLog.getInstance().logEvent(new Event("Failed to make a new booking"));
            return false;
        }
        EventLog.getInstance().logEvent(new Event("Failed to make a new booking"));
        return false;
    }

    //REQUIRE: new time is between 0 and 35
    //MODIFIES: this and User
    //EFFECTS:  change the user's booking if the newTime is between 1 and 35, and available, assign newTime to user's
    // bookedTime, afterwards return true. Otherwise, print out the error message and return false
    public boolean changeABooking(int newTime, User user) {
        int bookedTime = user.getBookedTime();
        if (!(bookedTime == -1)) {
            if (!(newTime == bookedTime) && (bookingSlots.get(newTime) == null)) {
                bookingSlots.set((bookedTime), null);
                bookingSlots.set((newTime), user);
                user.setBookedTime(newTime);
                return true;
            }
        }
        //System.out.println("Invalid operation, please re-enter the new slot.");
        return false;
    }

    //MODIFIES: this
    //EFFECTS: cancel the user's current booking
    public boolean cancelBooking(String name) {
        User userToCancel = getBookedUserByName(name);
        if (userToCancel != null) {
            int bookedTime = userToCancel.getBookedTime();
            bookingSlots.set(bookedTime, null);
            userToCancel.setBookedTime(-1);
            Event cancelEvent = new Event(userToCancel.geUserName() + "'s booking at" + bookedTime + " is canceled");
            EventLog.getInstance().logEvent(cancelEvent);
            return true;
        }
        return false;
    }


    //EFFECTS: Return the booking list
    public ArrayList<User> getBookingSlots() {
        return this.bookingSlots;
    }

    //EFFECT: locate the booking of the user
    public User getBookedUserByName(String name) {
        for (User user : bookingSlots) {
            if (user != null && name.equals(user.geUserName())) {
                return user;
            }
        }
        return null;
    }
}

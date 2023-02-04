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
    private ArrayList<User> bookings;
    LocalTime startTime;
    LocalTime endTime;

    // The Booking instructor is contributed by the HairSalon project provided at Edx
    // Effect: Create empty booking list
    public Booking() {
        bookings = new ArrayList<>();
        for (int i = 0; i < getNumberOfSlotsPerMachine(); i++) {
            bookings.add(i, null);
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
        if (time <= this.bookings.size()) {
            if ((this.bookings.get(time) == null)) {
                this.bookings.set(time, user);
                user.setBookedTime(time);
                return true;
            }
            return false;
        }
        return false;
    }

    //REQUIRE: new time is between 0 and 35
    //MODIFIES: this and User
    //EFFECTS:  change the user's booking if the newTime is between 1 and 35, and available, assign newTime to user's
    // bookedTime, afterwards return true. Otherwise, print out the error message and return false
    public boolean changeABooking(int newTime, User user) {
        int bookedTime = user.getBookedTime();
        if (!(bookedTime == -1)) {
            if (!(newTime == bookedTime) && (bookings.get(newTime) == null)) {
                bookings.set((bookedTime), null);
                bookings.set((newTime), user);
                user.setBookedTime(newTime);
                return true;
            }
        }
        //System.out.println("Invalid operation, please re-enter the new slot.");
        return false;
    }

    //MODIFIES: this
    //EFFECTS: cancel the user's current booking
    public boolean cancelBooking(User user) {
        int bookedTime = user.getBookedTime();
        if (bookedTime == -1) {
            return false;
        } else {
            bookings.set(bookedTime, null);
            user.setBookedTime(-1);
            return true;
        }
    }


    //EFFECTS: Return the booking list
    public ArrayList<User> getBookings() {
        return this.bookings;
    }

}

//package ui;
//
//import model.Booking;
//import model.Record;
//import model.User;
//import model.AllBookingRecord;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//// This bookingApp class is based on the TellerApp in the AccountRobust project
//// Laundry booking application
//public class BookingApp {
//    Booking booking;
//    User currentUser;
//    String command;
//    Scanner scanner;
//    int timeSlotChosen;
//    String nameInput;
//    LocalTime startTime;
//    LocalTime endTime;
//
//    private static final String JSON_STORE = "./data/bookingRecord.json.";
//    private AllBookingRecord bookingRecord;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//    private List<User> userBookings;
//    private Record thingy;
//
//
//    public BookingApp() throws FileNotFoundException {
//        command = null;
//        scanner = new Scanner(System.in);
//        booking = new Booking();
//        nameInput = "";
//        timeSlotChosen = -1;
//        bookingRecord = new AllBookingRecord("admin's booking record");
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        thingy = new Record(null, -1);
//        userBookings = new ArrayList<>();
//        runBooking();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user input
//    public void runBooking() {
//        enterUsername();
//        boolean keepGoing = true;
//        while (keepGoing) {
//            displayMenu();
//            command = scanner.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else if (command.equals("n")) {
//                runBooking();
//            } else {
//                processCommand(command);
//            }
//            userBookings.add(currentUser);
//        }
//        System.out.println("\nGoodbye!");
//    }
//
//    private void enterUsername() {
//        //String empty = scanner.nextLine();
//        System.out.println("Please enter the username");
//        nameInput = scanner.next();
//        currentUser = new User(nameInput);
//        users.add(currentUser);
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: processes user command
//    private void processCommand(String command) {
//        if  (command.equals("b")) {
//            //currentUser = new User(nameInput);
//            userMakeANewBooking();
//        } else if (command.equals("r")) {
//            userChangeABooking();
//        } else if (command.equals("v")) {
//            userViewBooking();
//        } else if (command.equals("c")) {
//            userCancelBooking();
//        } else if (command.equals("s")) {
//            saveBookingRecord();
//        } else if (command.equals("p")) {
//            printBookingRecord();
//        } else if (command.equals("a")) {
//            addToBookingRecord();
//        } else if (command.equals("l")) {
//            loadBookingRecord();
//        } else {
//            System.out.println("Invalid input, please select from the following menus.");
//        }
//    }
//
//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\tb -> book");
//        System.out.println("\tr -> reschedule");
//        System.out.println("\tc -> cancel");
//        System.out.println("\tv -> view");
//        System.out.println("\ta -> add the current booking to the booking record");
//        System.out.println("\ts -> save booking record to file");
//        System.out.println("\tl -> load booking record from file");
//        System.out.println("\tp -> print thingies");
//        System.out.println("\tn -> start booking for another user");
//        System.out.println("\tq -> quit");
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS: Make a new booking from the user input
//    public void userMakeANewBooking() {
//        printBookingsList();
//        System.out.println("Please select the time slot you wish to book (0-35)");
//        timeSlotChosen = scanner.nextInt();
//        booking.makeANewBooking(timeSlotChosen, currentUser);
//        userBookings.add(currentUser);
//        System.out.println("You booked the laundry machine at time " + booking.getStartTime(timeSlotChosen));
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS: Change the user's booking
//    public void userChangeABooking() {
//        System.out.println("Please enter the new time (0-35)");
//        timeSlotChosen = scanner.nextInt();
//        booking.changeABooking(timeSlotChosen, currentUser);
//        System.out.println("You changed your booking time to : " + booking.getStartTime(timeSlotChosen));
//    }
//
//    public void userViewBooking() {
//        for (int i = 0; i < userBookings.size(); i += 1) {
//            int time = userBookings.get(i).getBookedTime();
//            startTime = booking.getStartTime(time);
//            System.out.println(userBookings.get(i).geUserName() + ": " + startTime);
//        }
//        System.out.println("No more other bookings");
//    }
//
//    public void userCancelBooking() {
//        if (booking.cancelBooking(currentUser)) {
//            System.out.println("You have successfully canceled you booking.");
//        } else {
//            System.out.println("You don't have existing bookings.");
//        }
//    }
//
//    //EFFECTS: Print out the booking list, if one slot is null, print available, otherwise print not available
//    public void printBookingsList() {
//        for (int i = 0; i < booking.getBookingSlots().size(); i++) {
//            startTime = booking.getStartTime(i);
//            endTime = booking.getEndTime(i);
//            if (!(booking.getBookingSlots().get(i) == null)) {
//                System.out.println(i + ". " + startTime + " - " + endTime + ": not available");
//            } else {
//                System.out.println(i + ". " + startTime + " - " + endTime + ": available");
//            }
//        }
//    }
//
//
//    // codes cited from the sample project : JsonSerializationDemo
//    // MODIFIES: this
//    // EFFECTS: add the user's current booking to booking record
//    public void addToBookingRecord() {
//        thingy = new Record(currentUser.geUserName(), currentUser.getBookedTime());
////        thingy.setBookedUsername(currentUser.geUserName());
////        thingy.setBookedTime(currentUser.getBookedTime());
//        bookingRecord.addThingy(thingy);
//    }
//
//
//    // codes cited from the sample project : JsonSerializationDemo
//    // EFFECTS: prints all the thingies in booking record to the console
//    private void printBookingRecord() {
//        List<Record> thingies = bookingRecord.getThingies();
//
//        for (Record t : thingies) {
//            System.out.println(t);
//        }
//    }
//
//    // codes cited from the sample project : JsonSerializationDemo
//    // EFFECTS: saves the booking record to file
//    public void saveBookingRecord() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(bookingRecord);
//            jsonWriter.close();
//            System.out.println("Saved " + bookingRecord.getName() + " to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads booking record from file
//    public void loadBookingRecord() {
//        try {
//            bookingRecord = jsonReader.read();
//            System.out.println("Loaded " + bookingRecord.getName() + " from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//}
//

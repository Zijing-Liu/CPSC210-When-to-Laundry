package ui.windows;


import model.EventLog;
import model.Record;
import model.User;
import persistence.JsonReader;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static java.awt.Color.red;

public class BookWindow extends DefaultWindow implements ActionListener {
    JButton confirm;
    JButton cancel;
    JButton view;
    JButton load;
    JButton save;

    JLabel username;
    JTextField slotText;
    JPanel inputPanel;
    JTextArea message;
    JTextField usernameText;
    JPanel slotPanel;
    LogPrinter lp;


    public BookWindow() {
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        addPlaceHolder();
        addSlotPanel();
        allSlots();
        addInputPanel();
        this.createButtons();

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                lp = new LogPrinter();
                try {
                    lp.printLog(EventLog.getInstance());
                } catch (LogException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    // EFFECTS: Create CTA buttons and add then into the buttonPanel
    public void createButtons() {
        JPanel buttonPanel = new JPanel();
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        buttonPanel.add(confirm);

        view = new JButton("View");
        view.addActionListener(this);
        buttonPanel.add(view);

        load = new JButton("load");
        load.addActionListener(this);
        buttonPanel.add(load);

        save = new JButton("save");
        save.addActionListener(this);
        buttonPanel.add(save);

        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        buttonPanel.add(cancel);

        this.add(buttonPanel);
    }

    //EFFECTS: add an empty panel to the frame as a placeholder
    public void addPlaceHolder() {
        JPanel blankPanel = new JPanel();
        blankPanel.setPreferredSize(new Dimension(375, 50));
        this.add(blankPanel);
    }

    // EFFECTS: add a panel where the admin can make bookings for a certain user
    public void addInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setBorder(new TitledBorder("Enter the username and serial number of the slots (0-35)"));
        inputPanel.setPreferredSize(new Dimension(375, 150));
        username = new JLabel("username");
        usernameText = new JTextField();
        usernameText.setPreferredSize(new Dimension(50, 30));
        JLabel selectSlot = new JLabel("timeslot");
        slotText = new JTextField();
        slotText.setPreferredSize(new Dimension(50, 30));
        message = new JTextArea();
        message.setPreferredSize(new Dimension(200, 60));
        inputPanel.add(username);
        inputPanel.add(usernameText);
        inputPanel.add(selectSlot);
        inputPanel.add(slotText);
        inputPanel.add(message);
        this.add(inputPanel);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cancel) {
            cancelBooking();
        } else if (e.getSource() == confirm) {
            makeANewBooking();
        } else if (e.getSource() == view) {
            viewAllBooking();
        } else if (e.getSource() == load) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int response = fileChooser.showOpenDialog(null); //select file to open
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                filePath = file.getAbsolutePath();
                jsonReader = new JsonReader(filePath);
                loadBookingRecord();
                printBookingRecord();
            }
        } else if (e.getSource() == save) {
            saveBookingRecord();
        }
    }

    public void cancelBooking() {
        if (booking.cancelBooking(usernameText.getText())) {
            message.setText("");
            message.append("You have successfully canceled you booking at " + currentUser.getBookedTime() + ". \n");
            slotPanel.removeAll();
            allSlots();
        } else {
            message.setText("");
            message.append("You don't have existing bookings.");
        }
    }

    // Display all bookings made in the message box
    public void viewAllBooking() {
        String allBookings = "";
        for (User user : userBookings) {
            System.out.println(user.geUserName());
            String text = user.geUserName() + ": " + user.getBookedTime() + "; \n";
            allBookings += text;
            message.setText(allBookings);
        }
    }

    // MODIFIES: this, slotPanel
    // EFFECTS: make a new booking from the two text fields, and update the slotPanel
    public void makeANewBooking() {
        nameInput = usernameText.getText();
        timeSlotChosen = Integer.parseInt(slotText.getText());
        currentUser = new User(nameInput);
        booking.makeANewBooking(timeSlotChosen, currentUser);
        userBookings.add(currentUser);
        addToBookingRecord();
        message.setText("");
        message.append(nameInput + " booked at " + timeSlotChosen + "; \n");
        slotPanel.removeAll();
        allSlots();
    }


    //EFFECTS: add a panel to hold the time slots label components
    public void addSlotPanel() {
        slotPanel = new JPanel();
        slotPanel.setBorder(new TitledBorder("Available slots are displayed in black"));
        slotPanel.setPreferredSize(new Dimension(200, 450));
        add(slotPanel);
    }


    //EFFECTS: display all time slots to choose, if not available, text displayed in red, otherwise in black
    public void allSlots() {
        for (int i = 0; i < booking.getBookingSlots().size(); i++) {
            JLabel timeSlots = new JLabel();
            timeSlots.setPreferredSize(new Dimension(100, 30));
            startTime = booking.getStartTime(i);
            endTime = booking.getEndTime(i);
            String startTimeS = startTime.toString();
            String endTImeS = endTime.toString();
            String number = String.valueOf(i);
            timeSlots.setText(number + ". " + startTimeS + " - " + endTImeS);
            slotPanel.add(timeSlots);
            if ((booking.getBookingSlots().get(i) != null)) {
                timeSlots.setForeground(red);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads booking records from file
    public void loadBookingRecord() {
        try {
            bookingRecord = jsonReader.read();
            System.out.println("Loaded " + bookingRecord.getName() + " from " + filePath);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + filePath);
        }
    }

    // EFFECTS: display all the bookings in file in text box
    private void printBookingRecord() {
        List<Record> records = bookingRecord.getRecord();
        String allText = "";
        for (Record t : records) {
            String text = t + " \n";
            allText += text;
        }
        message.setText(allText);
    }

    // codes cited from the sample project : JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: add the user's current booking to booking record
    public void addToBookingRecord() {
        Record record = new Record(currentUser.geUserName(), currentUser.getBookedTime());

        bookingRecord.addRecord(record);
    }

    // codes cited from the sample project : JsonSerializationDemo
    // EFFECTS: saves the booking record to file
    public void saveBookingRecord() {
        try {
            jsonWriter.open();
            jsonWriter.write(bookingRecord);
            jsonWriter.close();
            System.out.println("Saved " + bookingRecord.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}

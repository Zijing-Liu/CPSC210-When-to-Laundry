package ui.windows;

import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomePage extends DefaultWindow implements ActionListener {
    JLabel label;
    ImageIcon userImage;
    JButton bookB;

    public HomePage() {
        this.createLabels();
        this.createButtons();

    }

    // create label components and add them to the frame
    public void createLabels() {
        userImage = new ImageIcon("src/main/ui/UserImageCeline.png");
        label = new JLabel();
        this.add(label);
        label.setText("Welcome, Celine!");
        label.setIcon(userImage);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setIconTextGap(30);
        label.setForeground(new Color(0, 0, 0));
        label.setOpaque(true);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Open Sans", Font.PLAIN, 24));
        label.setBounds(300, 331, 193, 193);

    }

    public void createButtons() {
        bookB = new JButton("Start Booking");
        bookB.setBounds(67, 406, 240, 45);
        bookB.addActionListener(this);
        this.add(bookB);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookB) {
            new BookWindow();
        }
    }


}


package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new BookingApp();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
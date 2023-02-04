package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


public class BookingTest {
    Booking testBooking;
    User celine;
    User david;


    @BeforeEach
    public void setUp() {
        testBooking = new Booking();
        celine = new User("Celine");
        david = new User("David");
    }

    @Test
    public void testMakeANewBooking() {
        testBooking.getBookings().get(1);
        assertEquals(testBooking.getBookings().get(1), null);
        assertTrue(testBooking.makeANewBooking(1, celine));
        assertEquals(celine, testBooking.getBookings().get(1));
        assertFalse(testBooking.makeANewBooking(40, celine));
        assertFalse(testBooking.makeANewBooking(1, david));
    }

    @Test
    void testChangeABooking() {
        testBooking.makeANewBooking(1, celine);
        assertTrue(testBooking.changeABooking(2, celine));
        assertEquals(null, testBooking.getBookings().get(1));
        assertEquals(celine, testBooking.getBookings().get(2));
        assertFalse(testBooking.changeABooking(0, david));
        testBooking.makeANewBooking(3, david);
        assertFalse(testBooking.changeABooking(3, david));
        assertFalse(testBooking.changeABooking(2, david));
    }

    @Test
    void testCancelABooking() {
        assertFalse(testBooking.cancelBooking(celine));
        testBooking.makeANewBooking(1, david);
        assertTrue(testBooking.cancelBooking(david));
        assertEquals(null, testBooking.getBookings().get(1));
    }

    @Test
    void testGetStartTime() {
        LocalTime t1 = LocalTime.of(0, 0);
        assertEquals(t1, testBooking.getStartTime(0));
        LocalTime t2 = LocalTime.of(23, 20);
        assertEquals(t2, testBooking.getStartTime(35));
    }

    @Test
    void testGetEndTime() {
        LocalTime t1 = LocalTime.of(0, 40);
        assertEquals(t1, testBooking.getEndTime(0));
        LocalTime t2 = LocalTime.of(0, 0);
        assertEquals(t2, testBooking.getEndTime(35));
    }

    @Test
    void testGetUserName() {
        assertEquals("Celine", celine.geUserName());
        assertEquals("David", david.geUserName());
    }
}

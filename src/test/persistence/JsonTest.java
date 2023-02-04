package persistence;

import model.Thingy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkThingy(String bookedUsername, int userBookedTime, Thingy thingy) {
        assertEquals(bookedUsername, thingy.getBookedUsername());
        assertEquals(userBookedTime, thingy.getBookedTime());
    }
}

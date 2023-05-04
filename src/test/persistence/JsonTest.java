package persistence;

import model.Record;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkThingy(String bookedUsername, int userBookedTime, Record thingy) {
        assertEquals(bookedUsername, thingy.getBookedUsername());
        assertEquals(userBookedTime, thingy.getBookedTime());
    }
}

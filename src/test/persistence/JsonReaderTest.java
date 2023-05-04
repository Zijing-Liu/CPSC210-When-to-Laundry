package persistence;

import model.AllBookingRecord;
import model.Record;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AllBookingRecord br = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyBookingRecord() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBookingRecord.json");
        try {
            AllBookingRecord br = reader.read();
            assertEquals("Admin's booking record", br.getName());
            assertEquals(0, br.numRecord());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralBookingRecord() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBookingRecord.json");
        try {
            AllBookingRecord br = reader.read();
            assertEquals("Admin's booking record", br.getName());
            List<Record> thingies = br.getRecord();
            assertEquals(3, thingies.size());
            checkThingy("Celine", 23, thingies.get(0));
            checkThingy("David", 34, thingies.get(1));
            checkThingy("Sam", 11, thingies.get(2));
            assertEquals("Celine: 23", thingies.get(0).toString());
            assertEquals("David: 34", thingies.get(1).toString());
            assertEquals("Sam: 11", thingies.get(2).toString());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}

package persistence;

import model.AllBookingRecord;
import model.Record;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {


    @Test
    void testWriterInvalidFile() {
        try {
            AllBookingRecord br = new AllBookingRecord("Admin's booking room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            AllBookingRecord br = new AllBookingRecord("Admin's booking record");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBookingRecord.json");
            writer.open();
            writer.write(br);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBookingRecord.json");
            br = reader.read();
            assertEquals("Admin's booking record", br.getName());
            assertEquals(0, br.numRecord());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBookingRecord() {
        try {
            AllBookingRecord br = new AllBookingRecord("Admin's booking record");
            br.addRecord(new Record("Celine", 23));
            br.addRecord(new Record("David", 34));
            br.addRecord(new Record("Sam", 11));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBookingRecord.json");
            writer.open();
            writer.write(br);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBookingRecord.json");
            br = reader.read();
            assertEquals("Admin's booking record", br.getName());
            List<Record> thingies = br.getRecord();
            assertEquals(3, thingies.size());
            checkThingy("Celine", 23, thingies.get(0));
            checkThingy("David", 34, thingies.get(1));
            checkThingy("Sam", 11, thingies.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

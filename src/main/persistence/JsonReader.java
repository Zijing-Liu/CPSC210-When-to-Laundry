package persistence;

import model.Thingy;
import model.BookingRecord;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads booking record from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BookingRecord read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookingRecord(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses booking record from JSON object and returns it
    private BookingRecord parseBookingRecord(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        BookingRecord wr = new BookingRecord(name);
        addThingies(wr, jsonObject);
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to booking record
    private void addThingies(BookingRecord wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("thingies");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addThingy(wr, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to booking record
    private void addThingy(BookingRecord wr, JSONObject jsonObject) {
        //String bookedUsername = jsonObject.keys().next();
        //int bookedTime = Integer.parseInt(jsonObject.getString("bookedUsername"));
        String bookedUsername = jsonObject.getString("bookedUsername");
        int bookedTime = jsonObject.getInt("bookedTime");
        Thingy thingy = new Thingy(bookedUsername, bookedTime);
        wr.addThingy(thingy);
    }
}

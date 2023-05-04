package persistence;

import model.AllBookingRecord;
import model.Record;
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
    public AllBookingRecord read() throws IOException {
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
    private AllBookingRecord parseBookingRecord(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        AllBookingRecord br = new AllBookingRecord(name);
        addRecords(br, jsonObject);
        return br;
    }

    // MODIFIES: wr
    // EFFECTS: parses records from JSON object and adds them to booking record
    private void addRecords(AllBookingRecord wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("allBookingRecords");
        for (Object json : jsonArray) {
            JSONObject nextRecord = (JSONObject) json;
            addRecord(wr, nextRecord);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses record from JSON object and adds it to booking record
    private void addRecord(AllBookingRecord wr, JSONObject jsonObject) {
        //String bookedUsername = jsonObject.keys().next();
        //int bookedTime = Integer.parseInt(jsonObject.getString("bookedUsername"));
        String bookedUsername = jsonObject.getString("bookedUsername");
        int bookedTime = jsonObject.getInt("bookedTime");
        Record record = new Record(bookedUsername, bookedTime);
        wr.addRecord(record);
    }
}

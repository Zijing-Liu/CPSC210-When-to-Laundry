package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Cited from the example project
// Represents a booking record having a collection of thingies
public class AllBookingRecord implements Writable {
    private String name;
    private List<Record> records;

    // EFFECTS: constructs booking record with a name and empty list of thingies
    public AllBookingRecord(String name) {
        this.name = name;
        records = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds thingy to this booking record
    public void addRecord(Record thingy) {
        records.add(thingy);
    }

    // EFFECTS: returns an unmodifiable list of thingies in this booking record
    public List<Record> getRecord() {
        return Collections.unmodifiableList(records);
    }

    // EFFECTS: returns number of thingies in this booking record
    public int numRecord() {
        return records.size();
    }

//    @Override
//    public JSONObject toJson() {
//        JSONObject json = new JSONObject();
//        json.put("name", name);
//        return json;
//    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("allBookingRecords", recordToJson());
        return json;
    }

    // EFFECTS: returns things in this booking record as a JSON array
    private JSONArray recordToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Record t : records) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}


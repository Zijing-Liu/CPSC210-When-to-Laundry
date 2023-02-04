package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Cited from the example project
// Represents a booking record having a collection of thingies
public class BookingRecord implements Writable {
    private String name;
    private List<Thingy> thingies;

    // EFFECTS: constructs booking record with a name and empty list of thingies
    public BookingRecord(String name) {
        this.name = name;
        thingies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds thingy to this booking record
    public void addThingy(Thingy thingy) {
        thingies.add(thingy);
    }

    // EFFECTS: returns an unmodifiable list of thingies in this booking record
    public List<Thingy> getThingies() {
        return Collections.unmodifiableList(thingies);
    }

    // EFFECTS: returns number of thingies in this booking record
    public int numThingies() {
        return thingies.size();
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
        json.put("thingies", thingiesToJson());
        return json;
    }

    // EFFECTS: returns things in this booking record as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Thingy t : thingies) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}


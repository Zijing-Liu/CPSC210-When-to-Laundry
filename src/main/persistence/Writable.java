package persistence;

import org.json.JSONObject;
// Cited from JsonSerializationDen project

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

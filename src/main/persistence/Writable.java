package persistence;

import org.json.JSONObject;

// INTERFACE: Interface for all objects that are writable to file
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

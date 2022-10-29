package persistence;

import org.json.JSONObject;


//code based on JsonSerializationDemo
public interface Writable {
    JSONObject toJson();
}

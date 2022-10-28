package persistence;

import model.PlanetList;
import model.ShuttleList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private String destination;
    private PrintWriter writer;

    public JsonWriter(String destination) {
        this.destination = destination;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    public void write(PlanetList planetList) {
        JSONObject json = planetList.toJson();
        saveToFile(json.toString());
    }

    public void write(ShuttleList shuttleList) {
        JSONObject json = shuttleList.toJson();
        saveToFile(json.toString());
    }

    private void saveToFile(String json) {
        writer.print(json);
    }

    public void close() {
        writer.close();
    }
}

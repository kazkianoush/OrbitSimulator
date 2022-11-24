package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class PlanetList implements Writable {

    List<Planet> planetList;
    private String listName;

    public PlanetList(String listName) {
        this.listName = listName;
        planetList = new ArrayList<>();
    }

    public void add(Planet planet) {
        if (!planet.getName().contains("Generic")) {
            EventLog.getInstance().logEvent(new Event("added a planet: " + planet.getName() + " to the planetList"));
        }
        planetList.add(planet);
    }


    public void remove(Planet planet) {
        EventLog.getInstance().logEvent(new Event("removed planet: " + planet.getName() + " from planetList"));
        this.planetList.remove(planet);
    }

    public Planet get(int index) {
        return planetList.get(index);
    }

    public int size() {
        return planetList.size();
    }

    public List<Planet> getList() {
        return planetList;
    }

    public String getListName() {
        return this.listName;
    }

    //EFFECT: turns PlanetList object into Json object
    //code based on JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listName", listName);
        json.put("planetList", planetListJson());
        return json;
    }

    //EFFECT: turns a given planet in planetList into a json object
    //code based on JsonSerializationDemo
    private JSONArray planetListJson() {
        JSONArray jsonArray = new JSONArray();
        for (Planet p : planetList) {
            jsonArray.put(p.toJSon());
        }
        return jsonArray;
    }
}

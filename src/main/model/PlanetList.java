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
        planetList.add(planet);
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listName", listName);
        json.put("planetList", planetListJson());
        return json;
    }

    private JSONArray planetListJson() {
        JSONArray jsonArray = new JSONArray();
        for (Planet p : planetList) {
            jsonArray.put(p.toJSon());
        }
        return jsonArray;
    }
}

package model;

import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;
import persistence.Writable;

public class ShuttleList  implements Writable {
    private ArrayList<Shuttle> shuttleList;
    private String shuttleListName;

    public ShuttleList(String name) {
        this.shuttleListName = name;
        this.shuttleList = new ArrayList<>();
    }

    public void add(Shuttle e) {
        this.shuttleList.add(e);
    }

    public Shuttle get(int index) {
        return this.shuttleList.get(index);
    }

    public ArrayList<Shuttle> getList() {
        return this.shuttleList;
    }

    public String getListName() {
        return this.shuttleListName;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listName", shuttleListName);
        json.put("shuttleList", shuttleListJson());
        return json;
    }

    private JSONArray shuttleListJson() {
        JSONArray jsonArray = new JSONArray();
        for (Shuttle s : shuttleList) {
            jsonArray.put(s.toJSon());
        }
        return jsonArray;
    }




}

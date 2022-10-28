package persistence;

import model.Planet;
import model.PlanetList;
import model.Shuttle;
import model.ShuttleList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    public PlanetList readPlanets() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlanetList(jsonObject);
    }

    public ShuttleList readShuttles() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShuttleList(jsonObject);
    }

    private ShuttleList parseShuttleList(JSONObject jsonObject) {
        String name = jsonObject.getString("listName");
        ShuttleList shuttleList = new ShuttleList(name);
        addShuttles(shuttleList, jsonObject);
        return shuttleList;
    }




    private PlanetList parsePlanetList(JSONObject jsonObject) {
        String name = jsonObject.getString("listName");
        PlanetList planetList = new PlanetList(name);
        addPlanets(planetList,jsonObject);
        return planetList;
    }

    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private void addPlanets(PlanetList planetList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("planetList");
        for (Object json : jsonArray) {
            JSONObject nextPlanet = (JSONObject) json;
            addPlanet(planetList,nextPlanet);
        }
    }

    private void addShuttles(ShuttleList shuttleList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("shuttleList");
        for (Object json : jsonArray) {
            JSONObject nextShuttle = (JSONObject) json;
            addShuttle(shuttleList, nextShuttle);
        }
    }

    private void addShuttle(ShuttleList shuttleList, JSONObject nextShuttle) {
        String name = nextShuttle.getString("name");
        int xcor = nextShuttle.getInt("xcor");
        int ycor = nextShuttle.getInt("ycor");
        int accelY = nextShuttle.getInt("accelY");
        int accelX = nextShuttle.getInt("accelX");
        Shuttle shuttle = new Shuttle(name, xcor, ycor, accelX, accelY);
        shuttleList.add(shuttle);

    }

    private void addPlanet(PlanetList planetList, JSONObject nextPlanet) {
        String name = nextPlanet.getString("name");
        float gravity = nextPlanet.getInt("gravity");
        boolean trees = nextPlanet.getBoolean("trees");
        boolean water = nextPlanet.getBoolean("water");
        int radius = nextPlanet.getInt("radius");
        Planet planet =  new Planet(name,gravity,trees,water,radius);
        planetList.add(planet);
    }
}

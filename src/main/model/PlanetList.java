package model;

import java.util.ArrayList;
import java.util.List;

public class PlanetList {

    List<Planet> planetList;

    public PlanetList() {
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
}

package ui;

import model.Planet;
import model.Shuttle;

import java.io.FileNotFoundException;
import java.util.ArrayList;

interface SetupInterface {
    ArrayList<Object> getSetupPlanet();

    Planet initPlanet();

    ArrayList<Object> getSetupShuttle();

    Shuttle initShuttle();

    void initGame(Planet p, Shuttle s) throws InterruptedException, FileNotFoundException;



}

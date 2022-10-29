package model;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;


class MyModelTest {


    public Planet a;
    public Planet b;
    public Shuttle c;
    public Shuttle d;
    public Shuttle e;
    public Shuttle f;

    public PlanetList planetList;
    public ShuttleList shuttleList;

    public JsonReader planetReader;
    public JsonReader shuttleReader;
    public JsonWriter planetWriter;
    public JsonWriter shuttleWriter;

    private static final String JSON_PLANET_LOC = "./data/planetList.json";
    private static final String JSON_SHUTTLE_LOC = "./data/shuttleList.json";

    // initiates Planet objects
    @BeforeEach
    void runBefore() {
        planetReader = new JsonReader(JSON_PLANET_LOC);
        planetWriter = new JsonWriter(JSON_PLANET_LOC);
        shuttleReader = new JsonReader(JSON_SHUTTLE_LOC);
        shuttleWriter = new JsonWriter(JSON_SHUTTLE_LOC);

        a = new Planet();
        b = new Planet("Generic 1",4,true,true,5);
        c = new Shuttle("oops");
        d = new Shuttle("brush");
        e = new Shuttle("lol");
        f = new Shuttle("ll2",3,3,3,3);

        planetList = new PlanetList("List1");
        planetList.add(a);
        planetList.add(b);
        shuttleList = new ShuttleList("sList1");
        shuttleList.add(c);
        shuttleList.add(d);
        shuttleList.add(e);
        shuttleList.add(f);





    }

    @Test
    void testInit() {
     //testing planetList and shuttleList
     assertEquals(planetList.get(0), a);
     assertEquals(planetList.get(0),planetList.getList().get(0));
     assertEquals(planetList.size(),2);
     assertEquals(planetList.getListName(),"List1");
     planetList.toJson();
     //testing json operations
     try {
         planetWriter.open();
         planetWriter.write(planetList);
         planetWriter.close();
         shuttleWriter.open();
         shuttleWriter.write(shuttleList);
         shuttleWriter.close();

     } catch (FileNotFoundException e) {
         throw new RuntimeException(e);
     }

     try {
         planetList = planetReader.readPlanets();
     } catch (IOException e) {
         throw new RuntimeException(e);
     }


        assertEquals(shuttleList.get(0), c);
     assertEquals(shuttleList.get(0),shuttleList.getList().get(0));
     assertEquals(shuttleList.getListName(),"sList1");
     shuttleList.toJson();


        try {
            shuttleList = shuttleReader.readShuttles();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    //sets the values of planet to the following and then tests them
    a.setGravity(2);
    a.setTrees(true);
    a.setWater(true);
    a.setRadius(1);
    assertEquals(a.getRadius(),1);
    assertEquals(a.getGravity(),2);

    //testing the setName method for the planets
    b.setName("hombre");
    assertEquals("hombre", b.getName());
    //testing the genericName replacement for planets with a  size 0 or 1 name
    b.setName("");
    assertEquals("GenericName", b.getName());
    // testing planet b values
    assertEquals(b.getGravity(),4);
    assertEquals(b.getTrees(), true);
    assertTrue(b.getWater());
    // testing shuttle c values
    assertEquals(c.getAccelY(),0);
    assertEquals(c.getAccelX(),0);
    int[] g = {0,0};
    assertEquals(c.getCor()[0],g[0]);
    assertEquals(c.getCor()[1], g[1]);
    c.setAccelY(1);
    assertEquals(c.getAccelY(),1);
    c.setAccelX(2);
    assertEquals(c.getAccelX(),2);
    c.setCor(2,5);
    assertEquals(c.getCor()[0], 2);

    a.toJSon();
    f.setName("bean");
    assertEquals("bean", f.getName());
    d.toJSon();


    }
}
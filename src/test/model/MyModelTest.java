package model;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class MyModelTest {


    public Planet a;
    public Planet b;
    public Shuttle c;
    public Shuttle d;

    // initiates Planet objects
    @BeforeEach
    void runBefore() {
        a = new Planet();
        b = new Planet("Generic 1",4,true,true,5);
        c = new Shuttle();
        d = new Shuttle();

    }

    @Test
    void testInit() {
    //sets the values of planet a to the following and then tests them
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

    }
}
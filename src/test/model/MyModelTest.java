package model;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class MyModelTest {

    public Planet a;
    public Planet b;
    public Shuttle c;
    public Shuttle d;

    @BeforeEach
    void runBefore() {
        a = new Planet();
        b = new Planet("Generic 1",4,true,true,5);
        c = new Shuttle();
        d = new Shuttle();

    }

    @Test
    void testInit() {

    a.setGravity(2);
    a.setTrees(true);
    a.setWater(true);
    a.setRadius(1);
    b.setName("hombre");
    assertEquals("hombre", b.getName());
    b.setName("");
    assertEquals("GenericName", b.getName());
    assertEquals(a.getRadius(),1);
    assertEquals(a.getGravity(),2);
    assertEquals(b.getGravity(),4);
    assertEquals(b.getTrees(), true);
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
    assertTrue(b.getWater());
    }

    // delete or rename this class!
}
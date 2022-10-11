package model;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.OrbitApp;


class MyModelTest {

    public Planet a;
    public Planet b;
    public Shuttle c;
    public Shuttle d;

    @BeforeEach
    void runBefore() {
        a = new Planet();
        b = new Planet(4,true,true,5);
        c = new Shuttle();
        d = new Shuttle(2,4);

    }

    @Test
    void testInit() {

    a.setGravity(2);
    a.setTrees(true);
    a.setWater(true);
    a.setRadius(1);
    assertEquals(a.getRadius(),1);
    assertEquals(a.getGravity(),0);
    assertEquals(b.getGravity(),4);
    assertEquals(b.getTrees(), true);
    assertEquals(c.getAccelY(),0);
    assertEquals(c.getAccelX(),0);
    int[] g = {0,0};
    assertEquals(c.getCor()[0],g[0]);
    c.setAccelY(1);
    c.setAccelX(2);
    c.setCor(2,5);
    }

    // delete or rename this class!
}
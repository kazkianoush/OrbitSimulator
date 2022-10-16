package model;

public class Planet {
    private float gravity;
    private boolean trees;
    private boolean water;
    private int radius;

    private String name;

    // Constructs Planet given no arguments
    public Planet() {
        this.name = "Generic Planet";
        this.gravity = 0;
        this.trees = false;
        this.water = false;
        this.radius = 1;
    }

    //constructs Planet object given name, gravity, trees, water, and radius
    public Planet(String name,float gravity, boolean trees, boolean water, int radius) {
        this.name = name;
        this.gravity = gravity;
        this.trees = trees;
        this.water = water;
        this.radius = radius;
    }

    public String getName() {
        return this.name;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public void setName(String name) {
        if (name.length() < 1) {
            this.name = "GenericName";
        } else {
            this.name = name;
        }
    }

    public void setTrees(boolean trees) {
        this.trees = trees;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public float getGravity() {
        return this.gravity;
    }

    public boolean getTrees() {
        return this.trees;
    }

    public boolean getWater() {
        return this.water;
    }

    public int getRadius() {
        return this.radius;
    }
}

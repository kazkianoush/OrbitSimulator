package model;

public class Planet {
    private float gravity;
    private boolean trees;
    private boolean water;
    private int radius;

    public Planet() {
        this.gravity = 0;
        this.trees = false;
        this.water = false;
        this.radius = 1;
    }

    public Planet(float gravity, boolean trees, boolean water, int radius) {
        this.gravity = gravity;
        this.trees = trees;
        this.water = water;
        this.radius = radius;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
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

    public int getRadius() {
        return this.radius;
    }
}

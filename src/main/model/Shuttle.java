package model;


// Represents a shuttle object, with an x coordinate, y coordinate, y acceleration, and x acceleration
public class Shuttle {
//    private int[] velocity;
    private int xcor;  // x coordinate of the shuttle
    private int ycor;  // y coordinate of the shuttle

    private int accelY; // y acceleration of the shuttle

    private int accelX; // x acceleration of the shuttle


    // EFFECT: constructs Shuttle given no arguments
    public Shuttle() {
        this.xcor = 0;
        this.ycor = 0;
        this.accelX = 0;
        this.accelY = 0;
    }

    public void setCor(int newX, int newY) {
        this.xcor = newX;
        this.ycor = newY;
    }

    public int[] getCor() {
        return new int[]{this.xcor, this.ycor};
    }

    public int getAccelX() {
        return this.accelX;
    }

    public int getAccelY() {
        return this.accelY;
    }

    public void setAccelX(int accelX) {
        this.accelX = accelX;
    }

    public void setAccelY(int accelY) {
        this.accelY = accelY;
    }



}

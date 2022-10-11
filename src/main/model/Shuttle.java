package model;

public class Shuttle {
//    private int[] velocity;
    private int xcor;
    private int ycor;

    private int accelY;

    private int accelX;

    private int mass = 10;

    public Shuttle() {
//        velocity = new int[2];
//        this.velocity[0] = 0;
//        this.velocity[1] = 0;
        this.xcor = 0;
        this.ycor = 0;
        this.accelX = 0;
        this.accelY = 0;
    }

    public Shuttle(int initialVx, int initialVy) {
//        velocity = new int[2];
//        this.velocity[0] = initialVx;
//        this.velocity[1] = initialVy;
        this.xcor = 0;
        this.ycor = 0;
        this.accelX = 0;
        this.accelY = 0;
    }

//    public void setVelocity(int newVx, int newVy) {
//        this.velocity = new int[2];
//        this.velocity[0] = newVx;
//        this.velocity[1] = newVy;
//    }

    public void setCor(int newX, int newY) {
        this.xcor = newX;
        this.ycor = newY;
    }


//    public int[] getVelocity() {
//        return this.velocity;
//    }
//
    public int[] getCor() {
        int[] coordinate = {this.xcor, this.ycor};
        return coordinate;
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

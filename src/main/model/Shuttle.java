package model;

public class Shuttle {
//    private int[] velocity;
    private int xcor;
    private int ycor;

    private int accelY;

    private int accelX;


    //constructs Shuttle given no arguments
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


//    public int[] getVelocity() {
//        return this.velocity;
//    }
//
    //EFFECT: returns the current coordinates of the shuttle
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

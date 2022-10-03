package model;

public class Shuttle {
    private int velocity;
    private int xcor;
    private int ycor;

    private int mass = 10;

    public void shuttle(int initialV) {
        this.velocity = initialV;
    }

    public void setVelocity(int newV) {
        this.velocity = newV;
    }

    public void setCor(int newX, int newY) {
        this.xcor = newX;
        this.ycor = newY;
    }

    public int getVelocity() {
        return this.velocity;
    }

    public int[] getCor() {
        int[] coordinate = {this.xcor, this.ycor};
        return coordinate;
    }



}

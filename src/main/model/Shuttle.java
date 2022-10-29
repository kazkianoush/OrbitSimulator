package model;


import org.json.JSONObject;

// Represents a shuttle object, with an x coordinate, y coordinate, y acceleration, and x acceleration
public class Shuttle {
//    private int[] velocity;
    private int xcor;  // x coordinate of the shuttle
    private int ycor;  // y coordinate of the shuttle

    private int accelY; // y acceleration of the shuttle

    private int accelX; // x acceleration of the shuttle

    private String name;


    // EFFECT: constructs Shuttle given no arguments
    public Shuttle(String name) {
        this.name = name;
        this.xcor = 0;
        this.ycor = 0;
        this.accelX = 0;
        this.accelY = 0;
    }

    public Shuttle(String name, int xcor, int ycor, int accelX, int accelY) {
        this.name = name;
        this.xcor = xcor;
        this.ycor = ycor;
        this.accelX = accelX;
        this.accelY = accelY;
    }



    public void setCor(int newX, int newY) {
        this.xcor = newX;
        this.ycor = newY;
    }

    public int[] getCor() {
        return new int[]{this.xcor, this.ycor};
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    //EFFECT: turns a shuttle object into json object
    //code based on JsonSerializationDemo
    public JSONObject toJSon() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("accelX",accelX);
        json.put("accelY", accelY);
        json.put("xcor", xcor);
        json.put("ycor",ycor);


        return json;
    }


}

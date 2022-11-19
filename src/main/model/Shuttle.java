package model;


import org.json.JSONObject;
import ui.GUI;

import javax.swing.*;
import java.awt.*;

// Represents a shuttle object, with an x coordinate, y coordinate, y acceleration, and x acceleration
public class Shuttle extends JComponent {
//    private int[] velocity;


    private static int DX = 1;

    private static  int DY = 1;

    private static final int GRAVITY =  5;
    private int xcor;  // x coordinate of the shuttle
    private int ycor;  // y coordinate of the shuttle

    private int accelY; // y acceleration of the shuttle

    private int accelX; // x acceleration of the shuttle

    private String name;
    private int direction;


    // EFFECT: constructs Shuttle given no arguments
    public Shuttle(String name) {
        this.name = name;
        this.xcor = 0;
        this.ycor = 0;
        this.accelX = 0;
        this.accelY = 0;
        this.direction = 0;
    }

    public Shuttle(String name, int xcor, int ycor, int accelX, int accelY) {
        this.name = name;
        this.xcor = xcor;
        this.ycor = ycor;
        this.accelX = accelX;
        this.accelY = accelY;
        this.direction = 0;
    }



    public void setCor(int newX, int newY) {
        this.xcor = newX;
        this.ycor = newY;
    }

    public void faceRight() {
        direction = 1;
    }

    public void faceLeft() {
        direction = -1;
    }

    public void faceUp() {
        direction = -2;
    }

    public void faceDown() {
        direction = 2;
    }


    public void move(Planet p, GUI gui) {
        int x2 = (gui.WIDTH / 2);
        int y2 = (gui.HEIGHT / 2);
        if (Math.abs(Math.sqrt(Math.pow(ycor - y2,2) + Math.pow(xcor - x2,2))) < 200) {
            double angle = Math.atan2((y2 - ycor), (x2 - xcor));
            System.out.println("angle: " + angle);
            xcor -= Math.cos(angle) * GRAVITY;
            System.out.println(Math.cos(angle) * GRAVITY);
            ycor -= Math.sin(angle) * GRAVITY;
            if (Math.abs(Math.sqrt(Math.pow(ycor - y2,2) + Math.pow(xcor - x2,2))) < 300) {
                checkIfs(angle);

            }

        } else if (direction == 2 || direction == -2) {
            DX = 1;
            DY = 1;
            ycor = ycor + DY * (direction / 2);

        } else {
            DX = 1;
            DY = 1;
            xcor = xcor + DX * (direction);
        }
    }

    private void checkIfs(double angle) {
        if (Math.cos(angle) * GRAVITY > 0) {
            xcor -= Math.cos(angle) * GRAVITY;
        }
        if (Math.cos(angle) * GRAVITY < 0) {
            xcor -= Math.cos(angle) * GRAVITY;
        }
        if (Math.sin(angle) * GRAVITY < 0) {
            ycor -= Math.sin(angle) * GRAVITY;
        }
        if (Math.sin(angle) * GRAVITY > 0) {
            ycor -= Math.sin(angle) * GRAVITY;
        }
    }

    public void flipX() {
        direction *= -1;
    }

    public void flipY() {
        direction *= -1;
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

package ui;


import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GUI extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;
    private Shuttle shuttle;
    private Planet planet;
    private PlanetButton activePlanet;

    public GUI(Shuttle s, Planet p) {
        this.shuttle = s;
        this.planet = p;
//        addKeyListener(this);
//        setFocusable(true);
//        setFocusTraversalKeysEnabled(false);
//        JFrame jframe = new JFrame("title");
//        jframe.setVisible(true);
//        jframe.setSize(new Dimension(300,300));
//        jframe.setLocation(new Point(200,200));
//        DrawShuttle shuttle = new DrawShuttle(s);
//        jframe.getContentPane().add(shuttle);
////        DrawPlanet planet = new DrawPlanet();
////        jframe.getContentPane().add(planet);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    public Shuttle getShuttle() {
        return this.shuttle;
    }

    public Planet getPlanet() {
        return this.planet;
    }

    public void update() {
//        if (shuttle.getCor()[0] > planet.getRadius() && shuttle.getCor()[0] < planet.getRadius() + planet.getRadius()
//                && shuttle.getCor()[1] > planet.getRadius() && shuttle.getCor()[1] < planet.getRadius()
//                + planet.getRadius()) {
//            shuttle.stay();
//            System.out.println("the shuttle is getting caught");
//        }
        boundaryCheck();
        shuttle.move(planet, this);
    }

    private void boundaryCheck() {
//        int newX = 0;
//        int newY = 0;
        if (shuttle.getCor()[0] > WIDTH || shuttle.getCor()[0] < 0) {
            shuttle.flipX();
        }
        if (shuttle.getCor()[1] > HEIGHT || shuttle.getCor()[1] < 0) {
            shuttle.flipY();
//            if (shuttle.getCor()[0] > WIDTH) {
//                newX = WIDTH - 1;
//            } else if (shuttle.getCor()[0] < 0) {
//                newX = 1;
//            } else if (shuttle.getCor()[1] > HEIGHT) {
//                newY = HEIGHT - 1;
//            } else if (shuttle.getCor()[1] < 0) {
//                newY = 1;
//            }
//            shuttle.setCor(newX, newY)
        }
    }

    public void keyPressed(int keyCode) {

        if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_KP_RIGHT) {
            shuttle.faceRight();
        } else if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT) {
            shuttle.faceLeft();
        } else if (keyCode == KeyEvent.VK_KP_UP || keyCode == KeyEvent.VK_UP) {
            shuttle.faceUp();
        } else if (keyCode == KeyEvent.VK_KP_DOWN || keyCode == KeyEvent.VK_DOWN) {
            shuttle.faceDown();
        }
    }




}

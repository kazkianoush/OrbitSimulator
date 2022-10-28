package ui;


import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GUI extends JFrame {
    public GUI() {
        JFrame jframe = new JFrame("title");
        KeyListener keyEvent = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        jframe.setVisible(true);
        jframe.setSize(new Dimension(300,300));
        jframe.setLocation(new Point(200,200));
        DrawPlanet planet = new DrawPlanet();
        jframe.getContentPane().add(planet);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}

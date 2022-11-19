package ui;

import model.Shuttle;

import javax.swing.*;
import java.awt.*;
import java.awt.Color.*;

public class DrawPlanet extends JPanel {
    private GUI gui;


    //initiates drawing of planet
    public DrawPlanet(GUI g) {
        setPreferredSize(new Dimension(gui.WIDTH, gui.HEIGHT));
        this.gui = g;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    public void drawGame(Graphics g) {
        drawShuttle(g);
    }

    private void drawShuttle(Graphics g) {
        Shuttle s = gui.getShuttle();
        Color color = Color.BLACK;
        g.setColor(color);
        g.fillRect(s.getCor()[0], s.getCor()[1],15,15);
        g.setColor(color);
    }
}

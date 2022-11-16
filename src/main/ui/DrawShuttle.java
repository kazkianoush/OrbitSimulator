package ui;

import model.Planet;
import model.Shuttle;

import javax.swing.*;
import java.awt.*;

public class DrawShuttle extends JPanel {

    private GUI gui;

    public DrawShuttle(GUI g) {
        setPreferredSize(new Dimension(gui.WIDTH, gui.HEIGHT));
        this.gui = g;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    public void drawGame(Graphics g) {
        drawShuttle(g);
        drawPlanet(g);
    }

    private void drawPlanet(Graphics g) {
        Planet p = gui.getPlanet();
        Color color = Color.GREEN;
        g.setColor(color);
        g.fillOval((gui.WIDTH / 2) - p.getRadius() / 2,(gui.HEIGHT / 2)
                - p.getRadius() / 2,p.getRadius(),p.getRadius());
        g.setColor(color);
    }

    private void drawShuttle(Graphics g) {
        Shuttle s = gui.getShuttle();
        Color color = Color.BLACK;
        g.setColor(color);
        g.fillRect(s.getCor()[0], s.getCor()[1],15,15);
        g.setColor(color);
    }
}

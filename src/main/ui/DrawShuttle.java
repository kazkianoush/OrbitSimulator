package ui;

import model.Planet;
import model.Shuttle;

import javax.swing.*;
import java.awt.*;

public class DrawShuttle extends JPanel {

    private GUI gui;
    private JPanel jp;

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

    private void drawPlanetButton(Graphics g) {
        jp = new JPanel();
        jp.setLayout(new GridLayout(0,1));
        jp.setSize(new Dimension(0,0));
        add(jp, BorderLayout.SOUTH);
//        PlanetButton p = new PlanetButton(jp);
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(0,1));
//        panel.setSize(new Dimension(0,0));
//        add(panel,BorderLayout.SOUTH);
//        PlanetButton planetButton = new PlanetButton(gui,panel);
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

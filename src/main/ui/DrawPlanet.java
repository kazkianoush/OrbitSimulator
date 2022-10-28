package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.Color.*;

public class DrawPlanet extends JPanel {
    public void paint(Graphics g) {
        setSize(500,500);
        g.drawOval(150,150,50,50);
        g.setColor(Color.GREEN);
        g.fillOval(150,200,50,50);
    }
}

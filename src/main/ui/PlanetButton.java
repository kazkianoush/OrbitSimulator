package ui;

import model.Planet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlanetButton {

    protected JButton button;
    protected TitleScreen app;

    protected Planet planet;
    protected JComponent parent;
    private boolean active;

    public PlanetButton(TitleScreen app, JComponent parent, Planet p) {
        this.parent = parent;
        this.planet = p;
        this.app = app;
        button = new JButton(p.getName());
        button = setupButton(button);
        parent.add(button);
        active = false;
        button.addActionListener(new PlanetButtonListener());
    }

    private JButton setupButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    private class PlanetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            app.setActivePlanet(PlanetButton.this);
        }
    }

    public void deactivate() {
        active = false;
    }

    public void activate() {
        active = true;
    }

    public void removeButton() {
        button.setVisible(false);
        parent.remove(button);
    }
}

package ui;

import model.Shuttle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShuttleButton {
    protected JButton button;
    protected TitleScreen app;
    protected JComponent parent;
    private boolean active;
    protected Shuttle shuttle;

    protected OwnPlanetSurvey survey;


    // code below influenced by DrawingPlayer and SpaceInvaders


    public ShuttleButton(TitleScreen app, JComponent parent, Shuttle s) {
        this.parent = parent;
        this.app = app;
        this.shuttle = s;
        button = new JButton(s.getName());
        button = setupButton(button);
        parent.add(button);
        active = false;
        button.addActionListener(new ShuttleButtonListener());
    }

    protected void getplanetInfo(OwnPlanetSurvey survey) {
        for (JTextField field : survey.fields) {
            System.out.println(field);
        }
    }

    //EFFECTS: initiates button
    private JButton setupButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }


    //EFFECTS: initiates listener
    private class ShuttleButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            app.setActiveShuttle(ShuttleButton.this);
        }
    }


    //EFFECTS: deactivates button
    public void deactivate() {
        active = false;
    }


    //EFFECTS: activates button
    public void activate() {
        active = true;
    }


    //EFFECTS: removes button
    public void removeButton() {
        button.setVisible(false);
        parent.remove(button);
    }
}

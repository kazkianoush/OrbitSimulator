package ui;

import model.Planet;
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

    public ShuttleButton(TitleScreen app, JComponent parent, Shuttle s, OwnPlanetSurvey survey) {
        this.survey = survey;
        this.parent = parent;
        this.app = app;
        this.shuttle = s;
        button = new JButton(s.getName());
        button = setupButton(button);
        parent.add(button);
//        active = false;
        button.addActionListener(new ShuttleButtonListener1());

    }

    protected void getplanetInfo(OwnPlanetSurvey survey) {
        for (JTextField field : survey.fields) {
            System.out.println(field);
        }
    }

    private JButton setupButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    private class ShuttleButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            app.setActiveShuttle(ShuttleButton.this);
        }
    }

    private class ShuttleButtonListener1 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            survey.getanswer(ShuttleButton.this);
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

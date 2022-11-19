package ui;

import model.Planet;
import model.Shuttle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OwnPlanetSurvey extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;

    protected boolean clicked;
    protected String planetName;
    protected int gravity1;
    protected int radius1;
    protected boolean trees1;
    protected boolean water1;

    protected Planet planet;
    protected ArrayList<JTextField> fields;

    private TitleScreen titleScreen;

    public OwnPlanetSurvey(TitleScreen titleScreen) {
        super("Make your own planet");
        this.titleScreen = titleScreen;
        fields = new ArrayList<>();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setupPlanetScreen();
    }

    private void setupPlanetScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        createPanel1();
        pack();
        centreOnScreen();
        setVisible(true);
    }

    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void createPanel1() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("<html><p>please enter a name for "
                + "your planet: </p></html>", SwingConstants.CENTER));
        JTextField name = new JTextField(15);
        panel.add(name);
        fields.add(name);
//        name.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                planetName = name.getText();
//            }
//        });
        panel.add(new JLabel("please enter the gravity of your planet: "));
        JTextField gravity = new JTextField(15);
        fields.add(gravity);
//        gravity.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                gravity1 = Integer.parseInt(gravity.getText());
//            }
//        });
        panel.add(gravity);
        panel.add(new JLabel("does your planet have trees?: "));
        JTextField trees = new JTextField(20);
        fields.add(trees);
//        trees.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                trees1 = trees.getText() == "yes";
//            }
//        });
        panel.add(trees);
        panel.add(new JLabel("does your planet have water?: "));
        JTextField water = new JTextField(15);
        fields.add(water);
//        water.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                water1 = water.getText() == "yes";
//            }
//        });
        panel.add(water);
        panel.add(new JLabel("what is the radius of your planet?: "));
        JTextField radius = new JTextField(15);
        fields.add(radius);
//        radius.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                radius1 = Integer.parseInt(radius.getText());
//            }
//        });
        panel.add(radius);

        panel.add(new JLabel("click this button once you are done choosing."));
//        ShuttleButton submit1 = new ShuttleButton(new TitleScreen(),panel, new Shuttle("submit1",0,0,0,0),this);
        JButton submit = new JButton("submit");
        submit.setBorderPainted(true);
        submit.setFocusPainted(true);
        submit.setContentAreaFilled(true);
        panel.add(submit);
        submit.addActionListener(new PlanetSurveyListener());
        add(panel);

    }

    private class PlanetSurveyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clicked = true;
            planetName = fields.get(0).getText();
            gravity1 = Integer.parseInt(fields.get(1).getText());
            trees1 = fields.get(2).getText() == "yes";
            water1 = fields.get(3).getText() == "yes";
            radius1 = Integer.parseInt(fields.get(4).getText());
            makePlanet();
        }
    }

    private void makePlanet() {
        System.out.println("making planet");
        this.dispose();
        setVisible(false);
        planet = new Planet(planetName,gravity1,trees1,water1,radius1);
        titleScreen.planetList.add(planet);
        try {
            titleScreen.setActivePlanet(this);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public Planet getPlanet() {
        return this.planet;
    }

    public void getanswer(ShuttleButton shuttleButton) {
        if (shuttleButton.shuttle.getName().equals("submit1")) {
            shuttleButton.getplanetInfo(this);
        }
    }


}

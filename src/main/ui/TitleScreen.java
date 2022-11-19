package ui;

import model.Planet;
import model.PlanetList;
import model.Shuttle;
import model.ShuttleList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TitleScreen extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;

    protected int loadFile;

    private boolean firstTime;

    protected PlanetList planetList;
    protected ShuttleList shuttleList;
    private PlanetButton activePlanet;
    private ShuttleButton activeShuttle;
    private boolean shuttleSubmitted;
    private boolean planetSubmitted;
    protected Planet chosenPlanet;
    protected Shuttle chosenShuttle;
    private JPanel panel;
    private JPanel panel2;
    private static final String JSON_PLANET_LOC = "./data/planetList.json";
    private static final String JSON_SHUTTLE_LOC = "./data/shuttleList.json";

    private JsonWriter jsonWriterPlanet;
    private JsonWriter jsonWriterShuttle;
    private JsonReader jsonReaderShuttle;
    private JsonReader jsonReaderPlanet;

    protected Image image;
    private boolean newShuttle;
    private boolean newPlanet;

    public TitleScreen() {
        super("TitleScreen");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        firstTime = true;
        setupTitleScreen();


    }

    private void setupTitleScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUndecorated(false);
        image = new ImageIcon(getClass().getResource("wallpaper.jpg")).getImage();

        createPanel1();
        createPanel2();
//        addKeyListener(new KeyHandler());
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
        if (firstTime) {
            JPanel popUp = new JPanel();
            popUp.add(new JLabel("would you like to load your saved file?"));
            loadFile = JOptionPane.showConfirmDialog(null, popUp);
            setupJson(loadFile);
        }
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setSize(new Dimension(30, 30));
        add(panel, BorderLayout.NORTH);
        panel.add(new JLabel("please select a planet, or create a new one!"));
        for (Planet p : planetList.getList()) {
            System.out.println(p.getName());
            PlanetButton planetButton = new PlanetButton(this, panel, p);
        }
        PlanetButton nextButton = new PlanetButton(this, panel, new Planet("new",0,true,true,0));
        panel.add(new JLabel("click this button once you are done choosing."));
        PlanetButton submit = new PlanetButton(this, panel, new Planet("submit",
                2, false, false, 0));
        PlanetButton delete = new PlanetButton(this, panel, new Planet("delete",
                2, false, false, 0));
        firstTime = false;

    }

    public void resetPanel(JPanel panel) {
        panel.removeAll();
        panel.updateUI();
        createPanel1();
    }

    public void resetPanel2(JPanel panel) {
        panel.removeAll();
        panel.updateUI();
        createPanel2();
    }

    private void createPanel2() {
        panel2 = new JPanel();
        panel2.add(new JLabel("please select a shuttle, or create a new one!"));
        panel2.setLayout(new GridLayout(0,1));
        panel2.setSize(new Dimension(30,30));
        add(panel2,BorderLayout.SOUTH);
        for (Shuttle s : shuttleList.getList()) {
            ShuttleButton shuttleButton = new ShuttleButton(this, panel2, s);
        }
        ShuttleButton nextButton2 = new ShuttleButton(this,panel2, new Shuttle("new", 0,0,0,0));
        panel2.add(new JLabel("click this button once you are done choosing."));
        ShuttleButton submit1 = new ShuttleButton(this,panel2,new Shuttle("submit",
                0,0,0,0));
        ShuttleButton delete = new ShuttleButton(this, panel2, new Shuttle("delete",
                2, 0, 0, 0));
    }

    private PlanetList makePlanetList() {
        planetList = new PlanetList("List1");
        Planet a = new Planet("Generic1", (float) 3, true, true, 2);
        Planet b = new Planet("Generic2", (float)3.2, true, false, 2);
        Planet c = new Planet("Generic3", (float)3.1, false, true, 2);

        planetList.add(a);
        planetList.add(b);
        planetList.add(c);
        return planetList;
    }

    private ShuttleList makeShuttleList() {
        Shuttle a = new Shuttle("SaturnI",3,4,4,4);
        Shuttle b = new Shuttle("SaturnII",5,4,4,3);
        Shuttle c = new Shuttle("SaturnIII",4,7,6,4);
        shuttleList = new ShuttleList("sList1");
        shuttleList.add(a);
        shuttleList.add(b);
        shuttleList.add(c);
        return shuttleList;
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void setActivePlanet(PlanetButton planetButton) {
        if (planetButton.planet.getName().equals("delete")) {
            removePlanetButton();
        }
        if (planetButton.planet.getName().equals("submit")) {
            if (activePlanet.planet.getName().equals("new")) {
                OwnPlanetSurvey planetSurvey = new OwnPlanetSurvey(this);
//                planetButton.removeButton();

            } else {
                chosenPlanet = activePlanet.planet;
                planetButton.removeButton();
                planetSubmitted = true;
                System.out.println(planetSubmitted + "1 " + shuttleSubmitted);
                if (planetSubmitted && shuttleSubmitted) {
                    nextStep();
                }
            }


        }
        if (activePlanet != null) {
            activePlanet.deactivate();
        }
        if (planetButton.planet == null) {
            newPlanet = true;
        } else {
            newPlanet = false;
            planetButton.activate();
            activePlanet = planetButton;
            System.out.println(activePlanet.planet.getName());
        }
    }

    public void setActivePlanet(OwnPlanetSurvey planetSurvey) throws InterruptedException {
        Planet planetToAdd = planetSurvey.getPlanet();
        resetPanel(panel);
        Thread.sleep(1000);
        System.out.println(planetSubmitted + " 2 " + shuttleSubmitted);

        if (planetSubmitted && shuttleSubmitted) {
            nextStep();
        }
    }

    private void removePlanetButton() {
        activePlanet.removeButton();
        planetList.getList().remove(activePlanet.planet);
        panel.updateUI();
    }

    private void removeShuttleButton() {
        activeShuttle.removeButton();
        shuttleList.getList().remove(activeShuttle.shuttle);
        panel2.updateUI();
    }



    private void nextStep() {
        System.out.println(planetSubmitted + "5 " + shuttleSubmitted);

        setVisible(false);
        dispose();
        JPanel popUp1 = new JPanel();
        popUp1.add(new JLabel("would you like to save this file?"));
        loadFile = JOptionPane.showConfirmDialog(null, popUp1);
        if (loadFile == 0) {
            saveGame();
        }
        SplashScreen splashScreen = new SplashScreen(this);
//        OrbitApp app = new OrbitApp(this);
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void setActiveShuttle(ShuttleButton shuttleButton) {
        if (shuttleButton.shuttle.getName().equals("delete")) {
            removeShuttleButton();
        }
        if (shuttleButton.shuttle.getName().equals("submit")) {
            if (activeShuttle.shuttle.getName().equals("new")) {
                OwnShuttleSurvey shuttleSurvey = new OwnShuttleSurvey(this);
//                shuttleButton.removeButton();
            } else {
                chosenShuttle = activeShuttle.shuttle;
                shuttleButton.removeButton();
                shuttleSubmitted = true;
                System.out.println(planetSubmitted + " 3 " + shuttleSubmitted);

                if (planetSubmitted && shuttleSubmitted) {
                    nextStep();
                }
            }

        }
        if (activeShuttle != null) {
            activeShuttle.deactivate();
        }
        if (shuttleButton.shuttle == null) {
            newShuttle = true;
        } else {
            newShuttle = false;
            shuttleButton.activate();
            activeShuttle = shuttleButton;
            System.out.println(activeShuttle.shuttle.getName());
        }
    }

    public void setActiveShuttle(OwnShuttleSurvey shuttleSurvey) throws InterruptedException {
        Shuttle shuttleToAdd = shuttleSurvey.getShuttle();
        resetPanel2(panel2);
        Thread.sleep(1000);
        System.out.println(planetSubmitted + "4 " + shuttleSubmitted);

        if (planetSubmitted && shuttleSubmitted) {
            nextStep();
        }
    }

    private void setupJson(int loadFile) {
        jsonWriterPlanet = new JsonWriter(JSON_PLANET_LOC);
        jsonWriterShuttle = new JsonWriter(JSON_SHUTTLE_LOC);
        jsonReaderPlanet = new JsonReader(JSON_PLANET_LOC);
        jsonReaderShuttle = new JsonReader(JSON_SHUTTLE_LOC);
        shuttleList = makeShuttleList();
        planetList = makePlanetList();
        if (loadFile == 0) {
            loadPlanetList();
            loadShuttleList();
        }
    }

    private void saveGame() {
        try {
            jsonWriterPlanet.open();
            jsonWriterPlanet.write(planetList);
            jsonWriterPlanet.close();
            jsonWriterShuttle.open();
            jsonWriterShuttle.write(shuttleList);
            jsonWriterShuttle.close();
            System.out.println("saved: " + planetList.getListName() + " to " + JSON_PLANET_LOC);
            System.out.println(shuttleList.getListName() + " to " + JSON_SHUTTLE_LOC);
        } catch (FileNotFoundException e) {
            System.out.println("couldn't find file" + JSON_PLANET_LOC);
        }
    }

    private void loadPlanetList() {
        try {
            planetList = jsonReaderPlanet.readPlanets();
            System.out.println("loaded" + planetList.getListName() + "From: " + JSON_PLANET_LOC);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //EFFECT: loads shuttles
    //code based on JsonSerializationDemo
    private void loadShuttleList() {
        try {
            shuttleList = jsonReaderShuttle.readShuttles();
            System.out.println("loaded" + shuttleList.getListName() + "From: " + JSON_SHUTTLE_LOC);

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


}

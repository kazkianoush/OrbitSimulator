package ui;


import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import model.*;
import model.Event;
import persistence.JsonWriter;
import persistence.JsonReader;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.util.ArrayList;
import java.util.logging.Handler;

public class OrbitApp extends JFrame implements SetupInterface {

    private GUI gui;

    private DrawShuttle drawShuttle;
    private DrawPlanet drawPlanet;
    private PlanetButton drawButton;
    private PlanetButton activePlanet;
    private Scanner input;
    private PlanetList planetList;
    private ShuttleList shuttleList;

    private static final String JSON_PLANET_LOC = "./data/planetList.json";
    private static final String JSON_SHUTTLE_LOC = "./data/shuttleList.json";

    private JsonWriter jsonWriterPlanet;
    private JsonWriter jsonWriterShuttle;
    private JsonReader jsonReaderShuttle;
    private JsonReader jsonReaderPlanet;

//    private TitleScreen titleScreen;

    //EFFECTS: initiates Json readers and writers, asks whether you want to load previous file or not
    //         then proceeds with the game
    public OrbitApp(TitleScreen titleScreen) {
        super("orbitApp");
//        this.titleScreen = titleScreen;
//        setupJson();

        Shuttle s = titleScreen.chosenShuttle;
        Planet p = titleScreen.chosenPlanet;
        try {
            initGame(p, s);
        } catch (InterruptedException e) {
            System.out.print("e");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        makeGui(s,p);

    }


    //EFFECTS: makes gui
    private void makeGui(Shuttle s, Planet p) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.getDescription());
                }
            }
        });
        setUndecorated(false);
        gui = new GUI(s,p);
        drawShuttle = new DrawShuttle(gui);
        add(drawShuttle);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
    }


    //EFFECTS: adds timer
    private void addTimer() {
        Timer t = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.update();
                drawShuttle.repaint();
            }
        });
        t.start();
    }

    //EFFECTS: sets up json
    private void setupJson() {
        jsonWriterPlanet = new JsonWriter(JSON_PLANET_LOC);
        jsonWriterShuttle = new JsonWriter(JSON_SHUTTLE_LOC);
        jsonReaderPlanet = new JsonReader(JSON_PLANET_LOC);
        jsonReaderShuttle = new JsonReader(JSON_SHUTTLE_LOC);
        input = new Scanner(System.in);
        shuttleList = makeShuttleList();
        planetList = makePlanetList();
        System.out.println("would you like to load your previous file?(yes/no)");
        String answer = input.next();
        if (answer.equals("yes")) {
            loadPlanetList();
            loadShuttleList();
        }
    }


    //EFFECTS: gives you a prompt to either choose a shuttle or make a new one
    private Shuttle chooseShuttle() {
        String chooseShuttle;
        Shuttle ns = null;
        System.out.println("please choose one of the following shuttles or make your own(new).");
        for (Shuttle s : shuttleList.getList()) {
            System.out.println(s.getName());
        }
        chooseShuttle = input.next();

        for (Shuttle shuttle : shuttleList.getList()) {
            if (chooseShuttle.equals("new")) {
                System.out.println("great choice!");
                ns = initShuttle();
                shuttleList.add(ns);
                break;
            }
            if (shuttle.getName().equals(chooseShuttle)) {
                System.out.println("great!, you have chosen shuttle: " + shuttle.getName());
                return shuttle;
            }
        }
        return ns;
    }


    //EFFECT:  makes generic planet list
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

    //EFFECTS:  makes generic shuttle list
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
    //EFFECTS: returns the planet chosen by the user, which is either
    //         a generic planet, or a custom one they made themselves

    public Planet choosePlanet() {
        String chosenPlanet;
        Planet p = null;
        System.out.println("please choose one of the following planets or make your own(new).");
        for (Planet planet : planetList.getList()) {
            System.out.println(planet.getName());
        }
        chosenPlanet = input.next();

        for (Planet planet : planetList.getList()) {
            if (chosenPlanet.equals("new")) {
                System.out.println("great choice!");
                p = initPlanet();
                planetList.add(p);
                break;
            }
            if (planet.getName().equals(chosenPlanet)) {
                System.out.println("great!, you have chosen planet: " + planet.getName());
                return planet;
            }
        }
        return p;
    }

    //EFFECT: returns an ArrayList of the gravity, and tree values given by the user for their custom planet

    public ArrayList<Object> getSetupPlanet() {
        ArrayList<Object> values = new ArrayList<>();
        boolean keepGoing = true;
        float gravity;
        boolean trees;
        String tracker;
        while (keepGoing) {
            System.out.println("what would you like the gravity of your planet to be?");
            gravity = input.nextFloat();
            if (gravity >= 0) {
                keepGoing = false;
                values.add(gravity);
            } else {
                System.out.println("please enter a gravity value greater than 0");
                continue;
            }
            System.out.println("would you like your planet to have trees? (y/n)");
            tracker = input.next();
            System.out.println(tracker.getClass().getSimpleName());
            trees = tracker.equals("y");
            System.out.println(trees);
            values.add(trees);
        }
        return values;

    }

    //EFFECT: returns the planet that the user created
    public Planet initPlanet() {
        Planet p = new Planet();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        p.setName(setPlanetName());
        ArrayList<Object> values = getSetupPlanet();
        p.setGravity((float)(values.get(0)));
        p.setTrees((boolean)values.get(1));
        p.setRadius(getRadius());
        System.out.println(p.getGravity());
        System.out.println(p.getTrees());
        return p;

    }

    private int getRadius() {
        int radius;
        System.out.println("what would you like the radius of your planet to be?");
        radius = input.nextInt();
        return radius;
    }


    //EFFECT: returns the name that the user wants their planet to have
    public String setPlanetName() {
        String name;
        System.out.println("what would you like the name of your planet to be?: ");
        name = input.next();
        return name;
    }

    //EFFECT: returns an arrayList of what the user wants the initial velocities to be + the name of the shuttle
    public ArrayList<Object> getSetupShuttle() {
        ArrayList<Object> values = new ArrayList<>();

        System.out.println("what would you like the name of your shuttle to be?");
        String name = input.next();
        System.out.println("what would you like the initial x acceleration to be?");
        int velocityX = input.nextInt();
        System.out.println("what would you like the initial y acceleration to be?");
        int velocityY = input.nextInt();
        values.add(velocityX);
        values.add(velocityY);
        values.add(name);

        return values;
    }

    //EFFECT: returns shuttle which is made by the user
    public Shuttle initShuttle() {
        Shuttle s = new Shuttle("base");
        input = new Scanner(System.in);
        ArrayList<Object> values = getSetupShuttle();
        s.setAccelX((int)values.get(0));
        s.setAccelY((int)values.get(1));
        s.setName((String)values.get(2));
        System.out.println((int)values.get(0));
        System.out.println((int)values.get(1));
        System.out.println((String)values.get(2));
        return s;
    }

    //EFFECT: makes game and runs shuttle simulation, asks whether you want to save shuttle or not.
    public void initGame(Planet p, Shuttle s) throws InterruptedException, FileNotFoundException {
        s.setAccelY((int)p.getGravity() * -1);
        int x = 0;
        int y = 200;
        s.setCor(x,y);
//        for (int i = 0; i < 500; i++) {
//            Thread.sleep(1000);
//            s.setCor(x,s.getAccelY() + s.getCor()[1]);
//            if (s.getCor()[1] <= p.getRadius()) {
//                System.out.println("we hit the ground");
//                s.setAccelY(s.getAccelY() * -1);
//            }
//
//            s.setCor(x,s.getCor()[1] + s.getAccelY());
//            s.setAccelY(s.getAccelY() + (int)p.getGravity() * -1);
//            System.out.println("xcor: " + s.getCor()[0] + "  ycor: " + s.getCor()[1]);
//
//        }
//        System.out.println("would you like to save your planet and shuttle? ");
//        String answer = input.next();
//        if (answer.equals("yes")) {
//            saveGame();
//        }
    }

    public void printPlanetList() {
        List<Planet> planets = planetList.getList();

        for (Planet p : planets) {
            System.out.println(p);
        }
    }


    //EFFECT:  saves game
    //code based on JsonSerializationDemo
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

    //EFFECT: loads planets

    //code based on JsonSerializationDemo
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

    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            gui.keyPressed(e.getKeyCode());
        }
    }

    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    public void setActivePlanet(PlanetButton planet) {
        if (activePlanet != null) {
            activePlanet.deactivate();
        }
        planet.activate();
        activePlanet = planet;
    }




}

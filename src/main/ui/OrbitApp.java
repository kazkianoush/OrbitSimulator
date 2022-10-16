package ui;


import java.util.List;
import java.util.Scanner;
import model.*;
import java.util.ArrayList;

public class OrbitApp implements SetupInterface {
    private Scanner input;
    private List<Planet> planetList;

    public OrbitApp() {
        Shuttle s = initShuttle();

        planetList = new ArrayList<>();
        Planet a = new Planet("Generic1", 3, true, true, 2);
        Planet b = new Planet("Generic2", 3, true, false, 2);
        Planet c = new Planet("Generic3", 3, false, true, 2);
        Planet d = new Planet("Generic4", 3, false, false, 2);

        planetList.add(a);
        planetList.add(b);
        planetList.add(c);
        planetList.add(d);

        Planet p = choosePlanet();

        System.out.println(planetList.get(planetList.size() - 1).getName());



        initGame(p,s);
    }

    public Planet choosePlanet() {
        String chosenPlanet;
        Planet p = null;
        System.out.println("please choose one of the following planets or make your own.");
        for (Planet planet : planetList) {
            System.out.println(planet.getName());
        }
        chosenPlanet = input.next();

        for (Planet planet : planetList) {
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

    public Planet initPlanet() {
        Planet p = new Planet();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        p.setName(setPlanetName());
        ArrayList<Object> values = getSetupPlanet();
        p.setGravity((float)(values.get(0)));
        p.setTrees((boolean)values.get(1));
        System.out.println(p.getGravity());
        System.out.println(p.getTrees());
        return p;

    }

    public String setPlanetName() {
        String name;
        System.out.println("what would you like the name of your planet to be?: ");
        name = input.next();
        return name;
    }

    public ArrayList<Object> getSetupShuttle() {
        ArrayList<Object> values = new ArrayList<>();

//        while (true) {
        System.out.println("what would you like the initial x velocity to be?");
        int velocityX = input.nextInt();
        System.out.println("what would you like the initial y velocity to be?");
        int velocityY = input.nextInt();
        values.add(velocityX);
        values.add(velocityY);
//          break;
//        }

        return values;
    }

    public Shuttle initShuttle() {
        Shuttle s = new Shuttle();
        input = new Scanner(System.in);
        ArrayList<Object> values = getSetupShuttle();
        s.setAccelX((int)values.get(0));
        s.setAccelY((int)values.get(1));
        System.out.println((int)values.get(0));
        System.out.println((int)values.get(1));
        return s;
    }

    public void initGame(Planet p, Shuttle s) {
        s.setAccelY((int)p.getGravity() * -1);
        int x = 0;
        int y = 200;
        System.out.println("when you are ready, type a number!");
        input.nextInt();
        s.setCor(x,y);

        for (int i = 0; i < 500; i++) {
            s.setCor(x,s.getAccelY() + s.getCor()[1]);
            if (s.getCor()[1] <= p.getRadius()) {
                System.out.println("we hit the ground");
                s.setAccelY(s.getAccelY() * -1);
            }

            s.setCor(x,s.getCor()[1] + s.getAccelY());
            s.setAccelY(s.getAccelY() + (int)p.getGravity() * -1);
            System.out.println("xcor: " + s.getCor()[0] + "  ycor: " + s.getCor()[1]);

        }
    }



}

package ui;

import model.Planet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class SplashScreen extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;

    private JFrame frame;
    private ImageIcon imageIcon;
    private Image image;
    private JLabel label;
    private JLabel text;

    public SplashScreen(TitleScreen titleScreen) {
//        imageIcon = new ImageIcon();


        frame = new JFrame("screen");
        frame.setSize(HEIGHT, WIDTH);
        image  = titleScreen.image;
        imageIcon = new ImageIcon(image);
        label = new JLabel(imageIcon);
        label.setSize(HEIGHT, WIDTH);
        text = new JLabel("loading planet: " + titleScreen.chosenPlanet.getName() + ", and shuttle: "
                + titleScreen.chosenShuttle.getName());
        text.setBounds(50,50,600,100);
        label.add(text);
        frame.add(label);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        int delay = 2000; // Delay in milliseconds

        Timer timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrbitApp app = new OrbitApp(titleScreen);
                frame.setVisible(false); // Hide the first frame
            }
        });
        timer.start();

    }
}

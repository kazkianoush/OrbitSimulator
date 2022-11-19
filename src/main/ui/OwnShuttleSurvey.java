package ui;

import model.Planet;
import model.Shuttle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OwnShuttleSurvey extends JFrame {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;

    protected String shuttleName;
    protected int xcor1;
    protected int ycor1;
    protected int accelX;
    protected int accelY;

    protected Shuttle shuttle;
    protected ArrayList<JTextField> fields;

    private TitleScreen titleScreen;

    public OwnShuttleSurvey(TitleScreen titleScreen) {
        super("Make your own shuttle");
        this.titleScreen = titleScreen;
        fields = new ArrayList<>();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setupShuttleScreen();
    }

    private void setupShuttleScreen() {
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

    private void createPanel1() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("<html><p>please enter a name for "
                + "your shuttle: </p></html>", SwingConstants.CENTER));
        JTextField name = new JTextField(15);
        panel.add(name);
        fields.add(name);

        panel.add(new JLabel("please enter the x coordinate of your shuttle: "));
        JTextField xcor = new JTextField(15);
        fields.add(xcor);

        panel.add(xcor);
        panel.add(new JLabel("please enter the y coordinate of your shuttle: "));
        JTextField ycor = new JTextField(20);
        fields.add(ycor);

        panel.add(ycor);
        panel.add(new JLabel("pleas enter the x acceleration of your shuttle: "));
        part2(panel);

    }

    private void part2(JPanel panel) {
        JTextField accelX = new JTextField(15);
        fields.add(accelX);

        panel.add(accelX);
        panel.add(new JLabel("please enter the y acceleration of your shuttle: "));
        JTextField accelY = new JTextField(15);
        fields.add(accelY);

        panel.add(accelY);

        panel.add(new JLabel("click this button once you are done choosing."));
//        ShuttleButton submit1 = new ShuttleButton(new TitleScreen(),panel, new Shuttle("submit1",0,0,0,0),this);
        JButton submit = new JButton("submit");
        submit.setBorderPainted(true);
        submit.setFocusPainted(true);
        submit.setContentAreaFilled(true);
        panel.add(submit);
        submit.addActionListener(new OwnShuttleSurvey.ShuttleSurveyListener());
        add(panel);
    }

    private class ShuttleSurveyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            shuttleName = fields.get(0).getText();
            xcor1 = Integer.parseInt(fields.get(1).getText());
            ycor1 = Integer.parseInt(fields.get(2).getText());
            accelX = Integer.parseInt(fields.get(3).getText());
            accelY = Integer.parseInt(fields.get(4).getText());
            makeShuttle();
        }
    }

    private void makeShuttle() {
        setVisible(false);
        this.dispose();
        shuttle = new Shuttle(shuttleName,xcor1,ycor1,accelX,accelY);
        titleScreen.shuttleList.add(shuttle);

        try {
            titleScreen.setActiveShuttle(this);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

    }

    public Shuttle getShuttle() {
        return this.shuttle;
    }

}

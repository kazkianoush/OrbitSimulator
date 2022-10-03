package ui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.TextArea;

public class Origin extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Example Frame");


//        frame.add(textArea, BorderLayout.NORTH);
//
//        frame.add(button, BorderLayout.SOUTH);

// Show the frame

        int width = 1800;

        int height = 1200;

        frame.setSize(width, height);

        frame.setVisible(true);


    }
}

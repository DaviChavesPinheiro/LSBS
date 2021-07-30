package src.frontend;

import java.nio.file.Path;
import javax.swing.*;
import java.awt.*;

public class Main {
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("LSBS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Change the icon image
        Path cwd = Path.of("src/images/icon.png").toAbsolutePath();
        ImageIcon img = new ImageIcon(cwd.toString());
        frame.setIconImage(img.getImage());

        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(29, 29, 29));
        menuBar.setBorderPainted(false);
        menuBar.setPreferredSize(new Dimension(1080, 20));
        frame.setJMenuBar(menuBar);

        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null); // Center
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

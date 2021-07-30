package src.frontend;

import java.nio.file.Path;
import javax.swing.*;

import src.frontend.components.Content;
import src.frontend.components.SideBar;

import java.awt.*;

public class Main {
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("LSBS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Change the icon image
        Path imgAbsPath = Path.of("src/images/icon.png").toAbsolutePath();
        ImageIcon img = new ImageIcon(imgAbsPath.toString());
        frame.setIconImage(img.getImage());

        // Create Main Panel
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(new SideBar(), BorderLayout.LINE_START);
        mPanel.add(new Content(), BorderLayout.CENTER);
        
        // Add Panel
        frame.setContentPane(mPanel);

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

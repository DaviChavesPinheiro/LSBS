package src;

import javax.swing.JFrame;
import src.view.ContentView;
import src.view.SideBarView;
import java.nio.file.Path;
import javax.swing.*;
import java.awt.*;

public class Main {
    private static void createAndShowGUI() {
        JFrame app = new JFrame("LSBS");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Change the icon image
        Path imgAbsPath = Path.of("src/images/icon.png").toAbsolutePath();
        ImageIcon img = new ImageIcon(imgAbsPath.toString());
        app.setIconImage(img.getImage());

        // Create Main Panel
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(new SideBarView(), BorderLayout.LINE_START);
        mPanel.add(new ContentView(), BorderLayout.CENTER);
        
        // Add Panel
        app.setContentPane(mPanel);

        //Display the window.
        app.pack();
        app.setResizable(false);
        app.setLocationRelativeTo(null); // Center
        app.setVisible(true);
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

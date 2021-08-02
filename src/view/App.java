package src.view;

import java.nio.file.Path;

import javax.swing.*;

import java.awt.*;

public class App extends JFrame {
    private static App instance = null;
    public static App getInstance() {
        if(instance == null) {
            instance = new App();
        }
        return instance;
    }
    
    public App() {
        super("LSBS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Change the icon image
        Path imgAbsPath = Path.of("src/images/icon.png").toAbsolutePath();
        ImageIcon img = new ImageIcon(imgAbsPath.toString());
        this.setIconImage(img.getImage());

        // Create Main Panel
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(new SideBar_V(), BorderLayout.LINE_START);
        mPanel.add(new Content_V(), BorderLayout.CENTER);
        
        // Add Panel
        this.setContentPane(mPanel);

        //Display the window.
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null); // Center
        this.setVisible(true);
    }
    
}

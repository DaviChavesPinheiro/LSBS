package src.view;

import java.nio.file.Path;

import javax.swing.*;

import java.awt.*;

public class AppView extends JFrame {
    private static AppView instance = null;
    public static AppView getInstance() {
        if(instance == null) {
            instance = new AppView();
        }
        return instance;
    }
    
    public AppView() {
        super("LSBS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Change the icon image
        Path imgAbsPath = Path.of("src/images/icon.png").toAbsolutePath();
        ImageIcon img = new ImageIcon(imgAbsPath.toString());
        this.setIconImage(img.getImage());

        // Create Main Panel
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(new SideBarView(), BorderLayout.LINE_START);
        mPanel.add(new ContentView(), BorderLayout.CENTER);
        
        // Add Panel
        this.setContentPane(mPanel);

        //Display the window.
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null); // Center
        this.setVisible(true);
    }
    
}

package src.view.Content.Files;
import javax.swing.*;

import java.awt.*;
import java.io.File;

public class FilesAreaView extends JPanel {
    private static FilesAreaView instance = null;
    public static FilesAreaView getInstance() {
        if(instance == null) {
            instance = new FilesAreaView();
        }
        return instance;
    }
    
    public FilesAreaView() {
        this.setOpaque(false);
        this.setMaximumSize(new Dimension(1080 / 4 * 3 - 50, 720 - (360 + 35 + 35 + 20)));

        this.setLayout(new FlowLayout());
        this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.add(new ExtractView());
        this.add(new AddFileView());
        this.add(new SaveAllView());
    }

    public void addFile(File file) {
        this.add(new FileView(file));
        this.revalidate();
        this.repaint();
    }
}

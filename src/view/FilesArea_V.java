package src.view;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FilesArea_V extends JPanel {
    private static FilesArea_V instance = null;
    public static FilesArea_V getInstance() {
        if(instance == null) {
            instance = new FilesArea_V();
        }
        return instance;
    }
    
    public FilesArea_V() {
        this.setOpaque(false);
        this.setMaximumSize(new Dimension(1080 / 4 * 3 - 50, 720 - (360 + 35 + 35 + 20)));

        this.setLayout(new FlowLayout());
        this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.add(new Extract_V());
        this.add(new AddFile_V());
        this.add(new SaveAll_V());
    }

    public void addFile(File file) {
        this.add(new File_V(file));
        this.revalidate();
        this.repaint();
    }
}

package src.view;
import javax.swing.*;
import java.awt.*;

public class FilesArea_V extends JPanel {
    public FilesArea_V() {
        this.setOpaque(false);
        this.setMaximumSize(new Dimension(1080 / 4 * 3 - 50, 720 - (360 + 35 + 35 + 20)));

        this.setLayout(new FlowLayout());
        this.add(new AddFile());
    }
}

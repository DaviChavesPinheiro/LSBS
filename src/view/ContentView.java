package src.view;

import javax.swing.*;
import java.awt.*;

public class ContentView extends JPanel {
    public ContentView() {
        this.setOpaque(true);
        this.setBackground(new Color(18, 18, 18));
        this.setPreferredSize(new Dimension(1080 / 4 * 3, 720));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Margin
        this.add(Box.createRigidArea(new Dimension(0, 35)));

        // SourceArea
        this.add(new SourceAreaView());

        // Margin
        this.add(Box.createRigidArea(new Dimension(0, 35)));

        // Files Area
        this.add(FilesAreaView.getInstance());
    }
}

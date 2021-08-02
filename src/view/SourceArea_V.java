package src.view;
import javax.swing.*;
import java.awt.*;

public class SourceArea_V extends JPanel {
    public SourceArea_V() {
        this.setOpaque(true);
        this.setBackground(new Color(29, 29, 29));
        this.setBorder(BorderFactory.createLineBorder(new Color(56, 56, 56), 2));
        this.setMaximumSize(new Dimension(640, 360));
        this.setMinimumSize(new Dimension(640, 360));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // SourceHeader
        this.add(new SourceHeader_V());

        // SourceContent
        this.add(SourceContent_V.getInstance());
    }
}

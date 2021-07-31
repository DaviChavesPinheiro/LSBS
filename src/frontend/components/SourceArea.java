package src.frontend.components;
import javax.swing.*;
import java.awt.*;

public class SourceArea extends JPanel {
    public SourceArea() {
        this.setOpaque(true);
        this.setBackground(new Color(29, 29, 29));
        this.setBorder(BorderFactory.createLineBorder(new Color(56, 56, 56), 2));
        this.setMaximumSize(new Dimension(640, 360));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // SourceHeader
        this.add(new SourceHeader());

        // SourceContent
        this.add(new SourceContent());
    }
}

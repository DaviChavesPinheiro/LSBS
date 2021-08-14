package src.view;
import javax.swing.*;
import java.awt.*;

public class SourceAreaView extends JPanel {
    public SourceAreaView() {
        this.setOpaque(true);
        this.setBackground(new Color(29, 29, 29));
        this.setBorder(BorderFactory.createLineBorder(new Color(56, 56, 56), 2));
        this.setMaximumSize(new Dimension(640, 360));
        this.setMinimumSize(new Dimension(640, 360));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // SourceHeader
        this.add(new SourceHeaderView());

        // SourceContent
        this.add(SourceContentView.getInstance());
    }
}

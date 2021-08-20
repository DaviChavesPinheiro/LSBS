package src.view.Content.Source;
import javax.swing.*;

import java.awt.*;

public class SourceHeaderView extends JPanel {
    public SourceHeaderView() {
        this.setOpaque(true);
        this.setBackground(new Color(56, 56, 56));
        this.setMaximumSize(new Dimension(640, 15));
        this.setMinimumSize(new Dimension(640, 15));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.add(new DeleteButtonView());
        this.add(Box.createHorizontalGlue());
        this.add(new DownloadButtonView());
    }
}

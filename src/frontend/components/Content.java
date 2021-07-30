package src.frontend.components;

import javax.swing.*;
import java.awt.*;

public class Content extends JPanel {
    public Content() {
        this.setOpaque(true);
        this.setBackground(new Color(18, 18, 18));
        this.setPreferredSize(new Dimension(1080 / 4 * 3, 720));
    }
}

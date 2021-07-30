package src.frontend.components;

import javax.swing.*;
import java.awt.*;

public class SideBar extends JPanel  {
    public SideBar() {
        this.setOpaque(true);
        this.setBackground(new Color(29, 29, 29));
        this.setPreferredSize(new Dimension(1080 / 4 * 1, 720));

        this.add(new Donut());
    }
}

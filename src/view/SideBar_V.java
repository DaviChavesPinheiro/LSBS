package src.view;

import javax.swing.*;
import java.awt.*;

public class SideBar_V extends JPanel  {
    public SideBar_V() {
        this.setOpaque(true);
        this.setBackground(new Color(29, 29, 29));
        this.setPreferredSize(new Dimension(1080 / 4 * 1, 720));

        this.add(Donut_V.getInstance());
    }
}

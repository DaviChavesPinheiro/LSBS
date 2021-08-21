package src.view.SideBar;

import javax.swing.*;

import src.view.SideBar.Donut.DonutView;

import java.awt.*;

public class SideBarView extends JPanel  {
    public SideBarView() {
        this.setOpaque(true);
        this.setBackground(new Color(29, 29, 29));
        this.setPreferredSize(new Dimension(1080 / 4 * 1, 720));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Margin
        this.add(Box.createRigidArea(new Dimension(0, 35)));

        this.add(new DonutView());
    }
}

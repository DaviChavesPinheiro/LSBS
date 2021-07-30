package src.frontend.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SideBar extends JPanel implements ActionListener  {
    public SideBar() {
        this.setOpaque(true);
        this.setBackground(new Color(29, 29, 29));
        this.setPreferredSize(new Dimension(1080 / 4 * 1, 720));

        JButton button = new JButton("Click Me");
        button.setPreferredSize(new Dimension(200, 80));
        this.add(button);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        b.setVisible(false);
    }
}

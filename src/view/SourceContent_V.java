package src.view;
import javax.swing.*;

import src.controller.SourceContent_C;

import java.awt.*;

public class SourceContent_V extends JPanel {
    public SourceContent_V() {
        this.setOpaque(true);
        this.setBackground(new Color(29, 29, 29));

        SourceContent_C controller = new SourceContent_C(this);
        this.addMouseListener(controller);
    }
}

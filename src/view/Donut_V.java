package src.view;
import java.awt.Color;

import javax.swing.*;
public class Donut_V extends JLabel {
    private static Donut_V instance = null;
    public static Donut_V getInstance() {
        if(instance == null) {
            instance = new Donut_V();
        }
        return instance;
    }
    
    private long maxSpace = 0;
    private long usedSpace = 0;

    public Donut_V() {
        this.setSpaceUsed(0);
        this.setForeground(new Color(135, 135, 135));
    }

    // Espaco máximo
    public void setMaxSpace(long space) {
        this.maxSpace = space;
        refresh();
    }

    // Espaco usado
    public void setSpaceUsed(long value) {
        this.usedSpace = value;
        refresh();
    }

    public void refresh() {
        this.setText("" + Long.toString(maxSpace - usedSpace) + " KB available");
    }
}

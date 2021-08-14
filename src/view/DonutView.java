package src.view;
import java.awt.Color;

import javax.swing.*;
public class DonutView extends JLabel {
    private static DonutView instance = null;
    public static DonutView getInstance() {
        if(instance == null) {
            instance = new DonutView();
        }
        return instance;
    }
    
    private long maxSpace = 0;
    private long usedSpace = 0;

    public DonutView() {
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

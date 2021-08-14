package src.view.SideBar;
import java.awt.Color;

import javax.swing.*;

import src.model.EventListener;
import src.model.EventTypes;
import src.model.LSBStegnographyModel;
public class DonutView extends JLabel implements EventListener {
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
        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.LSB_ENCODE, this);
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

    @Override
    public void eventUpdate(Object model) {
        LSBStegnographyModel lsbStegnographyModel = (LSBStegnographyModel)model;
        setMaxSpace(lsbStegnographyModel.getMaxSpaceAvailable());
    }
}

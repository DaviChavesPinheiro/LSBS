package src.view.SideBar;
import java.awt.Color;

import javax.swing.*;

import src.model.EventListener;
import src.model.EventTypes;
import src.model.LSBStegnographyModel;
import src.model.TargetFileModel;
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
        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.LSB_ENCODED_SET, this);
        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.TF_ADD_FILE, this);
    }

    // Espaco máximo
    private void setMaxSpace(long space) {
        this.maxSpace = space;
        refresh();
    }

    // Espaco usado
    private void setSpaceUsed(long value) {
        this.usedSpace = value;
        refresh();
    }

    private void refresh() {
        this.setText("" + Long.toString(maxSpace - usedSpace) + " KB available");
    }

    @Override
    public void onEvent(EventTypes eventType, Object model) {
        switch (eventType) {
            case LSB_ENCODED_SET:
                LSBStegnographyModel lsbStegnographyModel = (LSBStegnographyModel)model;
                setMaxSpace(lsbStegnographyModel.getMaxSpaceAvailable());
                break;
            case TF_ADD_FILE:
                TargetFileModel targetFileModel = (TargetFileModel)model;
                setSpaceUsed(targetFileModel.getTargetFileSize());
                break;
        
            default:
                break;
        }

        
    }
}

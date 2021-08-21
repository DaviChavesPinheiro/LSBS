package src.view.SideBar.Donut;

import java.awt.Dimension;

import javax.swing.*;

import src.model.LSBStegnographyModel;
import src.model.TargetFileModel;
import src.utils.EventListener;
import src.utils.EventTypes;
public class DonutView extends JPanel implements EventListener {
    private DonutContentView text;
    private long maxSpace = 0;
    private long usedSpace = 0;

    public DonutView() {
        text = new DonutContentView();
        this.add(text);

        this.setOpaque(false);
        this.setPreferredSize(new Dimension(1080 / 4 * 1 - 40, 1080 / 4 * 1 - 40));
        this.setSpaceUsed(0);

        // Subscribe to model
        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.LSB_ENCODED_SET, this);
        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.TF_ADD_FILE, this);
        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.LSB_ENCODED_REMOVED, this);
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
        text.setValue(maxSpace - usedSpace);
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
            case LSB_ENCODED_REMOVED:
                setMaxSpace(0);
                setSpaceUsed(0);
                break;
        
            default:
                break;
        }

        
    }
}

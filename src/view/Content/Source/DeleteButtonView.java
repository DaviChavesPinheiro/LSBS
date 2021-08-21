package src.view.Content.Source;

import java.awt.Dimension;
import java.nio.file.Path;

import javax.swing.*;

import src.controller.DeleteController;
import src.model.LSBStegnographyModel;
import src.utils.EventListener;
import src.utils.EventTypes;
import src.utils.ImageEffects;

public class DeleteButtonView extends JButton implements EventListener {
    ImageIcon img;
    public DeleteButtonView() {
        super();
        this.setPreferredSize(new Dimension(15, 15));
        this.setMinimumSize(new Dimension(15, 15));
        this.setMaximumSize(new Dimension(15, 15));

        this.setOpaque(false);

        Path imgAbsPath = Path.of("src/images/delete-icon.png").toAbsolutePath();
        img = new ImageIcon(imgAbsPath.toString());
        this.setIcon(img);

        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        
        DeleteController controller = new DeleteController(LSBStegnographyModel.getInstance());
        this.addActionListener(controller);

        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.LSB_ENCODED_SET, this);
        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.LSB_ENCODED_REMOVED, this);
    }

    @Override
    public void onEvent(EventTypes eventType, Object model) {
        switch (eventType) {
            case LSB_ENCODED_SET:
                this.setIcon(ImageEffects.changeBrightness(img, 1.8f));
                break;
            case LSB_ENCODED_REMOVED:
                this.setIcon(ImageEffects.changeBrightness(img, 1f));
                break;
            default:
                break;
        }
    }
}

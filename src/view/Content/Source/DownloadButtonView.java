package src.view.Content.Source;

import java.awt.Dimension;
import java.nio.file.Path;

import javax.swing.*;

import src.controller.DownloadController;
import src.model.LSBStegnographyModel;
import src.utils.EventListener;
import src.utils.EventTypes;
import src.utils.ImageEffects;

public class DownloadButtonView extends JButton implements EventListener {
    ImageIcon img;
    public DownloadButtonView() {
        super();
        this.setPreferredSize(new Dimension(15, 15));
        this.setMinimumSize(new Dimension(15, 15));
        this.setMaximumSize(new Dimension(15, 15));

        this.setOpaque(false);

        Path imgAbsPath = Path.of("src/images/download-icon.png").toAbsolutePath();
        img = new ImageIcon(imgAbsPath.toString());
        this.setIcon(img);

        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        
        DownloadController controller = new DownloadController(LSBStegnographyModel.getInstance());
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

package src.view.Content.Source;
import javax.swing.*;

import src.controller.SourceContentController;
import src.model.EventListener;
import src.model.EventTypes;
import src.model.ImageStegnographyModel;
import src.model.LSBStegnographyModel;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;

public class SourceContentView extends JPanel implements EventListener {
    private ImageIcon pngImage;

    public SourceContentView() {
        this.setOpaque(true);
        this.setBackground(new Color(29, 29, 29));
        this.setLayout(new BorderLayout());
        
        Path imgAbsPath = Path.of("src/images/png-icon.png").toAbsolutePath();
        pngImage = new ImageIcon(imgAbsPath.toString());
        setImage(pngImage);
        
        SourceContentController controller = new SourceContentController(LSBStegnographyModel.getInstance(), this);
        this.addMouseListener(controller);
        // Subscribe to model
        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.LSB_ENCODED_SET, this);
        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.LSB_ENCODED_REMOVED, this);
    }

    @Override
    public void onEvent(EventTypes eventType, Object model) {
        switch (eventType) {
            case LSB_ENCODED_SET:
                ImageStegnographyModel imageStegnographyModel = (ImageStegnographyModel)model;
                File encodedFile = imageStegnographyModel.getEncoded();
                setImage(new ImageIcon((new ImageIcon(encodedFile.getAbsolutePath())).getImage().getScaledInstance(this.getSize().width, this.getSize().height, Image.SCALE_DEFAULT)));
                break;
            case LSB_ENCODED_REMOVED:
                removeImage();
                break;
            default:
                break;
        }
    }

    private void setImage(ImageIcon img) {
        this.removeAll();
        this.add(new JLabel(img), BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    private void removeImage() {
        this.removeAll();
        this.add(new JLabel(pngImage), BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}

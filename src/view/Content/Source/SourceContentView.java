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
    private static SourceContentView instance = null;
    public static SourceContentView getInstance() {
        if(instance == null) {
            instance = new SourceContentView();
        }
        return instance;
    }

    private JLabel pngImage;

    public SourceContentView() {
        this.setOpaque(true);
        this.setBackground(new Color(29, 29, 29));
        this.setLayout(new BorderLayout());
        
        Path imgAbsPath = Path.of("src/images/png-icon.png").toAbsolutePath();
        ImageIcon img = new ImageIcon(imgAbsPath.toString());
        this.pngImage = new JLabel(img);
        
        // TODO: Remover
        this.onEvent(null, null);
        
        SourceContentController controller = new SourceContentController(LSBStegnographyModel.getInstance(), this);
        this.addMouseListener(controller);
        // Subscribe to model
        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.LSB_ENCODE, this);
    }

    @Override
    public void onEvent(EventTypes eventType, Object model) {
        if(model == null) {
            resetImage();
            return;
        }
        ImageStegnographyModel imageStegnographyModel = (ImageStegnographyModel)model;
        File encodedFile = imageStegnographyModel.getEncoded();
        if(encodedFile != null) {
            this.removeAll();
            ImageIcon img = new ImageIcon((new ImageIcon(encodedFile.getAbsolutePath())).getImage().getScaledInstance(this.getSize().width, this.getSize().height, Image.SCALE_DEFAULT));
            this.add(new JLabel(img), BorderLayout.CENTER);
        } else {
            resetImage();
        }
    }

    private void resetImage() {
        this.removeAll();
        this.add(pngImage, BorderLayout.CENTER);
    }
}

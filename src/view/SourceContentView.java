package src.view;
import javax.swing.*;

import src.controller.SourceContentController;
import src.model.LSBStegnographyModel;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;

public class SourceContentView extends JPanel {
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

        this.refresh();

        SourceContentController controller = new SourceContentController(this);
        this.addMouseListener(controller);
    }

    public void refresh() {
        File encodedFile = LSBStegnographyModel.getInstance().getEncoded();
        if(encodedFile != null) {
            this.removeAll();
            ImageIcon img = new ImageIcon((new ImageIcon(encodedFile.getAbsolutePath())).getImage().getScaledInstance(this.getSize().width, this.getSize().height, Image.SCALE_DEFAULT));
            this.add(new JLabel(img), BorderLayout.CENTER);
        } else {
            this.removeAll();
            this.add(pngImage, BorderLayout.CENTER);
        }
    }
}

package src.view;
import javax.swing.*;

import src.controller.SourceContent_C;
import src.model.LSBStegnography_M;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;

public class SourceContent_V extends JPanel {
    private static SourceContent_V instance = null;
    public static SourceContent_V getInstance() {
        if(instance == null) {
            instance = new SourceContent_V();
        }
        return instance;
    }

    private JLabel pngImage;

    public SourceContent_V() {
        this.setOpaque(true);
        this.setBackground(new Color(29, 29, 29));
        this.setLayout(new BorderLayout());

        Path imgAbsPath = Path.of("src/images/png-icon.png").toAbsolutePath();
        ImageIcon img = new ImageIcon(imgAbsPath.toString());
        this.pngImage = new JLabel(img);

        this.refresh();

        SourceContent_C controller = new SourceContent_C(this);
        this.addMouseListener(controller);
    }

    public void refresh() {
        File source = LSBStegnography_M.getInstance().getSource();
        if(source != null) {
            this.removeAll();
            ImageIcon img = new ImageIcon((new ImageIcon(source.getAbsolutePath())).getImage().getScaledInstance(this.getSize().width, this.getSize().height, Image.SCALE_DEFAULT));
            this.add(new JLabel(img), BorderLayout.CENTER);
        } else {
            this.removeAll();
            this.add(pngImage, BorderLayout.CENTER);
        }
    }
}

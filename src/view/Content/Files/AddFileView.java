package src.view.Content.Files;

import java.awt.Color;
import java.awt.Dimension;
import java.nio.file.Path;

import javax.swing.*;
import javax.swing.border.*;

import src.controller.AddFileController;
import src.model.LSBStegnographyModel;
import src.utils.ImageEffects;

import java.awt.*;

public class AddFileView extends JButton {
    Icon img, imgFocused;
    public AddFileView() {
        super();
        this.setPreferredSize(new Dimension(80, 80));
        this.setMinimumSize(new Dimension(80, 80));
        this.setMaximumSize(new Dimension(80, 80));

        this.setOpaque(true);
        this.setBackground(new Color(29, 29, 29));
        this.setForeground(new Color(76, 76, 76));

        this.setVerticalTextPosition(AbstractButton.BOTTOM);
        this.setHorizontalTextPosition(AbstractButton.CENTER);

        this.setText("Add File");
        this.setIconTextGap(6);

        Path imgAbsPath = Path.of("src/images/add-icon.png").toAbsolutePath();
        img = new ImageIcon(imgAbsPath.toString());
        imgFocused = ImageEffects.changeBrightness(img, 1.5f);
        this.setIcon(img);

        Border line = new LineBorder(new Color(56, 56, 56));
        Border margin = new EmptyBorder(15, 0, 0, 0);
        Border compound = new CompoundBorder(line, margin);
        this.setBorder(compound);

        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        
        AddFileController controller = new AddFileController(LSBStegnographyModel.getInstance());
        this.addActionListener(controller);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor((new Color(41, 41, 41)));
            this.setForeground(new Color(125, 125, 125));
            this.setIcon(imgFocused);
        } else if (getModel().isRollover()) {
            g.setColor((new Color(41, 41, 41)));
            this.setForeground(new Color(125, 125, 125));
            this.setIcon(imgFocused);
        } else {
            g.setColor(getBackground());
            this.setForeground(new Color(76, 76, 76));
            this.setIcon(img);
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
